package hash;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class HashC<E extends Comparable<E>> {

    protected class Element {

        int mark;
        Register<E> reg;

        public Element(int mark, Register<E> reg) {
            this.mark = mark;
            this.reg = reg;
        }
    }

    protected ArrayList<Element> table;
    protected int m;

    public HashC(int n) {
        this.m = obtenerPrimo(n);
        this.table = new ArrayList<>(m);
        for (int i = 0; i < m; i++) {
            this.table.add(new Element(0, null));
        }
    }

    private int functionHash(int key) {
        return key % m;
    }

    private int obtenerPrimo(int n) {
        if (n <= 2) {
            return 2;
        }

        if (esPrimo(n)) {
            return n;
        }

        for (int i = n - 1; i >= 2; i--) {
            if (esPrimo(i)) {
                return i;
            }
        }

        return 2;
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

    private int hash2(int key) {
        return m - (key % m);
    }

    private int linearProbing(int dressHash, int key) {
        int i = 0;
        int newIndex = (dressHash + i) % m;
        while (table.get(newIndex).reg != null && table.get(newIndex).reg.getKey() != key) {
            i++;
            newIndex = (dressHash + i) % m;
            if (i >= m) {
                throw new IllegalStateException("Tabla llena, no se puede insertar más elementos.");
            }
        }

        return newIndex;
    }

    private int quadraticProbing(int dressHash, int key) {
        int i = 0;
        int newIndex = (dressHash + i * i) % m;
        while (table.get(newIndex).reg != null && table.get(newIndex).reg.getKey() != key) {
            i++;
            newIndex = (dressHash + i * i) % m;
            if (i >= m) {
                throw new IllegalStateException("Tabla llena, no se puede insertar más elementos.");
            }
        }
        return newIndex;
    }

    private int doubleHashing(int dressHash, int key) {
        int i = 0;
        int newIndex = (dressHash + i * hash2(key)) % m;
        while (table.get(newIndex).reg != null && table.get(newIndex).reg.getKey() != key) {
            i++;
            newIndex = (dressHash + i * hash2(key)) % m;
            if (i >= m) {
                throw new IllegalStateException("Tabla llena, no se puede insertar más elementos.");
            }
        }
        return newIndex;
    }

    private int plus3Hash(int dressHash, int key) {
        int i = 0;
        int newIndex = (dressHash + 3 * i) % m;
        while (table.get(newIndex).reg != null && table.get(newIndex).reg.getKey() != key) {
            i++;
            newIndex = (dressHash + 3 * i) % m;
            if (i >= m) {
                throw new IllegalStateException("Tabla llena, no se puede insertar más elementos.");
            }
        }
        return newIndex;
    }
    private String lastMethod;
    private String lastDressHash;

    public void insert(int key, E value, String method, String dressHash) {
        Register<E> reg = new Register<>(key, value);
        int index;
        int hashValue;

        switch (method) {
            case "linear" -> {
                hashValue = switch (dressHash) {
                    case "pliegue" ->
                        metodoPorPliegue(key);
                    case "cuadrado" ->
                        metodoCuadrado(key);
                    default ->
                        functionHash(key);
                };
                index = linearProbing(hashValue, key);
                break;

            }
            case "quadratic" -> {
                hashValue = switch (dressHash) {
                    case "pliegue" ->
                        metodoPorPliegue(key);
                    case "cuadrado" ->
                        metodoCuadrado(key);
                    default ->
                        functionHash(key);
                };
                index = quadraticProbing(hashValue, key);
                break;
            }
            case "double" -> {
                hashValue = switch (dressHash) {
                    case "pliegue" ->
                        metodoPorPliegue(key);
                    case "cuadrado" ->
                        metodoCuadrado(key);
                    default ->
                        functionHash(key);
                };
                index = doubleHashing(hashValue, key);
                break;
            }
            case "plus3" -> {
                hashValue = switch (dressHash) {
                    case "pliegue" ->
                        metodoPorPliegue(key);
                    case "cuadrado" ->
                        metodoCuadrado(key);
                    default ->
                        functionHash(key);
                };
                index = plus3Hash(hashValue, key);
                break;
            }
            default ->
                throw new IllegalArgumentException("Método no reconocido: " + method);
        }
        this.lastMethod = method;
        this.lastDressHash = dressHash;

        table.set(index, new Element(1, reg));
    }

    public E search(int key) {
        if (lastMethod == null || lastDressHash == null) {
            return null;
        }

        int dressHash;
        switch (lastDressHash) {
            case "pliegue" ->
                dressHash = metodoPorPliegue(key);
            case "cuadrado" ->
                dressHash = metodoCuadrado(key);
            default ->
                dressHash = functionHash(key);
        }

        int i = 0;
        int newIndex = dressHash;

        while (table.get(newIndex).mark == 1) {
            if (table.get(newIndex).reg != null && table.get(newIndex).reg.getKey() == key) {
                return table.get(newIndex).reg.getValue();
            }
            i++;
            switch (lastMethod) {
                case "linear" ->
                    newIndex = (dressHash + i) % m;
                case "quadratic" ->
                    newIndex = (dressHash + i * i) % m;
                case "double" ->
                    newIndex = (dressHash + i * hash2(key)) % m;
                case "plus3" ->
                    newIndex = (dressHash + 3 * i) % m;
                default ->
                    throw new IllegalArgumentException("Método no reconocido: " + lastMethod);
            }
        }

        return null;
    }

    public void remove(int key) {
        if (lastMethod == null || lastDressHash == null) {
            throw new IllegalStateException("No se ha realizado ninguna inserción previa. No hay nada que eliminar");
        }

        int dressHash;
        switch (lastDressHash) {
            case "pliegue" ->
                dressHash = metodoPorPliegue(key);
            case "cuadrado" ->
                dressHash = metodoCuadrado(key);
            default ->
                dressHash = functionHash(key);
        }

        int i = 0;
        int newIndex = dressHash;

        while (table.get(newIndex).mark != 0) {
            if (table.get(newIndex).reg != null && table.get(newIndex).reg.getKey() == key) {
                table.get(newIndex).mark = 0;
                return;
            }
            i++;
            switch (lastMethod) {
                case "linear" ->
                    newIndex = (dressHash + i) % m;
                case "quadratic" ->
                    newIndex = (dressHash + i * i) % m;
                case "double" ->
                    newIndex = (dressHash + i * hash2(key)) % m;
                case "plus3" ->
                    newIndex = (dressHash + 3 * i) % m;
                default ->
                    throw new IllegalArgumentException("Método no reconocido: " + lastMethod);
            }
        }
    }

    @Override
    public String toString() {
        String s = "D.Real\tD.Hash\tRegister\n";
        int i = 0;
        for (Element item : table) {
            s += (i++) + "-->\t";
            if (item.mark == 1) {
                s += functionHash(item.reg.key) + "\t" + item.reg + "\n";
            } else {
                s += "vacio\n";
            }
        }
        return s;
    }

    public int metodoCuadrado(int key) {
        int cuadrado = key * key;
        String cuadradoStr = Integer.toString(cuadrado);
        int longitud = cuadradoStr.length();

        int inicio = (longitud / 2) - 1;
        int fin = (longitud / 2) + 1;

        if (longitud % 2 != 0) {
            inicio++;
        }

        String digitosCentralesStr = cuadradoStr.substring(inicio, fin);
        int digitosCentrales = Integer.parseInt(digitosCentralesStr);

        return digitosCentrales % m;
    }

    public int metodoPorPliegue(int key) {
        String keyStr = Integer.toString(key);
        int longitud = keyStr.length();
        int numeroPartes = (int) Math.ceil(longitud / 3.0);
        int longitudParte = (int) Math.ceil((double) longitud / numeroPartes);
        int suma = 0;

        for (int i = 0; i < longitud; i += longitudParte) {
            String parte = keyStr.substring(i, Math.min(i + longitudParte, longitud));
            suma += Integer.parseInt(parte);
        }
        return Math.abs(suma % m);
    }

    public void leerArchivo(String archivo, String method, String dressHash) {
        try ( BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] campos = linea.split("[,\\s]+");
                if (campos.length >= 3) {
                    int codigo = Integer.parseInt(campos[0]);
                    String nombre = campos[1];
                    String direccion = campos[2];
                    Empleado empleado = new Empleado(codigo, nombre, direccion);
                    insert(codigo, (E) empleado, method, dressHash);
                } else {
                    System.err.println("Formato incorrecto en la línea: " + linea);
                }
            }
        } catch (IOException e) {
            System.err.println("Error al leer el archivo: " + e.getMessage());
        }
    }

}
