package hash;

public class Node<T> {

    private T valor;
    private Node<T> siguiente;

    Node(T valor) {
        this(valor, null);
    }

    Node(T v, Node<T> s) {
        this.valor = v;
        this.siguiente = s;
    }

    public T getValor() {
        return this.valor;
    }

    public Node<T> getSiguiente() {
        return this.siguiente;
    }

    public void setValor(T valor) {
        this.valor = valor;
    }

    public void setSiguiente(Node<T> s) {
        this.siguiente = s;
    }

    @Override
    public String toString() {
        return "Node [valor=" + valor + ", siguiente=" + siguiente + "]";
    }
}
