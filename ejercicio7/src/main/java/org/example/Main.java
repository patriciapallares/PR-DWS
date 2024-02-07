package org.example;

// import org.example.ejercicios.Ejercicio7.entities.Constructor;
//import org.example.ejercicios.Ejercicio7.entities.Piloto;

import java.sql.*;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        // PENDIENTE. NO FUNCIONA PORQ ME DA NULL EL NOMBRE O ALGO Y SALTA LA EXCEPCIÓN EN EL MAIN :/


        String endpoint = "ejercicio7.cri6kii6mgv3.us-east-1.rds.amazonaws.com";
        String port = "5432";
        String usuario = "postgres";
        String passwd = "postgres";
        String urlCon = "jdbc:postgresql://" + endpoint + ":" + port + "/f12006";

        Constructor constructor1 = new Constructor("mclaren", "McLaren", "British", null);
        Piloto piloto1 = new Piloto("SAI", "Carlos", "Sainz", Date.valueOf("1994-09-01"), "Spanish", 0);
        Piloto piloto2 = new Piloto("ALM", "Manuel", "Alomán", Date.valueOf("2004-01-27"), "Spanish", 0);

        System.out.println("Conectando a la base de datos...");

        try (Connection conn = DriverManager.getConnection(urlCon, usuario, passwd)) {
            System.out.println("Conectado a la base de datos");
            try {
                conn.setAutoCommit(false);
                //Muestro los pilotos que hay ahora en la bbdd
                System.out.println("pilotos:");

                Piloto piloto3 = LeerPiloto(3);

                System.out.println(piloto3.getForename());

            } catch (SQLException e1) {
                try {
                    conn.rollback();
                    System.err.println("Rollback realizado: " + e1.getMessage());
                } catch (SQLException e2) {
                    System.err.println("Error al hacer el rollback");
                }
            }
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ":-. " + e.getMessage());
        }

    }

    public static Piloto LeerPiloto(int paramID) {
        // Funciona

        String endpoint = "ejercicio7.cri6kii6mgv3.us-east-1.rds.amazonaws.com";
        String port = "5432";
        String usuario = "postgres";
        String passwd = "postgres";
        String urlCon = "jdbc:postgresql://" + endpoint + ":" + port + "/f12006";

        Piloto pilotoP = new Piloto();

        try (Connection con = DriverManager.getConnection(urlCon, usuario, passwd))  {
            // Preparamos una sentencia que lanzaremos a través del PreparedStatement
            String consultaSQL = "SELECT * FROM drivers WHERE driverid = ? ";

            PreparedStatement sentencia = con.prepareStatement(consultaSQL);

            // Metemos como primer parámetro que el driverID
            sentencia.setInt(1, paramID);

            // La consulta SELECT se ejecuta pasándola por el método executeQuery. Si la consulta devuelve datos,
            // estos estarán accesibles a través de un "conjunto de resultados" (ResultSet)
            ResultSet resultados = sentencia.executeQuery();


            while (resultados.next()) {
                pilotoP.setCode(resultados.getString("code"));
                pilotoP.setDob(resultados.getDate(String.valueOf(Date.valueOf("dob"))));
                pilotoP.setUrl(resultados.getString("url"));
                pilotoP.setSurname(resultados.getString("surname"));
                pilotoP.setForename(resultados.getString("forename"));
                pilotoP.setNationality(resultados.getString("nationality"));
            }
            // Finalmente, se deben cerrar las sentencias (cuando una sentencia se cierra, su objeto ResultSet también se cierra)
            sentencia.close();

            return pilotoP;
        } catch (SQLException e) {
            // Gestionar errores mediante excepciones
            System.err.println(e.getClass().getName() + "::: " + e.getMessage()) ;
            throw new RuntimeException(e);
        }
    }

}
