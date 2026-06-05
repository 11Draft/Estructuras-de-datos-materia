import java.util.Scanner;

public class DivisionEnteros {
    public static void main(String[] args) {

        Scanner entrada = new Scanner(System.in);

        // Solicitar números al usuario
        System.out.print("Ingrese el valor de n: ");
        int n = entrada.nextInt();

        System.out.print("Ingrese el valor de m: ");
        int m = entrada.nextInt();

        // Calcular cociente y residuo
        int c = n / m;
        int r = n % m;

        // Mostrar resultado
        System.out.println(n + " entre " + m +
                " da un cociente " + c +
                " y un residuo " + r);

        entrada.close();
    }
}