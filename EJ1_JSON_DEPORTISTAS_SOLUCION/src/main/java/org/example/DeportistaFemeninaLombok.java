package ejemploJakson;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import lombok.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

//@Getter
@Data
@NoArgsConstructor
@AllArgsConstructor
//@Setter
public class DeportistaFemeninaLombok {
    private String nombre;
    private String deporte;
    private int edad;
    private String pais;


    public static void main(String[] args) {
        // Crear una lista de deportistas femeninas
        List<DeportistaFemeninaLombok> deportistas = new ArrayList<>();
        deportistas.add(new DeportistaFemeninaLombok("Lina Smith", "Tenis", 25, "Estados Unidos"));
        deportistas.add(new DeportistaFemeninaLombok("María Rodríguez", "Natación", 22, "España"));
        deportistas.add(new DeportistaFemeninaLombok("Anna Müller", "Atletismo", 28, "Alemania"));

        // Crear un objeto ObjectMapper de Jackson
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            // Mapear la lista de deportistas a un archivo JSON
            objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
            objectMapper.writeValue(new File("deportistas_femeninas.json"), deportistas);
            System.out.println("Los deportistas femeninos se han mapeado correctamente en el archivo JSON.");
            //Para asegurarnos, vamos a leer lo que hay en el archivo

            //System.out.println(objectMapper.readValue(new File("deportistas_femeninas.json"), new TypeReference<>() { }));
            List<DeportistaFemeninaLombok> deportistasLista = objectMapper.readValue(new File("deportistas_femeninas.json"), new TypeReference<>() { });
            deportistasLista.forEach(System.out::println);
            //System.out.println(deportistasLista.get(0).getPais());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
