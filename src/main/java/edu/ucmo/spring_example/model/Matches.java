package edu.ucmo.spring_example.model;

import javax.persistence.*;

@Entity
@Table(name = "matches")
public class Matches {



    @Id
    @Column
    private int userid;

    @Column
    private int companyid;




    public Matches(int userid, int companyid) {
        this.userid = userid;
        this.companyid = companyid;
    }
    public Matches(){

    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public int getCompanyid() {
        return companyid;
    }

    public void setCompanyid(int companyid) {
        this.companyid = companyid;
    }

    //TODO: toString for Matches
    @Override
    public String toString() {
        return "Matches{" +
                "userid=" + userid +
                ", companyid='" + companyid +
                '}';
    }
}