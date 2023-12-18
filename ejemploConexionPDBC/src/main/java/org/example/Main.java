package org.example;

import java.sql.*;

public class Main {
    public static void main(String[] args) {


        String sentenciaSQL = "SELECT * FROM personas";
        // están entre parentesis dentro del try para que cierre la ejecución automáticamente...
        try(Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/mi-base");
            PreparedStatement sentencia = con.prepareStatement(sentenciaSQL)){

            ResultSet resultados = sentencia.executeQuery();

            while (resultados.next()){
                String nombre = resultados.getString("nombre");
                Integer edad = resultados.getInt("edad");

            }

        }catch (SQLException e){
            throw new RuntimeException();
        }
    }
}