import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;
import java.util.Stack;

// ==============================================================
//  CLASE CURSO — La ficha de cada curso académico
// ==============================================================
class Curso {

    // Atributos privados (encapsulamiento)
    private String clave;
    private String nombre;
    private String docente;
    private int cupoMaximo;
    private int inscritos;

    // Constructor: se ejecuta al hacer new Curso(...)
    public Curso(String clave, String nombre, String docente, int cupoMaximo) {
        this.clave      = clave;
        this.nombre     = nombre;
        this.docente    = docente;
        this.cupoMaximo = cupoMaximo;
        this.inscritos  = 0;
    }

    // --- Getters ---
    public String getClave()    { return clave; }
    public String getNombre()   { return nombre; }
    public String getDocente()  { return docente; }
    public int getCupoMaximo()  { return cupoMaximo; }
    public int getInscritos()   { return inscritos; }

    // --- Métodos de negocio ---
    public boolean inscribirEstudiante() {
        if (inscritos < cupoMaximo) {
            inscritos++;
            return true;
        }
        return false;
    }

    public boolean darDeBaja() {
        if (inscritos > 0) {
            inscritos--;
            return true;
        }
        return false;
    }

    public boolean estaLleno() {
        return inscritos >= cupoMaximo;
    }

    @Override
    public String toString() {
        return String.format(
                "[%s] %-30s | Docente: %-20s | Inscritos: %d/%d",
                clave, nombre, docente, inscritos, cupoMaximo
        );
    }
}

// ==============================================================
//  CLASE PRINCIPAL — Contiene el main y todos los métodos del menú
// ==============================================================
public class SistemaGestionCursos {

    public static void main(String[] args) {

        // Estructuras de datos principales
        ArrayList<Curso> cursos   = new ArrayList<>();
        Stack<String>    historial = new Stack<>();
        Scanner          sc        = new Scanner(System.in);

        int opcion = 0;

        while (opcion != 8) {
            mostrarMenu();
            System.out.print("Selecciona una opción: ");

            // Manejo seguro de entrada no numérica
            if (sc.hasNextInt()) {
                opcion = sc.nextInt();
            } else {
                sc.next(); // Descartar token inválido
                opcion = -1;
            }
            sc.nextLine(); // Limpiar buffer

            switch (opcion) {
                case 1:
                    agregarCurso(cursos, historial, sc);
                    break;
                case 2:
                    mostrarCursos(cursos);
                    break;
                case 3:
                    buscarCurso(cursos, sc);
                    break;
                case 4:
                    inscribirEstudiante(cursos, historial, sc);
                    break;
                case 5:
                    darDeBaja(cursos, historial, sc);
                    break;
                case 6:
                    eliminarCurso(cursos, historial, sc);
                    break;
                case 7:
                    mostrarHistorial(historial);
                    break;
                case 8:
                    System.out.println("\n¡Hasta luego! Sesión cerrada del Sistema UTC.");
                    break;
                case 9:
                    ordenarPorNombre(cursos, historial);
                    break;
                default:
                    System.out.println("Opción no válida. Por favor elige entre 1 y 9.");
            }
        }

        sc.close();
    }

    // ----------------------------------------------------------
    //  Método auxiliar: muestra el menú principal
    // ----------------------------------------------------------
    private static void mostrarMenu() {
        System.out.println("\n========================================");
        System.out.println("       SISTEMA DE GESTIÓN DE CURSOS UTC");
        System.out.println("========================================");
        System.out.println("  1. Agregar curso");
        System.out.println("  2. Mostrar cursos");
        System.out.println("  3. Buscar curso por clave");
        System.out.println("  4. Inscribir estudiante");
        System.out.println("  5. Dar de baja estudiante");
        System.out.println("  6. Eliminar curso");
        System.out.println("  7. Mostrar historial de acciones");
        System.out.println("  8. Salir");
        System.out.println("  9. Ordenar cursos por nombre [EXTRA]");
        System.out.println("========================================");
    }

    // ----------------------------------------------------------
    //  Método auxiliar: busca un curso por clave (retorna null si no existe)
    // ----------------------------------------------------------
    private static Curso buscarPorClave(ArrayList<Curso> cursos, String clave) {
        for (Curso c : cursos) {
            if (c.getClave().equalsIgnoreCase(clave)) {
                return c;
            }
        }
        return null; // No encontrado
    }

    // ----------------------------------------------------------
    //  Opción 1: Agregar curso
    // ----------------------------------------------------------
    private static void agregarCurso(ArrayList<Curso> cursos,
                                     Stack<String> historial,
                                     Scanner sc) {
        System.out.println("\n--- Agregar nuevo curso ---");

        System.out.print("Clave del curso (ej. ED-101): ");
        String clave = sc.nextLine().trim().toUpperCase();

        // Validar que la clave no esté duplicada
        if (buscarPorClave(cursos, clave) != null) {
            System.out.println("Error: Ya existe un curso con la clave " + clave);
            return;
        }

        System.out.print("Nombre del curso: ");
        String nombre = sc.nextLine().trim();

        System.out.print("Docente: ");
        String docente = sc.nextLine().trim();

        System.out.print("Cupo máximo: ");
        int cupo = 0;
        if (sc.hasNextInt()) {
            cupo = sc.nextInt();
        }
        sc.nextLine(); // Limpiar buffer

        if (cupo <= 0) {
            System.out.println("Error: El cupo debe ser mayor a cero.");
            return;
        }

        Curso nuevo = new Curso(clave, nombre, docente, cupo);
        cursos.add(nuevo);
        historial.push("Se agregó el curso: " + nombre + " [" + clave + "]");
        System.out.println("Curso agregado exitosamente: " + nuevo);
    }

    // ----------------------------------------------------------
    //  Opción 2: Mostrar todos los cursos
    // ----------------------------------------------------------
    private static void mostrarCursos(ArrayList<Curso> cursos) {
        System.out.println("\n--- Lista de cursos registrados ---");

        if (cursos.isEmpty()) {
            System.out.println("No hay cursos registrados aún.");
            return;
        }

        System.out.println(String.format("%-8s %-32s %-22s %s",
                "CLAVE", "NOMBRE", "DOCENTE", "INSCRITOS"));
        System.out.println("-".repeat(80));

        for (Curso c : cursos) {
            System.out.println(c);
        }

        System.out.println("\nTotal de cursos: " + cursos.size());
    }

    // ----------------------------------------------------------
    //  Opción 3: Buscar curso por clave
    // ----------------------------------------------------------
    private static void buscarCurso(ArrayList<Curso> cursos, Scanner sc) {
        System.out.print("\nIngresa la clave del curso a buscar: ");
        String clave = sc.nextLine().trim();

        Curso encontrado = buscarPorClave(cursos, clave);

        if (encontrado == null) {
            System.out.println("No se encontró ningún curso con clave: " + clave);
        } else {
            System.out.println("\nCurso encontrado:");
            System.out.println(encontrado);
            System.out.println("Lugares disponibles: "
                    + (encontrado.getCupoMaximo() - encontrado.getInscritos()));
        }
    }

    // ----------------------------------------------------------
    //  Opción 4: Inscribir estudiante
    // ----------------------------------------------------------
    private static void inscribirEstudiante(ArrayList<Curso> cursos,
                                            Stack<String> historial,
                                            Scanner sc) {
        System.out.print("\nClave del curso para inscribir un estudiante: ");
        String clave = sc.nextLine().trim();

        Curso curso = buscarPorClave(cursos, clave);

        if (curso == null) {
            System.out.println("Error: No se encontró el curso con clave: " + clave);
        } else if (curso.estaLleno()) {
            System.out.println("Error: El curso \"" + curso.getNombre()
                    + "\" está lleno (cupo máximo: " + curso.getCupoMaximo() + ").");
        } else {
            curso.inscribirEstudiante();
            historial.push("Se inscribió un estudiante en: " + curso.getNombre()
                    + " [" + curso.getClave() + "]");
            System.out.println("Estudiante inscrito exitosamente.");
            System.out.println("Inscritos actuales: " + curso.getInscritos()
                    + "/" + curso.getCupoMaximo());
        }
    }

    // ----------------------------------------------------------
    //  Opción 5: Dar de baja a un estudiante
    // ----------------------------------------------------------
    private static void darDeBaja(ArrayList<Curso> cursos,
                                  Stack<String> historial,
                                  Scanner sc) {
        System.out.print("\nClave del curso para dar de baja a un estudiante: ");
        String clave = sc.nextLine().trim();

        Curso curso = buscarPorClave(cursos, clave);

        if (curso == null) {
            System.out.println("Error: No se encontró el curso con clave: " + clave);
        } else if (curso.getInscritos() == 0) {
            System.out.println("Error: El curso \"" + curso.getNombre()
                    + "\" no tiene estudiantes inscritos.");
        } else {
            curso.darDeBaja();
            historial.push("Se dio de baja a un estudiante en: " + curso.getNombre()
                    + " [" + curso.getClave() + "]");
            System.out.println("Baja realizada exitosamente.");
            System.out.println("Inscritos actuales: " + curso.getInscritos()
                    + "/" + curso.getCupoMaximo());
        }
    }

    // ----------------------------------------------------------
    //  Opción 6: Eliminar curso
    // ----------------------------------------------------------
    private static void eliminarCurso(ArrayList<Curso> cursos,
                                      Stack<String> historial,
                                      Scanner sc) {
        System.out.print("\nClave del curso a eliminar: ");
        String clave = sc.nextLine().trim();

        Curso curso = buscarPorClave(cursos, clave);

        if (curso == null) {
            System.out.println("Error: No se encontró el curso con clave: " + clave);
        } else {
            System.out.print("¿Confirmas eliminar el curso \""
                    + curso.getNombre() + "\"? (s/n): ");
            String confirmacion = sc.nextLine().trim().toLowerCase();

            if (confirmacion.equals("s")) {
                cursos.remove(curso);
                historial.push("Se eliminó el curso: " + curso.getNombre()
                        + " [" + curso.getClave() + "]");
                System.out.println("Curso eliminado exitosamente.");
            } else {
                System.out.println("Operación cancelada.");
            }
        }
    }

    // ----------------------------------------------------------
    //  Opción 7: Mostrar historial de acciones
    // ----------------------------------------------------------
    private static void mostrarHistorial(Stack<String> historial) {
        System.out.println("\n--- Historial de acciones (más reciente primero) ---");

        if (historial.isEmpty()) {
            System.out.println("El historial está vacío. Realiza algunas acciones primero.");
            return;
        }

        // Recorremos de arriba hacia abajo (más reciente → más antiguo)
        for (int i = historial.size() - 1; i >= 0; i--) {
            System.out.println((historial.size() - i) + ". " + historial.get(i));
        }

        System.out.println("\nTotal de acciones registradas: " + historial.size());
    }

    // ----------------------------------------------------------
    //  Opción 9 (FUNCIONALIDAD ADICIONAL): Ordenar por nombre
    // ----------------------------------------------------------
    private static void ordenarPorNombre(ArrayList<Curso> cursos,
                                         Stack<String> historial) {
        if (cursos.isEmpty()) {
            System.out.println("No hay cursos para ordenar.");
            return;
        }

        Collections.sort(cursos, new Comparator<Curso>() {
            @Override
            public int compare(Curso a, Curso b) {
                return a.getNombre().compareToIgnoreCase(b.getNombre());
            }
        });

        historial.push("Se ordenaron " + cursos.size() + " cursos alfabéticamente.");
        System.out.println("\nCursos ordenados alfabéticamente por nombre:");
        for (Curso c : cursos) {
            System.out.println(c);
        }
    }
}
