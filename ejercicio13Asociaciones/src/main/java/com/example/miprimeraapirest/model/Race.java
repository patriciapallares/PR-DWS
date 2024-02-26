package com.example.miprimeraapirest.model;

import jakarta.persistence.*;


import java.time.LocalDate;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity

@Table(name = "races")
public class Race {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "raceid")
    private Long raceid;
    private Integer year;
    private Integer round;

    // tipo de relación
    // un empleado tiene una dirección
    // one-to-one
    @OneToOne(cascade = {})
    @JoinColumn(name = "circuitid", foreignKey = @ForeignKey(name = "fk_race_circuit"))
    private Circuit circuit;

    // private Integer circuitid;

    private String name;

    @Temporal(TemporalType.DATE)
    private LocalDate date;
    @Temporal(TemporalType.TIME)
    private LocalDateTime time;
    private String url;
}
