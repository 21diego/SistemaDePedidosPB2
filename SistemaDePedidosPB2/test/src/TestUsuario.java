package src;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestUsuario {

	@Test
	public void testBuscarYDevolverPedidoPorNroDePedido() {
		Usuario user = new Cliente("didaco","contra123", "Diego", "Morinigo");
		Pedido pedido1 = new Pedido(10, user);
		Pedido pedido2 = new Pedido(12, user);
		Pedido pedido3 = new Pedido(8, user);
		Pedido pedido4 = new Pedido(2, user);
		
		user.agregarPedido(pedido1);
		user.agregarPedido(pedido2);
		user.agregarPedido(pedido3);
		user.agregarPedido(pedido4);
		
		String nroPedido1 = pedido1.getNroDePedido();
		String nroPedido2 = pedido2.getNroDePedido();
		String nroPedido3 = pedido3.getNroDePedido();
		String nroPedido4 = pedido4.getNroDePedido();
		
		assertTrue(pedido1.equals(user.buscarYDevolverPedidoPorNroDePedido(nroPedido1)));
		assertTrue(pedido2.equals(user.buscarYDevolverPedidoPorNroDePedido(nroPedido2)));
		assertTrue(pedido3.equals(user.buscarYDevolverPedidoPorNroDePedido(nroPedido3)));
		assertTrue(pedido4.equals(user.buscarYDevolverPedidoPorNroDePedido(nroPedido4)));
	}
	
	@Test
	public void testCancelarPedido() {
		Usuario user = new Cliente("didaco","contra123", "Diego", "Morinigo");
		Pedido pedido1 = new Pedido(10, user);
		user.agregarPedido(pedido1);
		
		Estado actual = pedido1.getEstado();
		assertTrue(actual.equals(Estado.ENPROCESO));
		
		user.cancelarPedido(pedido1.getNroDePedido());
		actual = pedido1.getEstado();
		assertTrue(actual.equals(Estado.CANCELADO));
	}

}
