package hw04_dynamic_arrays_and_priority_queue.arrays.impl;

import hw04_dynamic_arrays_and_priority_queue.arrays.DynamicArray;

import java.util.Objects;

public class FactorArray<T> implements DynamicArray<T> {

    private Object[] data;
    private int size;
    private final int factor;

    public FactorArray(int factor) {
        if (factor <= 1) throw new IllegalArgumentException("factor must be > 1");
        this.factor = factor;
        this.data = new Object[1];
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

    private void ensureCapacity(int needed) {
        if (needed <= data.length) return;

        int newCap = data.length * factor;
        if (newCap < needed) newCap = needed;

        Object[] next = new Object[newCap];
        System.arraycopy(data, 0, next, 0, size);
        data = next;
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

