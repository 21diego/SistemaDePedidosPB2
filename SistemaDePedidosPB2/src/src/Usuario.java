package src;

import java.util.*;

public class Usuario implements Comparable<Usuario> {
	private String nick;
	private String password;
	private static Integer id = 0;
	private Set<Pedido> listaDePedidos;

	public Usuario(String nick, String password) {
		this.nick = nick;
		this.password = password;
		this.id++;
		this.listaDePedidos = new TreeSet<Pedido>();
	}

	public static Integer getId() {
		return id;
	}

	public String getNick() {
		return nick;
	}

	public void setNick(String usuario) {
		this.nick = usuario;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public int compareTo(Usuario o) {
		return id.compareTo(o.getId());
	}

	public Set<Pedido> getListaDePedidos() {
		return listaDePedidos;
	}

	public void agregarPedido(Pedido pedido) {
		listaDePedidos.add(pedido);
	}

	public void mostrarPedidos() {
		Iterator<Pedido> listapedidos = listaDePedidos.iterator();
		while (listapedidos.hasNext()) {
			Pedido aux = listapedidos.next();
			System.out.println(aux.toString());
		}
	}

	public Pedido buscarYDevolverPedidoPorNroDePedido(String nroDePedido) {
		Iterator<Pedido> listapedidos = listaDePedidos.iterator();
		while (listapedidos.hasNext()) {
			Pedido aux = listapedidos.next();
			if (aux.getNroDePedido().equals(nroDePedido)) {
				return aux;
			}
		}
		return null;
	}

	public void cancelarPedido(String nroDePedido) {
		Pedido pedido = buscarYDevolverPedidoPorNroDePedido(nroDePedido);
		if (pedido != null) {
			pedido.cancelarPedido();
		}

	}

	@Override
	public String toString() {
		return "Usuario [nick=" + nick + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nick == null) ? 0 : nick.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usuario other = (Usuario) obj;
		if (nick == null) {
			if (other.nick != null)
				return false;
		} else if (!nick.equals(other.nick))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		return true;
	}

	
	
}
