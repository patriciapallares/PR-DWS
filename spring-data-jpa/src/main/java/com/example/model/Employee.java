package com.example.model;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "employees")

public class Employee {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column (name = "full_name")
    private String fullName;

    private LocalDate birthDate;

    private Boolean married;

    public Boolean getMarried() {
        return married;
    }

    public void setMarried(Boolean married) {
        this.married = married;
    }

    public Employee() {
    }

    public Employee(Long id, String fullName, LocalDate birthDate, Boolean married) {
        this.id = id;
        this.fullName = fullName;
        this.birthDate = birthDate;
        this.married = married;
    }

    public Long getId() {
        return id;
    }

    public String getFullName() {
        return fullName;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", fullName='" + fullName + '\'' +
                ", birthDate=" + birthDate +
                '}';
    }
}
