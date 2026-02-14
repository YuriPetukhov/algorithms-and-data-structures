package hw04_dynamic_arrays_and_priority_queue.arrays;

public interface DynamicArray<T> {
    void add(T item, int index);
    T remove(int index);
    T get(int index);
    void set(int index, T item);
    int size();
}


