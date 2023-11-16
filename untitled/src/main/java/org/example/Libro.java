package org.example;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class Libro {

    //  Cada libro debe tener los siguientes atributos:
    //  Título, autor, año de publicación y un
    //  identficador único (por ejemplo, un número ISBN).

    private String titulo;
    private String autor;
    private int anyoPublicacion;
    private String ISBN;

}
