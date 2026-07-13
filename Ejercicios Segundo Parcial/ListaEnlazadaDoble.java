import java.util.Scanner;

public class ListaEnlazadaDoble {
    Nodo cabeza;
    Nodo cola;

    public void agregarNodoInicio(String datoNodo) {
        Nodo nuevo = new Nodo(datoNodo);
        if (cabeza == null) {
            cabeza = nuevo;
            cola = nuevo;
            return;
        }
        nuevo.siguienteNodo = cabeza;
        cabeza.anteriorNodo = nuevo;
        cabeza = nuevo;
    }

    public void agregarFinal(String datoNodo) {
        Nodo nuevo = new Nodo(datoNodo);
        if (cabeza == null) {
            cabeza = nuevo;
            cola = nuevo;
            return;
        }
        cola.siguienteNodo = nuevo;
        nuevo.anteriorNodo = cola;
        cola = nuevo;
    }

    public void recorrido() {
        Nodo actual = cabeza;
        while (actual != null) {
            System.out.println(actual.Dato);
            actual = actual.siguienteNodo;
        }
    }

    public void recorridoInverso() {
        Nodo actual = cola;
        while (actual != null) {
            System.out.println(actual.Dato);
            actual = actual.anteriorNodo;
        }
    }

    public boolean eliminarNodo(String datoEliminar) {
        if (cabeza == null) return false;

        Nodo actual = cabeza;
        while (actual != null) {
            if (actual.Dato.equals(datoEliminar)) {
                if (actual.anteriorNodo != null) {
                    actual.anteriorNodo.siguienteNodo = actual.siguienteNodo;
                } else {
                    cabeza = actual.siguienteNodo;
                }

                if (actual.siguienteNodo != null) {
                    actual.siguienteNodo.anteriorNodo = actual.anteriorNodo;
                } else {
                    cola = actual.anteriorNodo;
                }

                return true;
            }
            actual = actual.siguienteNodo;
        }
        return false;
    }

    public boolean actualizarNodo(String datoActual, String datoNuevo) {
        Nodo actual = cabeza;
        while (actual != null) {
            if (actual.Dato.equals(datoActual)) {
                actual.Dato = datoNuevo;
                return true;
            }
            actual = actual.siguienteNodo;
        }
        return false;
    }

    public boolean buscarNodo(String datoBuscado) {
        Nodo actual = cabeza;
        while (actual != null) {
            if (actual.Dato.equals(datoBuscado)) {
                System.out.println("Elemento encontrado: " + datoBuscado);
                return true;
            }
            actual = actual.siguienteNodo;
        }
        System.out.println("El elemento \"" + datoBuscado + "\" no existe en la lista.");
        return false;
    }

    public int contarElementos() {
        int contador = 0;
        Nodo actual = cabeza;
        while (actual != null) {
            contador++;
            actual = actual.siguienteNodo;
        }
        return contador;
    }

    public void invertirLista() {
        Nodo actual = cabeza;
        Nodo temp = null;

        while (actual != null) {
            temp = actual.anteriorNodo;
            actual.anteriorNodo = actual.siguienteNodo;
            actual.siguienteNodo = temp;
            actual = actual.anteriorNodo;
        }

        temp = cabeza;
        cabeza = cola;
        cola = temp;
    }

    public static void main(String[] args) {
        ListaEnlazadaDoble lista = new ListaEnlazadaDoble();
        Scanner scanner = new Scanner(System.in);
        int opcion;

        do {
            System.out.println("\n===== MENU LISTA DOBLEMENTE ENLAZADA =====");
            System.out.println("1. Agregar elemento al inicio");
            System.out.println("2. Agregar elemento al final");
            System.out.println("3. Imprimir lista (inicio a fin)");
            System.out.println("4. Imprimir lista (fin a inicio)");
            System.out.println("5. Eliminar un elemento");
            System.out.println("6. Actualizar un elemento");
            System.out.println("7. Buscar un elemento");
            System.out.println("8. Contar elementos");
            System.out.println("9. Invertir la lista");
            System.out.println("10. Salir");
            System.out.print("Elige una opcion: ");

            String entradaOpcion = scanner.nextLine();
            opcion = -1;
            try {
                opcion = Integer.parseInt(entradaOpcion.trim());
            } catch (NumberFormatException e) {
                System.out.println("Por favor ingresa un numero valido del 1 al 10.");
                continue;
            }

            switch (opcion) {
                case 1:
                    System.out.print("Escribe el elemento a agregar al inicio: ");
                    String datoInicio = scanner.nextLine();
                    if (datoInicio.trim().isEmpty()) {
                        System.out.println("No puedes dejar el campo en blanco.");
                    } else {
                        lista.agregarNodoInicio(datoInicio);
                        System.out.println("Elemento agregado al inicio correctamente.");
                    }
                    break;

                case 2:
                    System.out.print("Escribe el elemento a agregar al final: ");
                    String datoFinal = scanner.nextLine();
                    if (datoFinal.trim().isEmpty()) {
                        System.out.println("No puedes dejar el campo en blanco.");
                    } else {
                        lista.agregarFinal(datoFinal);
                        System.out.println("Elemento agregado al final correctamente.");
                    }
                    break;

                case 3:
                    if (lista.cabeza == null) {
                        System.out.println("La lista esta vacia.");
                    } else {
                        System.out.println("Lista actual (inicio -> fin):");
                        lista.recorrido();
                    }
                    break;

                case 4:
                    if (lista.cabeza == null) {
                        System.out.println("La lista esta vacia.");
                    } else {
                        System.out.println("Lista actual (fin -> inicio):");
                        lista.recorridoInverso();
                    }
                    break;

                case 5:
                    if (lista.cabeza == null) {
                        System.out.println("La lista esta vacia. No hay nada que eliminar.");
                    } else {
                        System.out.print("Escribe el elemento a eliminar: ");
                        String datoEliminar = scanner.nextLine();
                        if (datoEliminar.trim().isEmpty()) {
                            System.out.println("No puedes dejar el campo en blanco.");
                        } else {
                            boolean eliminado = lista.eliminarNodo(datoEliminar);
                            if (eliminado) {
                                System.out.println("Elemento eliminado correctamente.");
                            } else {
                                System.out.println("El elemento \"" + datoEliminar + "\" no existe en la lista.");
                            }
                        }
                    }
                    break;

                case 6:
                    if (lista.cabeza == null) {
                        System.out.println("La lista esta vacia. No hay nada que actualizar.");
                    } else {
                        System.out.print("Escribe el elemento que quieres cambiar: ");
                        String datoActual = scanner.nextLine();
                        System.out.print("Escribe el nuevo valor: ");
                        String datoNuevo = scanner.nextLine();
                        if (datoActual.trim().isEmpty() || datoNuevo.trim().isEmpty()) {
                            System.out.println("No puedes dejar los campos en blanco.");
                        } else {
                            boolean actualizado = lista.actualizarNodo(datoActual, datoNuevo);
                            if (actualizado) {
                                System.out.println("Elemento actualizado correctamente.");
                            } else {
                                System.out.println("El elemento \"" + datoActual + "\" no existe en la lista.");
                            }
                        }
                    }
                    break;

                case 7:
                    if (lista.cabeza == null) {
                        System.out.println("La lista esta vacia. No hay nada que buscar.");
                    } else {
                        System.out.print("Escribe el elemento a buscar: ");
                        String datoBuscar = scanner.nextLine();
                        if (datoBuscar.trim().isEmpty()) {
                            System.out.println("No puedes dejar el campo en blanco.");
                        } else {
                            lista.buscarNodo(datoBuscar);
                        }
                    }
                    break;

                case 8:
                    int total = lista.contarElementos();
                    System.out.println("La lista tiene " + total + " elemento(s).");
                    break;

                case 9:
                    if (lista.cabeza == null) {
                        System.out.println("La lista esta vacia. No hay nada que invertir.");
                    } else {
                        lista.invertirLista();
                        System.out.println("Lista invertida correctamente.");
                    }
                    break;

                case 10:
                    System.out.println("Saliendo del programa. ¡Hasta luego!");
                    break;

                default:
                    System.out.println("Opcion no valida. Elige un numero del 1 al 10.");
                    break;
            }

        } while (opcion != 10);

        scanner.close();
    }
}

class Nodo {
    String Dato;
    Nodo siguienteNodo;
    Nodo anteriorNodo;

    public Nodo(String dato) {
        this.Dato = dato;
        this.siguienteNodo = null;
        this.anteriorNodo = null;
    }
}
