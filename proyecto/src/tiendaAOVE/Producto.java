package tiendaAOVE;

import java.util.ArrayList;

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
		if(nombre.length() < 4 || nombre.length() > 20 || nombre.isBlank() || nombre.isEmpty()) {
			throw new IllegalArgumentException("No puede tener menos de 4 ni mas de 20 caracteres");
		}
		this.nombre = nombre;
	}

	public Double getPrecio() {
		return precio;
	}

	public void setPrecio(Double precio) {
		if(precio > 0) {
			throw new IllegalArgumentException("Tiene que ser mayor de 0");
		}
		this.precio = precio;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		if(stock > 0) {
			throw new IllegalArgumentException("Tiene que ser mayor de 0");
		}
		this.stock = stock;
	}

	public int getValoracion() {
		return valoracion;
	}

	public void setValoracion(int valoracion) {
		if(valoracion > -1 || valoracion < 11) {
			throw new IllegalArgumentException("Tiene que ser mayor de 0 y menor de 11");
		}
		this.valoracion = valoracion;
	}

	@Override
	public String toString() {
		return "Producto [nombre=" + nombre + ", precio=" + precio + ", stock=" + stock + ", valoraciones=" + valoraciones.toString() + "]";
	}
	
	
}
