package hash;

public class TablaHashFrecuencia {

    private class Nodo {

        String clave;
        int frecuencia;

        Nodo(String clave, int frecuencia) {
            this.clave = clave;
            this.frecuencia = frecuencia;
        }
    }

    private ListLinked<Nodo>[] tabla;
    private int tama�o;

    @SuppressWarnings("unchecked")
    public TablaHashFrecuencia(int tama�o) {
        this.tama�o = tama�o;
        this.tabla = new ListLinked[tama�o];
        for (int i = 0; i < tama�o; i++) {
            this.tabla[i] = new ListLinked<>();
        }
    }

    private int hash(String clave) {
        return Math.abs(clave.hashCode()) % tama�o;
    }

    public void insertar(String clave) {
        int indice = hash(clave);
        ListLinked<Nodo> lista = tabla[indice];
        Node<TablaHashFrecuencia.Nodo> actual = lista.primero;
        while (actual != null) {
            if (actual.getValor().clave.equals(clave)) {
                actual.getValor().frecuencia++;
                return;
            }
            actual = actual.getSiguiente();
        }
        lista.insertFirst(new Nodo(clave, 1));
    }

    public int frecuencia(String clave) {
        int indice = hash(clave);
        ListLinked<Nodo> lista = tabla[indice];
        Node<TablaHashFrecuencia.Nodo> actual = lista.primero;
        while (actual != null) {
            if (actual.getValor().clave.equals(clave)) {
                return actual.getValor().frecuencia;
            }
            actual = actual.getSiguiente();
        }
        return 0;
    }

     public static void main(String[] args) {
        String texto = "hola mundo hola adios mundo mundo";
        String[] palabras = texto.split(" ");
        TablaHashFrecuencia tabla = new TablaHashFrecuencia(10);

        for (String palabra : palabras) {
            tabla.insertar(palabra);
        }

        System.out.println("Frecuencia de 'hola': " + tabla.frecuencia("hola")); // Output: 2
        System.out.println("Frecuencia de 'mundo': " + tabla.frecuencia("mundo")); // Output: 3
        System.out.println("Frecuencia de 'adios': " + tabla.frecuencia("adios")); // Output: 1
    }
}
