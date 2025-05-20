package tiendaAOVE;

public class AceiteAromatizado extends Producto{
	private String ingredienteAroma;
	private int intensidad;
	
	public AceiteAromatizado(String nombre, Double precio, int stock, int valoracion, String ingredienteAroma,
			int intensidad) {
		super(nombre, precio, stock, valoracion);
		setIngredienteAroma(ingredienteAroma);
		setIntensidad(intensidad);
	}

	public String getIngredienteAroma() {
		return ingredienteAroma;
	}

	public void setIngredienteAroma(String ingredienteAroma) {
		if(ingredienteAroma.isBlank() || ingredienteAroma.isEmpty()) {
			throw new IllegalArgumentException("No puede estar en blanco");
		}
		this.ingredienteAroma = ingredienteAroma;
	}

	public int getIntensidad() {
		return intensidad;
	}

	public void setIntensidad(int intensidad) {
		if(intensidad < 0 || intensidad > 5) {
			throw new IllegalArgumentException("Tiene que ser mayor de 0 y menor de 5");
		}
		this.intensidad = intensidad;
	}

	@Override
	public String toString() {
		return "AceiteAromatizado [ingredienteAroma=" + ingredienteAroma + ", intensidad=" + intensidad + ", " + super.toString() + "]";
	}
	
	
}
