package org.example;

import java.nio.file.Path;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OperacionesCRUDNuevo {

    static String ruta = "jdbc:postgresql://localhost:5432/f12006-pg";
    static String usuario = "postgres";
    static String contrasenya = "postgres";

    // CrearPiloto(), que reciba un objeto Piloto y lo añada a la base de datos.
    public static void CrearPiloto(Piloto pilotoParam){
        // Funciona

        try(Connection con = DriverManager.getConnection(ruta, usuario, contrasenya)) {

            // Preparamos una sentencia para crear un pedido
            String insercionSQL = "INSERT INTO drivers (code, forename, surname, dob, nationality, url) VALUES (?, ?, ?, ?, ?, ?)";


            // Añadir el parámetro PreparedStatement.RETURN_GENERATED_KEYS nos permite recuperar las claves generadas
            PreparedStatement sentencia = con.prepareStatement(insercionSQL);

            sentencia.setString(1, pilotoParam.getCode());
            sentencia.setString(2, pilotoParam.getForename());
            sentencia.setString(3, pilotoParam.getSurname());
            sentencia.setDate(4, Date.valueOf(pilotoParam.getDob()));
            sentencia.setString(5, pilotoParam.getNationality());
            sentencia.setString(6, pilotoParam.getUrl());

            sentencia.executeUpdate();
        }catch (SQLException e) {
            // Gestionar errores mediante excepciones
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    // LeerPiloto(), que reciba un entero y devuelva un objeto Piloto con la información del piloto
    // con el driverid coincidente.


    public static Piloto LeerPiloto(int paramID) {
        // Funciona

        Piloto pilotoP = new Piloto();

        try (Connection con = DriverManager.getConnection(ruta, usuario, contrasenya)) {
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
                pilotoP.setDob(resultados.getString("dob"));
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
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    // LeerPilotos(), que devuelva un listado completo de objetos Piloto.
    public static List<Piloto> LeerPilotos() {
        // Funciona
        List<Piloto> listaPilotos = new ArrayList<>();

        try (Connection con = DriverManager.getConnection(ruta, usuario, contrasenya)) {
            // Preparamos una sentencia que lanzaremos a través del PreparedStatement
            String consultaSQL = "SELECT * FROM drivers";
            PreparedStatement sentencia = con.prepareStatement(consultaSQL);

            // La consulta SELECT se ejecuta pasándola por el método executeQuery. Si la consulta devuelve datos,
            // estos estarán accesibles a través de un "conjunto de resultados" (ResultSet)
            ResultSet resultados = sentencia.executeQuery();

            while (resultados.next()) {
                Piloto pilotoP = new Piloto();
                pilotoP.setCode(resultados.getString("code"));
                pilotoP.setDob(resultados.getString("dob"));
                pilotoP.setUrl(resultados.getString("url"));
                pilotoP.setSurname(resultados.getString("surname"));
                pilotoP.setForename(resultados.getString("forename"));
                pilotoP.setNationality(resultados.getString("nationality"));
                listaPilotos.add(pilotoP);
            }
            // Finalmente, se deben cerrar las sentencias (cuando una sentencia se cierra, su objeto ResultSet también se cierra)
            sentencia.close();

            return listaPilotos;
        } catch (SQLException e) {
            // Gestionar errores mediante excepciones
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    // ActualizarPiloto(), que reciba un objeto Piloto, un entero y actualice los datos del
    // registro coincidente en la base de datos con el mismo driverid.

    public static void ActualizarPiloto(Piloto pilotoParam, int paramID) {
        // ¿?¿?¿?
        try (Connection con = DriverManager.getConnection(ruta, usuario, contrasenya)) {

            // Definir la consulta SQL de actualización
            String sql = "UPDATE drivers SET code = ?, forename = ?, forename = ?, dob = ?, nationality = ?, url = ? WHERE driverid = ?";


            // Preparar la sentencia SQL con los datos del piloto
            try (PreparedStatement pstmt = con.prepareStatement(sql)) {
                pstmt.setString(1, pilotoParam.getCode());
                pstmt.setString(2, pilotoParam.getForename());
                pstmt.setString(3, pilotoParam.getSurname());
                pstmt.setString(4, pilotoParam.getDob());
                pstmt.setString(5, pilotoParam.getNationality());
                pstmt.setString(6, pilotoParam.getUrl());
                pstmt.setInt(6, paramID);

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

    public static void BorrarPiloto(Piloto pilotoParam) {
        // ¿?¿?¿?
        try (Connection con = DriverManager.getConnection(ruta,usuario,contrasenya)) {

            // Creamos una nueva sentencia de modificación, en este caso un DELETE para borrar el piloto insertado
            String borradoSQL = "DELETE FROM drivers WHERE code = ?";
            PreparedStatement borrado = con.prepareStatement(borradoSQL);
            borrado.setString(1, pilotoParam.getCode());
            borrado.executeUpdate();

            System.out.println("He borrado a alguien");

            // Finalmente, se deben cerrar las sentencias (cuando una sentencia se cierra, su objeto ResultSet también se cierra)
            borrado.close();
        } catch (SQLException e) {
            // Gestionar errores mediante excepciones
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
