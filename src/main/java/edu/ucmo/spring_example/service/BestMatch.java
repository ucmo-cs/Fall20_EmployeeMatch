package edu.ucmo.spring_example.service;

import com.github.javafaker.Faker;
import edu.ucmo.spring_example.EmployeeMatchApplication;
import edu.ucmo.spring_example.model.*;

import java.io.IOException;
import java.sql.*;
import java.util.*;

public class BestMatch {
    private static DataFromApi employersData;
    private static DataFromApi employeePreferencesData;
    private static DataFromApi employerPreferencesData;
    private Employer[] employers;
    private Employee[] employees;
    private EmployeePreferences[] employeePreferences;
    private EmployerPreferences[] employerPreferences;
    private BestMatchEmployee[] bestMatchEmployees;
    private BestMatchEmployer[] bestMatchEmployers;
    public Hashtable matches;
    private static String url = "jdbc:mysql://www.math-cs.ucmo.edu:3306/F20Employee";

    public BestMatchEmployee[] getBestMatchEmployees() {
        return bestMatchEmployees;
    }

    public void setBestMatchEmployees(BestMatchEmployee[] bestMatchEmployees) {
        this.bestMatchEmployees = bestMatchEmployees;
    }

    public BestMatchEmployer[] getBestMatchEmployers() {
        return bestMatchEmployers;
    }

    public void setBestMatchEmployers(BestMatchEmployer[] bestMatchEmployers) {
        this.bestMatchEmployers = bestMatchEmployers;
    }

    public BestMatch() throws IOException, SQLException {
        employersData = new DataFromApi("http://localhost:8080/employer");
        employeePreferencesData = new DataFromApi("http://localhost:8080/employeepreferences");
        employerPreferencesData = new DataFromApi("http://localhost:8080/employerpreferences");
        employeePreferences = employeePreferencesData.getDataToEmployeePreferences();
        employerPreferences = employerPreferencesData.getDataToEmployerPreferences();
        employers = employersData.getDataToEmployer();
        initializeEmployees();
        matches = new Hashtable();
    }

    public void initializeMatch() throws SQLException, IOException {
        balanceEmployers();
        initializeMatch(employers, employees, employeePreferences, employerPreferences);
        postMatchesToDatabase(matches);
    }

    //two methods created here for testing purposes
    public void initializeMatch(Employer[] iEmployers, Employee[] iEmployees, EmployeePreferences[] iEmployeePreferences, EmployerPreferences[] iEmployerPreferences) {
        bestMatchEmployees = new BestMatchEmployee[iEmployees.length];
        bestMatchEmployers = new BestMatchEmployer[iEmployers.length];
        initiateBestMatchEmployee(iEmployers, iEmployees, iEmployeePreferences, iEmployerPreferences);
        initiateBestMatchEmployer(iEmployers, iEmployees, iEmployeePreferences, iEmployerPreferences);

        //Best Match Algorithm
        while (matches.size() < bestMatchEmployers.length) {
            //reset all rank queues for employers
            for (BestMatchEmployer i : bestMatchEmployers) {
                i.resetQueue();
            }

            for (BestMatchEmployer currentEmployer : bestMatchEmployers) {
                int currentProposal = 0;
                //every employer will extend a proposal from the front of their queue
                if (!currentEmployer.matched)
                    currentProposal = (int) currentEmployer.rankingsQueue.poll();
                //find employee with this id and extend a proposal
                for (int i = 0; i < bestMatchEmployees.length; i++) {
                    if (bestMatchEmployees[i].employeeId == currentProposal)
                        bestMatchEmployees[i].proposals.add(currentEmployer.companyId);
                }
            }
            //each employee accepts their highest ranked proposal
            for (BestMatchEmployee currentEmployee : bestMatchEmployees) {
                for (int i : currentEmployee.rankings) {
                    //loop through the rankings and break at the first proposal
                    if (currentEmployee.proposals.contains(i)) {
                        //if already matched, unmatch their pair
                        if (currentEmployee.matched) {
                            int companyToUnmatch = (int) matches.get(currentEmployee.employeeId);
                            for (BestMatchEmployer currentEmployer : bestMatchEmployers)
                                if (currentEmployer.companyId == companyToUnmatch)
                                    currentEmployer.matched = false;
                        }
                        //now match the employer to the employee
                        matches.put(currentEmployee.employeeId, i);
                        //mark the employee as having been matched
                        currentEmployee.matched = true;
                        //mark the newly matched employer as having been matched
                        for (BestMatchEmployer e : bestMatchEmployers)
                            if (e.companyId == i)
                                e.matched = true;
                        break;
                    }

                }
            }
        }

    }

    public void initializeEmployees() throws SQLException {
        Connection conn = DriverManager.getConnection(url,"F20Employee","F20Employee12#$");
        Statement stmt = conn.createStatement();
        stmt.executeUpdate("DELETE FROM matches");
        ResultSet employeeCount = stmt.executeQuery("SELECT COUNT(*) FROM employee");
        employeeCount.next();
        employees = new Employee[employeeCount.getInt("COUNT(*)")];

        int i =0;
        //while rs is not empty, convert each row into an employee object and append them to the tempEmployees
        for(ResultSet rs = stmt.executeQuery("SELECT * FROM employee"); rs.next();i++ )    {
            Employee currentEmployee = new Employee(rs.getInt("userid"), rs.getString("firstn"), rs.getString("lastn") ,rs.getString("email"), rs.getString("passhash"));
            employees[i] = currentEmployee;
        }
    }

    public void postMatchesToDatabase(Hashtable iMatches) throws SQLException {
        Connection conn = DriverManager.getConnection(url,"F20Employee","F20Employee12#$");
        Statement stmt = conn.createStatement();
        Object[] keySet = iMatches.keySet().toArray();
        for(Object currentKey: keySet)  {
            String query = "INSERT INTO matches VALUES (" +
                    currentKey+","+
                    matches.get(currentKey)
                    + ");";
            stmt.execute(query);
        }

    }



    private void initiateBestMatchEmployer(Employer[] iEmployers, Employee[] iEmployees, EmployeePreferences[] iEmployeePreferences, EmployerPreferences[] iEmployerPreferences) {
        int count = 0;
        for(Employer i: iEmployers) {
            HashMap correlation = new HashMap();
            int[] rankings = new int[iEmployees.length];

            for(Employee j : iEmployees) {
                //to find correlation: find correlation for each field then add them up and divide by total number of fields
                //to find correlation to field: min(eo, ew) / max(eo, ew)

                //code to linear search preferences and assigning jp - EmployerPreferences and ip - EmployeePreferences
                for(EmployeePreferences jp: iEmployeePreferences) {
                    if(jp.getUserid() == j.getUserid()) {
                        for(EmployerPreferences ip: iEmployerPreferences) {
                            if(ip.getCompanyid() == i.getCompanyid()) {
                                float[] traitCorrelation = {0, 0, 0, 0, 0};
                                traitCorrelation[0] = (float)ip.getEw1() / (float)jp.getEo1();
                                traitCorrelation[1] = (float)ip.getEw2() / (float)jp.getEo2();
                                traitCorrelation[2] = (float)ip.getEw3() / (float)jp.getEo3();
                                traitCorrelation[3] = (float)ip.getEw4() / (float)jp.getEo4();
                                traitCorrelation[4] = (float)ip.getEw5() / (float)jp.getEo5();

                                float sum = 0;
                                //sum up correlations
                                for(float q: traitCorrelation)
                                    sum += q;
                                correlation.put(j.getUserid(), sum/(float)traitCorrelation.length);
                                break;
                            }
                        }
                        break;
                    }
                }
            }

            int currentRank = 0;
            //put the max correlation at front of ranking queue one at a time until there are no more values left
            for(Iterator keySetIterator = correlation.keySet().iterator(); keySetIterator.hasNext(); keySetIterator = correlation.keySet().iterator()  ) {

                int key = (int) keySetIterator.next();
                float max = (float) correlation.get(key);
                int maxKey = key;
                while (keySetIterator.hasNext()) {
                    key = (int) keySetIterator.next();
                    if(max < (float)correlation.get(key))  {
                        max = (float)correlation.get(key);
                        maxKey = key;
                    }
                }
                rankings[currentRank++] = maxKey;
                correlation.remove(maxKey);
            }
            //create final bestMatchEmployee object
            bestMatchEmployers[count] = new BestMatchEmployer(i.getCompanyid(),rankings);
            count++;
        }
    }

    private void initiateBestMatchEmployee(Employer[] iEmployers, Employee[] iEmployees, EmployeePreferences[] iEmployeePreferences, EmployerPreferences[] iEmployerPreferences)  {
        int count = 0;
        for(Employee i: iEmployees) {
            HashMap correlation = new HashMap();
            int[] rankings = new int[iEmployers.length];

            for(Employer j : iEmployers) {
                //to find correlation: find correlation for each field then add them up and divide by total number of fields
                //to find correlation to field: min(eo, ew) / max(eo, ew)

                //code to linear search preferences and assigning jp - EmployerPreferences and ip - EmployeePreferences
                for(EmployerPreferences jp: iEmployerPreferences) {
                    if(jp.getCompanyid() == j.getCompanyid()) {
                        for(EmployeePreferences ip: iEmployeePreferences) {
                            if(ip.getUserid() == i.getUserid()) {
                                float[] traitCorrelation = {0, 0, 0, 0, 0};
                                traitCorrelation[0] = (float)ip.getEw1() / (float)jp.getEo1();
                                traitCorrelation[1] = (float)ip.getEw2() / (float)jp.getEo2();
                                traitCorrelation[2] = (float)ip.getEw3() / (float)jp.getEo3();
                                traitCorrelation[3] = (float)ip.getEw4() / (float)jp.getEo4();
                                traitCorrelation[4] = (float)ip.getEw5() / (float)jp.getEo5();

                                float sum = 0;
                                //sum up correlations
                                for(float q: traitCorrelation)
                                    sum += q;
                                correlation.put(j.getCompanyid(), sum/(float)traitCorrelation.length);
                                break;
                            }
                        }
                        break;
                    }
                }
            }

            int currentRank = 0;
            //put the max correlation at front of ranking queue one at a time until there are no more values left
            for(Iterator keySetIterator = correlation.keySet().iterator(); keySetIterator.hasNext(); keySetIterator = correlation.keySet().iterator()  ) {

                int key = (int) keySetIterator.next();
                //set max to the first element
                float max = (float) correlation.get(key);
                int maxKey = key;
                //if the current value is greater, set the max to the new value and set maxKey equal to the new max's key
                while (keySetIterator.hasNext()) {
                    key = (int)keySetIterator.next();
                    if(max < (float)correlation.get(key))  {
                        max = (float)correlation.get(key);
                        maxKey = key;
                    }
                }
                rankings[currentRank++] = (maxKey);
                correlation.remove(maxKey);
            }
            //create final bestMatchEmployee object
            bestMatchEmployees[count] = new BestMatchEmployee(i.getUserid(),rankings);
            count++;
        }
    }

    public static void balanceEmployers() throws IOException, SQLException {
        ResultSet rs = null;
        int numToAdd, numEmployees =0, numEmployers =0;
        try {

            Connection conn = DriverManager.getConnection(url,"F20Employee","F20Employee12#$");
            Statement stmt = conn.createStatement();
            rs = stmt.executeQuery("SELECT count(*) FROM employee");
            rs.next();
            numEmployees = rs.getInt("count(*)");

            conn.close();
        } catch (Exception e) {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        }
        numEmployers = employersData.getDataToEmployer().length;

        numToAdd = numEmployees - numEmployers;

        //create a new entry for each
        Random ran = new Random();

        String url = "jdbc:mysql://www.math-cs.ucmo.edu:3306/F20Employee";
        Connection conn = DriverManager.getConnection(url,"F20Employee","F20Employee12#$");
        Statement stmt = conn.createStatement();
        Faker faker = new Faker();
        for(int i = 0; i < numToAdd; i++)   {
            String fname = faker.name().firstName();
            String lname = faker.name().lastName();
            String companyName = faker.company().name();
            String email = (fname+"."+lname+"@"+companyName+".com").replaceAll(" |,|'", "");
            if(email.length() >= 50 )
                continue;
            int eo[] = new int[5];;
            int[] ew = new int[5];
            boolean[] usedRank = {false,false,false,false,false};
            int companyId;
            //post new employer to database
            String query = "INSERT INTO employer( companyName, email )" +
                    "VALUES (" +
                    "\""+companyName+"\", \""+email +
                    "\");";
            stmt.executeUpdate(query);

            //obtain company id for the company we just added
            query = "Select companyid FROM employer WHERE email = \""+email+"\";";
            rs = stmt.executeQuery(query);
            rs.next();
            companyId = rs.getInt("companyid");

            //assign eo and ew
            for(int z =1; z <= 5;z++)    {
                int currentRank = ran.nextInt(5);
                while(usedRank[currentRank])    {
                    currentRank = ran.nextInt(5);
                }
                eo[currentRank] = z;
                ew[currentRank] = z;
                usedRank[currentRank] = true;
            }

            //post employer preferences to database
            query ="INSERT INTO employerpreferences VALUES (" +
                    companyId+"," +
                    eo[0] + "," +
                    eo[1] + "," +
                    eo[2] + "," +
                    eo[3] + "," +
                    eo[4] + "," +
                    ew[0] + "," +
                    ew[1] + "," +
                    ew[2] + "," +
                    ew[3] + "," +
                    ew[4] + ");";
            //statement to stop on
            stmt.executeUpdate(query);
        }
        conn.close();
    }
}

