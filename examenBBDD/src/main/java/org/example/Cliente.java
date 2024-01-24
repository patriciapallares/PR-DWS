package org.example;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data

public class Cliente {

    private int clientid;
    private String nombre;
    private String apellidos;
    private String telefono;
    private String usuario;
    private String contrasenya;
    private String dni;
    private String email;
    private String nacionalidad;
    private Date dob;
    private String calle;
    private int cp;
    private String municipio;
    private String provincia;
}
