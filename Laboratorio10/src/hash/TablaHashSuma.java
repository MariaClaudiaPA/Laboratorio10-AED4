package hash;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TablaHashSuma {
    private Map<Integer, Integer> tabla;

    public TablaHashSuma() {
        this.tabla = new HashMap<>();
    }

    public void insertar(int clave) {
        tabla.put(clave, clave);
    }

    public boolean buscar(int clave) {
        return tabla.containsKey(clave);
    }

    public List<int[]> encontrarPares(int[] lista, int sumaObjetivo) {
        List<int[]> pares = new ArrayList<>();
        for (int numero : lista) {
            int complemento = sumaObjetivo - numero;
            if (buscar(complemento)) {
                pares.add(new int[]{complemento, numero});
            }
            insertar(numero);
        }
        return pares;
    }

    public static void main(String[] args) {
        int[] lista = {1, 2, 3, 4, 5};
        int suma = 6;

        TablaHashSuma tabla = new TablaHashSuma();
        List<int[]> resultado = tabla.encontrarPares(lista, suma);

        System.out.print("Pares que suman " + suma + ": ");
        for (int[] par : resultado) {
            System.out.print("(" + par[0] + ", " + par[1] + ") ");
        }
        // Output esperado: Pares que suman 6: (2, 4) (1, 5)
    }
}


