package org.example;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        Path miRutaAtletas_Femeninasxml = Paths.get("/home/daw2/Escriptori/PR-DWS/EJ1_XML_DEPORTISTAS/src/main/resources/atletas_femeninas.xml");
        String stringg = "/home/daw2/Escriptori/PR-DWS/EJ1_XML_DEPORTISTAS/src/main/resources/atletas_femeninas.xml";

        AtletaFemenina atleta1 = new AtletaFemenina("Carolina", List.of("A", "B", "C"), 21, "España");
        AtletaFemenina atleta2 = new AtletaFemenina("Jessica", List.of("A", "B", "C"), 25, "USA");
        AtletaFemenina atleta3 = new AtletaFemenina("Maria", List.of("A", "B"), 19, "España");

        // ColeccionAtletas listaAtletas = new ColeccionAtletas(List.of(atleta1, atleta2, atleta3));

        List<AtletaFemenina> listaAtletas  = new ArrayList<>();

        listaAtletas.add(atleta1);
        listaAtletas.add(atleta2);
        listaAtletas.add(atleta3);

        escribirListaObjetosXml(listaAtletas, miRutaAtletas_Femeninasxml);

        List<AtletaFemenina> listaAtletasRestaurado  = new ArrayList<>();

        listaAtletasRestaurado = leerArrayObjetosXml(miRutaAtletas_Femeninasxml);

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

    //
    public static List<AtletaFemenina> leerArrayObjetosXml(Path ruta) {
        try {
            XmlMapper xmlMapper = new XmlMapper();
            // return xmlMapper.readValue(ruta.toFile(), new TypeReference<List<Lenguaje2>>() { });
            return xmlMapper.readValue(ruta.toFile(), new TypeReference<>() { });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}