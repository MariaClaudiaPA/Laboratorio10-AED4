package hash;

public class TablaHashCoordenada<E extends Comparable<E>> {

    private HashA<E> tablaHash;
    private int tamano;

    public TablaHashCoordenada(int tamano) {
        this.tamano = tamano;
        this.tablaHash = new HashA<>(tamano);
    }

    public void insertar(int x, int y, E valor) {
        Coordenada coord = new Coordenada(x, y);
        Register<E> reg = new Register<>(coord.hashCode(), valor); 
        tablaHash.insert(coord.hashCode(), reg);
    }

    public E buscar(int x, int y) {
        Coordenada coord = new Coordenada(x, y);
        Register<E> encontrado = tablaHash.search(coord.hashCode());
        return encontrado != null ? encontrado.getValue() : null; 
    }

    public E eliminar(int x, int y) {
        Coordenada coord = new Coordenada(x, y);
        Register<E> encontrado = tablaHash.remove(coord.hashCode());
        return encontrado != null ? encontrado.getValue() : null; 
    }

    public static void main(String[] args) {
        TablaHashCoordenada<String> tabla = new TablaHashCoordenada<>(10);

        tabla.insertar(1, 2, "valor1");
        tabla.insertar(3, 4, "valor2");

        System.out.println("Resultado de la búsqueda:");
        System.out.println("Coordenadas (1, 2): " + tabla.buscar(1, 2)); // Output: valor1
        System.out.println("Coordenadas (3, 4): " + tabla.buscar(3, 4)); // Output: valor2

        System.out.println("\nEliminando el valor en coordenadas (1, 2)...");
        System.out.println("Valor eliminado: " + tabla.eliminar(1, 2)); // Output: valor1

        System.out.println("Coordenadas (1, 2): " + tabla.buscar(1, 2)); // Output: valor none

    }
}
