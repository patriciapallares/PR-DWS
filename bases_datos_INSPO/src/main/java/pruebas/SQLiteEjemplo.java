package pruebas;

import java.nio.file.Path;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Scanner;

public class SQLiteEjemplo {
    public static void main(String[] args) {
        Path ruta = Path.of("src", "main", "resources", "f12006sqlite.db");
        try (Connection con = DriverManager.getConnection("jdbc:sqlite:" + ruta.toString())) {
            con.setAutoCommit(false);
            Statement st = con.createStatement();
            /*ResultSet res = st.executeQuery("SELECT * FROM circuits");
            while (res.next()) {
                Integer circuitid = res.getInt("circuitid");
                String circuitref = res.getString("circuitref");
                String name = res.getString("name");
                String location = res.getString("location");
                String country = res.getString("country");
                Double lat = res.getDouble("lat");
                Double lng = res.getDouble("lng");
                Integer alt = res.getInt("alt");
                String url = res.getString("url");
                System.out.println(circuitid + "-" + circuitref + "-" + name
                + "-" + location + "-" + country + "-" + lat + "-" + lng + "-" + alt + "-" + url);
            }*/

            ResultSet pilEsp = st.executeQuery("SELECT * FROM drivers WHERE nationality='Spanish'");
            while (pilEsp.next()) {
                Integer driverid = pilEsp.getInt("driverid");
                String code = pilEsp.getString("code");
                String forename = pilEsp.getString("forename");
                String surname = pilEsp.getString("surname");
                String nationality = pilEsp.getString("nationality");
                Integer constructorid = pilEsp.getInt("constructorid");
                System.out.println(driverid + ", " + code + ", " + forename
                        + " " + surname +  ", " + nationality + ", " + constructorid);
            }
            System.out.println();

            /*CallableStatement resultadosPiloto = con.prepareCall("{call get_results_by_driver('ALO')}");
            resultadosPiloto.execute();
            pilEsp = resultadosPiloto.getResultSet();
            while (pilEsp.next()) {
                Integer round = pilEsp.getInt("round");
                String circuit = pilEsp.getString("circuit");
                Integer result =  pilEsp.getInt("result");
                Integer points =  pilEsp.getInt("points");
                System.out.println(round + ", " + circuit + ", " + result + ", " + points);
            }*/
            //st.executeUpdate("INSERT INTO drivers VALUES (4, 'ALO', 'prueba', 'prueba', '2003-5-16', 'prueba', 0, 'prueba')");
            //st.executeUpdate("INSERT INTO drivers VALUES (39, 'ANA', 'Ana', 'apellido', '2003-5-16', 'prueba', 0, 'prueba')");
        } catch (SQLException e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
    }
}
