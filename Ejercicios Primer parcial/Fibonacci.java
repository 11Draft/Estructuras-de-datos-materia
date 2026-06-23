public class Fibonacci {
    public static void main(String[] args) {
        int n = 50;
        long primerTermino = 0;
        long segundoTermino = 1;

        System.out.println("Los primeros 50 números de Fibonacci son:");

        for (int i = 1; i <= n; i++) {
            // Imprimimos el término actual con su salto de línea
            System.out.println(primerTermino);

            // Calculamos el siguiente término de la secuencia
            long siguienteTermino = primerTermino + segundoTermino;

            // Actualizamos los valores para la siguiente iteración
            primerTermino = segundoTermino;
            segundoTermino = siguienteTermino;
        }
    }
}
