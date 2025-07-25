package tiendaAOVE;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class App {

	static Scanner entrada = new Scanner(System.in);
	static ArrayList<Cliente> clientes = new ArrayList<>(); // Este e usado arraylist para guardarlo
	static ArrayList<Producto> productos = new ArrayList<>(); // Este tambien
	static ArrayList<Pedido> pedidos = new ArrayList<>(); // Este tambien
	static Cliente clienteAutenticado = null;

	public static void main(String[] args) throws IOException, ClassNotFoundException {
		// TODO Auto-generated method stub
		precargarProductos();
		Cliente c = new Cliente("administrador", "admin", "admin", "admin", "26538505R", "123123123");
		clientes.add(c);
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
				valorarProductos();
				break;
			case 6:
				mostrarPedidos();
				break;
			case 7:
				mostrarEstadisticas();
				break;
			case 8:
				sacarDatos();
				break;
			case 9:
				meterDatos();
				break;
			case 10:
				cerrarSesion();
				break;
			case 11:
				System.out.println("Has salido de la aplicacion");
			default:
				System.out.println("Opcion no valida.");
			}
			System.out.println("***************************\n");
		} while (opcion != 11);
	}

	private static void menu() {
		System.out.println("************************");
		System.out.println("1.- Añadir nuevo cliente");
		System.out.println("2.- Iniciar sesion");
		System.out.println("3.- Mostrar productos");
		System.out.println("4.- Comprar producto");
		System.out.println("5.- Añadir valoraciones a los productos");
		System.out.println("6.- Mostrar pedidos");
		System.out.println("7.- Mostrar estadisticas");
		System.out.println("8.- Guardar Datos en archivo");
		System.out.println("9.- Meter datos en archivo .ser");
		System.out.println("10.- Cerrar sesion");
		System.out.println("Salir de la aplicacion");
		System.out.println("************************");
	}

	private static void cerrarSesion() {
		System.out.println("Has cerrado sesion");
		clienteAutenticado = null;
	}

	private static void aniadirCliente() {
		try {
			if (clienteAutenticado == null) {
				Cliente c = new Cliente();
				System.out.println("Dime el nombre");
				String nombre = entrada.nextLine();
				if (c.setNombre(nombre) == false)
					throw new IllegalArgumentException("El nombre no puede estar en blanco");

				System.out.println("Dime los apellidos");
				String apellidos = entrada.nextLine();
				if (c.setApellidos(apellidos) == false)
					throw new IllegalArgumentException("Los apellidos no puede estar en blanco");

				System.out.println("Dime el dni");
				String dni = entrada.nextLine();
				if (c.setDni(dni) == false)
					throw new IllegalArgumentException("El dni no es válido");

				System.out.println("Dime el telefono");
				String telefono = entrada.nextLine();
				if (c.setTelefono(telefono) == false)
					throw new IllegalArgumentException("El telefono no es valido");

				System.out.println("Dime el email");
				String email = entrada.nextLine();
				if (c.setEmail(email) == false)
					throw new IllegalArgumentException("El email no puede estar en blanco");

				if (buscarClientePorEmail(email) != null) {
					System.out.println("Ya existe un cliente con ese email.");
					return;
				}

				System.out.println("Dime la contraseña");
				String contrasenia = entrada.nextLine();
				if (contrasenia.isBlank() || contrasenia.isEmpty())
					throw new IllegalArgumentException("El apellido no puede estar en blanco");

				Cliente c1 = new Cliente(email, contrasenia, nombre, apellidos, dni, telefono);
				clientes.add(c1);
				System.out.println("Se ha añadido correctamente");

			} else {
				System.out.println("Ya has inciado sesionº");
			}
		} catch (IllegalArgumentException IAE) {
			System.out.println("Error.- " + IAE.getMessage());
		}

	}

	public static void inicioSesion() {
		if (clienteAutenticado == null) {
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
		} else {
			System.out.println("Ya has inciado sesionº");
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
		if (!(clienteAutenticado == null || !clienteAutenticado.getEmail().equals("administrador"))) {
			System.out.println("Acceso denegado, es solo para administradores.");
			return;
		}

		Pedido pedido = new Pedido(clienteAutenticado);

		while (true) {
			mostrarProductos();
			System.out.print("Selecciona el numero del producto que quieres comprar \n");
			System.out.println("Si quieres terminar pulsa 0");
			int seleccion = Integer.parseInt(entrada.nextLine());

			if (seleccion == 0) {
				break;
			}

			if (seleccion < 1 || seleccion > productos.size()) {
				System.out.println("No puedes elegir esa seleccion");
			}

			Producto producto = productos.get(seleccion - 1);
			System.out.print("¿Cuantas unidades quieres de " + producto.getNombre() + "?: ");
			int cantidad = Integer.parseInt(entrada.nextLine());

			if (cantidad <= 0) {
				System.out.println("No se puede esa cantidad");
			}

			if (producto.getStock() < cantidad) {
				System.out.println("Stock insuficiente ya que hay " + producto.getStock());
			}

			pedido.agregarProducto(producto, cantidad);
			System.out.println("Producto añadido al pedido.");
		}

		if (pedido.getProductos().isEmpty()) {
			System.out.println("No se ha añadido ningun producto, el pedido no se ha podido hacer");
		} else {
			pedidos.add(pedido);
			System.out.println("Pedido completado");
			System.out.println(pedido.toString());
		}
	}

	private static void mostrarEstadisticas() {
		if (clienteAutenticado == null || !clienteAutenticado.getEmail().equals("administrador")) {
			System.out.println("Acceso denegado, es solo para administradores.");
			return;
		}

		if (productos.isEmpty()) {
			System.out.println("No hay productos para mostrar estadisticas.");
			return;
		}

		Producto masCaro = productos.get(0);
		Producto masBarato = productos.get(0);

		for (Producto p : productos) {
			if (p.getPrecio() > masCaro.getPrecio()) {
				masCaro = p;
			}
		}

		for (Producto p : productos) {
			if (p.getPrecio() < masBarato.getPrecio()) {
				masBarato = p;
			}
		}
		System.out.println("El producto mas caro es " + masCaro + "\n");
		System.out.println("El mas barato es " + masBarato + "\n");

		Collections.sort(productos, new ComparadorMedia());
		System.out.println("Producto con la mejor media: " + productos.get(productos.size() - 1).getNombre());
		System.out.println("Producto con la peor media: " + productos.get(0));
	}

	private static void valorarProductos() {
		if (clienteAutenticado == null) {
			System.out.println("Debes iniciar sesión para valorar productos.");
			return;
		}

		System.out.println("**** Productos que has comprado ****");
		ArrayList<Producto> productosComprados = new ArrayList<>();

		for (Pedido p : pedidos) {
			if (p.getCliente().equals(clienteAutenticado)) {
				productosComprados.addAll(p.getProductos().keySet());
			}
		}

		if (productosComprados.isEmpty()) {
			System.out.println("No has comprado todavia productos");
			return;
		}

		for (int i = 0; i < productosComprados.size(); i++) {
			System.out.println((i + 1) + ". " + productosComprados.get(i).getNombre());
		}

		System.out.print("Selecciona un producto que quieres valorar (0 si quieres cancelar): ");
		int seleccion = Integer.parseInt(entrada.nextLine());

		if (seleccion == 0) {
			return;
		}

		if (seleccion < 1 || seleccion > productosComprados.size()) {
			System.out.println("Esa seleccion no se puede");
			return;
		}

		Producto producto = productosComprados.get(seleccion - 1);

		System.out.print("Ingresa tu valoracion del 0 al 10 para " + producto.getNombre() + ": ");
		int valoracion = Integer.parseInt(entrada.nextLine());

		try {
			producto.addValoracion(clienteAutenticado, valoracion);
			System.out.println("Valoracion añadida");
		} catch (IllegalArgumentException e) {
			System.out.println("Error: " + e.getMessage());
		}
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

	private static void sacarDatos() throws IOException {
		try {
			System.out.println("Dime el nombre que quieres para el archivo");
			String nombre = entrada.nextLine();
			if (nombre.isBlank() || nombre.isEmpty()) {
				throw new Exception("No puede estar en blanco");
			}
			FileOutputStream fileOutputStream = new FileOutputStream(nombre + ".ser");
			ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
			objectOutputStream.writeObject(clientes);
			objectOutputStream.flush();
			objectOutputStream.close();

		} catch (Exception e) {
			System.out.println("Error.- " + e.getMessage());
		}
	}

	private static void meterDatos() throws IOException, ClassNotFoundException {
		try {
			System.out.println("Dime el nombre que quieres para meter el archivo");
			String nombre = entrada.nextLine();
		FileInputStream fileInputStream = new FileInputStream(nombre + ".ser");
		ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
		@SuppressWarnings("unchecked")
		ArrayList<Cliente> clientesLeidos = (ArrayList<Cliente>) objectInputStream.readObject();
		objectInputStream.close();

		for (int i = 0; i < clientesLeidos.size(); i++) {
			System.out.println(clientesLeidos.get(i));
		}
		} catch (Exception e) {
			System.out.println("Error.- " + e.getMessage());
		}
		
	}

}
