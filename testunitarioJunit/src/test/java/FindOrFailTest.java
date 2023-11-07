import org.example.*;

import org.example.Book;
import org.example.BookCollection;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

public class FindOrFailTest {

    @Test
    public void shouldGetExceptionWhenUsingFindOrFailWithANonExistentEntry() {
        final String isbnToLocate = "un-texto-que-no-existe-como-isbn-o-author";
        BookCollection books = new BookCollection(new Book[]{
                new Book("un-isbn-1", "un titulo 1", "un autor 1"),
                new Book("un-isbn-2", "un titulo 2", "un autor 2"),
                new Book("un-isbn-3", "un titulo 3", "un autor 3"),
        });

        Assertions.assertThrows(ExpectedToFindAtLeastABook.class, () ->
                books.findOrFail(isbnToLocate));
    }


}
