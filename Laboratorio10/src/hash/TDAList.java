package hash;

public interface TDAList<T> {

    boolean isEmpty();
    int length();
    void destroyList();
    int search(T x);
    void insertFirst(T x);
    void insert(T x);
    T removeNode(T x);
    

}
