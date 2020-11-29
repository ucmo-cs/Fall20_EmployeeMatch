package edu.ucmo.spring_example.model;


import java.util.HashMap;

public class BestMatchEmployee {
    public int employeeId;
    public int[] rankings;
    public BestMatchEmployee(int id, int[] inputRankings) {
        employeeId = id;
        rankings = inputRankings;
    }

}
