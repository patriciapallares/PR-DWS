package com.example.miprimeraapirest.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@AllArgsConstructor
// La anotación @Data es una característica de Lombok que combina varias otras anotaciones para generar
// automáticamente métodos comunes como getters, setters, toString(), equals(), y hashCode().
@Data

@Entity

@Table(name="drivers")
public class Driver {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "driverid")
    private Long driverid;

    @Column(unique = true)
    private String code;
    private String forename;
    private String surname;
    @JsonProperty("dateOfBirth")
    private LocalDate dob;
    private String nationality;


    // en una escudería hay más de un conductor
    // un conductor trabaja para sólo una escudería
    // manyToOne
    @ManyToOne
    @JoinColumn(name = "constructorid", foreignKey = @ForeignKey(name = "fk_driver_constructor"))
    @JsonIgnoreProperties("drivers")
    private Constructor constructor;

    // private Integer constructorid;
    private String url;

}
