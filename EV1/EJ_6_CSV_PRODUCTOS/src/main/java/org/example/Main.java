package org.example;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Comparator;
import java.util.Optional;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) throws IOException {

        ColeccionProductos coleccionProductos = new ColeccionProductos(Path.of(".", "src", "main", "resources", "Ej03-LeerFichero.csv"));

        /*
        *
        map(): Transforma cada elemento del flujo utilizando la función proporcionada.
        filter(): Filtra elementos según un predicado dado.
        forEach(): Ejecuta una acción para cada elemento del flujo.
        collect(): Recolecta los elementos del flujo en una colección o valor particular.
        reduce(): Realiza una reducción de los elementos del flujo a un solo resultado aplicando una función asociativa.
        flatMap(): Aplica una función a cada elemento del flujo y luego aplana los resultados en un solo flujo.
        sorted(): Ordena los elementos del flujo según un comparador.
        distinct(): Elimina elementos duplicados del flujo.
        skip(): Omite un número específico de elementos desde el inicio del flujo.
        limit(): Limita el número de elementos en el flujo a un máximo dado.
        anyMatch(), allMatch(), noneMatch(): Métodos para verificar si algún elemento, todos los elementos o ninguno, respectivamente, cumplen con un predicado.
        * */

        // 1. Imprimir la lista de productos
        //coleccionProductos.getLista().stream().map(Product::toString);

        // 2. Imprimir los nombres de los productos
        //coleccionProductos.getLista().stream().map(Product::getName).forEach(System.out::println);

        // 3. Imprimir los nombres de los productos cuyas unidades en stock sean menor que 10
        //coleccionProductos.getLista().stream().filter(product -> product.getUnitsInStock() < 10).map(Product::getName).forEach(System.out::println);

        // 4. Imprimir el nombre de los productos con unidades en stock mayor de 10 ordenados por unidad de
        //stock de forma descendente
        /*
        coleccionProductos.getLista().stream()
                .filter(product -> product.getUnitsInStock() < 10)
                .sorted((p1, p2) -> Integer.compare(p2.getUnitsInStock(), p1.getUnitsInStock()))
                .forEach(Product::obtenerNombreYUnitsInStockVoid);
        */
        // 5. Imprimir el número de productos agrupados por proveedor

        // ChatGPT
        coleccionProductos.getLista().stream()
                .collect(Collectors.groupingBy(Product::getSupplier, Collectors.counting()))
                .forEach((provider, count) -> System.out.println("Proveedor: " + provider + ", Número de productos: " + count));


        // 6. Imprimir el producto con el precio unitario más alto

        // Encontrar el producto con el precio unitario más alto sin Optional
        Product productWithHighestPrice = coleccionProductos.getLista().stream()
                .max(Comparator.comparingDouble(Product::getUnitPrice))
                .orElse(null);

        // Imprimir el producto con el precio unitario más alto, si se encontró alguno
        if (productWithHighestPrice != null) {
            System.out.println("Producto con el precio más alto: " + productWithHighestPrice.getName() +
                    ", Precio: " + productWithHighestPrice.getUnitPrice());
        }

        // 7. Imprimir el promedio de existencias en almacén

        // Calcular el promedio de existencias en almacén
        double averageStock = coleccionProductos.getLista().stream()
                .mapToDouble(Product::getUnitsInStock)
                .average()
                .orElse(0.0); // Si la lista está vacía, el promedio será 0

        // Imprimir el promedio de existencias en almacén
        System.out.println("El promedio de existencias en almacén es: " + averageStock);

    }
}