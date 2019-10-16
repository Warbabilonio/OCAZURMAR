package application.beans;

public class Tanque {

	private String nombre;

	private String codigo;

	public Tanque() {
		super();
	}

	public Tanque(String nombre, String codigo) {
		super();
		this.nombre = nombre;
		this.codigo = codigo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	@Override
	public String toString() {
		return "Tanque [nombre=" + nombre + ", codigo=" + codigo + "]";
	}
}
