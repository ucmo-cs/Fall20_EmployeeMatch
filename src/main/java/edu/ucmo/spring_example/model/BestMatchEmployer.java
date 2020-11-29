package edu.ucmo.spring_example.model;


public class BestMatchEmployer {
    public int companyId;
    public int[] rankings;
    public BestMatchEmployer(int id, int[] inputRankings) {
        companyId = id;
        rankings = inputRankings;
    }

}
