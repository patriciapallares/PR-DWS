package org.example;

import java.nio.file.Path;
import java.sql.*;


public class Main2 {

    public static void main(String[] args) {
        // URL de la BBDD
        Path ruta = Path.of("src", "main", "resources", "f12006sqlite.db");

        Piloto pilotoParam = new Piloto(1, "EOE", "Pepe", "Pepito", "16-12-1994", "Spanish", "URL");



        int paramID = 40;

        try (Connection con = DriverManager.getConnection("jdbc:sqlite:" + ruta.toString())) {

            // Preparamos una sentencia que lanzaremos a través del PreparedStatement
            String consultaSQL = "SELECT driverid, code, forename, surname, strftime('%d/%m/%Y', dob) AS formatted_dob, nationality " +
                    "FROM drivers ";
            PreparedStatement consulta = con.prepareStatement(consultaSQL);

            // La consulta SELECT se ejecuta pasándola por el método executeQuery. Si la consulta devuelve datos,
            // estos estarán accesibles a través de un "conjunto de resultados" (ResultSet)
            ResultSet resultados = consulta.executeQuery();

            // Consumimos los resultados de la consulta
            System.out.format("\n%3s%5s%25s%16s%20s\n", "Id", "Cod", "Nombre", "Fecha Nac", "Nacionalidad");
            System.out.println("--------------------------------------------------------------------");
            // El conjunto de resultados se recorre de forma secuencial: rs.next() será verdadero si hay más datos en el set.
            while (resultados.next()) {
                // Si cada fila del resultado está formada por varios campos, podemos obtener el valor de cada uno de ellos con rs.getString(x) o rs.getInt(x),
                // donde x puede ser la posición de la columna (empezando con 1) o el nombre del campo (indiferente de mayúsculas o minúsculas
                System.out.format("%3d%5s%25s%16s%20s\n", resultados.getInt("driverid"),
                        resultados.getString("code"),
                        resultados.getString("forename") + " " + resultados.getString("surname"),
                        resultados.getString("formatted_dob"),
                        resultados.getString("nationality"));
            }
        } catch (SQLException e) {
            // Gestionar errores mediante excepciones
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
