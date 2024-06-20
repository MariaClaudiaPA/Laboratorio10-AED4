package hash;

public class TestA {

    public static void main(String[] args) {

        HashA<String> tablaHash = new HashA<>(10);
        int[] claves = {34, 3, 7, 30, 11, 8, 7, 23, 41, 16, 34};
        String[] nombres = {"Alice", "Bob", "Charlie", "David", "Eve", "Frank", "Grace", "Heidi", "Ivan", "Judy", "Mallory"};

        for (int i = 0;
                i < claves.length;
                i++) {
            String nombre = nombres[i];
            Register<String> registro = new Register<>(claves[i], nombre);
            tablaHash.insert(claves[i], registro);
        }

        System.out.println(tablaHash);

        System.out.println("Tabla Hash:");
        System.out.println(tablaHash.toString());
        Register<String> busqueda = tablaHash.search(8);

        System.out.println("Busqueda:" + busqueda);
        tablaHash.remove(34);
        System.out.println(tablaHash.toString());

    }
}
