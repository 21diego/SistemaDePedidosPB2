package src;

import java.util.*;
import exceptions.*;

public class Interfaz {

	private static Interfaz instance;
	private Restaurant local;
	private Usuario userLogueado;
	private Scanner teclado = new Scanner(System.in);

	private Interfaz() {
		this.local = new Restaurant();
	}

	public static Interfaz getInstance() {
		if (instance == null) {
			instance = new Interfaz();
		}
		return instance;
	}

	public void run() {
		this.local.generarAdminPorDefecto();
		this.local.cargarProductosPorDefecto();

		String opcion = menuInicial();

		while (!opcion.equals(null)) {
			switch (opcion) {
			case "1": // Inicio de sesion
				pantallaInicioSesion();
				break;
			case "2": // Registro
				pantallaRegistro();
				break;
			case "0": // Salir
				System.out.println("-----ADIOS-----");
				return;
			}
			opcion = menuInicial();
		}
	}

	private String menuInicial() {
		String opcion;
		while (true) {
			try {
				System.out.println("-----Bienvenido-----\n "
						+ "1-para Iniciar Sesion.\n "
						+ "2-para Registrarse.\n "
						+ "0-para salir y luego presione Enter");
				opcion = teclado.next();
				if (!opcion.equals("1")&&!opcion.equals("2")&&!opcion.equals("0")){
					throw new IngresoException();
				}
				else {
					return opcion;
				}
			}
			catch (IngresoException e) {
				System.out.println(e.getMessage());
			}
		}
	}

	private void pantallaInicioSesion() {
		System.out.println("-----Sistema de Sesion-----");
		System.out.print("Ingrese su nombre de usuario: ");
		String user = teclado.next();
		System.out.print("Ingrese su contraseña: ");
		String pass = teclado.next(); //encriptar
		String opcion;
		
		if(this.local.login(user, pass)) {
			this.userLogueado = local.devolverUsuario(user, pass);
			if (userLogueado instanceof Cliente) {
				opcion = menuCliente();
				while (!opcion.equals(null)) {
					switch (opcion) {
					case "1": // Realizar pedido
						pantallaRealizarPedido();
						break;
					case "2": // Ver mis pedidos
						pantallaVerPedidos();
						break;
					case "3": // Cancelar Pedido
						pantallaCancelarPedido();
						break;
					case "0": // Cerrar sesion
						System.out.println("Cerrando sesion.....");
						return;
					}
					opcion = menuCliente();
				}
			}
			else if (userLogueado instanceof Administrador) {
				opcion = menuAdministrador();
				while (!opcion.equals(null)) {
					switch (opcion) {
					case "1": // Ver pedidos
						pantallaVerPedidos();
						break;
					case "2": // Ver ventas
						pantallaVerVentas();
						break;
					case "3": // Cargar producto
						pantallaCargarProducto();
						break;
					case "4": // Ver productos
						pantallaVerProductos();
						break;
					case "5": // Ver usuarios
						pantallaVerUsuarios();
						break;
					case "6": // Eliminar usuario
						pantallaEliminarUsuario();
						break;
					case "7": // Realizar pedido
						pantallaRealizarPedido();
						break;
					case "8": // Cancelar pedido
						pantallaCancelarPedido();
						break;
					case "0": // Cerrar sesion
						System.out.println("Cerrando sesion.....");
						return;
					}
					opcion = menuAdministrador();
				}
			}
		}
	}
	
	private String menuCliente() {
		String opcion;
		while (true) {
			try {
				System.out.println("Ingrese una opcion:\n"
						+ "1-Realizar pedido.\n"
						+ "2-Ver pedidos realizados.\n"
						+ "3-Cancelar pedido\n"
						+ "0-Cerrar Sesion");
				opcion = teclado.next();
				if (!opcion.equals("1")&&!opcion.equals("2")&&!opcion.equals("3")&&!opcion.equals("0")){
					throw new IngresoException();
				}
				else {
					return opcion;
				}
			}
			catch (IngresoException e) {
				System.out.println(e.getMessage());
			}
		}
	}
	
	private String menuAdministrador() {
		String opcion;
		while (true) {
			try {
				System.out.println("1-Ver pedidos.\n"
						+ "2-Ver ventas\n"
						+ "3-Cargar producto\n"
						+ "4-Ver productos\n"
						+ "5-Ver usuarios\n"
						+ "6-Eliminar usuario\n"
						+ "7-Realizar pedido\n"
						+ "8-Cancelar pedido\n"
						+ "0-Cerrar Sesion");
				opcion = teclado.next();
				if (!opcion.equals("1")&&!opcion.equals("2")&&!opcion.equals("3")&&
						!opcion.equals("4")&&!opcion.equals("5")&&!opcion.equals("6")&&
						!opcion.equals("7")&&!opcion.equals("8")&&!opcion.equals("0")){
					throw new IngresoException();
				}
				else {
					return opcion;
				}
			}
			catch (IngresoException e) {
				System.out.println(e.getMessage());
			}
		}
	}
	
	private void pantallaRegistro() {
		System.out.println("-----Registro-----");
		System.out.print("Desea registrase como administrador? [s/n]: ");
		String option = teclado.next();
		if (option.toUpperCase().equals("S")) {
			if (registro(0)) {
				System.out.println("---Registro Exitoso---");
			}
			else {
				System.out.println("No se puedo registar :c");
			}
		}
		else {
			if (registro(1)) {
				System.out.println("---Registro Exitoso---\n");
			}
			else {
				System.out.println("No se puedo registar :c");
			}
		}
	}
	
	private Boolean registro(Integer codigo) {
		System.out.print("Ingrese un nombre de Usuario: ");
		String user = teclado.next();
		System.out.print("Ingrese una contraseña: ");
		String pass = teclado.next();
		System.out.println("Ingrese su nombre: ");
		String nombre = teclado.next();
		System.out.println("Ingrese su apellido: ");
		String apellido = teclado.next();
		
		if (codigo.equals(1)) {
			return local.registrarCliente(user, pass, nombre, apellido);
		}
		else {
			return local.registrarAdministrador(user, pass, nombre, apellido);
		}
	}
	
//	private Integer validarNroMesa() {
//		Integer ingresoNum = null;
//		
//		do {
//			try {
//				System.out.println("Ingrese el numero de mesa y presione Enter:");
//				ingresoNum=teclado.nextInt();
//			} catch (InputMismatchException e) {
//				System.out.println("A ingresado un valor no valido.");	
//				ingresoNum=0;
//			}
//			
//			
//		}while(ingresoNum<=0&&ingresoNum>=100||ingresoNum==null);
//		
//		return ingresoNum;
//	}
	
	
	private void pantallaRealizarPedido() {
		System.out.println("Bienvenido al sistema de Pedidos");
//		Integer nroMesa = validarNroMesa();
		System.out.println("Ingrese el numero de mesa y presione Enter:");
		Integer nroMesa = teclado.nextInt();
		Pedido pedido = new Pedido(nroMesa, this.userLogueado);
		do {
			elegirItem(pedido);
			System.out.println("Desea agregar otro item? [s/n]: ");
			String opcion = teclado.next();
			if (!opcion.toUpperCase().equals("S")) {
				break;
			}
		}while(true);
		facturacion(pedido);
		userLogueado.agregarPedido(pedido);
		System.out.println("Recuerde que tiene 10 segundo para cancelar el pedido.");
		local.ejecutarTimerParaCargarVenta(pedido);
	}
	
	private void elegirItem(Pedido pedido) {
		local.mostrarListaDeProductos();
		System.out.println("Ingrese el id del producto: ");
		String idProd = teclado.next();
		Producto producto = local.buscarProductoPorId(idProd);
		System.out.println("Ingrese la cantidad a ordenar:");
		Integer cantidad = teclado.nextInt();
		pedido.cargarItem(producto, cantidad);
	}
	
	private void facturacion (Pedido pedido) {
		System.out.println("Ingrese forma de pago:\n"
				+ "1-Efectivo\n"
				+ "2-Tarjeta de Credito");
		Integer opcion = teclado.nextInt();
		pedido.calcularTotal(opcion);
		System.out.println("El total a pagar es: " + pedido.getTotalAPagar());
	}

	private void pantallaVerPedidos() {
		userLogueado.mostrarPedidos();
	}
	
	private void pantallaCancelarPedido() {
		System.out.println("Ingrese el numero del pedido a cancelar:");
		String nroDePedido = teclado.next();
		userLogueado.cancelarPedido(nroDePedido);
	}
	
	private void pantallaCargarProducto() {
		if (userLogueado instanceof Administrador) {
		System.out.println("Ingrese el Id del producto:");
			Integer numId = teclado.nextInt();
		System.out.println("Ingrese el nombre del producto:");
			String nombre = teclado.next();
		System.out.println("Ingrese la descripcion del producto:");
			String descripcion = teclado.next();
		System.out.println("Ingrese el precio y Presione Enter"); 
			Double precio = teclado.nextDouble();
		
		Producto producto = new Producto(nombre, descripcion, precio, numId);
		local.agregarProducto(producto);
		} else {
			System.out.println("Usted no es un administrador.");
		}
	}

	private void pantallaVerVentas() {
		if (userLogueado instanceof Administrador) {
		System.out.println("----Lista de Ventas del local----");
		local.mostrarListaDeVentas();
		} else {
			System.out.println("Usted no es administrador.");
		}
	}
	
	private void pantallaVerProductos() {
		System.out.println("----Lista de Productos----");
		local.mostrarListaDeProductos();
	}
	
	private void pantallaVerUsuarios() {
		if(userLogueado instanceof Administrador) {
		System.out.println("----Lista de Usuarios----");
		local.mostrarListaDeUsuarios();
		} else {
			System.out.println("Usted no es administrador.");
		}
	}
	
	private void pantallaEliminarUsuario() {
		if(userLogueado instanceof Administrador) {
		System.out.println("Desea ver la lista de usuarios? [s/n]:");
		String opcion = teclado.next();
		if (opcion.toUpperCase().equals("S")) {
			local.mostrarListaDeUsuarios();
		}
		System.out.println("Ingrese el nombre de usuario a eliminar: ");
		String nombreUser = teclado.next();
		Usuario user = local.devolverUsuario(nombreUser);
		if (user == null) {
			System.out.println("El usuario no existe!");
		}
		else if (user.equals(userLogueado)) {
			System.out.println("No te podes eliminar a vos mismo!");
		}
		else if (user instanceof Administrador) {
			System.out.println("No puedes eliminar a otro administrador!");
		}
		else {
			if (local.eliminarUsuarioporNick(nombreUser)) {
				System.out.println("Se elimino el usuario " + nombreUser);
			}
			else {
				System.out.println("No se pudo eliminar el usuario");
			}
		}
	}else {
		System.out.println("Usted no es administrador.");
		}
	}
	
	


}
