package tiendaAOVE;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Pedido {
	
	Scanner entrada = new Scanner(System.in);
	
    private Cliente cliente;
    private HashMap<Producto, Integer> productos;
    private double total;
    private LocalDateTime fecha;

    public Pedido(Cliente cliente) {
        this.cliente = cliente;
        this.productos = new HashMap<>();
        this.total = 0.0;
        this.fecha = LocalDateTime.now();
    }

    public void agregarProducto(Producto producto, int cantidad) {
        if (producto.getStock() < cantidad) {
            System.out.println("Stock insuficiente para el producto: " + producto.getNombre());
            return;
            
        }
        
        double precioFinal = producto.getPrecio();
        
        if (producto instanceof AceiteAromatizado || producto instanceof ProductosCosmetica) {
            System.out.print("Mete el porcentaje de descuento para " + producto.getNombre());
            int descuento = Integer.parseInt(entrada.nextLine());
            
            if (descuento > 0 && descuento <= 100) {
                precioFinal = producto.getPrecio() * (1 - descuento / 100.0);
            }
        }

        if (productos.containsKey(producto)) { //containsKey para ver si esta en producto
            productos.put(producto, productos.get(producto) + cantidad); //put para añadirlo
        } else {
            productos.put(producto, cantidad);
        }

        producto.setStock(producto.getStock() - cantidad);
        total += precioFinal * cantidad;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public Map<Producto, Integer> getProductos() {
        return productos;
    }

    public double getTotal() {
        return total;
    }

    @Override
    public String toString() {
        return "Pedido [cliente=" + cliente.getEmail() + ", total=" + total + ", productos=" 
    + productos + "fecha= " + fecha + ", Precio final con porcentaje si lo tuviera" + total + "]\n";
    }
}
