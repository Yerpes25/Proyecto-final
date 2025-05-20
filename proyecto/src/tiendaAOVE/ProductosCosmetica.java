package tiendaAOVE;

public class ProductosCosmetica  extends Producto{
	private String descripcion;

	public ProductosCosmetica(String nombre, Double precio, int stock, int valoracion, String descripcion) {
		super(nombre, precio, stock, valoracion);
		setDescripcion(descripcion);
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		if(descripcion.isBlank() || descripcion.isEmpty()) {
			throw new IllegalArgumentException("No puede estar vacio");
		}
		this.descripcion = descripcion;
	}

	@Override
	public String toString() {
		return "ProductosCosmetica [descripcion=" + descripcion + ", " + super.toString() + "]";
	}
	
}
