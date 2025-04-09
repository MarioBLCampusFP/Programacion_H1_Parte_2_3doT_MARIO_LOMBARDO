
import java.util.HashMap;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		// Estructura de datos para almacenar animales
		HashMap<Integer, Animal> animales = new HashMap<Integer, Animal>();
		
		// Estructura para almacenar las adopciones (número de chip y persona que lo adopta)
		HashMap<Integer, Persona> adopciones = new HashMap<>();
		
		// Crear el scanner para obtener entrada por teclado
		Scanner scanner = new Scanner(System.in);
		
		// Menú interactivo
		int opcion = 0;
		int numOpciones = 7;
		do {
			System.out.println();
			System.out.println("--- MENÚ ---");
            System.out.println("1. Dar de alta un animal");
            System.out.println("2. Listar animales");
            System.out.println("3. Buscar un animal");
            System.out.println("4. Realizar adopción");
            System.out.println("5. Dar de baja");
            System.out.println("6. Mostrar estadísticas de gatos");
            System.out.println("7. Salir");
            
            // Pedir número de opción
            System.out.print("Selecciona una opción: ");
            try {
            	opcion = scanner.nextInt();            	
            } catch (java.util.InputMismatchException e) {
            	System.out.println("Valor inválido.");
            	continue;
            } finally {            	
            	scanner.nextLine(); // Limpiar el buffer
            }
            
            switch (opcion) {
            	case 1:
            		// Dar de alta un animal
            		altaAnimal(scanner, animales);
            		break;
            	case 2:
            		// Listar animales
            		listarAnimales(animales);
            		break;
            	case 3:
            		// Buscar animal
            		buscarAnimal(scanner, animales);
            		break;
            	case 4:
            		// Realizar adopción
            		realizarAdopcion(scanner, animales, adopciones);
            		break;
            	case 5:
            		// Buscar animal
            		darBaja(scanner, animales, adopciones);
            		break;
            	case 6:
            		// Mostrar estadísticas de gatos
            		mostrarEstadisticasGatos(scanner, animales);
            		break;
            	case 7:
            		// Salir
            		System.out.println("Saliendo...");
            		break;
            	default:
                    System.out.println("Opción inválida.");
            }
            
		} while (opcion != numOpciones);
		
		// Cerrar scanner
		scanner.close();
	}
	
	// Método para dar de alta animales
	public static void altaAnimal(Scanner sc, HashMap<Integer, Animal> animales) {
		// Preguntar el tipo de animal a insertar
		System.out.print("Tipo de animal: ");
		String tipoAnimal = sc.nextLine().toLowerCase();
		
		// Gestionar tipo inválido
		if (!tipoAnimal.equals("perro") && !tipoAnimal.equals("gato")) {
			System.out.println("Error: Tipo inválido.");
			return;
		}
		
		// Pedir el número de chip
		int numChip;
		System.out.print("Número de chip: ");
        try {
        	numChip = sc.nextInt();
        } catch (java.util.InputMismatchException e) {
        	System.out.println("Valor inválido.");
        	return;
        } finally {            	
        	sc.nextLine(); // Limpiar el buffer
        }
		
		// Gestionar números de chip duplicados
		if (animales.containsKey(numChip)) {
			System.out.println("Error: Número de chip existente.");
			return;
		}
		
		// Preguntar los datos del animal
		System.out.print("Nombre: ");
		String nombre = sc.nextLine();		
		System.out.print("Edad: ");
		int edad;
        try {
        	edad = sc.nextInt();
        } catch (java.util.InputMismatchException e) {
        	System.out.println("Valor inválido.");
        	return;
        } finally {            	
        	sc.nextLine(); // Limpiar el buffer
        }
		System.out.print("Raza: ");
		String raza = sc.nextLine();
		System.out.print("Adoptado (true/false): ");
		boolean adoptado = sc.nextBoolean();
		sc.nextLine();

		// Gestionar según el tipo de animal
		Animal animal = null;
		if (tipoAnimal.equals("perro")) {
			System.out.print("Tamaño: ");
			String tamanio = sc.nextLine();
			animal = new Perro(numChip, nombre, edad, raza, adoptado, tamanio);
		} else if (tipoAnimal.equals("gato")) {
			System.out.print("Test Leucemia (true/false): ");
			boolean testLeucemia = sc.nextBoolean();
			animal = new Gato(numChip, nombre, edad, raza, adoptado, testLeucemia);
		}
		
		// Añadir animal
		if (animal != null) {
			animales.put(numChip, animal);
			System.out.println("Animal añadido con éxito");
		} else {
			System.out.println("Error al añadir al animal");
		}
	}
	
	// Método para buscar un animal
	public static void buscarAnimal(Scanner sc, HashMap<Integer, Animal> animales) {
		// Pedir número de chip
		System.out.print("Número de chip del animal a buscar: ");
		int numChip;
        try {
        	numChip = sc.nextInt();
        } catch (java.util.InputMismatchException e) {
        	System.out.println("Valor inválido.");
        	return;
        } finally {            	
        	sc.nextLine(); // Limpiar el buffer
        }
		
		if (!animales.containsKey(numChip)) {
			System.out.println("Error: El número de chip no existe.");
			return;
		}
		
		// Mostrar los datos del animal
		animales.get(numChip).mostrar();
	}
	
	// Método para listar todos los animales
	public static void listarAnimales(HashMap<Integer, Animal> animales) {
		// Gestionar la ausencia de animales
		if (animales.isEmpty()) {
			System.out.println("No hay animales para listar.");
			return;
		}
		
		// Mostrar los datos de cada animal
		for (Animal animal : animales.values()) {
			animal.mostrar();
		}
	}
	
	// Método para realizar adopciones
	public static void realizarAdopcion(Scanner sc, HashMap<Integer, Animal> animales, HashMap<Integer, Persona> adopciones) {
		// Pedir el número de chip del animal a adoptar
		System.out.print("Número de chip del animal a adoptar: ");
		int numChip;
        try {
        	numChip = sc.nextInt();
        } catch (java.util.InputMismatchException e) {
        	System.out.println("Valor inválido.");
        	return;
        } finally {            	
        	sc.nextLine(); // Limpiar el buffer
        }
		
		// Comprobar si existe el número de chip ingresado
		if (!animales.containsKey(numChip)) {
			System.out.println("Error: El número de chip no existe.");
			return;
		}
		
		// Comprobar si el animal está adoptado
		Animal animal = animales.get(numChip);
		if (animal.getAdoptado()) {
			System.out.println("El animal ya está adoptado.");
			return;
		}
		
		// Datos de la persona que lo va a adoptar
		System.out.print("Número de DNI de la persona que lo va a adoptar: ");
		String dni = sc.nextLine();
		System.out.print("Nombre de la persona que lo va a adoptar: ");
		String nombre = sc.nextLine();
		Persona persona = new Persona(dni, nombre);
		
		// Realizar adopción
		adopciones.put(numChip, persona);
		animal.setAdoptado(true);
	}
	
	public static void darBaja(Scanner sc, HashMap<Integer, Animal> animales, HashMap<Integer, Persona> adopciones) {
		// Pedir el número de chip del animal a dar de baja
		System.out.print("Número de chip del animal a dar de baja: ");
		int numChip;
        try {
        	numChip = sc.nextInt();
        } catch (java.util.InputMismatchException e) {
        	System.out.println("Valor inválido.");
        	return;
        } finally {            	
        	sc.nextLine(); // Limpiar el buffer
        }
		
		// Comprobar si el animal está registrado
		if (!animales.containsKey(numChip)) {
			System.out.println("Error: El número de chip no existe.");
			return;
		}
		
		// Dar de baja al animal
		animales.remove(numChip);
		adopciones.remove(numChip);
		System.out.println("Animal dado de baja exitosamente.");
	}
	
	public static void mostrarEstadisticasGatos(Scanner sc, HashMap<Integer, Animal> animales) {
		int totalGatos = 0;
		int totalGatosLeucemia = 0;
		
		// Recorrer animales
		for (Animal animal : animales.values()) {
			// Comprobar si es un gato
			if (animal instanceof Gato) {
				// Sumar total de gatos
				totalGatos++;
				// Sumar total de gatos con leucemia
				if (((Gato)animal).getTestLeucemia()) {
					totalGatosLeucemia++;
				}
			}
		}
		
		// Mostrar las estadísticas
		System.out.println("Total gatos: " + totalGatos);
		System.out.println("Total gatos con leucemia: " + totalGatosLeucemia);
	}
}
