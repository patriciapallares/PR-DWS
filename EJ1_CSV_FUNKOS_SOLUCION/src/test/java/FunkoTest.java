// import ejercicios.ejercicio2.ColecciondeFunkos;
// import ejercicios.ejercicio2.Funko;
import org.junit.Test;
import org.example.Funko;
import org.example.ColecciondeFunkos;

import java.util.ArrayList;
import java.util.List;

import org.example.Funko;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertEquals;

public class FunkoTest {
    Funko funko1 = new Funko("1", "Funko1", "Modelo1", "10.50", "2023-01-01");
    Funko funko2 = new Funko("2", "Funko2", "Modelo2", "invalid", "2023-01-01");
    Funko funko3 = new Funko("3", "Funko3", "Modelo3", "15.75", "2023-01-01");

    List<Funko> listaFunkos = new ArrayList<>();


    @Test
    public void testConstructor() {
        String id = "1";
        String nombre = "Funko1";
        String modelo = "Modelo1";
        String precioString = "10.50";
        String fechaLanzamiento = "2023-01-01";

        assertEquals(id, funko1.getId());
        assertEquals(nombre, funko1.getNombre());
        assertEquals(modelo, funko1.getModelo());
        assertEquals(10.50, funko1.getPrecio());
        assertEquals(fechaLanzamiento, funko1.getFechaLanzamiento());
    }

    @Test
    public void testConstructorWithInvalidPrice() {
        String id = "2";
        String nombre = "Funko2";
        String modelo = "Modelo2";
        String precioString = "invalid";
        String fechaLanzamiento = "2023-01-01";


        assertEquals(id, funko2.getId());
        assertEquals(nombre, funko2.getNombre());
        assertEquals(modelo, funko2.getModelo());
        assertEquals(0.0, funko2.getPrecio()); // Precio debería ser 0.0 debido al valor inválido.
        assertEquals(fechaLanzamiento, funko2.getFechaLanzamiento());
    }

    @Test
    public void testToString() {
        String id = "3";
        String nombre = "Funko3";
        String modelo = "Modelo3";
        String precioString = "15.75";
        String fechaLanzamiento = "2023-01-01";


        String expectedToString = "main.Funko{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", modelo='" + modelo + '\'' +
                ", precio=" + 15.75 +
                ", fechaLanzamiento=" + fechaLanzamiento +
                '}';

        assertEquals(expectedToString, funko3.toString());
    }

    @Test
    public void testFunkoMasCaro() {
        listaFunkos.add(funko1);
        listaFunkos.add(funko2);
        listaFunkos.add(funko3);
        ColecciondeFunkos colFunk = new ColecciondeFunkos(listaFunkos);
        String expectedFunkoMasCaro = "El funko más caro es: Funko3 y cuesta 15.75€";
        assertEquals(expectedFunkoMasCaro, colFunk.imprimirFunkoMasCaro());

    }
}
