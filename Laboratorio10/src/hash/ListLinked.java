package hash;

public class ListLinked<T> implements TDAList<T> {

    protected Node<T> primero;

    public ListLinked() {
        this.primero = null;
    }

    @Override
    public boolean isEmpty() {
        return primero == null;
    }

    @Override
    public int length() {
        int cont = 0;
        Node<T> actual = primero;
        while (actual != null) {
            cont++;
            actual = actual.getSiguiente();
        }
        return cont;
    }

    @Override
    public void destroyList() {
        primero = null;
    }

    @Override
    public int search(T searchItem) {
        int indice = 0;
        Node<T> actual = primero;
        while (actual != null) {
            if (actual.getValor().equals(searchItem)) {
                return indice;
            }
            indice++;
            actual = actual.getSiguiente();
        }
        return -1;
    }

    @Override
    public void insertFirst(T nuevoItem) {
        Node<T> nuevoNodo = new Node<>(nuevoItem);
        nuevoNodo.setSiguiente(primero);
        primero = nuevoNodo;
    }

    @Override
    public void insert(T nuevoItem) {
        Node<T> nuevoNodo = new Node<>(nuevoItem);
        if (primero == null) {
            primero = nuevoNodo;
        } else {
            Node<T> actual = primero;
            while (actual.getSiguiente() != null) {
                actual = actual.getSiguiente();
            }
            actual.setSiguiente(nuevoNodo);
        }
    }

    @Override
    public void removeNode(T borrarItem) {
        if (primero != null) {
            if (primero.getValor().equals(borrarItem)) {
                primero = primero.getSiguiente();
            } else {
                Node<T> actual = primero;
                while (actual.getSiguiente() != null) {
                    if (actual.getSiguiente().getValor().equals(borrarItem)) {
                        actual.setSiguiente(actual.getSiguiente().getSiguiente());
                        return;
                    }
                    actual = actual.getSiguiente();
                }
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        Node<T> actual = primero;
        while (actual != null) {
            sb.append(actual.getValor()).append(" ");
            actual = actual.getSiguiente();
        }
        return sb.toString();
    }  
}
