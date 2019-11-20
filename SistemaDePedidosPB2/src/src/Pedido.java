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
	}

	public String mostrarListaItems() {
		Iterator<Item> listadeitems = listaDeItems.iterator();
		while (listadeitems.hasNext()) {
			Item item = listadeitems.next();
			return (item.toString());
		}
		return null;
	}

	public Double getTotalAPagar() {
		return this.totalAPagar;
	}

	public void calcularTotal(Integer codigo) {
		Iterator<Item> listadeitems = listaDeItems.iterator();
		while (listadeitems.hasNext()) {
			Item item = listadeitems.next();
			this.totalAPagar += (item.getCantidad() * item.getProducto().getPrecio());
		}
		if(codigo.equals(2)) {
			this.totalAPagar *= 1.15;
		}
	}

	public Usuario getUsuario() {
		return usuario;
	}

	@Override
	public String toString() {
		return "" + nroDePedido + "\t" + estado + "\tfecha: " + fechaPedido + "\nItems" + listaDeItems.toString()
				+ "\nTotal a pagar= " + totalAPagar + "\n\n";
	}

	@Override
	public int compareTo(Pedido o) {
		return this.nroDePedido.compareTo(o.getNroDePedido());
	}

}
