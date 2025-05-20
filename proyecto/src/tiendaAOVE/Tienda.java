package tiendaAOVE;

import java.util.ArrayList;

public class Tienda {
    private ArrayList<Producto> productos;
    private ArrayList<Cliente> clientes;
    private ArrayList<Pedido> pedidos;

    public Tienda() {
        productos = new ArrayList<>();
        clientes = new ArrayList<>();
        pedidos = new ArrayList<>();
    }

    public void agregarProducto(Producto producto) {
        productos.add(producto);
    }

    public void registrarCliente(Cliente cliente) {
        clientes.add(cliente);
    }

    public void realizarPedido(Pedido pedido) {
        pedidos.add(pedido);
    }

    public ArrayList<Producto> getProductos() {
        return productos;
    }

    public ArrayList<Cliente> getClientes() {
        return clientes;
    }

    public ArrayList<Pedido> getPedidos() {
        return pedidos;
    }

    public void mostrarInventario() {
        System.out.println("Inventario:");
        for (Producto p : productos) {
            System.out.println(p);
        }
    }
}
