package org.example;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Piloto {

    private int driverid;
    private String code;
    private String forename;
    private String surname;
    private String dob;
    private String nationality;
    private String url;


}
