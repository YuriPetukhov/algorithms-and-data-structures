package hw04_dynamic_arrays_and_priority_queue.arrays.impl;

import hw04_dynamic_arrays_and_priority_queue.arrays.DynamicArray;

import java.util.ArrayList;
import java.util.Objects;

public class ArrayListWrapper<T> implements DynamicArray<T> {

    private final ArrayList<T> list;

    public ArrayListWrapper() {
        this.list = new ArrayList<>();
    }

    public ArrayListWrapper(int initialCapacity) {
        this.list = new ArrayList<>(Math.max(0, initialCapacity));
    }

    @Override
    public void add(T item, int index) {
        Objects.requireNonNull(item, "item");
        checkAddIndex(index);
        list.add(index, item);
    }

    @Override
    public T remove(int index) {
        checkIndex(index);
        return list.remove(index);
    }

    @Override
    public T get(int index) {
        checkIndex(index);
        return list.get(index);
    }

    @Override
    public void set(int index, T item) {
        Objects.requireNonNull(item, "item");
        checkIndex(index);
        list.set(index, item);
    }

    @Override
    public int size() {
        return list.size();
    }

    private void checkIndex(int index) {
        int size = list.size();
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("index=" + index + ", size=" + size);
        }
    }

    private void checkAddIndex(int index) {
        int size = list.size();
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("index=" + index + ", size=" + size);
        }
    }
}
