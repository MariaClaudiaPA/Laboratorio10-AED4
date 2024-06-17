package hash;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class HashA<E extends Comparable<E>> {

    protected class Element {

        Register<E> reg;

        public Element(Register<E> reg) {
            this.reg = reg;
        }

        @Override
        public String toString() {
            return reg.toString();
        }
    }

    protected ArrayList<ListLinked<Element>> table;
    protected int m;

    public HashA() {
        this.table = new ArrayList<>();
    }

    public HashA(int n) {
        this.m = n;
        this.table = new ArrayList<>(m);
        for (int i = 0; i < m; i++) {
            this.table.add(new ListLinked<>());
        }
    }

    public void setM(int registros) {
        this.m = registros;
        this.table = new ArrayList<>(registros); 
        for (int i = 0; i < registros; i++) {
            this.table.add(new ListLinked<>()); 
        }
    }

    private int functionHash(int key) {
        return key % m;
    }

    public void insert(int key, E value) {
        Register<E> reg = new Register<>(key, value);
        int index = functionHash(key);
        table.get(index).insert(new Element(reg));
    }

    public E search(int key) {
        int index = functionHash(key);
        ListLinked<Element> list = table.get(index);
        Node<Element> actual = list.primero;
        while (actual != null) {
            if (actual.getValor().reg.getKey() == key) {
                return actual.getValor().reg.value;
            }
            actual = actual.getSiguiente();
        }
        return null;
    }

    public void remove(int key) {
        int index = functionHash(key);
        ListLinked<Element> list = table.get(index);
        Node<Element> actual = list.primero;
        while (actual != null) {
            if (actual.getValor().reg.getKey() == key) {
                list.removeNode(actual.getValor());
                return;
            }
            actual = actual.getSiguiente();
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("D.Real\tD.Hash\tRegister\n");
        int i = 0;
        for (ListLinked<Element> list : table) {
            sb.append(i).append("-->\t");
            if (list.isEmpty()) {
                sb.append("vacio\n");
            } else {
                Node<Element> actual = list.primero;
                while (actual != null) {
                    Element item = actual.getValor();
                    sb.append(functionHash(item.reg.getKey())).append("\t").append(item.reg).append("\n");
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
            setM(contLineas);
            br.close();
            BufferedReader br2 = new BufferedReader(new FileReader(filePath));
            String line;
            while ((line = br2.readLine()) != null) {
                String[] parts = line.split(",");
                int key = Integer.parseInt(parts[0].trim());
                String nombre = parts[1].trim();
                String direccion = parts[2].trim();
                Empleado empleado = new Empleado(key, nombre, direccion);
                insert(key, (E) empleado);
            }
            br2.close();
        } catch (IOException e) {
            System.out.println("Excepcion" + e);
        }
        
    }

}
