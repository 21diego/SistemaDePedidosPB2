package src;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestProducto {

	@Test
	public void creacionDeObjetoCorrecto() {
		String nombreProd = "Milanesa de Carne";
		String descProd = "Filete de carne vacuna, pasado por huevo batido y luego por pan rallado";
		Double precioProd = 75.0;
		Integer codProd = 01;
		String codEspProd = "P01";
		
		Producto prod = new Producto(nombreProd,descProd,precioProd,codProd);
		
		//Mostrar en consola
		System.out.println(prod);
		
		//Verificacion de los datos
		assertTrue(prod.getNombre().equals(nombreProd));
		assertTrue(prod.getDescripcion().equals(descProd));
		assertTrue(prod.getPrecio().equals(precioProd));
		assertTrue(prod.getId().equals(codEspProd));
		
	}
}
