package org.example;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class Funko {

    private String cod;
    private String nombre;
    private String modelo; // marvel, disney, anime, otros
    private double precio; // dos decimales
    private LocalDate fecha_lanzamiento;

}
