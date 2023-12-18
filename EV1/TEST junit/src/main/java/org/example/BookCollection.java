package org.example;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
public class BookCollection {
    private final List<Book> books;
    public BookCollection(Book[] books) {
        this.books = Arrays.asList(books);
    }
    public List<Book> find(String textToFind) {
        List<Book> foundBooks = new ArrayList<>();
        for(Book book : this.books){
            if(book.getISBN().equals(textToFind) ||
                    book.getTitle().contains(textToFind)){
                foundBooks.add(book);
            }
        }
        return foundBooks;
    }

    public List<Book> findCopies(Book bookToFind) {
        List<Book> foundBooks = new ArrayList<>();
        for (Book book : this.books) {
            if (book.getISBN().equals(bookToFind.getISBN())
                    && book.getTitle().equals(bookToFind.getTitle())
                    && book.getAuthor().equals(bookToFind.getAuthor())
            ) {
                foundBooks.add(book);
            }
        }
        return foundBooks;
    }

    public List<Book> findOrFail(String textToFind) throws
            CollectionIsEmpty, NoBooksFound {
        List<Book> foundBooks = new ArrayList<>();

        if(this.books.isEmpty()){
            throw new CollectionIsEmpty();
        }

        for(Book book : this.books){
            if(book.getISBN().equals(textToFind) ||
                    book.getTitle().contains(textToFind)){
                foundBooks.add(book);
            }
        }

        if(foundBooks.isEmpty()){
            throw new NoBooksFound();
        }

        /**/

        return foundBooks;
    }




}