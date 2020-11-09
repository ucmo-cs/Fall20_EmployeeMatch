package edu.ucmo.spring_example.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Objects;
import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "employee")
public class Employee {



    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column
    private String firstn;
    @Column
    private String lastn;
    @Column
    private String email;
    @Column
    private String passHash;
    @Column(name = "active")
    private Boolean active;



    public Employee(String first, String last, String email) {
        this.firstn = first;
        this.lastn = last;
        this.email = email;
    }
    public Employee(){

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstn() {
        return firstn;
    }

    public void setFirstn(String firstn) {
        this.firstn = firstn;
    }

    public String getLastn() {
        return lastn;
    }

    public void setLastn(String lastn) {
        this.lastn = lastn;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    public String getPassHash() {
        return passHash;
    }

    public void setPassHash(String passHash) {
        this.passHash = passHash;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", firstn='" + firstn + '\'' +
                ", lastn='" + lastn + '\'' +
                ", email='" + email + '\'' +
                ", passHash='" + passHash + '\'' +
                '}';
    }
}