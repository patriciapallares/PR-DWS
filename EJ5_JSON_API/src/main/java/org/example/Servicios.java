package org.example;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Servicios {

    public static void main(String[] args) {
        try {
            // URL de la API de Penguin Random House
            // String apiUrl = "https://reststop.randomhouse.com/resources/";

            System.out.println("ALGO?");
            // Construir la URL con el endpoint deseado (por ejemplo, buscar libros)
            String endpoint = "books?author=Stephen%20King"; // Ejemplo: búsqueda de libros de Stephen King
            URL url = new URL("https://reststop.randomhouse.com/resources/authors?lastName=Smith");
            // URL url = new URL(apiUrl + endpoint);

            // Crear la conexión HTTP
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            // Leer la respuesta de la API
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder response = new StringBuilder();
            String line;

            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();

            // Imprimir la respuesta (puede ser necesario procesarla según la estructura de la API)
            System.out.println("Respuesta de la API:");
            System.out.println(response.toString());

            // Cerrar la conexión
            connection.disconnect();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
