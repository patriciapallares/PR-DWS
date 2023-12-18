package org.example;

import java.nio.file.Path;
import java.util.*;

import static org.example.OperacionesJSON.escribirListaObjetosJson;
import static org.example.OperacionesJSON.leerArrayObjetosJson;

public class Main {
    public static void main(String[] args) {

        //Crea una aplicación Java que gestione una lista de libros.

        Libro libro1 = new Libro("Crepúsculo", "SM", 2004, "123");
        Libro libro2 = new Libro("Crepúsculo", "SM", 2004, "123");
        Libro libro3 = new Libro("Crepúsculo", "SM", 2004, "123");
        Libro libro4 = new Libro("Crepúsculo", "SM", 2004, "123");
        Libro libro5 = new Libro("Fatine", "SM", 2004, "123");

        List<Libro> libros = List.of(libro1,libro2,libro3,libro4,libro5);

        Path rutaLibros = Path.of(".", "src", "main", "resources", "libros.json");

        System.out.println("\n**** Escritura de lista de objetos JSON a fichero ****");
        escribirListaObjetosJson(libros, rutaLibros);

        // 4. Luego, deserializa el archivo JSON de nuevo en una lista de libros.
        List<Libro> libros2 = leerArrayObjetosJson(rutaLibros);
        System.out.println("\n**** AHORA LEO LIBROS 2 ****");
        libros2.forEach(System.out::println);

       // Libro probando = encontrarLibroPorTitulo("Fatine",libros);

       //  System.out.println(probando.toString());

        
    }
    public static Libro encontrarLibroPorTitulo(String titulo, List<Libro> listaParametro) {

        Libro encontrado = new Libro();
       encontrado = (Libro) listaParametro.stream().filter(libro -> Objects.equals(libro.getTitulo(), titulo));

        return encontrado;
    }

    public Libro encontrarLibroPorAutor(String autor, List<Libro> lista) {

        Libro encontrado = new Libro();
        lista.stream().map(Libro::getTitulo);
        /*
        for(Libro Libro : libros){
            if(Libro.getISBN().equals(textToFind) ||
                    Libro.getTitulo().contains(textToFind)){
                foundLibros.add(Libro);
            }
        }*/
        return encontrado;
    }
}