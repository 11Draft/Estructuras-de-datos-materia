import java.util.Scanner;
import java.util.Stack;

public class Palindromo {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.print("Ingresa una palabra: ");
        String palabraOriginal = scanner.nextLine();

        // ─────────────────────────────────────────────────────────────────────
        // PASO 2 — Convertir la palabra a mayúsculas
        //
        // Para comparar de forma justa (sin que "A" y "a" sean distintas),
        // convertimos todas las palabras a mayúsculas.
        // Así, si el usuario escribe "Reconocer" o "RECONOCER", siempre
        // trabajamos con "RECONOCER".
        // ─────────────────────────────────────────────────────────────────────
        String palabra = palabraOriginal.toUpperCase();
        System.out.println("Palabra en mayúsculas: " + palabra);

        // ─────────────────────────────────────────────────────────────────────
        // PASO 3 — Crear la pila (nuestro "montón")
        //
        // Imagina una pila de bandejas en la cafetería de tu universidad.
        // Cada bandeja que colocas encima es la última en llegar, pero también
        // la primera que alguien tomará. Eso es exactamente lo que hace Stack:
        // guarda elementos en orden, y siempre devuelve primero el último
        // que entró. En informática esto se llama LIFO (Last In, First Out).
        // ─────────────────────────────────────────────────────────────────────
        Stack<Character> pila = new Stack<>();

        // ─────────────────────────────────────────────────────────────────────
        // PASO 4 — Empujar (push) cada letra de la palabra dentro de la pila
        //
        // Recorremos la palabra letra por letra. Cada letra es como una bandeja
        // que colocamos en la cima de nuestra pila de cafetería.
        // Si la palabra es "GATO", apilamos: G → A → T → O
        // La última letra apilada (O) quedará en el tope de la pila.
        // ─────────────────────────────────────────────────────────────────────
        for (int i = 0; i < palabra.length(); i++) {
            char letra = palabra.charAt(i);
            pila.push(letra);   // "apilamos" la bandeja (letra) encima
        }

        // ─────────────────────────────────────────────────────────────────────
        // PASO 5 — Sacar (pop) cada letra de la pila para construir el inverso
        //
        // Ahora "retiramos" bandejas de la cima una por una. Como la última
        // en entrar es la primera en salir, las letras aparecen en orden
        // contrario al original. Con "GATO" obtendremos: O → T → A → G,
        // es decir, la palabra "OTAG". Concatenamos cada letra para formar
        // el String invertido.
        // ─────────────────────────────────────────────────────────────────────
        String palabraInvertida = "";
        while (!pila.isEmpty()) {
            palabraInvertida += pila.pop();  // sacamos la bandeja de la cima
        }
        System.out.println("Palabra invertida: " + palabraInvertida);

        // ─────────────────────────────────────────────────────────────────────
        // PASO 6 — Comparar la palabra original con la invertida
        //
        // Un palíndromo es una palabra que se lee igual de izquierda a derecha
        // que de derecha a izquierda. Si la palabra invertida que construimos
        // con la pila es idéntica a la original, ¡es un palíndromo!
        // Por ejemplo: "RECONOCER" invertida sigue siendo "RECONOCER".
        // En cambio, "COMPUTADORA" invertida da "ARODATUPMOC", que es distinta.
        // ─────────────────────────────────────────────────────────────────────
        if (palabra.equals(palabraInvertida)) {
            System.out.println("¿Es palíndromo? Sí, " + palabra + " es un palíndromo.");
        } else {
            System.out.println("¿Es palíndromo? No, " + palabra + " no es un palíndromo.");
        }

        scanner.close();
    }
}
