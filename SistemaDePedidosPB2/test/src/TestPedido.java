package src;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.junit.Test;

public class TestVenta {

	@Test
	public void testCreacionDeVenta() {
		String nombreProd = "Milanesa de Carne";
		String descProd = "Filete de carne vacuna, pasado por huevo batido y luego por pan rallado";
		Double precioProd = 75.0;
		Integer codProd = 01;
		String codEspProd = "P01";
		
		Producto prod1 = new Producto(nombreProd,descProd,precioProd,codProd);
		
		String nombreProd2 = "Papas Fritas";
		String descProd2 = "Papas cortadas en forma de baston";
		Double precioProd2 = 60.0;
		Integer codProd2 = 02;
		String codEspProd2 = "P02";
		
		Producto prod2 = new Producto(nombreProd2,descProd2,precioProd2,codProd2);
		
	    String nick = "MatiGuerrero61";
	    String password = "mati61";
	    
	    Usuario user = new Usuario (nick, password);
	    
	    Pedido pedido = new Pedido(1,user);
	    
	    Double TotalAPagar = precioProd + precioProd2; 
	    
	    pedido.cargarItem(prod1, 2);
	    pedido.cargarItem(prod2, 1);
	    
	    assertEquals(TotalAPagar,pedido.calcularTotalAPagar())
}
