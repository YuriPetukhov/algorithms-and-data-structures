package hw04_dynamic_arrays_and_priority_queue.sparsearray;

public interface SparseArray<T> {
    void put(int index, T value);
    T get(int index);
    T remove(int index);
    int size();
    boolean contains(int index);
}

