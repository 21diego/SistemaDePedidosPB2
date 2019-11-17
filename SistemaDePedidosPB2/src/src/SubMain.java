package src;

import java.util.*;



public class SubMain {

	private static SubMain instance;
	Scanner teclado=new Scanner(System.in);
	Restaurant restaurant;
	private Usuario usuarioLogueado;
	
	private SubMain() {
		this.restaurant=new Restaurant();
	}
	
	public static SubMain getInstance() {
		if(instance == null) {
			instance = new SubMain();
		}
		
		return instance;
	}
	

	
	public void run() {
		String opcion;
		restaurant.generarAdminPorDefecto();
		restaurant.cargarProductosPorDefecto();
		opcion=ingresoDeOpciones();
		
		while (!opcion.equals("0") ) {
			
			switch (opcion) {
			case "1"://Inicio de Sesion
				System.out.println("Para Iniciar Secion...");
				System.out.println("Ingrese su nick y presione Enter");
				String nick=teclado.next();
				System.out.println("Ingrese su contraseña y presione Enter");
				String password=teclado.next();System.out.println("\n");
				
				if (login(nick,password)) {
					usuarioLogueado =devolverUsuario(nick, password);
					if (usuario instanceof Cliente) {//si es cliente aparece este menu
						
						opcion="1";
						while (!opcion.equals("0")) {
							System.out.println("Ingrese el digito de la opcion y luego presione Enter\n");
							System.out.println("1-Realizar pedido.\n2-Ver pedidos realizados.\n3-Cancelar pedidos\n5-Salir del sistema");
						opcion=teclado.next();
						
							switch (opcion) {
						case "1":
							String opcion3="";
							System.out.println("Ingrese el numero de la mesa");
							Integer nroDeMesa=teclado.nextInt();
							Pedido pedido=new Pedido(nroDeMesa,usuario);
							restaurant.mostrarListaDeProductos();
							do {
								System.out.println("Ingrese el ID del producto a pedir y presione Enter");
								String idProducto=teclado.next();
								Producto producto = restaurant.buscarProductoPorId(idProducto);
								System.out.println("Ingrese la cantidad a ordenar y presione Enter");
								Integer cantidad=teclado.nextInt();
								pedido.cargarItem(producto, cantidad);
								System.out.println("Ingrese S si desea seguir agregando productos o ingrese N para dejar de ingresar productos a su compra y presione Enter");
								opcion3=teclado.next();
							} while (opcion3.equals("S"));
							do {
							System.out.println("Ingrese el metodo de pago 1-para EFECTIVO 2-para TARJETA y presione Enter");
							opcion3=teclado.next();
							}while(!opcion3.equals("1")&&!opcion3.equals("2"));
							if (opcion3.equals("1")) {
								System.out.println("El total a pagar es: "+pedido.getTotalAPagar());
								usuario.agregarPedido(pedido);
								System.out.println("Recuerde que tiene 10 segundo para cancelar el pedido.");
								restaurant.ejecutarTimerParaCargarVenta(pedido);
							} else {
								System.out.println("El total a pagar es: "+pedido.calcularPagoConTarjeta());
								usuario.agregarPedido(pedido);
								System.out.println("Recuerde que tiene 10 segundo para cancelar el pedido.");
								restaurant.ejecutarTimerParaCargarVenta(pedido);
							}
							
							break;
							
						case "2":
							usuario.mostrarPedidos();
							break;
						case "3":
							System.out.println("Ingrese el numero del pedido a cancelar y presione Enter");
							String nroDePedido=teclado.next();
							usuario.cancelarPedido(nroDePedido);
							break;
						case "5":
							opcion="0";
							break;
						}
						}
					}else {//si es administrador aparece este menu
						opcion="1";
						while (!opcion.equals("0")) {
							System.out.println("Ingrese el digito de la opcion y luego presione Enter\n");
							System.out.println("1-Realizar pedido.\n2-Cancelar pedidos.\n3-Ver Pedidos\n4-Ver ventas.\n"
									+ "5-Cargar productos.\n6-Ver productos.\n7-Eliminar producto.\n8-Ver usuarios.\n"
									+ "9-Eliminar usuario.\n0-Salir");
						opcion=teclado.next();
						switch (opcion) {
						case "1":
							String opcion3="";
							System.out.println("Ingrese el numero de la mesa");
							Integer nroDeMesa=teclado.nextInt();
							Pedido pedido=new Pedido(nroDeMesa,usuario);
							restaurant.mostrarListaDeProductos();
							do {
								System.out.println("Ingrese el ID del producto a pedir y presione Enter");
								String idProducto=teclado.next();
								Producto producto = restaurant.buscarProductoPorId(idProducto);
								System.out.println("Ingrese la cantidad a ordenar y presione Enter");
								Integer cantidad=teclado.nextInt();
								pedido.cargarItem(producto, cantidad);
								System.out.println("Ingrese S si desea seguir agregando productos o ingrese N para dejar de ingresar productos a su compra y presione Enter");
								opcion3=teclado.next();
							} while (opcion3.equals("S"));
							do {
							System.out.println("Ingrese el metodo de pago 1-para EFECTIVO 2-para TARJETA y presione Enter");
							opcion3=teclado.next();
							}while(!opcion3.equals("1")&&!opcion3.equals("2"));
							if (opcion3.equals("1")) {
								System.out.println("El total a pagar es: "+pedido.getTotalAPagar());
								usuario.agregarPedido(pedido);
								System.out.println("Recuerde que tiene 10 segundo para cancelar el pedido.");
								restaurant.ejecutarTimerParaCargarVenta(pedido);
							} else {
								System.out.println("El total a pagar es: "+pedido.calcularPagoConTarjeta());
								usuario.agregarPedido(pedido);
								System.out.println("Recuerde que tiene 10 segundo para cancelar el pedido.");
								restaurant.ejecutarTimerParaCargarVenta(pedido);
							}
							break;
						case "2":
							System.out.println("Ingrese el numero del pedido a cancelar y presione Enter");
							String nroDePedido=teclado.next();
							usuario.cancelarPedido(nroDePedido);
							break;
						case "3":
							usuario.mostrarPedidos();
							break;
						case "4":
							restaurant.mostrarListaDeVentas();
							break;
						case "5":
							String opcion5=" ";
							do {
							System.out.println("Ingrese el nombre del producto y Presione Enter");
							String nombre=teclado.next();
							System.out.println("Ingrese la descripcion del producto y Presione Enter");
							String descripcion=teclado.next();
							System.out.println("Ingrese el precio y Presione Enter");
							Double precio=teclado.nextDouble();
							System.out.println("Ingrese el numero Id de dos digitos y Presione Enter");
							Integer numId=teclado.nextInt();
							Producto producto=new Producto(nombre, descripcion, precio, numId);
							restaurant.agregarProducto(producto);
							System.out.println("Ingrese S si desea seguir agregando productos o ingrese N para dejar de ingresar productos y presione Enter");
							opcion5=teclado.next();
							}while(opcion5.equals("S"));
							break;
						case "6":
							restaurant.mostrarListaDeProductos();
							break;
						case "7":
							System.out.println("Ingrese el ID del producto a eliminar");
							String id=teclado.next();
							if(restaurant.darDeBajaUnProductoPorId(id)) {
								System.out.println("Producto elimidado corectamente");
							}else {
								System.out.println("El producto no pudo ser eliminado, verifique el codigo ID");
							}
							break;
						case "8":
							restaurant.mostrarListaDeUsuarios();
							break;
						case "9":
							System.out.println("Ingrese el nick del usuario a eliminar");
							String nick1=teclado.next();
							restaurant.eliminarUsuarioporNick(nick1);
							break;
						case "0":
							opcion="0";
							break;
						}
						}
					}
					
					
				} else {//mal logeo
					System.out.println("Los datos ingresados no fueron encontrados, vuelva a intentarlo.");
					break;
				}
				
				break;

			case "2"://Registrarse
				String opcion1="";
				System.out.println("Ingrese 1 si es cliente.\nIngrese 2 si el Empleado.\n Y presione Enter.");
				opcion1=teclado.next();
				if (opcion1.equals("1")||opcion1.equals("2")) {
					if (opcion1.equals("1")) {
						System.out.println("Ingrese su nickname y presione Enter");
						String nick2=teclado.next();
						System.out.println("Ingrese su contaseña y presione Enter");
						String contraseña=teclado.next();
						System.out.println("Ingrese su nombre y presione Enter");
						String nombre=teclado.next();
						System.out.println("Ingrese su apellido y presione Enter");
						String apellido=teclado.next();
						if(restaurant.registrarCliente(nick2, contraseña, nombre, apellido)) {
							System.out.println("Registro Exitoso.");
						}else {
							System.out.println("No se puedo resgistrar, Vuelva a intentarlo.");
						}
					} else {
						System.out.println("Ingresa la clave de administrador");
						String clave=teclado.next();
						if (clave.equals("admin")) {
							System.out.println("Ingrese su nickname y presione Enter");
							String nick2=teclado.next();
							System.out.println("Ingrese su contaseña y presione Enter");
							String contraseña=teclado.next();
							System.out.println("Ingrese su nombre y presione Enter");
							String nombre=teclado.next();
							System.out.println("Ingrese su apellido y presione Enter");
							String apellido=teclado.next();
							if(restaurant.registrarAdministrador(nick2, contraseña, nombre, apellido)) {
								System.out.println("Registro Exitoso.");
							}else {
								System.out.println("No se puedo resgistrar, cuelva a intentarlo.");
							}
						} else {
							System.out.println("Clave de administrador incorrecta.");
						}
					}
				} else {
					System.out.println("Opcion incorrecta vuelva a intentarlo.");
				}
				
				break;
			}
			
		opcion=ingresoDeOpciones();
		}	
		
	}
	
	
	                                   //fin del metodo run ...
	
	
	
	
	public String ingresoDeOpciones() {
		String opcion;
		do {
			System.out.println("Ingrese:\n 1-para Iniciar Sesion.\n 2-para Registrarse.\n 0-para salir y luego presione Enter");
			opcion=teclado.next();
		} while (!opcion.equals("1") && !opcion.equals("2") && !opcion.equals("0"));
		return opcion;
	}
	
	public boolean login (String nick, String password) {
		Iterator<Usuario> listausuario = restaurant.getListaDeUsuarios().iterator();
		while(listausuario.hasNext()) {
			Usuario usuario = listausuario.next();
			if(usuario.getNick().equals(nick)&&usuario.getPassword().equals(password)) {
				return true;
			}
		}
		return false;
	}
	
	public Usuario devolverUsuario (String nick, String password) {

		Iterator<Usuario> listausuario = restaurant.getListaDeUsuarios().iterator();
		while(listausuario.hasNext()) {
			Usuario usuario = listausuario.next();
			if(usuario.getNick().equals(nick)&&usuario.getPassword().equals(password)) {
				return usuario;
			}
		}
		return null;
	}
	
	
	
}
