
public class Perro extends Animal {
	String tamanio;
	
	public Perro(int numeroChip, String nombre, int edad, String raza, boolean adoptado, String tamanio) {
		super(numeroChip, nombre, edad, raza, adoptado);
		this.tamanio = tamanio;
	}
	
	@Override
	public void mostrar() {
        System.out.println("Número de Chip: " + getNumeroChip());
        System.out.println("Nombre: " + nombre);
        System.out.println("Edad: " + edad);
        System.out.println("Raza: " + raza);
        System.out.println("Adoptado: " + (getAdoptado() ? "Sí" : "No"));
        System.out.println("Tamaño: " + tamanio);
	}
}
