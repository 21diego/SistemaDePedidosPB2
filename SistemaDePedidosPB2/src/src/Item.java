package src;

public class Item {

	private Producto producto;
	private Integer cantidad;

	public Item(Producto producto, Integer cantidad) {
		this.producto = producto;
		this.cantidad = cantidad;
	}

	@Override
	public String toString() {
		return "\nId: " + producto.getId() + "  " + producto.getNombre() + " Cantidad: " + cantidad + "\n";
	}

	public Integer getCantidad() {
		return this.cantidad;
	}

	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}

	public Producto getProducto() {
		return this.producto;
	}
}
