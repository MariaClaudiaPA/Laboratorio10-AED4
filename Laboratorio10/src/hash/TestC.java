package hash;

public class TestC {

    public static void main(String[] args) {
        HashC<String> tablaHash = new HashC<>(11);

        int[] claves = {34, 3, 7, 30, 11, 8, 7, 23, 41, 16, 34};
        String[] nombres = {"Alice", "Bob", "Charlie", "David", "Eve", "Frank", "Grace", "Heidi", "Ivan", "Judy", "Mallory"};

        for (int i = 0; i < claves.length; i++) {
            tablaHash.insert(claves[i], nombres[i], "linear", "hash");
        }

        System.out.println("Tabla Hash Cerrado:");
        System.out.println(tablaHash);
        String resultado = tablaHash.search(7, "linear");
        System.out.println("Resultado de la búsqueda: " + resultado);
        tablaHash.remove(7, "linear");
        System.out.println(tablaHash);

//        HashC<String> tablaHash2 = new HashC<>(100);
//        int k1 = 7259;
//        int h_k1 = tablaHash2.metodoCuadrado(k1);
//        tablaHash2.insert(k1, "Ana", "linear", "cuadrado");
//        System.out.println("h(k1) (método del cuadrado): " + h_k1);
//    
//        int k2 = 33249696;
//        int h_k2 = tablaHash2.metodoPorPliegue(k2);
//        tablaHash2.insert(k2, "Ejemplo 2", "linear", "pliegue"); 
//        System.out.println("h(k2) (método por pliegue suma): " + h_k2);
//
//        System.out.println("Tabla Hash:");
//        System.out.println(tablaHash2);
    }

}
