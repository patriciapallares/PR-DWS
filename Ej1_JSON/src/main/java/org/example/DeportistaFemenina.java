package org.example;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class DeportistaFemenina {
    private String nombre;
    private String deporte;
    private int edad;
    private String pais;

}
