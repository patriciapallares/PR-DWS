package pruebas;

import java.sql.*;

public class SQLEjemplo {
    public static void main(String[] args) {
        String sentenciaSQL = "SELECT * FROM actores";
        String insertSQL = "INSERT INTO actores VALUES (2, 'Bea', 17)";
        String deleteSQL = "DELETE FROM actores WHERE id = 2";
        String usuario = "postgres";
        String contra = "postgres";

        try (Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/mi-base", usuario, contra);
             PreparedStatement sentencia = con.prepareStatement(sentenciaSQL);
             PreparedStatement insert = con.prepareStatement(insertSQL);
             PreparedStatement delete = con.prepareStatement(deleteSQL)){

            System.out.println("SELECT");
            ResultSet resultados = sentencia.executeQuery();
            while (resultados.next()) {
                Integer id = resultados.getInt("id");
                String nombre = resultados.getString("nombre");
                Integer edad = resultados.getInt("edad");
                System.out.println("ID:"+ id + ", NOMBRE:" + nombre + ", EDAD:" + edad);
            }

            System.out.println("INSERT");
            insert.executeUpdate();

            ResultSet resultados2 = sentencia.executeQuery();
            while (resultados2.next()) {
                Integer id = resultados2.getInt("id");
                String nombre = resultados2.getString("nombre");
                Integer edad = resultados2.getInt("edad");
                System.out.println("ID:"+ id + ", NOMBRE:" + nombre + ", EDAD:" + edad);
            }

            System.out.println("DELETE");
            delete.executeUpdate();

            ResultSet resultados3 = sentencia.executeQuery();
            while (resultados3.next()) {
                Integer id = resultados3.getInt("id");
                String nombre = resultados3.getString("nombre");
                Integer edad = resultados3.getInt("edad");
                System.out.println("ID:"+ id + ", NOMBRE:" + nombre + ", EDAD:" + edad);
            }

        } catch (SQLException ex) {
            System.out.println();
        }
    }
}
