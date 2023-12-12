package org.example;

import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

import static java.nio.file.Files.lines;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ColeccionProductos implements Serializable {
    final String COMMA_DELIMITER = ",";
    // List<Product> listaCSV = new ArrayList<>();

    List<Product> lista = new ArrayList<Product>();


    public ColeccionProductos(Path path) throws IOException {
        //Leer el fichero de funkos.csv y crear una lista de objetos main.Funko
        try (Stream<String> contenidoFichero3 = Files.lines(path);) {


            List<String> linea = Files.readAllLines(Path.of(".", "src", "main", "resources", "Ej03-LeerFichero.csv"));
            String[] fun;
           // System.out.println("Toda la info: ");

            for (int i = 1; i < linea.size(); i++) {
                Product productoo = new Product();
                fun = linea.get(i).split(",");
                productoo.setId(Integer.parseInt(fun[0]));
                productoo.setName(fun[1]);
                productoo.setSupplier(Integer.parseInt(fun[2]));
                productoo.setCategory(Integer.parseInt(fun[3]));
                productoo.setUnitPrice(Double.parseDouble(fun[5]));
                productoo.setUnitsInStock(Integer.parseInt(fun[6]));
                lista.add(productoo);
              //  System.out.println(productoo.toString());
            }


        } catch (
                IOException e) {
            e.printStackTrace(System.out);
        }
    }




}
