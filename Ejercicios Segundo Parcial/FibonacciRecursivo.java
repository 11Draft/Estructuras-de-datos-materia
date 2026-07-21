import java.util.Scanner;

public class FibonacciRecursivo {

    // Método recursivo para calcular Fibonacci
    static int fib(int n) {
        // Casos base
        if (n == 0) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }

        // Caso recursivo
        return fib(n - 1) + fib(n - 2);
    }

    // Método principal
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.print("¿Cuántos números de la secuencia Fibonacci quieres imprimir? ");
        int cantidad = scanner.nextInt();

        // Validación de la entrada
        if (cantidad < 0) {
            System.out.println("La cantidad debe ser un número positivo.");
        } else {
            // Imprimir la secuencia
            for (int i = 0; i < cantidad; i++) {
                System.out.println("fib(" + i + ") = " + fib(i));
            }
        }

        scanner.close();
    }
}