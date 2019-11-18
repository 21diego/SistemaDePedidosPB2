package src;

import java.util.*;

public class Restaurant {

	private String nombre;
	private Set<Producto> listaDeProductos;
	private Set<Usuario> listaDeUsuarios;
	private Set<Pedido> listaDePedidos;
	private Set<Ventas> listaDeVentas;

	public Restaurant() {
		this.nombre = "d-_-b";
		this.listaDeProductos = new HashSet<Producto>();
		this.listaDeUsuarios = new HashSet<Usuario>();
		this.listaDeVentas = new TreeSet<Ventas>();
	}

	public Restaurant(String nombre) {
		this.nombre = nombre;
		this.listaDeProductos = new HashSet<Producto>();
		this.listaDeUsuarios = new HashSet<Usuario>();
		this.listaDeVentas = new TreeSet<Ventas>();
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Set<Producto> getListaDeProductos() {
		return this.listaDeProductos;
	}

	public Set<Pedido> getListaDePedidos() {
		return this.listaDePedidos;
	}

	public Set<Usuario> getListaDeUsuarios() {
		return this.listaDeUsuarios;
	}

	public boolean login(String nick, String password) {
		Iterator<Usuario> listausuario = listaDeUsuarios.iterator();
		while (listausuario.hasNext()) {
			Usuario usuario = listausuario.next();
			if (usuario.getNick().equals(nick) && usuario.getPassword().equals(password)) {
				return true;
			}
		}
		return false;
	}

	public boolean registrarAdministrador(String nick, String password, String nombre, String apellido) {

		if (!buscarUsuarioPorNickYPassword(nick, password)) {
			Usuario usuario = new Administrador(nick, password, nombre, apellido);
			this.listaDeUsuarios.add(usuario);
			return true;
		}
		return false;
	}

	public boolean registrarCliente(String nick, String password, String nombre, String apellido) {
		if (listaDeUsuarios.size() == 0) {
			Usuario usuario = new Cliente(nick, password, nombre, apellido);
			listaDeUsuarios.add(usuario);
			return true;
		} else {
			if (!buscarUsuarioPorNickYPassword(nick, password)) {
				Usuario usuario = new Cliente(nick, password, nombre, apellido);
				listaDeUsuarios.add(usuario);
				return true;
			}
		}
		return false;
	}

	public boolean buscarUsuarioPorNickYPassword(String nick, String password) {
		Iterator<Usuario> listausuario = listaDeUsuarios.iterator();
		while (listausuario.hasNext()) {
			Usuario usuario = listausuario.next();
			if (usuario.getNick().equals(nick) && usuario.getPassword().equals(password)) {
				return true;
			}
		}
		return false;
	}

	public Usuario devolverUsuario(String nick, String password) {
		Iterator<Usuario> listausuario = listaDeUsuarios.iterator();
		while (listausuario.hasNext()) {
			Usuario usuario = listausuario.next();
			if (usuario.getNick().equals(nick) && usuario.getPassword().equals(password)) {
				return usuario;
			}
		}
		return null;
	}

	public Producto buscarProductoPorId(String id) {
		Iterator<Producto> listaproductos = listaDeProductos.iterator();
		while (listaproductos.hasNext()) {
			Producto producto = listaproductos.next();
			if (producto.getId().equals(id)) {
				return producto;
			}
		}
		return null;
	}

	public void agregarProducto(Producto producto) {
		listaDeProductos.add(producto);
	}

	public boolean darDeBajaUnProductoPorId(String id) {
		Iterator<Producto> listaproducto = listaDeProductos.iterator();
		while (listaproducto.hasNext()) {
			Producto producto = listaproducto.next();
			if (producto.getId().equals(id)) {
				listaproducto.remove();
				return true;
			}
		}
		return false;
	}

	public void mostrarListaDeProductos() {
		System.out.println(" ID\t\tNOMBRE\t\tPRECIO");
		Iterator<Producto> listaproductos = listaDeProductos.iterator();
		while (listaproductos.hasNext()) {
			Producto producto = listaproductos.next();
			System.out.println(producto.toString());
		}
	}

	public void mostrarListaDeVentas() {
		Iterator<Ventas> listaventas = listaDeVentas.iterator();
		while (listaventas.hasNext()) {
			Ventas venta = listaventas.next();
			System.out.println(venta.toString());
		}
	}

	public void generarAdminPorDefecto() {
		registrarAdministrador("adminDef", "admin", "nombre", "apellido");
	}

	public void cargarProductosPorDefecto() {
		Producto p = new Producto("agua", "descripcion", 10.0, listaDeProductos.size() + 1);
		listaDeProductos.add(p);
		Producto p1 = new Producto("gaseosa", "descripcion", 20.0, listaDeProductos.size() + 1);
		listaDeProductos.add(p1);
		Producto p2 = new Producto("vino", "descripcion", 80.0, listaDeProductos.size() + 1);
		listaDeProductos.add(p2);
		Producto p3 = new Producto("carne", "al horno", 50.0, listaDeProductos.size() + 1);
		listaDeProductos.add(p3);
		Producto p4 = new Producto("ensalada", "rusa", 60.0, listaDeProductos.size() + 1);
		listaDeProductos.add(p4);
		Producto p5 = new Producto("ensalada", "verduras", 30.0, listaDeProductos.size() + 1);
		listaDeProductos.add(p5);
		Producto p6 = new Producto("papa fritas", "descripcion", 25.0, listaDeProductos.size() + 1);
		listaDeProductos.add(p6);
		Producto p7 = new Producto("ravioles", "descripcion", 75.0, listaDeProductos.size() + 1);
		listaDeProductos.add(p7);
		Producto p8 = new Producto("ñoquis", "descripcion", 68.0, listaDeProductos.size() + 1);
		listaDeProductos.add(p8);
		Producto p9 = new Producto("espaguetis", "descripcion", 46.0, listaDeProductos.size() + 1);
		listaDeProductos.add(p9);
	}

	public void ejecutarTimerParaCargarVenta(Pedido pedido) {// timer
		Timer tim = new Timer();
		TimerTask tarea = new TimerTask() {

			@Override
			public void run() {
				if (pedido.getEstado().equals(Estado.ENTREGADO)) {
					listaDeVentas.add(new Ventas(pedido));
				}
			}
		};

		tim.schedule(tarea, 11000);// luego de los 10 seg de realizar el pago si no cancelas el pedido se cambia el
									// estado a Entregado
	}

	public void mostrarListaDeUsuarios() {
		Iterator<Usuario> listausuario = listaDeUsuarios.iterator();
		while (listausuario.hasNext()) {
			Usuario aux = listausuario.next();
			System.out.println(aux.toString());
		}

	}

	public void mostrarVentaPorUsuario(String nick) {
		Iterator<Ventas> listaventas = listaDeVentas.iterator();
		while (listaventas.hasNext()) {
			Ventas venta = listaventas.next();
			if (venta.getPedido().getUsuario().getNick().equals(nick)) {
				System.out.println(venta.toString());
			}
		}
	}

	public void eliminarUsuarioporNick(String nick1) {
		Iterator<Usuario> listausuario = listaDeUsuarios.iterator();
		while (listausuario.hasNext()) {
			Usuario aux = listausuario.next();
			if (aux.getNick().equals(nick1)) {
				listausuario.remove();
			}
		}

	}

}
