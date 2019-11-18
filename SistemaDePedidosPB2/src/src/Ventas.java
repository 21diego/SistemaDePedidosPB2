package src;

import java.util.*;

public class Ventas implements Comparable<Ventas> {

	private Pedido pedido;
	private Date fecha;

	public Ventas(Pedido pedido) {
		this.pedido = pedido;
		this.fecha = pedido.getFecha();
	}

	@Override
	public String toString() {
		return "Numero De Pedido= " + pedido.getNroDePedido() + ", Fecha= " + fecha + "]";
	}

	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	@Override
	public int compareTo(Ventas o) {
		return fecha.compareTo(o.getFecha());
	}

}
