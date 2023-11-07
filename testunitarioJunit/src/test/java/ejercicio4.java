import org.example.Book;
import org.example.BookCollection;
import org.example.CollectionIsEmpty;
import org.example.NoBooksFound;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.util.List;

import static org.junit.Assert.assertTrue;

public class ejercicio4 {
    String isbnToLocate = "";
    BookCollection books = new BookCollection(new Book[]{
            new Book("un-isbn-1", "un titulo 1", "un autor 1"),
            new Book(isbnToLocate, "un titulo 2", "un autor 2"),
            new Book(isbnToLocate, "un titulo 2", "un autor 2"),
            new Book("un-isbn-3", "un titulo 3", "un autor 3"),
    });

    // Añade un caso de prueba shouldNotFindIfCollectionIsEmpty() que compruebe que si la colección
    // está vacía, la lista devuelta por la función find() estará vacía.

    @Test
    public void shouldNotFindIfCollectionIsEmpty(){

        BookCollection books = new BookCollection(new Book[]{});
        List<Book> foundBooks = books.find("un-isbn-2");
        Assertions.assertTrue(foundBooks::isEmpty);
        // versión de ana:
        assertTrue(foundBooks.isEmpty());

    }

    //Añade un caso de prueba shouldGetAnEmptyListIfNoMatchesAreFound() que compruebe que si el
    //parámetro pasado a la función find() no tiene coincidencias en la colección de libros, se devuelve
    //una lista vacía.

    @Test
    public void shouldGetAnEmptyListIfNoMatchesAreFound(){
        isbnToLocate = "ISBN";

        List<Book> foundBooks = books.find("un-isbn-2");
        Assertions.assertTrue(foundBooks::isEmpty);
    }


    //Crea el caso de prueba shouldGetExceptionWhenUsingFindOrFailWithAnEmptyCollection(). Este
    //caso comprobará que si se utiliza un método findOrFail() de la clase BookCollection, con una
    //colección vacía, lanzará una excepción de tipo CollectionIsEmpty. Comprueba que la prueba falla y,
    //entonces, implementa el método findOrFail() que valide esta prueba.

    @Test
    public void shouldGetExceptionWhenUsingFindOrFailWithAnEmptyCollection(){

        isbnToLocate = "un-texto-que-no-existe-como-isbn-o-author";
        BookCollection empty = new BookCollection(new Book[]{});

        Assertions.assertThrows(CollectionIsEmpty.class, () ->
                empty.findOrFail(isbnToLocate));

    }

    // Crea el caso de prueba shouldGetExceptionWhenUsingFindOrFailWithANonExistentEntry(). Este
    //caso comprobará que si el método findOrFail() no arroja ningún resultado en la búsqueda, lanzará
    //una excepción de tipo NoBooksFound. De nuevo, comprueba que la prueba falla y, entonces, rehaz
    //el método findOrFail() que valide esta prueba.


    @Test
    public void shouldGetExceptionWhenUsingFindOrFailWithANonExistentEntry(){

        // final String isbnToLocate = "un-isbn-2";
        isbnToLocate = "un-texto-que-no-existe-como-isbn-o-author";

        Assertions.assertThrows(NoBooksFound.class, () ->
                books.findOrFail(isbnToLocate));

    }




}
