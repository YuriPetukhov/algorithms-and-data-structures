package hw04_dynamic_arrays_and_priority_queue.queue;

public interface PriorityQueue<T> {
    void enqueue(int priority, T item);
    T dequeue();
    int size();
    boolean isEmpty();
}

