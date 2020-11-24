package edu.ucmo.spring_example.model;



public class BestMatchEmployee {
    public int employeeId;
    public float[] rankings;
    public BestMatchEmployee(int id, float[] inputRankings) {
        employeeId = id;
        rankings = inputRankings;
    }

}
