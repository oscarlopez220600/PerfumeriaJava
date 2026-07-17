package modelo;

public class Venta {

    private int id;
    private int clienteId;
    private int perfumeId;
    private int cantidad;
    private double total;

    public Venta() {
    }

    public Venta(int id, int clienteId, int perfumeId, int cantidad, double total) {
        this.id = id;
        this.clienteId = clienteId;
        this.perfumeId = perfumeId;
        this.cantidad = cantidad;
        this.total = total;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getClienteId() {
        return clienteId;
    }

    public void setClienteId(int clienteId) {
        this.clienteId = clienteId;
    }

    public int getPerfumeId() {
        return perfumeId;
    }

    public void setPerfumeId(int perfumeId) {
        this.perfumeId = perfumeId;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
}