public class Perro {

    // ── Atributos ──────────────────────────────────────────
    String nombre;
    String raza;
    int    edad;

    // ── Constructor ────────────────────────────────────────
    public Perro(String nombre, String raza, int edad) {
        this.nombre = nombre;
        this.raza   = raza;
        this.edad   = edad;
    }

    // ── Métodos ────────────────────────────────────────────
    public void ladrar() {
        System.out.println(nombre + " dice: ¡Guau! ¡Guau!");
    }

    public void presentarse() {
        System.out.println("Hola, me llamo " + nombre +
                           ", soy un/a " + raza +
                           " y tengo " + edad + " año(s).");
    }

    // ── Main para probar ───────────────────────────────────
    public static void main(String[] args) {
        Perro miPerro = new Perro("Max", "Labrador", 3);

        miPerro.presentarse();
        miPerro.ladrar();
    }
}
