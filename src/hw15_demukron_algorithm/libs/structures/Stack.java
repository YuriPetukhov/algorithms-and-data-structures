package hw15_demukron_algorithm.libs.structures;

public interface Stack<T> {
    void push(T item);

    T pop();

    T peek();

    boolean isEmpty();

    int size();

    void clear();
}