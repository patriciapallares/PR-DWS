package com.example.miprimeraapirest.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@AllArgsConstructor
@Data

@Entity
@Table(name = "constructors")
public class Constructor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "constructorid")
    private Long constructorid;
    @Column(unique = true)
    private String constructorref;
    private String name;
    private String nationality;
    private String url;


    // el owner es driver
    // tiene que coincidir entre comillas con el nombre del atributo Constructor en la clase Driver
    @OneToMany(mappedBy = "constructor")
    private List<Driver> drivers = new ArrayList<>();
    // en vez de listas se puede utilizar sets ¿?


}
