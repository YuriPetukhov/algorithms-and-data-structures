package hw15_demukron_algorithm.libs.structures;

import hw04_dynamic_arrays_and_priority_queue.arrays.DynamicArray;
import hw04_dynamic_arrays_and_priority_queue.arrays.impl.FactorArray;

public class FactorArrayStack<T> implements Stack<T> {
    private final DynamicArray<T> data = new FactorArray<>(2);

    @Override
    public void push(T item) {
        data.add(item, data.size());
    }

    @Override
    public T pop() {
        if (isEmpty()) {
            throw new IllegalStateException("Stack is empty");
        }

        return data.remove(data.size() - 1);
    }

    @Override
    public T peek() {
        if (isEmpty()) {
            throw new IllegalStateException("Stack is empty");
        }

        return data.get(data.size() - 1);
    }

    @Override
    public boolean isEmpty() {
        return data.size() == 0;
    }

    @Override
    public int size() {
        return data.size();
    }

    @Override
    public void clear() {
        while (!isEmpty()) {
            pop();
        }
    }
}