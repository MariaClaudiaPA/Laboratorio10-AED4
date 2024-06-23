package hash;

import java.util.ArrayList;
import java.util.List;
class NumeroInvalidoException extends RuntimeException {
    public NumeroInvalidoException(String mensaje) {
        super(mensaje);
    }
}

public class TablaHashSuma {

    private HashA<Integer> tablaHash;

    public TablaHashSuma(int tamano) {
        this.tablaHash = new HashA<>(tamano);
    }

    public List<int[]> encontrarPares(int[] lista, int suma) {
        int maximoEnLista = Integer.MIN_VALUE;
        for (int numero : lista) {
            if (numero > maximoEnLista) {
                maximoEnLista = numero;
            }
        }
        if (suma <= maximoEnLista) {
            throw new NumeroInvalidoException("La suma debe ser mayor que todos los números en la lista.");
        }
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
        int[] lista = {1, 2, 3, 4, 5};
        int suma = 6;

        TablaHashSuma tabla = new TablaHashSuma(10);
        try {
            List<int[]> resultado = tabla.encontrarPares(lista, suma);
            System.out.print("Pares que suman " + suma + ": ");
            for (int[] par : resultado) {
                System.out.print("(" + par[0] + ", " + par[1] + ") ");
            }
        } catch (NumeroInvalidoException e) {
            System.out.println("Excepción: " + e.getMessage());
        }
    }
}
