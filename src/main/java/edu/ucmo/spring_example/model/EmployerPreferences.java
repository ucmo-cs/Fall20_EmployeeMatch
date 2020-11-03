package edu.ucmo.spring_example.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "employerpreferences")
public class EmployerPreferences {

    @Id
    private int companyid;
    @Column
    private int ew1;
    @Column
    private int ew2;



    @Column
    private int ew3;
    @Column
    private int ew4;
    @Column
    private int ew5;
    @Column
    private int eo1;
    @Column
    private int eo2;
    @Column
    private int eo3;
    @Column
    private int eo4;
    @Column
    private int eo5;

    public EmployerPreferences(int id, int ew1, int ew2, int ew3, int ew4, int ew5, int eo1, int eo2, int eo3, int eo4, int eo5)
    {
        this.companyid = id;
        this.ew1 = ew1;
        this.ew2 = ew2;
        this.ew3 = ew3;
        this.ew4 = ew4;
        this.ew5 = ew5;
        this.eo1 = eo1;
        this.eo2 = eo2;
        this.eo3 = eo3;
        this.eo4 = eo4;
        this.eo5 = eo5;
    }
    public EmployerPreferences()
    {

    }
    public int getCompanyid() {
        return companyid;
    }

    public void setCompanyid(int id) {
        this.companyid = id;
    }

    public int getEw1() {
        return ew1;
    }

    public void setEw1(int ew1) {
        this.ew1 = ew1;
    }

    public int getEw2() {
        return ew2;
    }

    public void setEw2(int ew2) {
        this.ew2 = ew2;
    }

    public int getEw3() {
        return ew3;
    }

    public void setEw3(int ew3) {
        this.ew3 = ew3;
    }

    public int getEw4() {
        return ew4;
    }

    public void setEw4(int ew4) {
        this.ew4 = ew4;
    }

    public int getEw5() {
        return ew5;
    }

    public void setEw5(int ew5) {
        this.ew5 = ew5;
    }

    public int getEo1() {
        return eo1;
    }

    public void setEo1(int eo1) {
        this.eo1 = eo1;
    }

    public int getEo2() {
        return eo2;
    }

    public void setEo2(int eo2) {
        this.eo2 = eo2;
    }

    public int getEo3() {
        return eo3;
    }

    public void setEo3(int eo3) {
        this.eo3 = eo3;
    }

    public int getEo4() {
        return eo4;
    }

    public void setEo4(int eo4) {
        this.eo4 = eo4;
    }

    public int getEo5() {
        return eo5;
    }

    public void setEo5(int eo5) {
        this.eo5 = eo5;
    }

}
