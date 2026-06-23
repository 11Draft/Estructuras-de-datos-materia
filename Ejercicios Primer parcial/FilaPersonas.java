import java.util.LinkedList;
import java.util.Queue;

public class FilaPersonas {
    public static void main(String[] args) {
        // 1. Crear la cola y agregar 5 nombres
        Queue<String> fila = new LinkedList<>();
        fila.add("Angel");
        fila.add("Cruz");
        fila.add("Alex");
        fila.add("Maria");
        fila.add("Sofia");

        System.out.println("Fila inicial: " + fila);

        // 2. Mostrar quien esta al frente (sin eliminarlo)
        System.out.println("Persona al frente: " + fila.peek());

        // 3. Atender (eliminar) a dos personas
        System.out.println("Atendiendo a: " + fila.poll());
        System.out.println("Atendiendo a: " + fila.poll());

        // 4. Mostrar la cola restante
        System.out.println("Fila restante: " + fila);
    }
}
