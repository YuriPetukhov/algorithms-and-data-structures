package hw04_dynamic_arrays_and_priority_queue.sparsearray.impl;

import hw04_dynamic_arrays_and_priority_queue.arrays.DynamicArray;
import hw04_dynamic_arrays_and_priority_queue.arrays.impl.FactorArray;
import hw04_dynamic_arrays_and_priority_queue.sparsearray.SparseArray;

public class IndexedSparseArray<T> implements SparseArray<T> {

    private final DynamicArray<Integer> indices = new FactorArray<>(2);
    private final DynamicArray<T> values = new FactorArray<>(2);

    @Override
    public void put(int index, T value) {
        int pos = find(index);

        if (pos >= 0) {
            values.set(pos, value);
            return;
        }

        int insertPos = -pos - 1;

        indices.add(index, insertPos);
        values.add(value, insertPos);
    }

    @Override
    public T get(int index) {
        int pos = find(index);
        return pos >= 0 ? values.get(pos) : null;
    }

    @Override
    public T remove(int index) {
        int pos = find(index);

        if (pos < 0) return null;

        indices.remove(pos);
        return values.remove(pos);
    }

    @Override
    public boolean contains(int index) {
        return find(index) >= 0;
    }

    @Override
    public int size() {
        return values.size();
    }

    private int find(int index) {
        int left = 0;
        int right = indices.size() - 1;

        while (left <= right) {
            int mid = (left + right) >>> 1;
            int midVal = indices.get(mid);

            if (midVal < index) left = mid + 1;
            else if (midVal > index) right = mid - 1;
            else return mid;
        }

        return -(left + 1);
    }
}
