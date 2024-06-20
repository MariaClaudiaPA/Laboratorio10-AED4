package hash;

public class TablaHashFrecuencia {

    private HashC<Contador> tablaHash;

    public TablaHashFrecuencia(int tamano) {
        tablaHash = new HashC<>(tamano);
    }

 
    public void insertarPalabra(String palabra) {
        int hashCode = palabra.hashCode();
        Contador contador = tablaHash.search(hashCode);

        if (contador == null) {
            contador = new Contador(palabra, 1);
            tablaHash.insert(hashCode, contador, "linear", "default");
        } else {
            contador.incrementar();
            tablaHash.remove(hashCode); // Remover el contador antiguo
            tablaHash.insert(hashCode, contador, "linear", "default"); // Insertar el contador actualizado
        }
    }
    public int obtenerFrecuencia(String palabra) {
        Contador contador = tablaHash.search(palabra.hashCode());
        if (contador != null) {
            return contador.getCuenta();
        }
        return 0;
    }

    private class Contador implements Comparable<Contador> {

        private String palabra;
        private int cuenta;

        public Contador(String palabra, int cuenta) {
            this.palabra = palabra;
            this.cuenta = cuenta;
        }

        public void incrementar() {
            this.cuenta++;
        }

        public int getCuenta() {
            return this.cuenta;
        }

        @Override
        public int compareTo(Contador o) {
            return this.palabra.compareTo(o.palabra);
        }

        @Override
        public String toString() {
            return palabra + ": " + cuenta;
        }
    }

    public static void main(String[] args) {
        String texto = "hola mundo hola adios mundo mundo";
        String[] palabras = texto.split(" ");
        TablaHashFrecuencia tabla = new TablaHashFrecuencia(10);

        for (String palabra : palabras) {
            tabla.insertarPalabra(palabra);
        }

        System.out.println("Frecuencia de 'hola': " + tabla.obtenerFrecuencia("hola")); // Output: 2
        System.out.println("Frecuencia de 'mundo': " + tabla.obtenerFrecuencia("mundo")); // Output: 3
        System.out.println("Frecuencia de 'adios': " + tabla.obtenerFrecuencia("adios")); // Output: 1
    }
}
