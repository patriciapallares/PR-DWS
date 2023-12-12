package org.example;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class PeliculaOscarizada {
                            // en csv:
    private String pelicula; // -> movie posición 4
    private int anyo; // -> year posición 1
    private String actor; // -> name posición 3
    private int edad; // -> age posición 2
    private String sexo; // no está. Añadir manualmente M si es female o H si es male



}
