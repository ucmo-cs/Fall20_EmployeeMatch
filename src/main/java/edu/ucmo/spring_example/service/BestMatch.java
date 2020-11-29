package edu.ucmo.spring_example.service;

import java.io.IOException;

public class BestMatch {


    //TODO: add enough employers to where there is an even amount of employees to employers
    public static void balanceEmployers() throws IOException {
        DataFromApi employeesData = new DataFromApi("http://localhost:8080/employees");
        DataFromApi employersData = new DataFromApi("http://localhost:8080/employers");
        int numToBeAdded;
    }
}

