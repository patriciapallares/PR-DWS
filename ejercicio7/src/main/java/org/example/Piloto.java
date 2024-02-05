package org.example;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.sql.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Piloto {
    private String code;
    private String forename;
    private String surname;
    private Date dob;
    private String nationality;
    private int constructorid;

}
