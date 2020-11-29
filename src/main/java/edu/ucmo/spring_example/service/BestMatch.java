package edu.ucmo.spring_example.service;

import edu.ucmo.spring_example.EmployeeMatchApplication;
import edu.ucmo.spring_example.model.*;

import java.io.IOException;
import java.sql.*;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Queue;

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




    public BestMatch() throws IOException {
        employersData= new DataFromApi("http://localhost:8080/employer");
        employeePreferencesData = new DataFromApi("http://localhost:8080/employeepreferences");
        employerPreferencesData = new DataFromApi("http://localhost:8080/employerpreferences");;
        employers = employersData.getDataToEmployer();
    }

    public void initializeMatch()    {
        initializeMatch(employers, employees, employeePreferences, employerPreferences);
    }

    //two methods created here for testing purposes
    public void initializeMatch(Employer[] iEmployers, Employee[] iEmployees, EmployeePreferences[] iEmployeePreferences, EmployerPreferences[] iEmployerPreferences) {
        bestMatchEmployees = new BestMatchEmployee[iEmployees.length];
        bestMatchEmployers = new BestMatchEmployer[iEmployees.length];
        initiateBestMatchEmployee(iEmployers, iEmployees, iEmployeePreferences, iEmployerPreferences);
        initiateBestMatchEmployer(iEmployers, iEmployees, iEmployeePreferences, iEmployerPreferences);
        //TODO: left off here. Parsed employees and employers for processing. need to write bestMatch alorithm now

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
                rankings[currentRank] = maxKey;
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
                rankings[currentRank] = (maxKey);
                correlation.remove(maxKey);
            }
            //create final bestMatchEmployee object
            bestMatchEmployees[count] = new BestMatchEmployee(i.getUserid(),rankings);
            count++;
        }
    }

    //TODO: add enough employers to where there is an even amount of employees to employers
    public static void balanceEmployers() throws IOException, SQLException {
        ResultSet rs = null;
        int numToAdd, numEmployees, numEmployers =0;
        try {
            String url = "https://www.math-cs.ucmo.edu/phpmyadmin/";
            Connection conn = DriverManager.getConnection(url,"F20Employee","F20Employee12#$");
            Statement stmt = conn.createStatement();
            rs = stmt.executeQuery("SELECT COUNT(*) FROM employees ");
            conn.close();
        } catch (Exception e) {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        }
        numEmployers = employersData.getDataToEmployer().length;
        numEmployees = rs.getInt("count(*)");
        numToAdd = numEmployees - numEmployers;
        for(int i = 0; i < numToAdd; i++)   {
            //TODO: post data to server with random values
        }



        System.out.println(rs);
    }
}

