package org.example;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class OperacionesCuenta {
    static String ruta = "jdbc:postgresql://localhost:5432/cuentas";
    static String usuario = "postgres";
    static String contrasenya = "postgres";

    // crearCuenta(), que reciba un objeto Cuenta y lo añada a la base de datos.
    public static void CrearCuenta(Cuenta cuentaParam){
        System.out.println("EO");
        try(Connection con = DriverManager.getConnection(ruta, usuario, contrasenya)) {

            // Preparamos una sentencia para crear un pedido
            String insercionSQL = "INSERT INTO accounts (accountid, iban, balance, clientid) VALUES" +
                    "(?, ?, ?, ?)";

            PreparedStatement sentencia = con.prepareStatement(insercionSQL);

            sentencia.setInt(1, cuentaParam.getAccountid());
            sentencia.setString(2, cuentaParam.getIban());
            sentencia.setDouble(3, cuentaParam.getBalance());
            sentencia.setInt(4, cuentaParam.getClientid());

            sentencia.executeUpdate();

        }catch (SQLException e) {
            // Gestionar errores mediante excepciones
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.out.println("Error: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    // BorrarCuentaIban(), que reciba un iban y elimine la cuenta de la base de datos.
    public static void BorrarCuentaPorIban(String ibanParam) {

        try (Connection con = DriverManager.getConnection(ruta,usuario,contrasenya)) {

            String borradoSQL = "DELETE FROM accounts WHERE iban = ?";

            PreparedStatement borrado = con.prepareStatement(borradoSQL);

            borrado.setString(1, ibanParam);

            borrado.executeUpdate();

            borrado.close();
        } catch (SQLException e) {
            // Gestionar errores mediante excepciones
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    // BorrarCuentaID(), que reciba un ID y elimine la cuenta de la base de datos.
    public static void BorrarCuentaID(int idParam) {

        try (Connection con = DriverManager.getConnection(ruta,usuario,contrasenya)) {

            String borradoSQL = "DELETE FROM accounts WHERE accountid = ?";
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

    // borrar todas las cuentas de un Cuenta **PASANDO EL CLIENTID COMO PARAMETRO**

    public static void BorrarCuentasCliente(int idParam) {

        try (Connection con = DriverManager.getConnection(ruta,usuario,contrasenya)) {

            String borradoSQL = "DELETE FROM accounts WHERE clientid = ?";
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

    // actualizar cuenta

    public static void ActualizarCuenta(Cuenta cuentaParam) {

        System.out.println("Hago algo");
        try (Connection con = DriverManager.getConnection(ruta,usuario,contrasenya)){

            // (accountid, iban, balance, clientid)
            String update = "UPDATE accounts SET iban = ?, balance = ?, clientid = ? WHERE accountid = ?";

            PreparedStatement sentencia = con.prepareStatement(update);

            sentencia.setString(1, cuentaParam.getIban());
            sentencia.setDouble(2, cuentaParam.getBalance());
            sentencia.setInt(3, cuentaParam.getClientid());
            sentencia.setInt(4, cuentaParam.getAccountid());

            sentencia.executeUpdate();

        } catch (SQLException ex) {
            System.out.println("Error: " + ex.getMessage());
        }
    }

    // listar todas las cuentas y su información
    // retorna List<Cienta> y hace SOUT

    public static List<Cuenta> LeerCuentas() {
        // Funciona
        List<Cuenta> listaCuentas = new ArrayList<>();

        try (Connection con = DriverManager.getConnection(ruta, usuario, contrasenya)) {
            // Preparamos una sentencia que lanzaremos a través del PreparedStatement
            String consultaSQL = "SELECT * FROM accounts";
            PreparedStatement sentencia = con.prepareStatement(consultaSQL);

            // La consulta SELECT se ejecuta pasándola por el método executeQuery. Si la consulta devuelve datos,
            // estos estarán accesibles a través de un "conjunto de resultados" (ResultSet)
            ResultSet resultados = sentencia.executeQuery();

            while (resultados.next()) {
                Cuenta CuentaP = new Cuenta();

                CuentaP.setAccountid(resultados.getInt("accountid"));
                CuentaP.setClientid(resultados.getInt("clientid"));
                CuentaP.setIban(resultados.getString("iban"));
                CuentaP.setBalance(resultados.getDouble("balance"));

                System.out.println("IBAN: " +resultados.getString("iban") + ". "
                        +  "Balance: " + resultados.getDouble("balance") + "€ ");

                listaCuentas.add(CuentaP);
            }
            // Finalmente, se deben cerrar las sentencias (cuando una sentencia se cierra, su objeto ResultSet también se cierra)
            sentencia.close();

            return listaCuentas;
        } catch (SQLException e) {
            // Gestionar errores mediante excepciones
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public static void MostrarInfoCuenta(int idParam) {

        try (Connection con = DriverManager.getConnection(ruta, usuario, contrasenya)) {

            String consultaSQL = "SELECT * FROM accounts WHERE accountid = ?";
            PreparedStatement sentencia = con.prepareStatement(consultaSQL);
            sentencia.setInt(1, idParam);
            ResultSet resultados = sentencia.executeQuery();

            while (resultados.next()) {
                Cuenta CuentaP = new Cuenta();

                CuentaP.setAccountid(resultados.getInt("accountid"));
                CuentaP.setClientid(resultados.getInt("clientid"));
                CuentaP.setIban(resultados.getString("iban"));
                CuentaP.setBalance(resultados.getDouble("balance"));

                System.out.println("IBAN: " + resultados.getString("iban") + ". "
                        +  "Balance: " + resultados.getDouble("balance") + "€. "
                        + "ID Cuenta: " + resultados.getInt("accountid")+ ". "
                        + "ID Cliente: " + resultados.getInt("clientid")+ ". "
                );
            }

            sentencia.close();

        } catch (SQLException e) {
            // Gestionar errores mediante excepciones
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    // Retornar una cuenta según ID
    public static Cuenta ObtenerCuenta( int idParam) {
        // Funciona
        Cuenta CuentaP = new Cuenta();
        try (Connection con = DriverManager.getConnection(ruta, usuario, contrasenya)) {

            String consultaSQL = "SELECT * FROM accounts WHERE accountid = ?";
            PreparedStatement sentencia = con.prepareStatement(consultaSQL);
            sentencia.setInt(1, idParam);

            ResultSet resultados = sentencia.executeQuery();

            while (resultados.next()) {
                CuentaP.setAccountid(resultados.getInt("accountid"));
                CuentaP.setClientid(resultados.getInt("clientid"));
                CuentaP.setIban(resultados.getString("iban"));
                CuentaP.setBalance(resultados.getDouble("balance"));
            }
            // Finalmente, se deben cerrar las sentencias (cuando una sentencia se cierra, su objeto ResultSet también se cierra)
            sentencia.close();

            return CuentaP;
        } catch (SQLException e) {
            // Gestionar errores mediante excepciones
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public static void Transaccion(Cuenta origen, Cuenta destino, Double cantidad) {

        // NO FUNCIONA NI EL SELECT NI NADA
        try (Connection con = DriverManager.getConnection(ruta, usuario, contrasenya)) {
            try {
                con.setAutoCommit(false);


                // BALANCE CUENTA DE ORIGEN
                Double balanceO;

                String consultaSQL = "SELECT balance FROM accounts WHERE accountid = ?";
                PreparedStatement sentencia = con.prepareStatement(consultaSQL);
                sentencia.setInt(1, origen.getAccountid());

                ResultSet resultados = sentencia.executeQuery();

                balanceO = resultados.getDouble("balance");




                System.out.println(" Balance Origen " + balanceO);
                // Finaliza la transacción, confirmando los cambios en la base de datos
                con.commit();
                // Es buena práctica volver a activar la confirmación automática
                con.setAutoCommit(true);

            } catch (SQLException ex1) {
                System.err.println(ex1.getClass().getName() + ": " + ex1.getMessage());
                try {
                    // Deshacemos las operaciones realizadas en la base de datos
                    con.rollback();
                    System.err.println("ROLLBACK ejecutado");
                } catch (SQLException ex2) {
                    System.err.println("Error haciendo ROLLBACK");
                }
            }
        } catch (SQLException ex) {
            System.err.println(ex.getClass().getName() + ": " + ex.getMessage());
        }
    }
}
