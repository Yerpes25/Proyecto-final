package tiendaAOVE;

public class Cliente {
	private String email;
	private int contrasenia;
	private String nombre;
	private String apellidos;
	private String dni;
	private String telefono;
	

	public Cliente(String email, String contrasenia, String nombre, String apellidos, String dni, String telefono) {
		super();
		try {
		int codigoHash = contrasenia.hashCode();
		setEmail(email);
		setContraseña(codigoHash);
		setNombre(nombre);
		setApellidos(apellidos);
		setDni(dni);
		setTelefono(telefono);
		}catch(Exception e) {
			System.out.println("Error.- " + e.getMessage());
		}
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
			if (email.isBlank() || email.isEmpty() || email == null) {
				System.out.println("No puede estar el email en blanco");
			}
			this.email = email;
	}

	public int getContrasenia() {
		return contrasenia;
	}

	public void setContraseña(int contrasenia) {
		this.contrasenia = contrasenia;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		try {
			if (nombre.isBlank() || nombre.isEmpty()) {
				System.out.println("El nombre no puede estar en blanco");
			}
		} catch (Exception n) {
			System.out.println("Error.- " + n.getMessage());
		}
		this.nombre = nombre;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		try {
			if (apellidos.isBlank() || apellidos.isEmpty()) {
				System.out.println("Los apellidos no puede estar en blanco");
			}
		} catch (Exception a) {
			System.out.println("Error.- " + a.getMessage());
		}
		this.apellidos = apellidos;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		if (dni.length() == 9) {
			int digitos = Integer.parseInt(dni.substring(0, 8));
			String letras = "TRWAGMYFPDXBNJZSQVHLGKE";
			int posicionLetras = digitos % 23;

			char letraCalc = letras.charAt(posicionLetras);
			if (letraCalc == dni.charAt(9)) {
				this.dni = dni;

			} else {
				System.out.println("DNI incorrecto");
				this.dni = "no válido";
			}

		} else {
			System.out.println("DNI introducido no válido");
			this.dni = "no válido";
		}
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		if (telefono.length() == 9) {
			boolean comprobacion = true;
			for (int i = 0; i < telefono.length(); i++) {
				if (!Character.isDigit(telefono.charAt(i))) {
					comprobacion = false;
					break;
				}
			}
			if (comprobacion) {
				this.telefono = telefono;
			} else {
				System.out.println("El teléfono debe contener solo numeros.");
			}
		} else {
			System.out.println("El teléfono debe tener exactamente 9 dígitos.");
		}
	}
	

}
