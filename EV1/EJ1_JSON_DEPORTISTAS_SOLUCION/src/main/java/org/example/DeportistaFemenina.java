package ejemploJakson;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DeportistaFemenina {

        private String nombre;
        private String deporte;
        private int edad;
        private String pais;

        // Constructor, getters y setters

        public DeportistaFemenina(String nombre, String deporte, int edad, String pais) {
            this.nombre = nombre;
            this.deporte = deporte;
            this.edad = edad;
            this.pais = pais;
        }

        // Getter y Setter

        public String getNombre() {
            return nombre;
        }
        public void setNombre(String nombre) {
            this.nombre = nombre;
        }

        public String getDeporte() {
            return deporte;
        }

        public void setDeporte(String deporte) {
            this.deporte = deporte;
        }

        public int getEdad() {
            return edad;
        }
        public void setEdad(int edad) {
            this.edad = edad;
        }
        public String getPais() {
            return pais;
        }

        public void setPais(String pais) {
            this.pais = pais;
        }

        public static void main(String[] args) {
            // Crear una lista de deportistas femeninas
            List<DeportistaFemenina> deportistas = new ArrayList<>();
            deportistas.add(new DeportistaFemenina("Lina Smith", "Tenis", 25, "Estados Unidos"));
            deportistas.add(new DeportistaFemenina("María Rodríguez", "Natación", 22, "España"));
            deportistas.add(new DeportistaFemenina("Anna Müller", "Atletismo", 28, "Alemania"));

            // Crear un objeto ObjectMapper de Jackson
            ObjectMapper objectMapper = new ObjectMapper();

            try {
                // Mapear la lista de deportistas a un archivo JSON
                objectMapper.writeValue(new File("deportistas_femeninas.json"), deportistas);
                System.out.println("Los deportistas femeninos se han mapeado correctamente en el archivo JSON.");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


}
