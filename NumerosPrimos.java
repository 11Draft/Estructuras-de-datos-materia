public class NumerosPrimos {
    public static void main(String[] args) {
        System.out.println("Números primos del 1 al 100:");

        // Bucle que recorre los números del 1 al 100
        for (int i = 1; i <= 100; i++) {
            // Llamamos a la función para verificar si 'i' es primo
            if (esPrimo(i)) {
                System.out.println(i);
            }
        }
    }

    /**
     * Método que comprueba si un número es primo o no.
     * Devuelve true si es primo, false en caso contrario.
     */
    public static boolean esPrimo(int numero) {
        // Los números menores o iguales a 1 no son primos
        if (numero <= 1) {
            return false;
        }

        // Optimizamos el bucle evaluando solo hasta la raíz cuadrada del número
        for (int i = 2; i <= Math.sqrt(numero); i++) {
            // Si el residuo es 0, significa que tiene un divisor además de 1 y de sí mismo
            if (numero % i == 0) {
                return false;
            }
        }

        // Si no se encontró ningún divisor, el número es primo
        return true;
    }
}