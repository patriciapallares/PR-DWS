package collection;

import java.util.ArrayList;

public class BookCollection {

    ArrayList<Book> books;

    public BookCollection() {
        books = new ArrayList<>();
    }

    // todo: getters y setters??

    public void addBook(Book book) {
        books.add(book);
    }


    public int getNumberOfBooks() {
        return books.size();
    }

    public Book getBook(int index) {
        if (index >= 0 && index < books.size()) {
            return books.get(index);
        } else {
            return null;
        }
    }

    // métodos
    // Obtener la cantidad de libros con más de 500 páginas.
    public int masDeQuinientos() {
        int num = 0, pages;
        for (Book elemento : books) {
            pages = elemento.getPages();
            if (pages > 500) {
                num++;
            }
        }
        return num;
    }

    public int menosDeTrescientos() {
        int num = 0, pages;
        for (Book elemento : books) {
            pages = elemento.getPages();
            if (pages < 300) {
                num++;
            }
        }
        return num;
    }

    public void tituloMasDeQuinientos() {
        int pages;
        String title;
        for (Book elemento : books) {
            pages = elemento.getPages();
            title = elemento.getTitle();
            if (pages > 500) {
                System.out.println("-> " + title);
            }
        }
    }

    public int sumaPaginas() {
        int pages = 0;
        for (Book elemento : books) {
            pages += elemento.getPages();
        }
        return pages;
    }

    public void superaPromedio() {
        int promedio = sumaPaginas() / getNumberOfBooks();
        String title;
        int pages = 0;
        for (Book elemento : books) {
            title = elemento.getTitle();
            pages = elemento.getPages();
            if (pages > promedio) {
                System.out.println("-> " + title);
            }
        }
    }

    public void tituloLibroConMasPaginas(){
        String title;
        int pages = 0;
        int maxPages = 0;
        Book mayor = new Book("",0,"");

        for (Book elemento : books) {
            title = elemento.getTitle();
            pages = elemento.getPages();
            if (pages > maxPages) {
                mayor.setPages(pages);
                mayor.setTitle(title);
                maxPages = pages;
            }
        }
        System.out.println("El libro es: " + mayor.getTitle());
    }

}
