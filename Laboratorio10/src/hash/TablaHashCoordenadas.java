package hash;
import java.util.ArrayList;
import java.util.List;

class TablaHashCoordenadas {
    private int tamaño;
    private List<List<Pair>> tabla;

    public TablaHashCoordenadas(int tamaño) {
        this.tamaño = tamaño;
        this.tabla = new ArrayList<>(tamaño);
        for (int i = 0; i < tamaño; i++) {
            this.tabla.add(null);
        }
    }

    public int hash(int[] clave) {
        return (clave[0] * 31 + clave[1]) % tamaño;
    }

    public void insertar(int[] clave, String valor) {
        int indice = hash(clave);
        if (tabla.get(indice) == null) {
            tabla.set(indice, new ArrayList<>());
        }
        List<Pair> lista = tabla.get(indice);
        for (Pair pair : lista) {
            if (pair.clave[0] == clave[0] && pair.clave[1] == clave[1]) {
                pair.valor = valor;
                return;
            }
        }
        lista.add(new Pair(clave, valor));
    }

    public String buscar(int[] clave) {
        int indice = hash(clave);
        List<Pair> lista = tabla.get(indice);
        if (lista != null) {
            for (Pair pair : lista) {
                if (pair.clave[0] == clave[0] && pair.clave[1] == clave[1]) {
                    return pair.valor;
                }
            }
        }
        return null;
    }

    public String eliminar(int[] clave) {
        int indice = hash(clave);
        List<Pair> lista = tabla.get(indice);
        if (lista != null) {
            for (int i = 0; i < lista.size(); i++) {
                Pair pair = lista.get(i);
                if (pair.clave[0] == clave[0] && pair.clave[1] == clave[1]) {
                    lista.remove(i);
                    return pair.valor;
                }
            }
        }
        return null;
    }

    private static class Pair {
        int[] clave;
        String valor;

        Pair(int[] clave, String valor) {
            this.clave = clave;
            this.valor = valor;
        }
    }

    public static void main(String[] args) {
        TablaHashCoordenadas tabla = new TablaHashCoordenadas(10);
        tabla.insertar(new int[]{1, 2}, "valor1");
        tabla.insertar(new int[]{3, 4}, "valor2");

        System.out.println(tabla.buscar(new int[]{1, 2})); // Output: valor1
        System.out.println(tabla.buscar(new int[]{3, 4})); // Output: valor2
        System.out.println(tabla.eliminar(new int[]{1, 2})); // Output: valor1
        System.out.println(tabla.buscar(new int[]{1, 2})); // Output: null
    }
}
