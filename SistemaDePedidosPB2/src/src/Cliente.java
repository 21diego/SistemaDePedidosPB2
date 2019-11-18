package src;

public class Cliente extends Usuario {

	private String apellido;
	private String nombre;

	public Cliente(String nick, String password, String nombre, String apellido) {
		super(nick, password);
		this.nombre = nombre;
		this.apellido = apellido;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
}
