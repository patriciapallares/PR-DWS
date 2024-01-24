package pruebas;

import java.sql.*;
import java.util.Scanner;

public class SQLF1 {
    public static void main(String[] args) throws SQLException {
        String usuario = "postgres";
        String contra = "postgres";
        Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/f12006-pg", usuario, contra);
        try {
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
                Date dob = pilEsp.getDate("dob");
                String nationality = pilEsp.getString("nationality");
                Integer constructorid = pilEsp.getInt("constructorid");
                System.out.println(driverid + ", " + code + ", " + forename
                        + " " + surname + ", " + dob + ", " + nationality + ", " + constructorid);
            }
            System.out.println();
            /*ResultSet procAlma = st.executeQuery("SELECT * FROM get_results_by_driver('ALO')");
            while (procAlma.next()) {
                Integer round = procAlma.getInt("round");
                String circuit = procAlma.getString("circuit");
                Integer result =  procAlma.getInt("result");
                Integer points =  procAlma.getInt("points");
                Date date =  procAlma.getDate("date");
                System.out.println(round + ", " + circuit + ", " + result + ", " + points + ", " + date);
            }*/
            CallableStatement resultadosPiloto = con.prepareCall("{call get_results_by_driver('ALO')}");
            resultadosPiloto.execute();
            pilEsp = resultadosPiloto.getResultSet();
            while (pilEsp.next()) {
                Integer round = pilEsp.getInt("round");
                String circuit = pilEsp.getString("circuit");
                Integer result =  pilEsp.getInt("result");
                Integer points =  pilEsp.getInt("points");
                System.out.println(round + ", " + circuit + ", " + result + ", " + points);
            }

            //st.executeUpdate("INSERT INTO drivers VALUES (4, 'ALO', 'prueba', 'prueba', '2003-5-16', 'prueba', 0, 'prueba')");
            //st.executeUpdate("INSERT INTO drivers VALUES (39, 'ANA', 'Ana', 'apellido', '2003-5-16', 'prueba', 0, 'prueba')");

        } catch (SQLException ex) {
            if (con!=null) {
                try {
                    con.rollback();
                    System.out.println("ROLLBACK EJECUTADO");
                } catch (SQLException e) {
                    System.out.println(e.toString());
                }
            }
        }
    }
}
