package edu.ucmo.spring_example.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "employer")
public class Employer {



    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int companyid;
    @Column
    private String companyname;
    @Column
    private String email;

    public Employer(String companyname, String email) {
        this.companyname = companyname;
        this.email = email;
    }
    public Employer(int companyid, String companyname, String email) {
        this.companyid = companyid;
        this.companyname = companyname;
        this.email = email;
    }
    public Employer(){

    }


    public int getCompanyid() {
        return companyid;
    }

    public void setCompanyid(int companyId) {
        this.companyid = companyId;
    }

    public String getCompanyname() {
        return companyname;
    }

    public void setCompanyname(String companyName) {
        this.companyname = companyName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public int hashCode() {

        return Objects.hash(companyid, companyname,email);
    }
    @Override
    public String toString() {
        return "Employer{" +
                "companyId=" + companyid +
                ", companyName='" + companyname + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}