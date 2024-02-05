package org.example;

import org.example.ejercicios.Ejercicio7.entities.Constructor;
import org.example.ejercicios.Ejercicio7.entities.Piloto;
import java.sql.*;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        String endpoint = "ejercicio7.ctpxbpm90jra.us-east-1.rds.amazonaws.com";
        String port = "5432";
        String usuario = "postgres";
        String passwd = "12345678";
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
                System.out.println(leerPilotos());

                //Muestro las carrocerias
                System.out.println("constructors:");
                System.out.println(leerConstructor());

                //Preparación del insert del constructor, piloto1 y piloto2
                PreparedStatement insertConstructor = insertConstructor(constructor1, conn);
                PreparedStatement insertPilot1 = insertPilot(piloto1, conn);
                PreparedStatement insertPilot2 = insertPilot(piloto2, conn);


                //Ejecuto el insert del constructor y recojo el id generado
                insertConstructor.executeUpdate();
                ResultSet res = insertConstructor.getGeneratedKeys();
                res.next();

                //Ejecuto los inserts de los pilotos con el id del constructor generado
                //en el INSERT anterior, le añado en el ? número 6, que es el constructorId,
                // el id del constructor que acabo de crear

                insertPilot1.setInt(6, res.getInt(1));
                //insertPilot1.setInt(6, 1); //mclaren tiene el id1
                insertPilot1.executeUpdate();
                insertPilot1.close();

                insertPilot2.setInt(6, res.getInt(1));
                //insertPilot2.setInt(6, 1);
                insertPilot2.executeUpdate();
                insertPilot2.close();

                //Hasta que no termino de recoger los datos del ResultSet no cierro el Statemet del insert del constructor
                insertConstructor.close();


                //PASO 4

                //Preparo la llamada de un procedimiento almacenado que me devuelva los resultados de las carreras de un piloto
                CallableStatement resultsById = conn.prepareCall("{call get_results_by_driver(?)}");
                resultsById.setString(1, "ALO");
                resultsById.execute();
                ResultSet rs = resultsById.getResultSet();
                System.out.println("Results of driver ALO:");
                while (rs.next()) {
                    System.out.println(rs.getInt("round") + ".- " + rs.getString("circuit") + " | " + rs.getInt("result") + " | " + rs.getInt("points") + " | " + rs.getDate("date"));
                }

                //Preparo la llamada de un procedimiento almacenado que me devuelva la clasificación final de los pilotos por puntos
                CallableStatement finalClassification = conn.prepareCall("{call get_drivers_standings()}");
                finalClassification.execute();
                ResultSet rs2 = finalClassification.getResultSet();
                System.out.println("\nFinal classification:");
                while (rs2.next()) {
                    System.out.println(rs2.getString("driver") + " | " + rs2.getInt("points"));
                }

                conn.commit();
                conn.setAutoCommit(true);

            } catch (SQLException e1) {
                try {
                    conn.rollback();
                    System.err.println("Rollback realizado: " + e1.getMessage());
                } catch (SQLException e2) {
                    System.err.println("Error al hacer el rollback");
                }
            }
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }

    }


    private static PreparedStatement insertPilot(Piloto p, Connection conn) throws SQLException {
        String insertPilot = "INSERT INTO drivers (code, forename, surname, dob, nationality, constructorid) " +
                "VALUES (?, ?, ?, ?, ?, ?)";

        PreparedStatement insert = conn.prepareStatement(insertPilot);
        insert.setString(1, p.getCode());
        insert.setString(2, p.getForename());
        insert.setString(3, p.getSurname());
        insert.setDate(4, p.getDob());
        insert.setString(5, p.getNationality());

        return insert;
    }

    private static PreparedStatement insertConstructor(Constructor c, Connection conn) throws SQLException {
        String insertConstructor = "INSERT INTO constructors (constructorref, name, nationality) " +
                "VALUES (?, ?, ?)";

        PreparedStatement insert = conn.prepareStatement(insertConstructor, PreparedStatement.RETURN_GENERATED_KEYS);
        insert.setString(1, c.getConstructorref());
        insert.setString(2, c.getName());
        insert.setString(3, c.getNationality());

        return insert;
    }
    public static List<org.example.ejercicios.Ejercicio6JDBCySQLite.Piloto> leerPilotos() {
        String endpoint = "ejercicio7.ctpxbpm90jra.us-east-1.rds.amazonaws.com";
        String port = "5432";
        String usuario = "postgres";
        String passwd = "12345678";
        String urlCon = "jdbc:postgresql://" + endpoint + ":" + port + "/f12006";

        // Conexión a la base de datos
        List<org.example.ejercicios.Ejercicio6JDBCySQLite.Piloto> pilotos = new ArrayList<>();

        // Consulta de la tabla drivers
        try (Connection conexion = DriverManager.getConnection(urlCon, usuario, passwd)) {

            System.out.println("Conexión establecida");

            // Preparación de la consulta
            String sql = "SELECT * FROM drivers";

            // Ejecución de la consulta
            PreparedStatement select = conexion.prepareStatement(sql);

            // Resultados de la consulta
            ResultSet resultados = select.executeQuery();

            // Creación de la lista de pilotos
            while (resultados.next()){

                // Añadir piloto a la lista
                pilotos.add(new org.example.ejercicios.Ejercicio6JDBCySQLite.Piloto(
                        resultados.getInt("driverId"),
                        resultados.getString("code"),
                        resultados.getString("forename"),
                        resultados.getString("surname"),
                        resultados.getString("dob"),
                        resultados.getString("nationality"),
                        resultados.getInt("constructorId"),
                        resultados.getString("url")
                ));
            }

        } catch (Exception SQLException) {
            System.err.println("Error al leer los pilotos");
        }
        // Devolución de la lista de pilotos
        System.out.println("Pilotos creados");
        return pilotos;
    }

    public static List<Constructor> leerConstructor() {
        String endpoint = "ejercicio7.ctpxbpm90jra.us-east-1.rds.amazonaws.com";
        String port = "5432";
        String usuario = "postgres";
        String passwd = "12345678";
        String urlCon = "jdbc:postgresql://" + endpoint + ":" + port + "/f12006";

        // Conexión a la base de datos
        List<Constructor> constructors = new ArrayList<>();

        // Consulta de la tabla constructors
        try (Connection conexion = DriverManager.getConnection(urlCon, usuario, passwd)) {

            System.out.println("Conexión establecida");

            // Preparación de la consulta
            String sql = "SELECT * FROM constructors";

            // Ejecución de la consulta
            PreparedStatement select = conexion.prepareStatement(sql);

            // Resultados de la consulta
            ResultSet resultados = select.executeQuery();

            // Creación de la lista de pilotos
            while (resultados.next()){

                // Añadir piloto a la lista
                constructors.add(new Constructor(
                        resultados.getInt("constructorid"),
                        resultados.getString("constructorref"),
                        resultados.getString("name"),
                        resultados.getString("nationality"),
                        resultados.getString("url")
                ));
            }

        } catch (Exception SQLException) {
            System.err.println("Error al leer los constructors");
        }
        // Devolución de la lista de pilotos
        System.out.println("constuctors mostrados");
        return constructors;
    }
}
