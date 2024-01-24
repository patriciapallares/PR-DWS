package org.example;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.text.DecimalFormat;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Cuenta {

    private int accountid;
    private String iban;
    private Double balance;
    private int clientid;

}
