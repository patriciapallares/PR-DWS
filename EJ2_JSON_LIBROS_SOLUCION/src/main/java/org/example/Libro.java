package ejemploJakson;

public class Libro {
    private String titulo;
    private String autor;
    private int anyo;
    private String isbn;

    public Libro(String titulo, String autor, int anyo, String isbn) {
        this.titulo = titulo;
        this.autor = autor;
        this.anyo = anyo;
        this.isbn = isbn;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public int getAnyo() {
        return anyo;
    }

    public void setAnyo(int anyo) {
        this.anyo = anyo;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    @Override
    public String toString() {
        return "Título: " + titulo + "\n" +
                "Autor: " + autor + "\n" +
                "Año de Publicación: " + anyo + "\n" +
                "ISBN: " + isbn + "\n";
    }

}
