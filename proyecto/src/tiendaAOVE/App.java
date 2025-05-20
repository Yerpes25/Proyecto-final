package tiendaAOVE;

import java.util.ArrayList;
import java.util.Scanner;

public class App {

	static Scanner entrada = new Scanner(System.in);
	static ArrayList<Cliente> clientes = new ArrayList<>();

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		int opcion;
		do {
			menu();
			System.out.print("Seleccione una opcion: ");
			opcion = Integer.parseInt(entrada.nextLine());
			
			switch (opcion) {
			case 1:
				aniadirCliente();
				break;
			case 2:
				inicioSesion();
				break;
			case 3:
				System.out.println("Hasta pronto");
				;
				break;
			default:
				System.out.println("Opcion no valida.");
			}
			System.out.println("***************************\n");
		} while (opcion != 3);
	}

	private static void aniadirCliente() {
		System.out.println("Dime el nombre");
		String nombre = entrada.nextLine();
		
		System.out.println("Dime los apellidos");
		String apellidos = entrada.nextLine();
		
		System.out.println("Dime el dni");
		String dni = entrada.nextLine();
		
		System.out.println("Dime el telefono");
		String telefono = entrada.nextLine();
		
		System.out.println("Dime el email");
		String email= entrada.nextLine();
		
		if (buscarClientePorEmail(email) != null) {
			System.out.println("Ya existe un cliente con ese email.");
			return;
		}
		
		System.out.println("Dime la contraseña");
		String contrasenia = entrada.nextLine();
		
		Cliente c = new Cliente(email, contrasenia, nombre, apellidos, dni, telefono);
		clientes.add(c);
		System.out.println("Se ha añadido correctamente");
		
	}

	private static void menu() {
		System.out.println("************************");
		System.out.println("1.- Añadir nuevo cliente");
		System.out.println("2.- Iniciar sesion");
		System.out.println("3.- Salir");
		System.out.println("************************");
	}
	
	public static void inicioSesion() {
		System.out.print("Email: ");
		String email = entrada.nextLine();

		System.out.print("Contraseña: ");
		String contrasenia = entrada.nextLine();

		Cliente cliente = buscarClientePorEmail(email);

		if (cliente == null) {
			System.out.println("El email no esta registrado");
			return;
		}
		
		int codigoContra = contrasenia.hashCode();
		
		if (cliente.getContrasenia() == (codigoContra)) {
			System.out.println("Inicio de sesion correcto " + cliente.getNombre());
		} else {
			System.out.println("Contraseña incorrecta");
		}
	}
	
	private static Cliente buscarClientePorEmail(String email) {
		for (Cliente c : clientes) {
			if (c.getEmail().equalsIgnoreCase(email)) {
				return c;
			}
		}
		return null;
	}
	
}
