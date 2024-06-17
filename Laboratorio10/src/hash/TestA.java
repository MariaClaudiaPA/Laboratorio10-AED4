package hash;

public class TestA {
   public static void main(String[] args) {
        int[] claves = {34, 3, 7, 30, 11, 8, 7, 23, 41, 16, 34};
        String[] nombres = {"Alice", "Bob", "Charlie", "David", "Eve", "Frank", "Grace", "Heidi", "Ivan", "Judy", "Mallory"};

        HashA<String> tablaHash = new HashA<>(10);

        for (int i = 0; i < claves.length; i++) {
            tablaHash.insert(claves[i], nombres[i]);
        }

        System.out.println("Tabla Hash:");
        System.out.println(tablaHash.toString());
    }
}
