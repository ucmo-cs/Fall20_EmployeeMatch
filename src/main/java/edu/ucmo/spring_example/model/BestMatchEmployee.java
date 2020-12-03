package edu.ucmo.spring_example.model;


import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class BestMatchEmployee {
    public int employeeId;
    public int[] rankings;
    public boolean matched;
    public List proposals = new LinkedList();
    public BestMatchEmployee(int id, int[] inputRankings) {
        employeeId = id;
        rankings = inputRankings;
        matched = false;
    }

}
