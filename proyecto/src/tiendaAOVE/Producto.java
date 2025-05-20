package tiendaAOVE;

import java.util.ArrayList;
import java.util.List;

public abstract class Producto {
	private String nombre;
	private Double precio;
	private int stock;
	private int valoracion;
	private ArrayList<Producto> valoraciones = new ArrayList<>();
	
	public Producto(String nombre, Double precio, int stock, int valoracion) {
		super();
		this.nombre = nombre;
		this.precio = precio;
		this.stock = stock;
		this.valoracion = valoracion;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Double getPrecio() {
		return precio;
	}

	public void setPrecio(Double precio) {
		this.precio = precio;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public int getValoracion() {
		return valoracion;
	}

	public void setValoracion(int valoracion) {
		this.valoracion = valoracion;
	}

	@Override
	public String toString() {
		return "Producto [nombre=" + nombre + ", precio=" + precio + ", stock=" + stock + ", valoraciones=" + valoraciones.toString() + "]";
	}
	
	
}
