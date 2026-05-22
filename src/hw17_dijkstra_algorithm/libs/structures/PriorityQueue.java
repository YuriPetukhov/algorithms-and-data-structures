package hw17_dijkstra_algorithm.libs.structures;

public interface PriorityQueue<T> {
    void enqueue(int priority, T item);

    T dequeue();

    boolean isEmpty();

    int size();
}