package tiendaAOVE;

public class AceiteDeOliva extends Producto{
	private TIPOACEITUNA tipoAceituna;
	private String procedencia;
	
	public AceiteDeOliva(String nombre, Double precio, int stock, int valoracion, TIPOACEITUNA tipoAceituna,
			String procedencia) {
		super(nombre, precio, stock, valoracion);
		setTipoAceituna(tipoAceituna);
		setProcedencia(procedencia);
	}

	public TIPOACEITUNA getTipoAceituna() {
		return tipoAceituna;
	}

	public void setTipoAceituna(TIPOACEITUNA tipoAceituna) {
		this.tipoAceituna = tipoAceituna;
	}

	public String getProcedencia() {
		return procedencia;
	}

	public void setProcedencia(String procedencia) {
		if(procedencia.isBlank() || procedencia.isEmpty()) {
			throw new IllegalArgumentException("No puede estar en blanco");
		}
		this.procedencia = procedencia;
	}

	@Override
	public String toString() {
		return "AceiteDeOliva [tipoAceituna=" + tipoAceituna + ", procedencia=" + procedencia + ", " + super.toString() + "]";
	}
	
	
}
