package test;

import static org.junit.Assert.*;

import org.junit.Test;

import src.Cliente;
import src.Pedido;
import src.Producto;
import src.Usuario;

public class TestPedido {

	Producto prod1=new Producto("producto1", "descripcion", 10.0, 5);
	Producto prod2=new Producto("producto2", "descripcion", 15.0, 6);
	Usuario user=new Cliente("nick", "password", "nombre", "apellido");
	
	@Test
	public void test() {
	Usuario user=new Cliente("nick", "password", "nombre", "apellido");
	Pedido pedido=new Pedido(1, user);
	pedido.cargarItem(prod1, 5);
	pedido.calcularTotal(1);
	pedido.getEstado();
	}

}
