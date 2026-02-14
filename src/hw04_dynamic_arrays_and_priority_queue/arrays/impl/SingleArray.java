package hw04_dynamic_arrays_and_priority_queue.arrays.impl;

import hw04_dynamic_arrays_and_priority_queue.arrays.DynamicArray;

import java.util.Objects;

public class SingleArray<T> implements DynamicArray<T> {

    private Object[] data = new Object[0];
    private int size = 0;

    @Override
    public void add(T item, int index) {
        Objects.requireNonNull(item, "item");
        checkAddIndex(index);

        Object[] next = new Object[size + 1];

        System.arraycopy(data, 0, next, 0, index);

        next[index] = item;

        System.arraycopy(data, index, next, index + 1, size - index);

        data = next;
        size++;
    }

    @Override
    @SuppressWarnings("unchecked")
    public T remove(int index) {
        checkIndex(index);

        Object removed = data[index];

        Object[] next = new Object[size - 1];

        System.arraycopy(data, 0, next, 0, index);

        System.arraycopy(data, index + 1, next, index, size - index - 1);

        data = next;
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
