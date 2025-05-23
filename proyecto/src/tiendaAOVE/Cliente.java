package tiendaAOVE;

import java.io.Serializable;
import java.util.Objects;

public class Cliente implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String email;
	private int contrasenia;
	private String nombre;
	private String apellidos;
	private String dni;
	private String telefono;
	

	public Cliente(String email, String contrasenia, String nombre, String apellidos, String dni, String telefono) {
		super();
		int codigoHash = contrasenia.hashCode();
		this.email = email;
		this.contrasenia = codigoHash;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.dni = dni;
		this.telefono = telefono;
	}
	
	

	public Cliente() {
		super();
	}



	public String getEmail() {
		return email;
	}

	public boolean setEmail(String email) {
			if (email.isBlank() || email.isEmpty() || email == null) {
				return false;
			}
			return true;
	}

	public int getContrasenia() {
		return contrasenia;
	}

	public void setContrasenia(int contrasenia) {
		this.contrasenia = contrasenia;
	}

	public String getNombre() {
		return nombre;
	}

	public boolean setNombre(String nombre) {
			if (nombre.isBlank() || nombre.isEmpty()) {
				return false;
			}
		return true;
	}

	public String getApellidos() {
		return apellidos;
	}

	public boolean setApellidos(String apellidos) {
			if (apellidos.isBlank() || apellidos.isEmpty()) {
				return false;
			}
		return true;
	}

	public String getDni() {
		return dni;
	}

	public boolean setDni(String dni) {
		if (dni.length() == 9) {
			int digitos = Integer.parseInt(dni.substring(0, 8));
			String letras = "TRWAGMYFPDXBNJZSQVHLGKE";
			int posicionLetras = digitos % 23;

			char letraCalc = letras.charAt(posicionLetras);
			if (letraCalc == dni.charAt(8)) {
				return true;

			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	public String getTelefono() {
		return telefono;
	}

	public boolean setTelefono(String telefono) {
		if (telefono.length() == 9) {
			boolean comprobacion = true;
			for (int i = 0; i < telefono.length(); i++) {
				if (!Character.isDigit(telefono.charAt(i))) {
					comprobacion = false;
					break;
				}
			}
			if (comprobacion) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	@Override
	public int hashCode() {
		return Objects.hash(contrasenia);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cliente other = (Cliente) obj;
		return contrasenia == other.contrasenia;
	}
	
	
}
