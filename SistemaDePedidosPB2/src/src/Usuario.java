package src;

import java.util.*;

public class Usuario implements Comparable<Usuario>{
	private String nick;
	private String password;
	private static Integer id=0;
    private Set<Pedido> listaDePedidos;
	
	public Usuario(String nick,String password) {
		this.nick=nick;
		this.password=password;
		this.id++;
		this.listaDePedidos=new TreeSet<Pedido>();
	}
	
	public  static Integer getId() {
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
		Iterator<Pedido> listapedidos= listaDePedidos.iterator();
		while(listapedidos.hasNext()) {
			Pedido aux=listapedidos.next();
			System.out.println(aux.toString());
		}
	}
	
	public Pedido buscarYDevolverPedidoPorNroDePedido(String nroDePedido) {
		Iterator<Pedido> listapedidos= listaDePedidos.iterator();
		while(listapedidos.hasNext()) {
			Pedido aux=listapedidos.next();
			if (aux.getNroDePedido().equals(nroDePedido)) {
				return aux;
			}
		}
		return null;
	}
	
	public void cancelarPedido(String nroDePedido) {
		Pedido pedido=buscarYDevolverPedidoPorNroDePedido(nroDePedido);
		if (pedido!=null) {
			pedido.cancelarPedido();
		}

	}

	
	
	
	
	
}
