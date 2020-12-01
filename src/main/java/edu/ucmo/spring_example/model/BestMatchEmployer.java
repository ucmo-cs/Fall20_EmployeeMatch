package edu.ucmo.spring_example.model;


import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class BestMatchEmployer {
    public int companyId;
    public int[] rankings;
    public Queue rankingsQueue = new LinkedList();
    public boolean matched;
    public BestMatchEmployer(int id, int[] inputRankings) {
        companyId = id;
        rankings = inputRankings;
        matched = false;
        resetQueue();
    }
    public void resetQueue() {
        for(int i : rankings)
            rankingsQueue.add(i);
    }


}
