
public class Persona {
	private String dni;
	String nombre;
	
	public Persona(String dni, String nombre) {
		this.setDni(dni);
		this.nombre = nombre;
	}

	// Definir getters y setters
	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}		
}
