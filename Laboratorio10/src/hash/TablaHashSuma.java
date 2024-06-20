package hash;

import java.util.ArrayList;
import java.util.List;

public class TablaHashSuma {

    private HashA<Integer> tablaHash;

    public TablaHashSuma(int tamano) {
        this.tablaHash = new HashA<>(tamano);
    }

    public List<int[]> encontrarPares(int[] lista, int suma) {
        List<int[]> pares = new ArrayList<>();

        for (int numero : lista) {
            int complemento = suma - numero;
            Register<Integer> complementoEncontrado = tablaHash.search(complemento);
            if (complementoEncontrado != null) {
                pares.add(new int[]{complemento, numero});
            }
            tablaHash.insert(numero, new Register<>(numero, numero));
        }

        return pares;
    }

    public static void main(String[] args) {
        int[] lista = {1, 2};
        int suma = 3;

        TablaHashSuma tabla = new TablaHashSuma(10);
        List<int[]> resultado = tabla.encontrarPares(lista, suma);

        System.out.print("Pares que suman " + suma + ": ");
        for (int[] par : resultado) {
            System.out.print("(" + par[0] + ", " + par[1] + ") ");
        }
    }
}
