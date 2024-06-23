package hash;

public class TestC {

    public static void main(String[] args) {
//        HashC<String> tablaHash = new HashC<>(11);
//
//        int[] claves = {34, 3, 7, 30, 11, 8, 7, 23, 41, 16, 34};
//        String[] nombres = {"Manzana", "Uva", "Banana", "Pera", "Papaya", "Piña", "Mandarina", "Cereza", "Guayaba", "Lucuma", "Palta"};
//        //Inserción
//        for (int i = 0; i < claves.length; i++) {
//            tablaHash.insert(claves[i], nombres[i], "linear", "hash");
//        }
//        System.out.println("Tabla Hash Cerrado:");
//        System.out.println(tablaHash);
//        //Búsqueda
//        String resultado = tablaHash.search(7);
//        System.out.println("Resultado de la búsqueda: " + resultado);
//        //Eliminación
//        tablaHash.remove(7);
//        System.out.println("Tabla Hash Cerrado Actualizada:\n" + tablaHash);
//    }
//}
        HashC<String> tablaHash2 = new HashC<>(100);
        int k1 = 7259;
        int h_k1 = tablaHash2.metodoCuadrado(k1);
        tablaHash2.insert(k1, "Ana", "linear", "cuadrado");
        System.out.println("h(k1) (método del cuadrado): " + h_k1);

        HashC<String> tablaHash3 = new HashC<>(300);
        int k2 = 33242546;
        int h_k2 = tablaHash3.metodoPorPliegue(k2);
        tablaHash3.insert(k2, "Julia", "linear", "pliegue");
        System.out.println("h(k2) (método por pliegue suma): " + h_k2);

        System.out.println("Tabla Hash:");
        System.out.println(tablaHash3);
    }

}
