package collection;


public class Collection {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        BookCollection bookCollection = new BookCollection();

        Book book1 = new Book("Programming", 750, "John John");
        Book book2 = new Book("Basics of Python", 200, "Jane S.");
        Book book3 = new Book("SQL Basics", 550, "J. Smith");
        Book book4 = new Book("HTML", 1000, "Patty P.");

        bookCollection.addBook(book1);
        bookCollection.addBook(book2);
        bookCollection.addBook(book3);
        bookCollection.addBook(book4);

        System.out.println("1. Cantidad de libros con más de 500 páginas: " + bookCollection.masDeQuinientos());

        System.out.println("2. Cantidad de libros con menos de 300 páginas: " + bookCollection.menosDeTrescientos());

        System.out.println("3. Título de todos aquellos libros con más de 500 páginas: ");
        bookCollection.tituloMasDeQuinientos();

        // System.out.println("4. Título de los 3 libros con mayor número de páginas.");

        System.out.println("5. Suma total de las páginas de todos los libros: " + bookCollection.sumaPaginas());

        System.out.println("6. Libros que superen el promedio en cuanto a número de páginas se refiere: ");
        bookCollection.superaPromedio();

        // System.out.println("7. Autores de todos los libros, sin repetir nombres de autores.");

        // System.out.println("8. Autores que tengan más de 1 libro listado.");
        
        System.out.println("9. Libro con mayor número de páginas: ");
        bookCollection.tituloLibroConMasPaginas();

        //System.out.println("10. Colección con todos los títulos de los libros."); ¿ARRAY DE LOS TÍTULOS SÓLO?


    }

}
