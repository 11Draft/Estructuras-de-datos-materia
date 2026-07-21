/*
 * # DOCUMENTO DE CAMBIOS Y LÓGICA — Sistema de Gestión de Cursos UTC 2.0
 *
 * ## Objetivo
 * Con este segundo parcial busqué llevar el Sistema de Gestión de Cursos UTC
 * más allá del ArrayList que usé en la primera entrega. Mi meta fue construir
 * mis propias estructuras dinámicas (listas enlazadas simples y dobles) y
 * reforzar el manejo de nodos, referencias y recursividad, tal como lo pide
 * el documento del proyecto integrador. Quise que el sistema conservara toda
 * la funcionalidad anterior, pero ahora sostenida por estructuras que yo
 * mismo implementé desde cero.
 *
 * ## Descripción general del proyecto
 * El sistema administra cursos académicos (clave, nombre, docente, cupo e
 * inscritos) desde un menú de consola. La lista enlazada simple es la
 * estructura principal de almacenamiento y gestión; la lista doblemente
 * enlazada funciona como un módulo secundario de navegación tipo carrusel.
 * Un Stack<String> conserva el historial de acciones, igual que en el primer
 * parcial.
 *
 * ## Cambios respecto al Primer Parcial
 * - Sustituí el ArrayList<Curso> por una ListaSimple implementada con nodos
 *   propios (NodoSimple), porque el objetivo del parcial era dejar de
 *   depender de las colecciones estándar de Java.
 * - Agregué una ListaDoble (NodoDoble) exclusivamente para recorridos en
 *   ambos sentidos y para el navegador carrusel, ya que la lista simple no
 *   permite retroceder de forma natural.
 * - Incorporé una clase Recursividad con métodos que antes hacía con ciclos
 *   for/while, para practicar el pensamiento recursivo que vimos en clase.
 * - Añadí tres métodos adicionales (ordenarPorNombre, obtenerCursoMasLleno y
 *   contarCursosConCupoDisponible) para enriquecer el sistema más allá de lo
 *   mínimo pedido.
 * - Conservé intacta la clase Curso del primer parcial y el uso de
 *   Stack<String> para el historial, tal como se indicó en la consigna.
 *
 * ## Lista Enlazada Simple
 * Una lista enlazada simple es una secuencia de nodos donde cada nodo
 * (NodoSimple) guarda un dato (el Curso) y una referencia al siguiente nodo.
 * No existe una referencia hacia atrás, por lo que el recorrido solo puede
 * hacerse en un sentido, desde la cabeza hasta el último nodo (donde
 * "siguiente" es null). Se eligió como estructura principal porque es la
 * forma más directa de almacenar una colección dinámica de tamaño variable
 * sin usar las clases de colecciones de Java, cumpliendo el requisito del
 * parcial de implementarla manualmente.
 *
 * ## Lista Doblemente Enlazada
 * Cada NodoDoble mantiene dos referencias: "siguiente" y "anterior", lo que
 * permite recorrer la lista en ambos sentidos sin necesidad de reiniciar el
 * recorrido. Esta estructura se sincroniza con la lista simple: cada vez que
 * se agrega un curso, también se agrega al final de la lista doble. Se usa
 * como módulo de navegación de solo lectura (mostrarDeInicioAFin,
 * mostrarDeFinAInicio) y como base del carrusel interactivo, donde un
 * puntero "actual" se desplaza con "siguiente" y "anterior" según la opción
 * elegida por el usuario. Al llegar al último curso, la opción "avanzar"
 * regresa al primer nodo (cabeza), dando al carrusel un comportamiento
 * circular sin necesidad de modificar los enlaces reales de la lista.
 *
 * ## Recursividad
 * - contarCursos(nodo): caso base "nodo == null" retorna 0; caso recursivo
 *   retorna 1 más el conteo del resto de la lista.
 * - buscarPorClaveRecursivo(nodo, clave): caso base "nodo == null" retorna
 *   null; si la clave del nodo actual coincide, retorna ese curso; si no,
 *   se llama recursivamente sobre "nodo.siguiente".
 * - sumarInscritos(nodo): caso base "nodo == null" retorna 0; caso recursivo
 *   suma los inscritos del nodo actual más la suma recursiva del resto.
 * - contarCursosConCupoDisponible(nodo): caso base "nodo == null" retorna 0;
 *   caso recursivo evalúa si el curso actual tiene cupo libre y suma 1 (o 0)
 *   al resultado recursivo del resto de la lista.
 *
 * ## Nuevos métodos adicionales
 * - ordenarPorNombre(): ordena los nodos de la ListaSimple con un bubble
 *   sort que compara nombres e intercambia únicamente los datos (Curso) de
 *   los nodos, dejando los enlaces sin modificar.
 * - obtenerCursoMasLleno(): recorre la lista calculando la proporción
 *   inscritos/cupoMaximo de cada curso y retorna el curso con la proporción
 *   más alta; en caso de empate conserva el primero encontrado.
 * - contarCursosConCupoDisponible(): versión recursiva que cuenta cuántos
 *   cursos todavía tienen al menos un lugar disponible.
 *
 * ## Diagrama de clases (texto)
 *
 *   +----------------+        +----------------+        +----------------+
 *   |     Curso      |<-------|   NodoSimple    |        |    NodoDoble    |
 *   +----------------+  dato  +----------------+        +----------------+
 *   | clave          |        | dato: Curso     |        | dato: Curso     |
 *   | nombre         |        | siguiente       |        | siguiente       |
 *   | docente        |        +----------------+        | anterior        |
 *   | cupoMaximo     |                 ^                 +----------------+
 *   | inscritos      |                 | usa                     ^
 *   +----------------+        +----------------+        | usa
 *                              |   ListaSimple   |        +----------------+
 *                              +----------------+        |   ListaDoble    |
 *                              | cabeza          |        +----------------+
 *                              | agregarCurso()  |        | cabeza / cola   |
 *                              | buscarPorClave() |       | agregarAlFinal()|
 *                              | eliminarPorClave()|      | mostrarInicioFin()|
 *                              | ordenarPorNombre()|      | mostrarFinInicio()|
 *                              | obtenerCursoMasLleno()|  | navegadorCarrusel()|
 *                              +----------------+        +----------------+
 *                                       ^                          ^
 *                                       |         sincroniza       |
 *                                       +--------------------------+
 *                                                   |
 *                                            +----------------+
 *                                            | Recursividad    |
 *                                            +----------------+
 *                                            | contarCursos()  |
 *                                            | buscarRecursivo()|
 *                                            | sumarInscritos()|
 *                                            | contarConCupo() |
 *                                            +----------------+
 *                                                   |
 *                                            +----------------+
 *                                            |      Main       |
 *                                            +----------------+
 *                                            | menú + Stack<String> historial |
 *                                            +----------------+
 *
 * ## Conclusión
 * Construir mis propias listas enlazadas me ayudó a entender de una forma
 * mucho más concreta cómo funcionan por dentro las estructuras que antes solo
 * usaba como caja negra (ArrayList, LinkedList). Trabajar con la lista doble
 * para el carrusel también me dejó claro por qué a veces conviene sacrificar
 * un poco de memoria extra (el puntero "anterior") a cambio de recorridos más
 * flexibles. La parte de recursividad fue la que más me costó al principio,
 * pero repetir el patrón de caso base / caso recursivo en varios métodos
 * terminó de aclarar la lógica. En general siento que este parcial cerró
 * bien el ciclo que empezamos con el sistema del primer parcial.
 */

import java.util.Scanner;
import java.util.Stack;

/**
 * Representa un curso académico del Sistema de Gestión de Cursos UTC.
 * Clase conservada del Primer Parcial: mantiene sus atributos y métodos
 * originales (clave, nombre, docente, cupoMaximo, inscritos) sin eliminar
 * nada de la versión anterior.
 */
class Curso {

    private String clave;
    private String nombre;
    private String docente;
    private int cupoMaximo;
    private int inscritos;

    /**
     * Crea un nuevo curso con cero inscritos.
     *
     * @param clave      identificador único del curso
     * @param nombre     nombre del curso
     * @param docente    nombre del docente responsable
     * @param cupoMaximo número máximo de estudiantes que admite el curso
     */
    public Curso(String clave, String nombre, String docente, int cupoMaximo) {
        this.clave = clave;
        this.nombre = nombre;
        this.docente = docente;
        this.cupoMaximo = cupoMaximo;
        this.inscritos = 0;
    }

    /** @return la clave única del curso */
    public String getClave() { return clave; }

    /** @return el nombre del curso */
    public String getNombre() { return nombre; }

    /** @return el nombre del docente */
    public String getDocente() { return docente; }

    /** @return el cupo máximo permitido */
    public int getCupoMaximo() { return cupoMaximo; }

    /** @return el número actual de inscritos */
    public int getInscritos() { return inscritos; }

    /**
     * Inscribe a un estudiante si hay cupo disponible.
     *
     * @return true si se pudo inscribir, false si el curso está lleno
     */
    public boolean inscribirEstudiante() {
        if (inscritos < cupoMaximo) {
            inscritos++;
            return true;
        }
        return false;
    }

    /**
     * Da de baja a un estudiante si hay inscritos.
     *
     * @return true si se pudo dar de baja, false si no había inscritos
     */
    public boolean darDeBaja() {
        if (inscritos > 0) {
            inscritos--;
            return true;
        }
        return false;
    }

    /** @return true si el curso alcanzó su cupo máximo */
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

/**
 * Nodo de la lista enlazada simple. Guarda un curso y una referencia al
 * siguiente nodo de la lista.
 */
class NodoSimple {
    Curso dato;
    NodoSimple siguiente;

    NodoSimple(Curso dato) {
        this.dato = dato;
        this.siguiente = null;
    }
}

/**
 * Lista enlazada simple implementada manualmente. Es la estructura
 * principal de almacenamiento y gestión de cursos del sistema.
 */
class ListaSimple {
    NodoSimple cabeza;
    private int tamanio;

    ListaSimple() {
        this.cabeza = null;
        this.tamanio = 0;
    }

    /** @return la cantidad de cursos almacenados */
    public int getTamanio() { return tamanio; }

    /**
     * Inserta un curso al final de la lista, validando que la clave no
     * exista previamente.
     *
     * @param c curso a agregar
     * @return true si se agregó, false si la clave ya existía
     */
    public boolean agregarCurso(Curso c) {
        if (buscarPorClave(c.getClave()) != null) {
            return false;
        }
        NodoSimple nuevo = new NodoSimple(c);
        if (cabeza == null) {
            cabeza = nuevo;
        } else {
            NodoSimple actual = cabeza;
            while (actual.siguiente != null) {
                actual = actual.siguiente;
            }
            actual.siguiente = nuevo;
        }
        tamanio++;
        return true;
    }

    /**
     * Recorre e imprime todos los cursos de la lista. Muestra un mensaje
     * si la lista está vacía.
     */
    public void mostrarCursos() {
        if (cabeza == null) {
            System.out.println("No hay cursos registrados aún.");
            return;
        }
        NodoSimple actual = cabeza;
        int contador = 1;
        while (actual != null) {
            System.out.println(contador + ". " + actual.dato);
            actual = actual.siguiente;
            contador++;
        }
        System.out.println("\nTotal de cursos: " + tamanio);
    }

    /**
     * Búsqueda iterativa de un curso por clave.
     *
     * @param clave clave a buscar
     * @return el curso encontrado o null si no existe
     */
    public Curso buscarPorClave(String clave) {
        NodoSimple actual = cabeza;
        while (actual != null) {
            if (actual.dato.getClave().equalsIgnoreCase(clave)) {
                return actual.dato;
            }
            actual = actual.siguiente;
        }
        return null;
    }

    /**
     * Elimina el nodo correspondiente a una clave, validando que exista.
     *
     * @param clave clave del curso a eliminar
     * @return true si se eliminó, false si no existía el curso
     */
    public boolean eliminarPorClave(String clave) {
        if (cabeza == null) {
            return false;
        }
        if (cabeza.dato.getClave().equalsIgnoreCase(clave)) {
            cabeza = cabeza.siguiente;
            tamanio--;
            return true;
        }
        NodoSimple anterior = cabeza;
        NodoSimple actual = cabeza.siguiente;
        while (actual != null) {
            if (actual.dato.getClave().equalsIgnoreCase(clave)) {
                anterior.siguiente = actual.siguiente;
                tamanio--;
                return true;
            }
            anterior = actual;
            actual = actual.siguiente;
        }
        return false;
    }

    /**
     * Ordena los nodos de la lista por nombre usando bubble sort,
     * intercambiando únicamente los datos de los nodos. No hace nada si la
     * lista tiene cero o un elemento.
     */
    public void ordenarPorNombre() {
        if (cabeza == null || cabeza.siguiente == null) {
            return;
        }
        boolean huboIntercambio = true;
        while (huboIntercambio) {
            huboIntercambio = false;
            NodoSimple actual = cabeza;
            while (actual.siguiente != null) {
                if (actual.dato.getNombre().compareToIgnoreCase(actual.siguiente.dato.getNombre()) > 0) {
                    Curso temp = actual.dato;
                    actual.dato = actual.siguiente.dato;
                    actual.siguiente.dato = temp;
                    huboIntercambio = true;
                }
                actual = actual.siguiente;
            }
        }
    }

    /**
     * Recorre la lista y retorna el curso con mayor proporción de inscritos
     * respecto a su cupo máximo. En caso de empate retorna el primero
     * encontrado.
     *
     * @return el curso más lleno, o null si la lista está vacía
     */
    public Curso obtenerCursoMasLleno() {
        if (cabeza == null) {
            return null;
        }
        NodoSimple actual = cabeza;
        Curso masLleno = actual.dato;
        double mejorProporcion = (double) actual.dato.getInscritos() / actual.dato.getCupoMaximo();
        actual = actual.siguiente;
        while (actual != null) {
            double proporcion = (double) actual.dato.getInscritos() / actual.dato.getCupoMaximo();
            if (proporcion > mejorProporcion) {
                mejorProporcion = proporcion;
                masLleno = actual.dato;
            }
            actual = actual.siguiente;
        }
        return masLleno;
    }
}

/**
 * Nodo de la lista doblemente enlazada. Mantiene referencias al nodo
 * siguiente y al nodo anterior.
 */
class NodoDoble {
    Curso dato;
    NodoDoble siguiente;
    NodoDoble anterior;

    NodoDoble(Curso dato) {
        this.dato = dato;
        this.siguiente = null;
        this.anterior = null;
    }
}

/**
 * Lista doblemente enlazada usada como módulo de navegación carrusel y
 * para recorridos bidireccionales. Se mantiene sincronizada con la
 * ListaSimple.
 */
class ListaDoble {
    NodoDoble cabeza;
    NodoDoble cola;

    /**
     * Inserta un curso al final de la lista doble.
     *
     * @param c curso a agregar
     */
    public void agregarAlFinal(Curso c) {
        NodoDoble nuevo = new NodoDoble(c);
        if (cabeza == null) {
            cabeza = nuevo;
            cola = nuevo;
        } else {
            cola.siguiente = nuevo;
            nuevo.anterior = cola;
            cola = nuevo;
        }
    }

    /**
     * Elimina de la lista doble el primer nodo cuya clave coincida.
     * Método auxiliar usado para mantener sincronizadas ambas estructuras.
     *
     * @param clave clave del curso a eliminar
     */
    public void eliminarPorClave(String clave) {
        NodoDoble actual = cabeza;
        while (actual != null) {
            if (actual.dato.getClave().equalsIgnoreCase(clave)) {
                if (actual.anterior != null) {
                    actual.anterior.siguiente = actual.siguiente;
                } else {
                    cabeza = actual.siguiente;
                }
                if (actual.siguiente != null) {
                    actual.siguiente.anterior = actual.anterior;
                } else {
                    cola = actual.anterior;
                }
                return;
            }
            actual = actual.siguiente;
        }
    }

    /** Recorre e imprime la lista del primer al último nodo. */
    public void mostrarDeInicioAFin() {
        if (cabeza == null) {
            System.out.println("No hay cursos registrados aún.");
            return;
        }
        NodoDoble actual = cabeza;
        while (actual != null) {
            System.out.println(actual.dato);
            actual = actual.siguiente;
        }
    }

    /** Recorre e imprime la lista del último al primer nodo. */
    public void mostrarDeFinAInicio() {
        if (cola == null) {
            System.out.println("No hay cursos registrados aún.");
            return;
        }
        NodoDoble actual = cola;
        while (actual != null) {
            System.out.println(actual.dato);
            actual = actual.anterior;
        }
    }

    /**
     * Menú interactivo tipo carrusel: muestra el curso actual y permite
     * avanzar, retroceder o salir. Al avanzar desde el último curso, el
     * carrusel regresa automáticamente al primero (comportamiento
     * circular). Maneja el caso de lista vacía sin lanzar excepciones.
     *
     * @param sc Scanner compartido para leer la entrada del usuario
     */
    public void navegadorCarrusel(Scanner sc) {
        if (cabeza == null) {
            System.out.println("No hay cursos registrados. No es posible navegar el carrusel.");
            return;
        }

        NodoDoble actualNodo = cabeza;
        boolean salir = false;

        while (!salir) {
            System.out.println("\n--- Navegador de cursos (carrusel) ---");
            System.out.println("Curso actual: " + actualNodo.dato);
            System.out.println("1. Ver detalle del curso actual");
            System.out.println("2. Avanzar al siguiente");
            System.out.println("3. Regresar al anterior");
            System.out.println("4. Salir del carrusel");
            System.out.print("Selecciona una opción: ");

            int opcion = -1;
            if (sc.hasNextInt()) {
                opcion = sc.nextInt();
            } else {
                sc.next();
            }
            sc.nextLine();

            switch (opcion) {
                case 1:
                    System.out.println(actualNodo.dato);
                    break;
                case 2:
                    if (actualNodo.siguiente != null) {
                        actualNodo = actualNodo.siguiente;
                    } else {
                        actualNodo = cabeza;
                        System.out.println("Llegaste al final. Regresando al primer curso.");
                    }
                    break;
                case 3:
                    if (actualNodo.anterior != null) {
                        actualNodo = actualNodo.anterior;
                    } else {
                        System.out.println("Ya estás en el primer curso.");
                    }
                    break;
                case 4:
                    salir = true;
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
        }
    }
}

/**
 * Agrupa los métodos recursivos que operan sobre nodos de la lista simple.
 * Ninguno de estos métodos usa ciclos internos; toda la iteración se
 * resuelve mediante llamadas recursivas.
 */
class Recursividad {

    /**
     * Cuenta de forma recursiva el número total de cursos a partir de un
     * nodo dado.
     *
     * @param nodo nodo inicial (usualmente la cabeza de la lista)
     * @return número total de cursos desde ese nodo hasta el final
     */
    public static int contarCursos(NodoSimple nodo) {
        if (nodo == null) {
            return 0;
        }
        return 1 + contarCursos(nodo.siguiente);
    }

    /**
     * Busca de forma recursiva un curso por clave a partir de un nodo dado.
     *
     * @param nodo  nodo inicial
     * @param clave clave a buscar
     * @return el curso encontrado o null si no existe
     */
    public static Curso buscarPorClaveRecursivo(NodoSimple nodo, String clave) {
        if (nodo == null) {
            return null;
        }
        if (nodo.dato.getClave().equalsIgnoreCase(clave)) {
            return nodo.dato;
        }
        return buscarPorClaveRecursivo(nodo.siguiente, clave);
    }

    /**
     * Suma de forma recursiva el total de estudiantes inscritos a partir de
     * un nodo dado.
     *
     * @param nodo nodo inicial
     * @return suma total de inscritos
     */
    public static int sumarInscritos(NodoSimple nodo) {
        if (nodo == null) {
            return 0;
        }
        return nodo.dato.getInscritos() + sumarInscritos(nodo.siguiente);
    }

    /**
     * Cuenta de forma recursiva cuántos cursos tienen al menos un cupo
     * disponible a partir de un nodo dado.
     *
     * @param nodo nodo inicial
     * @return número de cursos con cupo disponible
     */
    public static int contarCursosConCupoDisponible(NodoSimple nodo) {
        if (nodo == null) {
            return 0;
        }
        int resto = contarCursosConCupoDisponible(nodo.siguiente);
        if (nodo.dato.getInscritos() < nodo.dato.getCupoMaximo()) {
            return 1 + resto;
        }
        return resto;
    }
}

/**
 * Clase principal del sistema. Contiene el menú de consola y coordina la
 * ListaSimple, la ListaDoble y el historial de acciones.
 */
public class Curso_2 {

    public static void main(String[] args) {
        ListaSimple listaSimple = new ListaSimple();
        ListaDoble listaDoble = new ListaDoble();
        Stack<String> historial = new Stack<>();
        Scanner sc = new Scanner(System.in);

        int opcion = -1;

        while (opcion != 16) {
            mostrarMenu();
            System.out.print("Selecciona una opción: ");

            if (sc.hasNextInt()) {
                opcion = sc.nextInt();
            } else {
                sc.next();
                opcion = -1;
            }
            sc.nextLine();

            switch (opcion) {
                case 1:
                    agregarCurso(listaSimple, listaDoble, historial, sc);
                    break;
                case 2:
                    System.out.println("\n--- Lista de cursos registrados ---");
                    listaSimple.mostrarCursos();
                    break;
                case 3:
                    buscarCurso(listaSimple, sc);
                    break;
                case 4:
                    eliminarCurso(listaSimple, listaDoble, historial, sc);
                    break;
                case 5:
                    inscribirEstudiante(listaSimple, historial, sc);
                    break;
                case 6:
                    darDeBaja(listaSimple, historial, sc);
                    break;
                case 7:
                    System.out.println("\n--- Cursos de inicio a fin ---");
                    listaDoble.mostrarDeInicioAFin();
                    break;
                case 8:
                    System.out.println("\n--- Cursos de fin a inicio ---");
                    listaDoble.mostrarDeFinAInicio();
                    break;
                case 9:
                    listaDoble.navegadorCarrusel(sc);
                    break;
                case 10:
                    System.out.println("\nTotal de cursos (recursivo): "
                            + Recursividad.contarCursos(listaSimple.cabeza));
                    break;
                case 11:
                    buscarCursoRecursivo(listaSimple, sc);
                    break;
                case 12:
                    ordenarPorNombre(listaSimple, historial);
                    break;
                case 13:
                    obtenerCursoMasLleno(listaSimple);
                    break;
                case 14:
                    System.out.println("\nCursos con cupo disponible (recursivo): "
                            + Recursividad.contarCursosConCupoDisponible(listaSimple.cabeza));
                    break;
                case 15:
                    mostrarHistorial(historial);
                    break;
                case 16:
                    System.out.println("\n¡Hasta luego! Sesión cerrada del Sistema UTC 2.0.");
                    break;
                default:
                    System.out.println("Opción no válida. Por favor elige entre 1 y 16.");
            }
        }

        sc.close();
    }

    /** Imprime el menú principal de 16 opciones. */
    private static void mostrarMenu() {
        System.out.println("\n===== SISTEMA DE GESTIÓN DE CURSOS UTC 2.0 =====");
        System.out.println(" 1. Agregar curso");
        System.out.println(" 2. Mostrar cursos");
        System.out.println(" 3. Buscar curso por clave");
        System.out.println(" 4. Eliminar curso");
        System.out.println(" 5. Inscribir estudiante a curso");
        System.out.println(" 6. Dar de baja estudiante de curso");
        System.out.println(" 7. Mostrar cursos de inicio a fin (lista doble)");
        System.out.println(" 8. Mostrar cursos de fin a inicio (lista doble)");
        System.out.println(" 9. Navegador de cursos (carrusel)");
        System.out.println("10. Contar cursos usando recursividad");
        System.out.println("11. Buscar curso usando recursividad");
        System.out.println("12. Ordenar cursos por nombre");
        System.out.println("13. Obtener curso más lleno");
        System.out.println("14. Contar cursos con cupo disponible (recursividad)");
        System.out.println("15. Mostrar historial de acciones");
        System.out.println("16. Salir");
        System.out.println("=================================================");
    }

    /** Opción 1: agrega un curso a ambas listas y registra la acción. */
    private static void agregarCurso(ListaSimple listaSimple, ListaDoble listaDoble,
                                      Stack<String> historial, Scanner sc) {
        System.out.println("\n--- Agregar nuevo curso ---");

        System.out.print("Clave del curso (ej. ED-101): ");
        String clave = sc.nextLine().trim().toUpperCase();

        if (listaSimple.buscarPorClave(clave) != null) {
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
        sc.nextLine();

        if (cupo <= 0) {
            System.out.println("Error: El cupo debe ser mayor a cero.");
            return;
        }

        Curso nuevo = new Curso(clave, nombre, docente, cupo);
        listaSimple.agregarCurso(nuevo);
        listaDoble.agregarAlFinal(nuevo);
        historial.push("Se agregó el curso: " + nombre + " [" + clave + "]");
        System.out.println("Curso agregado exitosamente: " + nuevo);
    }

    /** Opción 3: busca un curso por clave e indica si fue encontrado. */
    private static void buscarCurso(ListaSimple listaSimple, Scanner sc) {
        System.out.print("\nIngresa la clave del curso a buscar: ");
        String clave = sc.nextLine().trim();

        Curso encontrado = listaSimple.buscarPorClave(clave);

        if (encontrado == null) {
            System.out.println("No se encontró ningún curso con clave: " + clave);
        } else {
            System.out.println("\nCurso encontrado:");
            System.out.println(encontrado);
            System.out.println("Lugares disponibles: "
                    + (encontrado.getCupoMaximo() - encontrado.getInscritos()));
        }
    }

    /** Opción 4: elimina un curso de ambas listas, validando su existencia. */
    private static void eliminarCurso(ListaSimple listaSimple, ListaDoble listaDoble,
                                       Stack<String> historial, Scanner sc) {
        System.out.print("\nClave del curso a eliminar: ");
        String clave = sc.nextLine().trim();

        Curso curso = listaSimple.buscarPorClave(clave);

        if (curso == null) {
            System.out.println("Error: No se encontró el curso con clave: " + clave);
            return;
        }

        System.out.print("¿Confirmas eliminar el curso \"" + curso.getNombre() + "\"? (s/n): ");
        String confirmacion = sc.nextLine().trim().toLowerCase();

        if (confirmacion.equals("s")) {
            listaSimple.eliminarPorClave(clave);
            listaDoble.eliminarPorClave(clave);
            historial.push("Se eliminó el curso: " + curso.getNombre() + " [" + curso.getClave() + "]");
            System.out.println("Curso eliminado exitosamente.");
        } else {
            System.out.println("Operación cancelada.");
        }
    }

    /** Opción 5: inscribe un estudiante validando que haya cupo. */
    private static void inscribirEstudiante(ListaSimple listaSimple, Stack<String> historial, Scanner sc) {
        System.out.print("\nClave del curso para inscribir un estudiante: ");
        String clave = sc.nextLine().trim();

        Curso curso = listaSimple.buscarPorClave(clave);

        if (curso == null) {
            System.out.println("Error: No se encontró el curso con clave: " + clave);
        } else if (curso.estaLleno()) {
            System.out.println("Error: El curso \"" + curso.getNombre()
                    + "\" está lleno (cupo máximo: " + curso.getCupoMaximo() + ").");
        } else {
            curso.inscribirEstudiante();
            historial.push("Se inscribió un estudiante en: " + curso.getNombre() + " [" + curso.getClave() + "]");
            System.out.println("Estudiante inscrito exitosamente.");
            System.out.println("Inscritos actuales: " + curso.getInscritos() + "/" + curso.getCupoMaximo());
        }
    }

    /** Opción 6: da de baja a un estudiante validando que existan inscritos. */
    private static void darDeBaja(ListaSimple listaSimple, Stack<String> historial, Scanner sc) {
        System.out.print("\nClave del curso para dar de baja a un estudiante: ");
        String clave = sc.nextLine().trim();

        Curso curso = listaSimple.buscarPorClave(clave);

        if (curso == null) {
            System.out.println("Error: No se encontró el curso con clave: " + clave);
        } else if (curso.getInscritos() == 0) {
            System.out.println("Error: El curso \"" + curso.getNombre() + "\" no tiene estudiantes inscritos.");
        } else {
            curso.darDeBaja();
            historial.push("Se dio de baja a un estudiante en: " + curso.getNombre() + " [" + curso.getClave() + "]");
            System.out.println("Baja realizada exitosamente.");
            System.out.println("Inscritos actuales: " + curso.getInscritos() + "/" + curso.getCupoMaximo());
        }
    }

    /** Opción 11: busca un curso por clave usando el método recursivo. */
    private static void buscarCursoRecursivo(ListaSimple listaSimple, Scanner sc) {
        System.out.print("\nIngresa la clave del curso a buscar (recursivo): ");
        String clave = sc.nextLine().trim();

        Curso encontrado = Recursividad.buscarPorClaveRecursivo(listaSimple.cabeza, clave);

        if (encontrado == null) {
            System.out.println("No se encontró ningún curso con clave: " + clave);
        } else {
            System.out.println("\nCurso encontrado (búsqueda recursiva):");
            System.out.println(encontrado);
        }
    }

    /** Opción 12: ordena los cursos por nombre y registra la acción. */
    private static void ordenarPorNombre(ListaSimple listaSimple, Stack<String> historial) {
        if (listaSimple.cabeza == null) {
            System.out.println("No hay cursos para ordenar.");
            return;
        }
        listaSimple.ordenarPorNombre();
        historial.push("Se ordenaron " + listaSimple.getTamanio() + " cursos alfabéticamente.");
        System.out.println("\nCursos ordenados alfabéticamente por nombre:");
        listaSimple.mostrarCursos();
    }

    /** Opción 13: muestra el curso con mayor proporción de inscritos. */
    private static void obtenerCursoMasLleno(ListaSimple listaSimple) {
        Curso masLleno = listaSimple.obtenerCursoMasLleno();
        if (masLleno == null) {
            System.out.println("No hay cursos registrados aún.");
        } else {
            System.out.println("\nEl curso más lleno es:");
            System.out.println(masLleno);
        }
    }

    /** Opción 15: muestra el historial de acciones (más reciente primero). */
    private static void mostrarHistorial(Stack<String> historial) {
        System.out.println("\n--- Historial de acciones (más reciente primero) ---");

        if (historial.isEmpty()) {
            System.out.println("El historial está vacío. Realiza algunas acciones primero.");
            return;
        }

        for (int i = historial.size() - 1; i >= 0; i--) {
            System.out.println((historial.size() - i) + ". " + historial.get(i));
        }

        System.out.println("\nTotal de acciones registradas: " + historial.size());
    }
}
