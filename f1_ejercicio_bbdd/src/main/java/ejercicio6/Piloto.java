package ejercicio6;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Piloto {
    String code;
    String forename;
    String surname;
    Date dob;
    String nationality;
}
