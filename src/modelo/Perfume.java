package modelo;

public class Perfume {

    private int id;
    private String nombre;
    private String marca;
    private double precio;
    private String codigo;
    private int cantidad;
    private Integer proveedorId;
    private String categoria;
    private String genero;
    private boolean disponible;
    private String descripcion;
    private String aroma;

    public Perfume() {
    }

    public Perfume(int id, String nombre, String marca, double precio,
                   String codigo, int cantidad, Integer proveedorId,
                   String categoria, String genero, boolean disponible,
                   String descripcion, String aroma) {

        this.id = id;
        this.nombre = nombre;
        this.marca = marca;
        this.precio = precio;
        this.codigo = codigo;
        this.cantidad = cantidad;
        this.proveedorId = proveedorId;
        this.categoria = categoria;
        this.genero = genero;
        this.disponible = disponible;
        this.descripcion = descripcion;
        this.aroma = aroma;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public Integer getProveedorId() {
        return proveedorId;
    }

    public void setProveedorId(Integer proveedorId) {
        this.proveedorId = proveedorId;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getAroma() {
        return aroma;
    }

    public void setAroma(String aroma) {
        this.aroma = aroma;
    }

    @Override
    public String toString() {
        return nombre;
    }
}