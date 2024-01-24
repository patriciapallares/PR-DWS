package org.example;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OperacionesCliente {
    static String ruta = "jdbc:postgresql://localhost:5432/cuentas";
    static String usuario = "postgres";
    static String contrasenya = "postgres";

    // crearCliente(), que reciba un objeto Cliente y lo añada a la base de datos.
    public static void CrearCliente(Cliente ClienteParam) {
        // Funciona

        try (Connection con = DriverManager.getConnection(ruta, usuario, contrasenya)) {

            // Preparamos una sentencia para crear un Cliente
            String insercionSQL = "INSERT INTO clients (clientid, dni, nombre, apellidos, telefono, usuario, contrasenya, " +
                    "email, nacionalidad, dob, calle, municipio, provincia, cp) VALUES" +
                    "(?, ?, ?, ?, ?, ?, ?, " +
                    " ?, ?, ?, ?, ?, ?, ?);";

            PreparedStatement sentencia = con.prepareStatement(insercionSQL);

            sentencia.setInt(1, ClienteParam.getClientid());
            sentencia.setString(2, ClienteParam.getDni());
            sentencia.setString(3, ClienteParam.getNombre());
            sentencia.setString(4, ClienteParam.getApellidos());
            sentencia.setString(5, ClienteParam.getTelefono());
            sentencia.setString(6, ClienteParam.getUsuario());
            sentencia.setString(7, ClienteParam.getContrasenya());
            sentencia.setString(8, ClienteParam.getEmail());
            sentencia.setString(9, ClienteParam.getNacionalidad());
            sentencia.setDate(10, Date.valueOf(String.valueOf(ClienteParam.getDob())));
            sentencia.setString(11, ClienteParam.getCalle());
            sentencia.setString(12, ClienteParam.getMunicipio());
            sentencia.setString(13, ClienteParam.getProvincia());
            sentencia.setInt(14, ClienteParam.getCp());

            sentencia.executeUpdate();
        } catch (SQLException e) {
            // Gestionar errores mediante excepciones
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    // BorrarClienteID(), que reciba un ID y elimine el Cliente de la base de datos.
    public static void BorrarClienteID(int idParam) {

        try (Connection con = DriverManager.getConnection(ruta, usuario, contrasenya)) {

            String borradoSQL = "DELETE FROM clients WHERE clientid = ?";
            PreparedStatement borrado = con.prepareStatement(borradoSQL);
            borrado.setInt(1, idParam);
            borrado.executeUpdate();

            borrado.close();
        } catch (SQLException e) {
            // Gestionar errores mediante excepciones
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    // BorrarCliente(), que reciba un cliente y elimine el Cliente de la base de datos.
    public static void BorrarCliente(Cliente clienteParam) {

        try (Connection con = DriverManager.getConnection(ruta, usuario, contrasenya)) {

            String borradoSQL = "DELETE FROM clients WHERE clientid = ?";
            PreparedStatement borrado = con.prepareStatement(borradoSQL);
            borrado.setInt(1, clienteParam.getClientid());
            borrado.executeUpdate();

            borrado.close();
        } catch (SQLException e) {
            // Gestionar errores mediante excepciones
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    // actualizar cliente

    public static void ActualizarCliente(Cliente ClienteParam) {

        System.out.println("Hago algo");
        try (Connection con = DriverManager.getConnection(ruta, usuario, contrasenya)) {

            // (accountid, iban, balance, clientid)
            String update = "UPDATE clients SET dni = ?, nombre = ?, apellidos = ?, telefono = ?, usuario = ?, contrasenya = ?, " +
                    "email = ?, nacionalidad = ?, dob = ?, calle = ?, municipio = ?, provincia = ?, cp = ? WHERE clientid = ?";

            PreparedStatement sentencia = con.prepareStatement(update);


            sentencia.setString(1, ClienteParam.getDni());
            sentencia.setString(2, ClienteParam.getNombre());
            sentencia.setString(3, ClienteParam.getApellidos());
            sentencia.setString(4, ClienteParam.getTelefono());
            sentencia.setString(5, ClienteParam.getUsuario());
            sentencia.setString(6, ClienteParam.getContrasenya());
            sentencia.setString(7, ClienteParam.getEmail());
            sentencia.setString(8, ClienteParam.getNacionalidad());
            sentencia.setDate(9, Date.valueOf(String.valueOf(ClienteParam.getDob())));
            sentencia.setString(10, ClienteParam.getCalle());
            sentencia.setString(11, ClienteParam.getMunicipio());
            sentencia.setString(12, ClienteParam.getProvincia());
            sentencia.setInt(13, ClienteParam.getCp());
            sentencia.setInt(14, ClienteParam.getClientid());

            sentencia.executeUpdate();

        } catch (SQLException ex) {
            System.out.println("Error: " + ex.getMessage());
        }
    }

    // listar todos los clientes y su información
    // retorna List<Cliente> y hace SOUT

    public static List<Cliente> LeerClientes() {
        // Funciona
        List<Cliente> listaClientes = new ArrayList<>();

        try (Connection con = DriverManager.getConnection(ruta, usuario, contrasenya)) {
            // Preparamos una sentencia que lanzaremos a través del PreparedStatement
            String consultaSQL = "SELECT * FROM clients";
            PreparedStatement sentencia = con.prepareStatement(consultaSQL);

            // La consulta SELECT se ejecuta pasándola por el método executeQuery. Si la consulta devuelve datos,
            // estos estarán accesibles a través de un "conjunto de resultados" (ResultSet)
            ResultSet resultados = sentencia.executeQuery();

            while (resultados.next()) {
                Cliente ClienteP = new Cliente();

                ClienteP.setCp(resultados.getInt("cp"));
                ClienteP.setDni(resultados.getString("dni"));
                ClienteP.setApellidos(resultados.getString("apellidos"));
                ClienteP.setCalle(resultados.getString("calle"));
                ClienteP.setDob(resultados.getDate("dob"));
                ClienteP.setContrasenya(resultados.getString("contrasenya"));
                ClienteP.setEmail(resultados.getString("email"));
                ClienteP.setClientid(resultados.getInt("clientid"));
                ClienteP.setNacionalidad(resultados.getString("nacionalidad"));
                ClienteP.setNombre(resultados.getString("nombre"));
                ClienteP.setMunicipio(resultados.getString("municipio"));
                ClienteP.setProvincia(resultados.getString("provincia"));
                ClienteP.setTelefono(resultados.getString("telefono"));
                ClienteP.setUsuario(resultados.getString("usuario"));

                System.out.println(
                        "Nombre: " + resultados.getString("nombre") + ". " +
                                "Usuario: " + resultados.getString("usuario") + ". "

                );

                listaClientes.add(ClienteP);
            }
            // Finalmente, se deben cerrar las sentencias (cuando una sentencia se cierra, su objeto ResultSet también se cierra)
            sentencia.close();

            return listaClientes;
        } catch (SQLException e) {
            // Gestionar errores mediante excepciones
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    // mostrar un cliente dado un ID

    public static void MostrarInfoCliente(int idParam) {

        try (Connection con = DriverManager.getConnection(ruta, usuario, contrasenya)) {

            String consultaSQL = "SELECT * FROM clients WHERE clientid = ?";
            PreparedStatement sentencia = con.prepareStatement(consultaSQL);
            sentencia.setInt(1, idParam);
            ResultSet resultados = sentencia.executeQuery();

            while (resultados.next()) {
                Cliente ClienteP = new Cliente();

                ClienteP.setCp(resultados.getInt("cp"));
                ClienteP.setDni(resultados.getString("dni"));
                ClienteP.setApellidos(resultados.getString("apellidos"));
                ClienteP.setCalle(resultados.getString("calle"));
                ClienteP.setDob(resultados.getDate("dob"));
                ClienteP.setContrasenya(resultados.getString("contrasenya"));
                ClienteP.setEmail(resultados.getString("email"));
                ClienteP.setClientid(resultados.getInt("clientid"));
                ClienteP.setNacionalidad(resultados.getString("nacionalidad"));
                ClienteP.setNombre(resultados.getString("nombre"));
                ClienteP.setMunicipio(resultados.getString("municipio"));
                ClienteP.setProvincia(resultados.getString("provincia"));
                ClienteP.setTelefono(resultados.getString("telefono"));
                ClienteP.setUsuario(resultados.getString("usuario"));

                System.out.println(
                        "Clientid " + resultados.getInt("clientid") + ". " +
                                "Nombre: " + resultados.getString("nombre") + ". " +
                                "Apellidos: " + resultados.getString("apellidos") + ". " +
                                "Teléfono: " + resultados.getString("telefono") + ". " +
                                "Usuario: " + resultados.getString("usuario") + ". " +
                                "Contraseña: " + resultados.getString("contrasenya") + ". " +
                                "DNI: : " + resultados.getString("dni") + ". " +
                                "Email: : " + resultados.getString("email") + ". " +
                                "Nacionalidad: : " + resultados.getString("nacionalidad") + ". " +
                                "Fecha de nacimiento: : " + resultados.getString("dob") + ". "
                );

            }

            sentencia.close();

        } catch (SQLException e) {
            // Gestionar errores mediante excepciones
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public static Cliente ObtenerCliente(int idParam) {
        Cliente ClienteP = new Cliente();

        try (Connection con = DriverManager.getConnection(ruta, usuario, contrasenya)) {

            String consultaSQL = "SELECT * FROM clients WHERE clientid = ?";
            PreparedStatement sentencia = con.prepareStatement(consultaSQL);
            sentencia.setInt(1, idParam);

            ResultSet resultados = sentencia.executeQuery();

            while (resultados.next()) {

                ClienteP.setCp(resultados.getInt("cp"));
                ClienteP.setDni(resultados.getString("dni"));
                ClienteP.setApellidos(resultados.getString("apellidos"));
                ClienteP.setCalle(resultados.getString("calle"));
                ClienteP.setDob(resultados.getDate("dob"));
                ClienteP.setContrasenya(resultados.getString("contrasenya"));
                ClienteP.setEmail(resultados.getString("email"));
                ClienteP.setClientid(resultados.getInt("clientid"));
                ClienteP.setNacionalidad(resultados.getString("nacionalidad"));
                ClienteP.setNombre(resultados.getString("nombre"));
                ClienteP.setMunicipio(resultados.getString("municipio"));
                ClienteP.setProvincia(resultados.getString("provincia"));
                ClienteP.setTelefono(resultados.getString("telefono"));
                ClienteP.setUsuario(resultados.getString("usuario"));

            }
            // Finalmente, se deben cerrar las sentencias (cuando una sentencia se cierra, su objeto ResultSet también se cierra)
            sentencia.close();

            return ClienteP;
        } catch (SQLException e) {
            // Gestionar errores mediante excepciones
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
