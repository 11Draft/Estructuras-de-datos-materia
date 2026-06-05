public class Funciones {

    // ─────────────────────────────────────────────
    // 1. Saludo genérico sin parámetros
    //    Imprime "Hola amigo" cada vez que se invoca.
    // ─────────────────────────────────────────────
    public static void saludar() {
        System.out.println("Hola amigo");
    }

    // ─────────────────────────────────────────────
    // 2. Saludo personalizado
    //    Recibe una cadena "nombre" e imprime "Hola, <nombre>".
    // ─────────────────────────────────────────────
    public static void saludarPorNombre(String nombre) {
        System.out.println("Hola, " + nombre);
    }

    // ─────────────────────────────────────────────
    // 3. Factorial
    //    Recibe un entero positivo y devuelve n!
    //    Usa long para soportar valores grandes (hasta ~20!).
    //    Lanza excepción si el número es negativo.
    // ─────────────────────────────────────────────
    public static long factorial(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("El número debe ser positivo.");
        }
        long resultado = 1;
        for (int i = 2; i <= n; i++) {
            resultado *= i;
        }
        return resultado;
    }

    // ─────────────────────────────────────────────
    // 4a. Total de factura con IVA personalizado
    //     Recibe el subtotal y el porcentaje de IVA a aplicar.
    //     Devuelve el total final (subtotal + IVA).
    // ─────────────────────────────────────────────
    public static double calcularFactura(double subtotal, double porcentajeIVA) {
        double iva = subtotal * (porcentajeIVA / 100.0);
        System.out.printf("  Subtotal: %.2f | IVA (%.0f%%): %.2f%n",
                subtotal, porcentajeIVA, iva);
        return subtotal + iva;
    }

    // ─────────────────────────────────────────────
    // 4b. Total de factura con IVA por defecto (21%)
    //     Sobrecarga del método anterior; si no se indica
    //     el porcentaje, se aplica automáticamente el 21%.
    // ─────────────────────────────────────────────
    public static double calcularFactura(double subtotal) {
        return calcularFactura(subtotal, 21.0);
    }

    // ─────────────────────────────────────────────
    // 5. Área de un círculo
    //    Recibe el radio y devuelve π × r².
    //    Se reutilizará internamente en volumenCilindro().
    // ─────────────────────────────────────────────
    public static double areaCirculo(double radio) {
        return Math.PI * radio * radio;
    }

    // ─────────────────────────────────────────────
    // 6. Volumen de un cilindro
    //    Recibe radio y altura.
    //    Reutiliza areaCirculo() → V = Área base × altura.
    // ─────────────────────────────────────────────
    public static double volumenCilindro(double radio, double altura) {
        return areaCirculo(radio) * altura;
    }

    // ─────────────────────────────────────────────
    // MAIN — Demostración de todas las funciones
    // ─────────────────────────────────────────────
    public static void main(String[] args) {

        System.out.println("══════════════════════════════════════");
        System.out.println(" 1. Saludo genérico");
        System.out.println("══════════════════════════════════════");
        saludar();
        saludar();   // Puede invocarse todas las veces que se quiera

        System.out.println("\n══════════════════════════════════════");
        System.out.println(" 2. Saludo personalizado");
        System.out.println("══════════════════════════════════════");
        saludarPorNombre("Carlos");
        saludarPorNombre("Ana");

        System.out.println("\n══════════════════════════════════════");
        System.out.println(" 3. Factorial");
        System.out.println("══════════════════════════════════════");
        System.out.println("0! = " + factorial(0));   // caso base
        System.out.println("5! = " + factorial(5));   // 120
        System.out.println("10! = " + factorial(10)); // 3628800

        System.out.println("\n══════════════════════════════════════");
        System.out.println(" 4. Cálculo de factura con IVA");
        System.out.println("══════════════════════════════════════");
        double total1 = calcularFactura(200.0);          // IVA 21% por defecto
        System.out.printf("  Total (21%% defecto): %.2f%n", total1);

        double total2 = calcularFactura(200.0, 10.0);    // IVA 10% personalizado
        System.out.printf("  Total (10%% personalizado): %.2f%n", total2);

        System.out.println("\n══════════════════════════════════════");
        System.out.println(" 5. Área del círculo");
        System.out.println("══════════════════════════════════════");
        double radio = 5.0;
        System.out.printf("  Radio = %.1f → Área = %.4f%n", radio, areaCirculo(radio));

        System.out.println("\n══════════════════════════════════════");
        System.out.println(" 6. Volumen del cilindro");
        System.out.println("══════════════════════════════════════");
        double altura = 10.0;
        System.out.printf("  Radio = %.1f, Altura = %.1f → Volumen = %.4f%n",
                radio, altura, volumenCilindro(radio, altura));
    }
}