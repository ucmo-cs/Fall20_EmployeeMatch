package TestFiles;

import edu.ucmo.spring_example.model.Employee;
import edu.ucmo.spring_example.model.EmployeePreferences;
import edu.ucmo.spring_example.model.Employer;
import edu.ucmo.spring_example.model.EmployerPreferences;
import edu.ucmo.spring_example.service.BestMatch;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class BestMatchTest {

    Employee[] employees = {new Employee(1,"Adam", "Regusea", "adam.Regusea@gmail.com", "password" ),
            new Employee(2,"Bill", "Maher", "Bill.Maher@gmail.com", "pass" ),
            new Employee(3,"Bill", "Nye", "TheScienceGuy@gmail.com", "word" ),
    };

    Employer[] employers = {new Employer(1,"Amazon", "AmazonOfficial@Amazon.com" ),
            new Employer(2,"Google", "hr@gmail.com"),
            new Employer(3,"Netflix", "hr@netflix.com" ),
    };
    EmployeePreferences[] employeePreferences = {new EmployeePreferences(1,1,2,3,4,5,1,2,3,4,5),
            new EmployeePreferences(2,2,3,4,1,5,2,3,4,1,5),
            new EmployeePreferences(3,5,4,3,2,1,5,4,3,2,1)};
    EmployerPreferences[] employerPreferences = {new EmployerPreferences(1,1,2,3,4,5,1,2,3,4,5),
            new EmployerPreferences(2,2,3,4,1,5,2,3,4,1,5),
            new EmployerPreferences(3,5,4,3,2,1,5,4,3,2,1)};

    BestMatch bestMatch;

    BestMatchTest() throws IOException {
    }

    @BeforeEach
    void setUp() throws IOException {
        bestMatch = new BestMatch();
    }

    @Test
    void balanceEmployers() throws IOException {
        //bestMatch.balanceEmployers();
    }

    @Test
    void initializeMatches()    {
        bestMatch.initializeMatch(employers,employees,employeePreferences,employerPreferences);
        System.out.println("BestMatchEmployees: "+bestMatch.getBestMatchEmployees()[0].employeeId);
        System.out.println("BestMatchEmployers: "+bestMatch.getBestMatchEmployers()[0].companyId);
    }
}