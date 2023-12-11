package org.example;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class Book implements Serializable{

    // 1. Define una clase Book para representar un libro con los atributos mencionados anteriormente.
    private String isbn;
    private String titulo;
    private String autor;
    private int paginas;
    private String anyoPublicacion;

}
