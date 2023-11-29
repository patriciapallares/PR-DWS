package org.example;

import java.io.*;
import java.net.*;

public class Api {
    // LITERALMENTE COPIADO DE http://www.penguinrandomhouse.biz/webservices/rest/
    static final String kuser = "testuser";
    static final String kpass = "testpassword";

    static class RHAuthenticator extends Authenticator {
        public PasswordAuthentication getPasswordAuthentication() {
            return (new PasswordAuthentication(kuser, kpass.toCharArray()));
        }
    }

    public static void main(String[] args) throws IOException {
        // autenticación de Penguin
        Authenticator.setDefault(new RHAuthenticator());
        // url con endpoint
        URL url = new URL("https://reststop.randomhouse.com/resources/authors?lastName=Smith");

        // según ChatGPT:
        String apiUrl = "https://api.penguinrandomhouse.com/resources/v1/";
        String endpoint = "authors?lastName=Smith";
        // URL url = new URL(apiUrl + endpoint);

        // Crear la conexión HTTP
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        InputStream is = con.getInputStream();

        // Leer la respuesta de la API
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        // según ChatGPT
        StringBuilder response = new StringBuilder();

        /*
        * Se declara una variable de tipo String llamada line que será utilizada para leer cada línea
        * de la respuesta de la API.
        El bucle while se ejecuta mientras la llamada a reader.readLine() no retorne un valor nulo.
        * readLine() lee la respuesta línea por línea y devuelve cada línea como un String.
        * Cuando alcanza el final de la respuesta, devuelve null, lo que hace que el bucle se detenga.
        Dentro del bucle, se asigna cada línea de la respuesta a la variable line.
        Se utiliza el método append() del StringBuilder para agregar cada línea al objeto response,
        * construyendo así la respuesta completa como una cadena de caracteres.
        Cuando se completa la lectura de la respuesta (cuando readLine() devuelve null), el bucle
        * while termina y se cierra el BufferedReader.
        * */
        String str;

        while ((str = reader.readLine()) != null) {
            // según ChatGPT:
            response.append(str);
            System.out.println(str);
        }

        // Imprimir la respuesta (puede ser necesario procesarla según la estructura de la API)
        System.out.println("Respuesta de la API:");
        System.out.println(response.toString());
        reader.close();
    }
}