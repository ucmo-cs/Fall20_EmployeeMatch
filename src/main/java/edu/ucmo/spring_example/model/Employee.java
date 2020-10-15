package edu.ucmo.spring_example.model;

import java.util.Objects;
import javax.persistence.*;

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
    private int email;
    @Column
    private int passHash;



    public Employee(String first, String last, int email) {
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

    public int getEmail() {
        return email;
    }

    public void setEmail(int email) {
        this.email = email;
    }
    public int getPassHash() {
        return passHash;
    }

    public void setPassHash(int passHash) {
        this.passHash = passHash;
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