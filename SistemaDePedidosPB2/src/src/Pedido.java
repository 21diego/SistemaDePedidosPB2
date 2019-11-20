package src;

import java.util.*;

public class Pedido implements Comparable<Pedido> {

	private String nroDePedido;
	private Integer nroDeMesa;
	private Estado estado;
	private Date fechaPedido;
	private Usuario usuario;
	private Double totalAPagar;
	private List<Item> listaDeItems = new LinkedList<Item>();
	private static Integer count = 0;

	public Pedido(Integer nroDeMesa, Usuario usuario) {
		count++;
		this.nroDePedido = String.format("P%05d", count);// permite generar un codigo de pedido hasta 99.999
		this.nroDeMesa = nroDeMesa;
		this.estado = Estado.ENPROCESO;
		this.fechaPedido = new Date();
		this.totalAPagar = 0.0;
		this.usuario = usuario;
		
	}

	public Estado getEstado() {
		return estado;
	}

//	public void ejecutarTimer() {// timer
//		Timer tim = new Timer();
//		TimerTask tarea = new TimerTask() {
//
//			@Override
//			public void run() {
//				if (estado.equals(Estado.ENPROCESO)) {
//					entregarPedido();
//				}
//			}
//		};
//
//		tim.schedule(tarea, 10000);// luego de los 10 seg de realizar el pago si no cancelas el pedido se cambia el
//									// estado a Entregado
//	}

	public String getNroDePedido() {
		return nroDePedido;
	}

	public Integer getNroDeMesa() {
		return nroDeMesa;
	}

	public void setNroDeMesa(Integer nroDeMesa) {
		this.nroDeMesa = nroDeMesa;
	}

	public void entregarPedido() {
		this.estado = Estado.ENTREGADO;
	}

	public void cancelarPedido() {
		this.estado = Estado.CANCELADO;
	}

	public Date getFecha() {
		return this.fechaPedido;
	}

	public void cargarItem(Producto producto, Integer cantidad) {
		Item e = new Item(producto, cantidad);
		this.listaDeItems.add(e);
		this.totalAPagar += e.getCantidad() * e.getProducto().getPrecio();
	}

	public String mostrarListaItems() {
		Iterator<Item> listadeitems = listaDeItems.iterator();
		while (listadeitems.hasNext()) {
			Item item = listadeitems.next();
			System.out.println(item.toString());
		}
		return null;
	}

	public Double getTotalAPagar() {
		return this.totalAPagar;
	}

	public void calcularRecargo(Integer codigo) {
		if(codigo.equals(2)) {
			this.totalAPagar *= 1.15;
		}
	}

	public Usuario getUsuario() {
		return usuario;
	}

	@Override
	public String toString() {
		return "" + nroDePedido + "\t" + estado + "\tfecha: " + fechaPedido + "\n-----Items-----" + listaDeItems.toString()
				+ "\n---------------" + "\nTotal a pagar= " + totalAPagar + "\n\n";
	}

	@Override
	public int compareTo(Pedido o) {
		return this.nroDePedido.compareTo(o.getNroDePedido());
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((fechaPedido == null) ? 0 : fechaPedido.hashCode());
		result = prime * result + ((nroDePedido == null) ? 0 : nroDePedido.hashCode());
		result = prime * result + ((usuario == null) ? 0 : usuario.hashCode());
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
		Pedido other = (Pedido) obj;
		if (fechaPedido == null) {
			if (other.fechaPedido != null)
				return false;
		} else if (!fechaPedido.equals(other.fechaPedido))
			return false;
		if (nroDePedido == null) {
			if (other.nroDePedido != null)
				return false;
		} else if (!nroDePedido.equals(other.nroDePedido))
			return false;
		if (usuario == null) {
			if (other.usuario != null)
				return false;
		} else if (!usuario.equals(other.usuario))
			return false;
		return true;
	}

	
}
