import org.example.Book;
import org.example.BookCollection;
import org.example.CollectionIsEmpty;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.util.List;

public class FindTest {

    @Test
    public void shouldFindABookByISBN()
    {
        final String isbnToLocate = "un-isbn-2";
        BookCollection books = new BookCollection(new Book[]{
                new Book("un-isbn-1", "un titulo 1", "un autor 1"),
                new Book(isbnToLocate, "un titulo 2", "un autor 2"),
                new Book(isbnToLocate, "un titulo 2", "un autor 2"),
                new Book("un-isbn-3", "un titulo 3", "un autor 3"),
        });


        List<Book> foundBooks = books.find("un-isbn-2");
        // si no está vacío pasa el test.
        Assertions.assertFalse(foundBooks::isEmpty);
        //
        foundBooks.forEach((Book b)-> Assertions.assertEquals("un-isbn-2",
                b.getISBN()));
    }


}
