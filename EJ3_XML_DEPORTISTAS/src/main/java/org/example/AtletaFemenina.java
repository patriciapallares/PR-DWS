package org.example;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@AllArgsConstructor
@NoArgsConstructor
@Data
public class AtletaFemenina {

/*
• nombre (String): El nombre de la atleta.
• prueba (List<String>): La prueba en la que compite.
• edad (int): La edad de la atleta.
• pais (String): El país de origen de la atleta.
*/

    private String nombre;
    private List<String> prueba;
    private int edad;
    private String pais;


}
