package org.example;

import java.nio.file.Path;
import java.sql.*;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // MEJORABLE: ACCEDER AL JSON MEDIANTE LECTURA DE FICHERO
        // no sé leer dirección en JSON :(
        Path ruta = Path.of(".", "src", "main", "resources", "clientes.json");


        Cliente cliente1 = new Cliente(2, "Ariadna", "Rico Rodríguez", "639659948", "adriana_santas", "kB2mV1eV", "39913984Z", "8gvmd684g@talk21.com", "España", Date.valueOf("1995-08-17"), "Avenida De España, 47", 24298, "Peñalba de Santiago", "León");

        //OperacionesCliente.crearCliente(cliente1);
        // OperacionesCliente.ActualizarCliente(cliente1);

        //OperacionesCliente.LeerClientes();

        //  OperacionesCliente.MostrarInfoCliente(1);
        // Cliente obtenido = OperacionesCliente.ObtenerCliente(1);

        //  System.out.println(obtenido.getNombre());
        Cuenta cuenta1 = new Cuenta(2, "ES2131908836405121554653", 200.00, 2);

        // OperacionesCuenta.crearCuenta(cuenta1);
        // OperacionesCuenta.BorrarCuentasCliente(cuenta1.getClientid());
        // OperacionesCuenta.ActualizarCuenta(cuenta1);

        //  OperacionesCuenta.LeerCuentas();
        OperacionesCuenta.MostrarInfoCuenta(3);
        OperacionesCuenta.MostrarInfoCuenta(4);

        Cuenta obtenida = OperacionesCuenta.ObtenerCuenta(3);
        Cuenta obtenida2 = OperacionesCuenta.ObtenerCuenta(4);
        System.out.println(obtenida.getBalance());

    }


}
