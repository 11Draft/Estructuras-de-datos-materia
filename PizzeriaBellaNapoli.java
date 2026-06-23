import java.util.Scanner;

public class PizzeriaBellaNapoli {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // 1. Entrada del tipo de pizza
        System.out.println("--- BIENVENIDO A BELLA NAPOLI ---");
        System.out.println("Seleccione el tipo de pizza:");
        System.out.println("1. Vegetariana");
        System.out.println("2. No vegetariana");
        System.out.print("Ingrese el número de su opción: ");
        int tipoPizza = scanner.nextInt();

        // Variables para el resultado
        String nombreTipo = "";
        String ingredienteElegido = "";
        boolean eleccionValida = true;

        // 2. Procesamiento según el tipo de pizza y su ingrediente
        switch (tipoPizza) {
            case 1 -> {
                nombreTipo = "Vegetariana";
                System.out.println("\nIngredientes vegetarianos disponibles:");
                System.out.println("a. Pimiento");
                System.out.println("b. Tofu");
                System.out.print("Elija la letra del ingrediente: ");
                char ingrediente = scanner.next().toLowerCase().charAt(0);

                switch (ingrediente) {
                    case 'a' -> ingredienteElegido = "Pimiento";
                    case 'b' -> ingredienteElegido = "Tofu";
                    default  -> eleccionValida = false;
                }
            }
            case 2 -> {
                nombreTipo = "No vegetariana";
                System.out.println("\nIngredientes no vegetarianos disponibles:");
                System.out.println("a. Peperoni");
                System.out.println("b. Jamón");
                System.out.println("c. Salmón");
                System.out.print("Elija la letra del ingrediente: ");
                char ingrediente = scanner.next().toLowerCase().charAt(0);

                switch (ingrediente) {
                    case 'a' -> ingredienteElegido = "Peperoni";
                    case 'b' -> ingredienteElegido = "Jamón";
                    case 'c' -> ingredienteElegido = "Salmón";
                    default  -> eleccionValida = false;
                }
            }
            default -> eleccionValida = false;
        }

        // 3. Mostrar el resultado en pantalla
        System.out.println("\n--- DETALLE DE TU PEDIDO ---");
        if (eleccionValida) {
            System.out.println("Tipo de pizza: " + nombreTipo);
            System.out.println("Ingredientes: Tomate, Mozzarella, " + ingredienteElegido + ".");
        } else {
            System.out.println("Error: Selección no válida. Por favor, intente de nuevo.");
        }
        System.out.println("----------------------------");

        scanner.close(); // Cerramos el scanner
    }
}