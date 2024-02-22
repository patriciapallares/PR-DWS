package springdatajpaasociaciones.model;


import jakarta.persistence.*;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 300)
    private String name;
    private Integer age;
    private Boolean married;
    private Double salary;
    // Normalmente trabajamos con localdate pero.
    private Instant createdDate;

    private LocalDate birthDate;

    private LocalDateTime registrationDate;

    // generamos su propia tabla
    @ElementCollection
    private List<String> email = new ArrayList<>();

    // enumeración. se puede guardar como texto
    @Enumerated(EnumType.STRING)
    private EmployeeType category;

    // tipo de relación
    // un empleado tiene una dirección
    // one-to-one
    @OneToOne
    @JoinColumn(name = "id_address", foreignKey = @ForeignKey(name = "fk_employee_address"))
    private Address address;


    // en una empresa hay más de un empleado
    // un empleado trabaja solo en una empresa
    // many-to-one

    @ManyToOne
    @JoinColumn(name = "id_company", foreignKey = @ForeignKey(name = "fk_employee_company"))
    private Company company;

    @ManyToMany

    // si no ponemos esto no pasa nada :/
    @JoinTable(name = "employee_project",
            joinColumns = @JoinColumn(name = "id_employee", referencedColumnName = "id "),
            inverseJoinColumns = @JoinColumn(name = "id_project", referencedColumnName = "id")
    )
    private List<Project> projects = new ArrayList<>();


    public Employee() {
    }

    public Employee(Long id, String name, Integer age, Boolean married, Double salary, Instant createdDate, LocalDate birthDate, LocalDateTime registrationDate, List<String> email, EmployeeType category, Address address, Company company, List<Project> projects) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.married = married;
        this.salary = salary;
        this.createdDate = createdDate;
        this.birthDate = birthDate;
        this.registrationDate = registrationDate;
        this.email = email;
        this.category = category;
        this.address = address;
        this.company = company;
        this.projects = projects;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", married=" + married +
                ", salary=" + salary +
                ", createdDate=" + createdDate +
                ", birthDate=" + birthDate +
                ", registrationDate=" + registrationDate +
                ", email=" + email +
                ", category=" + category +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Boolean getMarried() {
        return married;
    }

    public void setMarried(Boolean married) {
        this.married = married;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public Instant getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Instant createdDate) {
        this.createdDate = createdDate;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public LocalDateTime getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(LocalDateTime registrationDate) {
        this.registrationDate = registrationDate;
    }

    public List<String> getEmail() {
        return email;
    }

    public void setEmail(List<String> email) {
        this.email = email;
    }

    public EmployeeType getCategory() {
        return category;
    }

    public void setCategory(EmployeeType category) {
        this.category = category;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public List<Project> getProjects() {
        return projects;
    }

    public void setProjects(List<Project> projects) {
        this.projects = projects;
    }
}
