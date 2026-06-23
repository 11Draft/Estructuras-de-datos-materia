public class FizzBuzz {
    public static void main(String[] args) {
        for (int i = 1; i <= 100; i++) {
            // ¿Es múltiplo de 3 y de 5? (Equivale a ser múltiplo de 15)
            if (i % 3 == 0 && i % 5 == 0) {
                System.out.println("fizzbuzz");
            }
            // ¿Es múltiplo de 3?
            else if (i % 3 == 0) {
                System.out.println("fizz");
            }
            // ¿Es múltiplo de 5?
            else if (i % 5 == 0) {
                System.out.println("buzz");
            }
            // Si no es ninguno de los anteriores, se imprime el número
            else {
                System.out.println(i);
            }
        }
    }
}
