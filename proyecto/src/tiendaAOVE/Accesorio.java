package tiendaAOVE;

public class Accesorio extends Producto{
	private String material;

	public Accesorio(String nombre, Double precio, int stock, int valoracion, String material) {
		super(nombre, precio, stock, valoracion);
		this.material = material;
	}

	public String getMaterial() {
		return material;
	}

	public void setMaterial(String material) {
		this.material = material;
	}

	@Override
	public String toString() {
		return "Accesorio [material=" + material + ", " + super.toString() + "]";
	}
	
	
}
