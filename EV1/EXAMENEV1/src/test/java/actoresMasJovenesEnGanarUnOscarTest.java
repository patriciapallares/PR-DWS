import org.example.PeliculaOscarizada;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.util.ArrayList;
import java.util.List;

import static org.example.Utilidades.actoresMasJovenesEnGanarUnOscar;

public class actoresMasJovenesEnGanarUnOscarTest {


    PeliculaOscarizada peli1 = new PeliculaOscarizada("Peli 1", 2001, "Actor 1", 19, "H");
    PeliculaOscarizada peli2 = new PeliculaOscarizada("Peli 2", 2002, "Actor 2", 17, "M");
    PeliculaOscarizada peli3 = new PeliculaOscarizada("Peli 3", 2003, "Actor 3", 35, "H");
    PeliculaOscarizada peli4 = new PeliculaOscarizada("Peli 4", 2004, "Actor 4", 45, "M");

    List<PeliculaOscarizada> pelisOscarizadas = new ArrayList<>();
    @Test
    public void devuelveLosMasJovenes() {

        pelisOscarizadas.add(peli1);
        pelisOscarizadas.add(peli2);
        pelisOscarizadas.add(peli3);
        pelisOscarizadas.add(peli4);

        List<String> nombresTest = new ArrayList<>();

        nombresTest.add(peli1.getActor());
        nombresTest.add(peli2.getActor());

        List<String> nombresMétodo = actoresMasJovenesEnGanarUnOscar(pelisOscarizadas);

        Assertions.assertEquals(nombresTest,nombresMétodo);

    }
}
