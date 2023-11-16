package org.example;

import java.util.List;

public class Main {
    public static void main(String[] args) {

        //Crea una aplicación Java que gestione una lista de libros.

        Libro libro1 = new Libro("Crepúsculo", "SM", 2004, "123");
        Libro libro2 = new Libro("Crepúsculo", "SM", 2004, "123");
        Libro libro3 = new Libro("Crepúsculo", "SM", 2004, "123");
        Libro libro4 = new Libro("Crepúsculo", "SM", 2004, "123");
        Libro libro5 = new Libro("Crepúsculo", "SM", 2004, "123");

        List<Libro> libros = List.of(libro1,libro2,libro3,libro4,libro5);

    }
}