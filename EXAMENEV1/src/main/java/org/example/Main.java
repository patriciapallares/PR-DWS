package org.example;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import static org.example.Utilidades.*;

public class Main {
    public static void main(String[] args) throws IOException {

        Path pelisFemale = Path.of(".", "src", "main", "resources", "oscar_age_female.csv");

        Path pelisMale = Path.of(".", "src", "main", "resources", "oscar_age_male.csv");

        // Opción 1 para almacenar en una lista de objetos PeliculasOscarizadas la información obtenida de los 2 ficheros .csv.

        List<PeliculaOscarizada> listaPelisMale = leerPeliculasOscarizadasCsv(pelisMale,"H");
        List<PeliculaOscarizada> listaPelisFemale = leerPeliculasOscarizadasCsv(pelisFemale,"M");

        List<PeliculaOscarizada> listaPelisMain = new ArrayList<>();

        listaPelisMain.addAll(listaPelisMale);
        listaPelisMain.addAll(listaPelisFemale);

        // Opción 2 para almacenar en una lista de objetos PeliculasOscarizadas la información obtenida de los 2 ficheros .csv.

        // TO-DO: CREAR UN MÉTODO QUE RECIBA 2 PATHS POR PARÁMETROS

        List<Actor> listaActoresMain = convertirPeliculasOscarizadasEnActores(listaPelisMain);
        // Comprobación del funcionamiento de convertirPeliculasOscarizadasEnActores()
        // listaActoresMale.stream().forEach(Actor::muestraNombreYAnyo);


        Path actoresJSON = Path.of(".", "src", "main", "resources", "salida", "actores.json");

        escribirActoresenJson(listaActoresMain,actoresJSON);

        actoresMasJovenesEnGanarUnOscar(listaPelisMain);

        actoresConMasdeUnOscar(listaPelisMain);

    }
}