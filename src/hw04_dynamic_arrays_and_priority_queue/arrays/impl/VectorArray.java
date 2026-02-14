package hw04_dynamic_arrays_and_priority_queue.arrays.impl;

import hw04_dynamic_arrays_and_priority_queue.arrays.DynamicArray;

import java.util.Objects;

public class VectorArray<T> implements DynamicArray<T> {

    private final int step;
    private Object[] data;
    private int size;

    public VectorArray(int step) {
        if (step <= 0) throw new IllegalArgumentException("step must be > 0");
        this.step = step;
        this.data = new Object[step];
        this.size = 0;
    }

    @Override
    public void add(T item, int index) {
        Objects.requireNonNull(item, "item");
        checkAddIndex(index);

        ensureCapacity(size + 1);

        if (index < size) {
            System.arraycopy(data, index, data, index + 1, size - index);
        }

        data[index] = item;
        size++;
    }

    @Override
    @SuppressWarnings("unchecked")
    public T remove(int index) {
        checkIndex(index);

        Object removed = data[index];

        int tail = size - index - 1;
        if (tail > 0) {
            System.arraycopy(data, index + 1, data, index, tail);
        }

        data[size - 1] = null;
        size--;

        return (T) removed;
    }

    @Override
    @SuppressWarnings("unchecked")
    public T get(int index) {
        checkIndex(index);
        return (T) data[index];
    }

    @Override
    public void set(int index, T item) {
        Objects.requireNonNull(item, "item");
        checkIndex(index);
        data[index] = item;
    }

    @Override
    public int size() {
        return size;
    }

    private void ensureCapacity(int needed) {
        if (needed <= data.length) return;

        int newCap = data.length;
        while (newCap < needed) {
            newCap += step;
        }

        Object[] next = new Object[newCap];
        System.arraycopy(data, 0, next, 0, size);
        data = next;
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("index=" + index + ", size=" + size);
        }
    }

    private void checkAddIndex(int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("index=" + index + ", size=" + size);
        }
    }
}
