package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.nio.file.Path;
import java.sql.SQLException;
import java.sql.*;


public class OperacionesCRUDPilotos {



    // CrearPiloto(), que reciba un objeto Piloto y lo añada a la base de datos.
    public static void crearPiloto(Path ruta, Piloto pilotoParam) {
        try (Connection con = DriverManager.getConnection("jdbc:sqlite:" + ruta.toString())) {

            // Creamos ahora una sentencia de modificación, en este caso un INSERT
            // String insercionSQL = "INSERT or REPLACE INTO drivers (driverid, code, forename, surname, dob, nationality, url) VALUES (?, ?, ?, ?, ?, ?, ?)";
            String insercionSQL = "INSERT INTO drivers (code, forename, surname, dob, nationality, url) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement insercion = con.prepareStatement(insercionSQL);

            insercion.setString(1, pilotoParam.getCode());
            insercion.setString(2, pilotoParam.getForename());
            insercion.setString(3, pilotoParam.getSurname());
            insercion.setString(4, pilotoParam.getDob());
            insercion.setString(5, pilotoParam.getNationality());
            insercion.setString(6, pilotoParam.getUrl());

            System.out.println("He hecho algo?");
            // Finalmente, se deben cerrar las sentencias (cuando una sentencia se cierra, su objeto ResultSet también se cierra)
            insercion.close();
        } catch (SQLException e) {
            // Gestionar errores mediante excepciones
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    // LeerPiloto(), que reciba un entero y devuelva un objeto Piloto con la información del piloto
    // con el driverid coincidente.

    public static void LeerPiloto(Path ruta, int paramID) {
        try (Connection con = DriverManager.getConnection("jdbc:sqlite:" + ruta.toString())) {

            // Preparamos una sentencia que lanzaremos a través del PreparedStatement
            String consultaSQL = "SELECT driverid, code, forename, surname, strftime('%d/%m/%Y', dob) AS formatted_dob, nationality " +
                    "FROM drivers " +
                    "WHERE driverid = ? ";
            PreparedStatement consulta = con.prepareStatement(consultaSQL);
            // Metemos como primer parámetro que la nacionalidad sea Española
            consulta.setInt(1, paramID);

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
// Finalmente, se deben cerrar las sentencias (cuando una sentencia se cierra, su objeto ResultSet también se cierra)
            consulta.close();
        } catch (SQLException e) {
            // Gestionar errores mediante excepciones
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    // LeerPilotos(), que devuelva un listado completo de objetos Piloto.

    public static void LeerPilotos(Path ruta) {

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
            }// Finalmente, se deben cerrar las sentencias (cuando una sentencia se cierra, su objeto ResultSet también se cierra)
            consulta.close();
        } catch (SQLException e) {
            // Gestionar errores mediante excepciones
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            throw new RuntimeException(e);
        }

    }


    // ActualizarPiloto(), que reciba un objeto Piloto, un entero y actualice los datos del
    // registro coincidente en la base de datos con el mismo driverid.

    public static void ActualizarPiloto(Path ruta, Piloto pilotoParam, int paramID) {
        try (Connection con = DriverManager.getConnection("jdbc:sqlite:" + ruta.toString())) {

            // Definir la consulta SQL de actualización
            String sql = "UPDATE Pilotos SET code = ?, forename = ?, forename = ?, dob = ?, nationality = ?, url = ? WHERE driverid = ?";

            // Preparar la sentencia SQL con los datos del piloto
            try (PreparedStatement pstmt = con.prepareStatement(sql)) {
                pstmt.setString(1, pilotoParam.getCode());
                pstmt.setString(2, pilotoParam.getForename());
                pstmt.setString(3, pilotoParam.getSurname());
                pstmt.setString(4, pilotoParam.getDob());
                pstmt.setString(5, pilotoParam.getNationality());
                pstmt.setString(6, pilotoParam.getUrl());


                // Ejecutar la actualización
                pstmt.executeUpdate();
            }

            // Cerrar la conexión a la base de datos
            con.close();

            System.out.println("Piloto actualizado correctamente.");
        } catch (SQLException e) {
            System.err.println("Error al actualizar el piloto: " + e.getMessage());
        }
    }


    // BorrarPiloto(), que reciba un objeto Piloto y lo elimine de la base de datos.

    public static void BorrarPiloto(Path ruta, Piloto pilotoParam) {
        try (Connection con = DriverManager.getConnection("jdbc:sqlite:" + ruta.toString())) {

            // Creamos una nueva sentencia de modificación, en este caso un DELETE para borrar el piloto insertado
            String borradoSQL = "DELETE FROM drivers WHERE code = ?";
            PreparedStatement borrado = con.prepareStatement(borradoSQL);
            borrado.setString(1, pilotoParam.getCode());
            borrado.executeUpdate();

// Finalmente, se deben cerrar las sentencias (cuando una sentencia se cierra, su objeto ResultSet también se cierra)
            borrado.close();
        } catch (SQLException e) {
            // Gestionar errores mediante excepciones
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            throw new RuntimeException(e);
        }
    }


    // MostrarClasificacionPiloto(), que muestre la clasificación final del mundial
    // ordenada por puntos de los pilotos.




    // MostrarClasificacionConstructores(), que muestre la clasificación del mundial
    // ordenada por puntos de los equipos. Tanto en este método como en el anterior
    // tendrás que realizar consultas multitabla.

}



