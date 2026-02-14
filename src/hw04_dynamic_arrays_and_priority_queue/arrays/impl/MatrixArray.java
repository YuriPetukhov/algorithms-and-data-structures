package hw04_dynamic_arrays_and_priority_queue.arrays.impl;

import hw04_dynamic_arrays_and_priority_queue.arrays.DynamicArray;

import java.util.Objects;

public class MatrixArray<T> implements DynamicArray<T> {

    private final int blockSize;

    private Object[][] blocks = new Object[0][];
    private int size = 0;

    public MatrixArray(int blockSize) {
        if (blockSize <= 0) throw new IllegalArgumentException("blockSize must be > 0");
        this.blockSize = blockSize;
    }

    @Override
    public void add(T item, int index) {
        Objects.requireNonNull(item, "item");
        checkAddIndex(index);

        ensureCapacity(size + 1);

        if (index == size) {
            setAt(size, item);
            size++;
            return;
        }

        for (int i = size; i > index; i--) {
            setAt(i, getAt(i - 1));
        }

        setAt(index, item);
        size++;
    }

    @Override
    @SuppressWarnings("unchecked")
    public T remove(int index) {
        checkIndex(index);

        Object removed = getAt(index);

        for (int i = index; i < size - 1; i++) {
            setAt(i, getAt(i + 1));
        }

        setAt(size - 1, null);
        size--;

        return (T) removed;
    }

    @Override
    @SuppressWarnings("unchecked")
    public T get(int index) {
        checkIndex(index);
        return (T) getAt(index);
    }

    @Override
    public void set(int index, T item) {
        Objects.requireNonNull(item, "item");
        checkIndex(index);
        setAt(index, item);
    }

    @Override
    public int size() {
        return size;
    }

    private void ensureCapacity(int needed) {
        int neededBlocks = (needed + blockSize - 1) / blockSize;
        if (neededBlocks <= blocks.length) return;

        Object[][] next = new Object[neededBlocks][];
        System.arraycopy(blocks, 0, next, 0, blocks.length);

        for (int i = blocks.length; i < neededBlocks; i++) {
            next[i] = new Object[blockSize];
        }

        blocks = next;
    }

    private Object getAt(int index) {
        int b = index / blockSize;
        int o = index % blockSize;
        return blocks[b][o];
    }

    private void setAt(int index, Object value) {
        int b = index / blockSize;
        int o = index % blockSize;
        blocks[b][o] = value;
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
