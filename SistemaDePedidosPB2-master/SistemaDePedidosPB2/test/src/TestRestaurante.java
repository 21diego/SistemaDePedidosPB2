package src;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.junit.Test;

public class TestVenta {

	@Test
	public void testBuscarUsuarioPorNickYPass() {
		Restaurant r = new Restaurant("La farola");
		
		r.registrarCliente("mati warrior", "mati61", "Matias", "Guerrero");
		
		AssertEquals("Matias", r.buscarUsuarioPorNickYPassword("mati warrior", "mati61").getNombre);
	    }
