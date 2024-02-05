package com.example.model;

import jakarta.persistence.*;

@Entity
@Table(name = "manufacturers")
public class Manufacturer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(unique= true)
    private String name;
    @Column(name = "num_employees")
    private Integer numEmployees;

    private Integer year;

    public Manufacturer(long id, String name, Integer numEmployees, Integer year) {
        this.id = id;
        this.name = name;
        this.numEmployees = numEmployees;
        this.year = year;
    }

    public Manufacturer() {
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setNumEmployees(Integer numEmployees) {
        this.numEmployees = numEmployees;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getNumEmployees() {
        return numEmployees;
    }

    public Integer getYear() {
        return year;
    }
}
