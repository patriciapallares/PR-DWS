import org.example.Book;
import org.example.BookCollection;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.util.List;

public class FindCopiesTest {
    @Test
    public void shouldFindCopiesOfABook() {
        final Book bookToBeFound = new Book("libro-que-debe-encontrase", "un titulo 1", "un autor 1");
        BookCollection books = new BookCollection(new Book[]{
                new Book("libro-que-debe-encontrase", "un titulo 1", "un autor 1"),
                new Book("libro-que-debe-encontrase", "un titulo 1", "un autor 1"),
                new Book("un-isbn-2", "un titulo 2", "un autor 2"),
                new Book("un-isbn-3", "un titulo 3", "un autor 3"),
        });
        List<Book> foundBooks = books.findCopies(bookToBeFound);

        Assertions.assertFalse(foundBooks.isEmpty());
        foundBooks.forEach((Book book) -> Assertions.assertEquals(book,
                bookToBeFound)
        );
    }

}
