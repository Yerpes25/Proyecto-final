package tiendaAOVE;

import java.sql.Date;
import java.util.ArrayList;

public class Pedido {
    private Cliente cliente;
    private ArrayList<Producto> productos;
    private double total;
    private Date fecha;

    public Pedido(Cliente cliente) {
        this.cliente = cliente;
        this.productos = new ArrayList<>();
        this.total = 0.0;
    }

    public void agregarProducto(Producto producto) {
        if (producto.getStock() <= 0) {
            System.out.println("No hay stock del producto: " + producto.getNombre());
            return;
        }
        productos.add(producto);
        for(int i = 0; i < productos.size(); i++) {
        	total = total + producto.getPrecio();
            producto.setStock(producto.getStock() - 1);
        }
    }

    public Cliente getCliente() {
        return cliente;
    }

    public ArrayList<Producto> getProductos() {
        return productos;
    }

    public double getTotal() {
        return total;
    }

    @Override
    public String toString() {
        return "Pedido [cliente=" + cliente.getEmail() + ", total=" + total + ", productos=" + productos + "]";
    }
}
