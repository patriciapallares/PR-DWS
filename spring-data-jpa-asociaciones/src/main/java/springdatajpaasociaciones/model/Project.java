package springdatajpaasociaciones.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "project")
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private LocalDate deliveryDate;

    // un empleado puede trabajar en más de un proyecto a la vez
    // un proyecto tiene más de un empleado trabajando en él
    // con una columna no vale


    // employee es el owner
    @ManyToMany(mappedBy = "projects")
    private List<Employee> employees = new ArrayList<>();


    public Project() {
    }

    public Project(Long id, String name, LocalDate deliveryDate, List<Employee> employees) {
        this.id = id;
        this.name = name;
        this.deliveryDate = deliveryDate;
        this.employees = employees;
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

    public LocalDate getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(LocalDate deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    @Override
    public String toString() {
        return "Project{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", deliveryDate=" + deliveryDate +
                ", employees=" + employees +
                '}';
    }
}
