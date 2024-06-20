package hash;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class HashA<E extends Comparable<E>> {

    protected ArrayList<ListLinked<Register<E>>> table;
    protected int m;

    public HashA() {
        this.table = new ArrayList<>();
    }

    public HashA(int n) {
        this.m = encontrarPrimo(n);
        this.table = new ArrayList<>(m);
        for (int i = 0; i < m; i++) {
            this.table.add(new ListLinked<>());
        }
    }

    private int functionHash(int key) {
        return key % m;
    }

    public void insert(int key, Register<E> value) {
        int index = functionHash(key);
        ListLinked<Register<E>> list = table.get(index);
        list.insert(value);
    }

    public Register<E> search(int key) {
        int index = functionHash(key);
        ListLinked<Register<E>> list = table.get(index);
        Register<E> dummy = new Register<>(key, null); // Dummy para buscar por clave
        int searchIndex = list.search(dummy);
        if (searchIndex != -1) {
            return list.get(searchIndex);
        }
        return null;
    }

    public Register<E> remove(int key) {
        int index = functionHash(key);
        ListLinked<Register<E>> list = table.get(index);
        Register<E> dummy = new Register<>(key, null); // Dummy para buscar por clave
        return list.removeNode(dummy);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("D.Real\tD.Hash\tRegister\n");
        int i = 0;
        for (ListLinked<Register<E>> list : table) {
            sb.append("\n").append(i).append("-->\t");
            if (list.isEmpty()) {
                sb.append("vacio\n");
            } else {
                Node<Register<E>> actual = list.primero;
                while (actual != null) {
                    Register<E> item = actual.getValor();
                    sb.append(functionHash(item.getKey())).append("\t").append(item).append("\n");
                    actual = actual.getSiguiente();
                }
            }
            i++;
        }
        return sb.toString();
    }

    public void leerArchivo(String filePath) {
        try ( BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            int contLineas = 0;
            while (br.readLine() != null) {
                contLineas++;
            }
            HashA<E> nuevoHashA = new HashA<>(contLineas);
            br.close();
            try ( BufferedReader br2 = new BufferedReader(new FileReader(filePath))) {
                String line;
                while ((line = br2.readLine()) != null) {
                    String[] parts = line.split(",");
                    int key = Integer.parseInt(parts[0].trim());
                    String nombre = parts[1].trim();
                    String direccion = parts[2].trim();
                    Register<E> register = new Register<>(key, (E) new Empleado(key, nombre, direccion));
                    nuevoHashA.insert(key, register);
                }
            }
            this.table = nuevoHashA.table;
            this.m = nuevoHashA.m;
        } catch (IOException e) {
            System.out.println("Excepcion" + e);
        }
    }

    private boolean esPrimo(int num) {
        if (num <= 1) {
            return false;
        }
        for (int i = 2; i * i <= num; i++) {
            if (num % i == 0) {
                return false;
            }
        }
        return true;
    }

    private int encontrarPrimo(int num) {
        if (num <= 2) {
            return 2;
        }

        if (esPrimo(num)) {
            return num;
        }

        for (int i = num - 1; i >= 2; i--) {
            if (esPrimo(i)) {
                return i;
            }
        }

        return 2;
    }

}
