public class Producto {

    private String nombre;
    private int id;
    private double precio;

    public Producto(String nombre) {
        this.nombre = nombre;
    }

    public Producto(String nombre, int id, double precio) {
        this.nombre = nombre;
        this.id = id;
        this.precio = precio;
    }

    public String getNombre() {
        return nombre;
    }

    public int getId() {
        return id;
    }

    public double getPrecio() {
        return precio;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }
}
