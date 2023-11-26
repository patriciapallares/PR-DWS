import org.example.Funko;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static org.example.Main.precioMedio;

public class precioMedioTest {

    List<Funko> listaFunkos = new ArrayList<>();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    String fecha = "2022-05-01";
    LocalDate fechaLocal = LocalDate.parse(fecha, formatter);

    Funko funko1 = new Funko("4b6c6f58-7c6b-434b-82ab-01b2d6e4434a", "Stitch","DISNEY",25.99, fechaLocal);
    Funko funko2 = new Funko("4b6c6f58-7c6b-434b-82ab-01b2d6e4434a", "Lilo","DISNEY",23.99,LocalDate.parse(fecha, formatter));
    Funko funko3 = new Funko("4b6c6f58-7c6b-434b-82ab-01b2d6e4434a", "Bart","DISNEY",15.99,LocalDate.parse(fecha, formatter));


    @Test
    // Test para comprobar que calcula la media correctamente
    public void debeDevolverMedia(){
        listaFunkos.add(funko1);
        listaFunkos.add(funko2);
        listaFunkos.add(funko3);

        double precioMedio = (funko1.getPrecio()+ funko2.getPrecio()+funko3.getPrecio())/3;
        double precioMedioMetodo = precioMedio(listaFunkos);

        Assertions.assertEquals(precioMedioMetodo, precioMedio);
    }

    @Test
    // Test para comprobar que devuelve un Double
    public void debeDevolverDouble(){
        listaFunkos.add(funko1);
        listaFunkos.add(funko2);
        listaFunkos.add(funko3);

        Double precioMedio = (funko1.getPrecio()+ funko2.getPrecio()+funko3.getPrecio())/3;
        Double precioMedioMetodo = precioMedio(listaFunkos);

        Assertions.assertTrue(precioMedioMetodo instanceof Double == precioMedio instanceof Double);
    }
}
