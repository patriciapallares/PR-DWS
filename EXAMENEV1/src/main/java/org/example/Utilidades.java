package org.example;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

// En la cual crearás todos los métodos necesarios para
//manipular la información que necesites de las clases anteriores.
public class Utilidades {
    private final static String COMMA_DELIMITER = ";";

    // 1. lee un fichero CSV y devuelve una lista de objetos PeliculaOscarizada.
    //Debe tener en cuenta también el parámetro sexo para filtrar por sexo.


    // para ejecutar la función hay que pasar Ruta y "H"/"M"
    public static List leerPeliculasOscarizadasCsv(Path ruta, String sexoParam) throws IOException {
        List<PeliculaOscarizada> listaPelisOscarizadas = new ArrayList<>();

        try (Stream<String> contenidoFichero = Files.lines(ruta)) {
            List<List<String>> pelisOscarizadasString = contenidoFichero.map(l -> Arrays.asList(l.split(COMMA_DELIMITER))).toList();

            // redundante
            String sexo = sexoParam;


            for (int i = 1; i < pelisOscarizadasString.size(); i++) {


                List<String> unaPeliOscarizada = pelisOscarizadasString.get(i);
                PeliculaOscarizada peliOscarizada = new PeliculaOscarizada();
                peliOscarizada.setAnyo(Integer.parseInt(unaPeliOscarizada.get(1)));
                peliOscarizada.setEdad(Integer.parseInt(unaPeliOscarizada.get(2)));
                peliOscarizada.setActor(unaPeliOscarizada.get(3));
                peliOscarizada.setPelicula(unaPeliOscarizada.get(4));
                peliOscarizada.setSexo(sexo);

                listaPelisOscarizadas.add(peliOscarizada);
            }
        } catch (IOException e) {
            e.printStackTrace(System.out);
        }
        return listaPelisOscarizadas;
    }

    // 2. dada una lista de PeliculasOscarizadas, deberás devolver
    // una lista de objetos Actor, en la que estarán incluidos todos los actores y actrices.


    public static List convertirPeliculasOscarizadasEnActores(List<PeliculaOscarizada> lista) {
        List<Actor> listaActores = new ArrayList<>();


        for (int i = 0; i < lista.size(); i++) {

            List<Pelicula> listaPelisParaActores = new ArrayList<>();
            PeliculaOscarizada peliOscarizada = lista.get(i);

            Actor actor = new Actor();
            Pelicula peli = new Pelicula(peliOscarizada.getPelicula(), peliOscarizada.getAnyo());
            listaPelisParaActores.add(peli);

            actor.setNombre(peliOscarizada.getActor());
            actor.setAnyoNacimiento(peliOscarizada.getAnyo() - peliOscarizada.getEdad());
            actor.setSexo(peliOscarizada.getSexo());
            actor.setPeliculas(listaPelisParaActores);

            listaActores.add(actor);
        }
        return listaActores;
    }
        ;

        // 3. dado una lista de objetos Actor, escribe en un fichero JSON la lista de
        // actores/actrices en el formato solicitado.



        public static void escribirActoresenJson(List<Actor> actores, Path ruta) {

            try {
                Files.deleteIfExists(ruta);
                ObjectMapper objectMapper = new ObjectMapper();
                // La siguiente línea es opcional, pero hace que el JSON se muestre con formato
                objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
                objectMapper.writeValue(ruta.toFile(), actores);
            } catch (IOException e) {
                System.out.println("El fichero no existe");
            }
        }


        // opcionalmente

        // 4.  dada una lista de PeliculasOscarizadas, deberás devolver una
        //lista de nombres de actores o actrices que hayan ganado MÁS DE un oscar, ordenado de forma ascendente

    // NO RETORNA LISTA NI ORDENA DE FORMA ASCENDENTE :(
        public static void actoresConMasdeUnOscar(List<PeliculaOscarizada> peliculaOscarizadas) {
            List<String> nombres = new ArrayList<>();
            System.out.println("Me ejecuto");

            peliculaOscarizadas.stream()
                    .collect(Collectors.groupingBy(PeliculaOscarizada::getActor, Collectors.counting()))
                    .forEach((actor, count) -> System.out.println("Actor: " + actor + " Núm de películas: " + count));


            // return nombres;
        }

        // 5. devuelve una lista de Strings con el actor y la actriz más jóvenes en
        //ganar un Oscar

        public static List actoresMasJovenesEnGanarUnOscar(List<PeliculaOscarizada> peliculaOscarizadas){
          List<String> nombres = new ArrayList<>();

          PeliculaOscarizada actorConMenosAnyos = peliculaOscarizadas.stream()
                  .filter(peliculaOscarizada -> Objects.equals(peliculaOscarizada.getSexo(), "H"))
                  .min(Comparator.comparingInt(PeliculaOscarizada::getEdad))
                  .orElse(null);

            PeliculaOscarizada actrizConMenosAnyos = peliculaOscarizadas.stream()
                    .filter(peliculaOscarizada -> Objects.equals(peliculaOscarizada.getSexo(), "M"))
                    .min(Comparator.comparingInt(PeliculaOscarizada::getEdad))
                    .orElse(null);

            nombres.add(actorConMenosAnyos.getActor());
            System.out.println(actorConMenosAnyos.getActor() + " " + actorConMenosAnyos.getEdad());
            nombres.add(actrizConMenosAnyos.getActor());
            System.out.println(actrizConMenosAnyos.getActor() + " " + actrizConMenosAnyos.getEdad());

          return nombres;

        };

    }


