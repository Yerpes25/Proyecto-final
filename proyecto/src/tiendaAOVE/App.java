package tiendaAOVE;

import java.util.ArrayList;
import java.util.Scanner;

public class App {

	static Scanner entrada = new Scanner(System.in);
	static ArrayList<Cliente> clientes = new ArrayList<>();
	static ArrayList<Producto> productos = new ArrayList<>();
	static ArrayList<Pedido> pedidos = new ArrayList<>();
	static Cliente clienteAutenticado = null;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		precargarProductos();
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
				mostrarProductos();
				break;
			case 4:
				comprarProducto();
				break;
			case 5: 
				mostrarPedidos();
				break;
			case 6: 
				System.out.println("Has salido");
			default:
				System.out.println("Opcion no valida.");
			}
			System.out.println("***************************\n");
		} while (opcion != 6);
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
		System.out.println("3.- Mostrar productos");
		System.out.println("4.- Comprar producto");
		System.out.println("5.- Mostrar pedidos");
		System.out.println("6.- Salir");
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
			clienteAutenticado = cliente;
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
	
	private static void precargarProductos() {
		productos.add(new AceiteDeOliva("Aceite Virgen Extra", 10.0, 5, 0, TIPOACEITUNA.PICUAL, "Jaen"));
		productos.add(new ProductosCosmetica("Crema", 15.0, 10, 0, "Con aloe vera"));
		productos.add(new Accesorio("Botella de cristal", 8.0, 20, 0, "Vidrio"));
		productos.add(new AceiteAromatizado("Aceite", 12.0, 7, 0, "Romero", 3));
	}
	
	private static void mostrarProductos() {
		System.out.println("********** Productos disponibles **********");
		int i = 1;
		for (Producto p : productos) {
			System.out.println(i++ + ". " + p);
		}
	}

	private static void comprarProducto() {
		if (clienteAutenticado == null) {
			System.out.println("No hay ningun cliente autenticado.");
			return;
		}

		mostrarProductos();
		System.out.print("Seleccione el numero del que quieres comprar: ");
		int seleccion = Integer.parseInt(entrada.nextLine());

		if (seleccion < 1 || seleccion > productos.size()) {
			System.out.println("No puedes elegir esa seleccion");
			return;
		}

		Producto producto = productos.get(seleccion);

		if (producto.getStock() <= 0) {
			System.out.println("No hay stock disponible");
			return;
		}

		producto.setStock(producto.getStock() - 1);

		Pedido pedido = new Pedido(clienteAutenticado);
		pedido.agregarProducto(producto);
		pedidos.add(pedido);
		System.out.println("Producto comprado correctamente.");
	}

	private static void mostrarPedidos() {
		if (clienteAutenticado == null) {
			System.out.println("No hay ningun cliente autenticado.");
			return;
		}

		System.out.println("********** Tus pedidos **********");
		for (Pedido p : pedidos) {
			if (p.getCliente().equals(clienteAutenticado)) {
				System.out.println(p);
			}
		}
	}
}
