package org.example;

import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        /*
        Luego, deberás crear una lista de objetos AtletaFemenina que represente a varias atletas con
        información relevante.
        A continuación, utilizando la biblioteca Jackson, deberás escribir código Java para realizar
        las siguientes tareas:
        1. Mapear la lista de objetos AtletaFemenina a un archivo XML llamado atletas_femeninas.xml.
        2. Asegurarte de que el archivo XML generado contenga información detallada sobre las atletas,
                incluyendo sus nombres, deportes, edades y países de origen.
        3. Crea un método que extraiga una lista de objetos AtletaFemenina a partir de un archivo XML.
        Verifica la funcionalidad de serialización (escritura) y deserialización (lectura)
        de objetos en el archivo XML.
        */

        String miRutaAtletas_Femeninasxml = "/Users/patriciapallares/IdeaProjects/PR-DWS/EJ1_XML_DEPORTISTAS/src/main/resources/atletas_femeninas.xml";

        AtletaFemenina atleta1 = new AtletaFemenina("Carolina", List.of("A", "B", "C"), 21, "España");
        AtletaFemenina atleta2 = new AtletaFemenina("Jessica", List.of("A", "B", "C"), 25, "USA");
        AtletaFemenina atleta3 = new AtletaFemenina("Maria", List.of("A", "B"), 19, "España");

        // ColeccionAtletas listaAtletas = new ColeccionAtletas(List.of(atleta1, atleta2, atleta3));

        List<AtletaFemenina> listaAtletas  = new ArrayList<>();

        listaAtletas.add(atleta1);
        listaAtletas.add(atleta2);
        listaAtletas.add(atleta3);

        escribirListaObjetosXml(listaAtletas, miRutaAtletas_Femeninasxml);

    }

    // Si queremos serializar una lista de objetos, se pueden manipular las etiquetas XML creando
    // una clase auxiliar que contenga la lista de objetos y que tenga la etiqueta raíz
    public static void escribirListaObjetosXml(List listaAtletasParam, Path ruta) {

        try {
            Files.deleteIfExists(ruta);
            XmlMapper xmlMapper = new XmlMapper();
            // La siguiente línea es opcional, pero hace que el JSON se muestre con formato
            xmlMapper.enable(SerializationFeature.INDENT_OUTPUT);
            xmlMapper.writeValue(ruta.toFile(), listaAtletasParam);
        } catch (IOException e) {
            System.out.println("El fichero no existe");
        }
    }
}