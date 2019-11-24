package src;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestPedido {

	@Test
	public void testQuePruebaEstadosDelPedido() {
		Usuario user = new Cliente("messias","eluno","lionel","messi");
		Pedido pedido1 = new Pedido(10,user);
		
		Estado actual = pedido1.getEstado();
		assertTrue(actual.equals(Estado.ENPROCESO));
		
		pedido1.cancelarPedido();
		actual = pedido1.getEstado();
		assertTrue(actual.equals(Estado.CANCELADO));
		
		pedido1.entregarPedido();
		actual = pedido1.getEstado();
		assertTrue(actual.equals(Estado.ENTREGADO));
	}

	@Test
	public void testCalculoDelTotal() {
		Usuario user = new Cliente("messias","eluno","lionel","messi");
		Pedido pedido1 = new Pedido(10,user);
		Producto prod1 = new Producto("milanesa", "de carne", 75.0, 15);
		Producto prod2 = new Producto("papas", "fritas", 50.0, 10);
		pedido1.cargarItem(prod1, 2);
		pedido1.cargarItem(prod2, 3);
		user.agregarPedido(pedido1);
		
		//pago en efectivo
		pedido1.calcularRecargo(1);
		assertEquals(300.0,pedido1.getTotalAPagar(),0.1);
		
		//pago con tarjeta
		pedido1.calcularRecargo(2);
		assertEquals(345.0,pedido1.getTotalAPagar(),0.1);
	}
}
