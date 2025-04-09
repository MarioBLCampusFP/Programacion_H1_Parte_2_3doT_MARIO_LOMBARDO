
public abstract class Animal {
	private int numeroChip;
	String nombre;
	int edad;
	String raza;
	private boolean adoptado;
	
	public Animal(int numeroChip, String nombre, int edad, String raza, boolean adoptado) {
		this.numeroChip = numeroChip;
		this.nombre = nombre;
		this.edad = edad;
		this.raza = raza;
		this.adoptado = adoptado;
	}
	
	abstract void mostrar();
	
	int getNumeroChip() {
		return numeroChip;
	}
	
	boolean getAdoptado() {
		return adoptado;
	}
	
	void setAdoptado(boolean estado) {
		this.adoptado = estado;
	}
}
