import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

public class Cafeteria {

    public static void main(String[] args) {
        // Aquí viven nuestras dos estructuras: la fila de espera
        // y el historial de cancelaciones. Empiezan vacías.
        Queue<String> colaClientes = new LinkedList<>();
        Stack<String> pilaCancelados = new Stack<>();

        Scanner sc = new Scanner(System.in);
        int opcion = -1;

        // El bucle sigue mientras el usuario no elija "0" (salir).
        // Esto es lo que mantiene el programa "vivo" entre acciones.
        while (opcion != 0) {
            // Mostramos el menú cada vez, para recordarle al
            // usuario qué opciones tiene disponibles.
            System.out.println("\n--- Cafeteria ---");
            System.out.println("1. Llega un cliente");
            System.out.println("2. Atender al siguiente cliente");
            System.out.println("3. Cancelar el pedido del frente de la fila");
            System.out.println("4. Mostrar la fila de espera");
            System.out.println("5. Mostrar pedidos cancelados");
            System.out.println("0. Salir");
            System.out.print("Elige una opcion: ");

            // nextInt() lee un numero. Si el usuario escribe texto
            // en lugar de un numero, el programa se romperia aqui --
            // pero por ahora, mantenemos esta version simple y
            // confiamos en que el usuario escribe numeros.
            opcion = sc.nextInt();
            sc.nextLine(); // Limpiamos el "salto de linea" que queda pendiente.

            // Segun la opcion elegida, llamamos al metodo correspondiente.
            // Cada metodo es una de las "cinco operaciones" que ya conoces.
            switch (opcion) {
                case 1:
                    llegarCliente(colaClientes, sc);
                    break;
                case 2:
                    atenderCliente(colaClientes);
                    break;
                case 3:
                    cancelarPedido(colaClientes, pilaCancelados);
                    break;
                case 4:
                    mostrarCola(colaClientes);
                    break;
                case 5:
                    mostrarCancelaciones(pilaCancelados);
                    break;
                case 0:
                    System.out.println("Cerrando la cafeteria. Hasta luego.");
                    break;
                default:
                    // Si el usuario elige un numero que no esta en el menu,
                    // no queremos que el programa se confunda en silencio.
                    System.out.println("Opcion no valida. Intenta de nuevo.");
            }
        }

        sc.close();
    }

    // --- Operacion 1: llegar cliente ---
    // Un cliente nuevo se forma al final de la fila.
    public static void llegarCliente(Queue<String> colaClientes, Scanner sc) {
        System.out.print("Nombre del cliente que llega: ");
        String nombre = sc.nextLine();

        // offer() agrega al FINAL de la cola, respetando el orden de llegada.
        colaClientes.offer(nombre);
        System.out.println(nombre + " se ha unido a la fila.");
    }

    // --- Operacion 2: atender cliente ---
    // Atendemos a quien lleva mas tiempo esperando: el frente de la fila.
    public static void atenderCliente(Queue<String> colaClientes) {
        // Si no hay nadie, no tiene sentido "atender" a nadie.
        if (colaClientes.isEmpty()) {
            System.out.println("No hay clientes esperando. Todo tranquilo por aqui.");
            return;
        }

        // poll() saca y devuelve al de adelante en un solo paso.
        String cliente = colaClientes.poll();
        System.out.println("Atendiendo a " + cliente + ". Que disfrute su cafe!");
    }

    // --- Operacion 3: cancelar pedido ---
    // Movemos al cliente del frente de la fila hacia el historial de cancelados.
    public static void cancelarPedido(Queue<String> colaClientes, Stack<String> pilaCancelados) {
        // Sin nadie en la fila, no hay nada que cancelar.
        if (colaClientes.isEmpty()) {
            System.out.println("No hay nadie en la fila para cancelar un pedido.");
            return;
        }

        String cliente = colaClientes.poll();
        // push() coloca este elemento en el TOPE de la pila de cancelados.
        pilaCancelados.push(cliente);
        System.out.println("Se cancelo el pedido de " + cliente + ".");
    }

    // --- Operacion 4: mostrar la cola ---
    // Solo miramos quien esta esperando, sin modificar nada.
    public static void mostrarCola(Queue<String> colaClientes) {
        if (colaClientes.isEmpty()) {
            System.out.println("La fila de espera esta vacia.");
            return;
        }

        System.out.println("Clientes esperando (en orden de llegada):");
        int posicion = 1;
        for (String cliente : colaClientes) {
            System.out.println(posicion + ". " + cliente);
            posicion++;
        }
    }

    // --- Operacion 5: mostrar cancelaciones ---
    // Recorrer una Stack va del TOPE hacia la BASE: lo mas reciente primero.
    public static void mostrarCancelaciones(Stack<String> pilaCancelados) {
        if (pilaCancelados.isEmpty()) {
            System.out.println("No hay pedidos cancelados todavia.");
            return;
        }

        System.out.println("Pedidos cancelados (del mas reciente al mas antiguo):");
        int posicion = 1;
        for (String cliente : pilaCancelados) {
            System.out.println(posicion + ". " + cliente);
            posicion++;
        }
    }
}