package hw04_dynamic_arrays_and_priority_queue.queue.impl;

import hw04_dynamic_arrays_and_priority_queue.arrays.DynamicArray;
import hw04_dynamic_arrays_and_priority_queue.arrays.impl.FactorArray;
import hw04_dynamic_arrays_and_priority_queue.queue.PriorityQueue;

import java.util.Objects;

public class ScanFifoPriorityQueue<T> implements PriorityQueue<T> {

    private final DynamicArray<DynamicArray<T>> buckets = new FactorArray<>(2);
    private int size = 0;

    private int minNonEmptyPriority = -1;

    @Override
    public void enqueue(int priority, T item) {
        if (priority < 0) throw new IllegalArgumentException("priority must be >= 0");
        Objects.requireNonNull(item, "item");

        ensureBucket(priority);

        DynamicArray<T> bucket = buckets.get(priority);
        bucket.add(item, bucket.size());
        size++;

        if (minNonEmptyPriority == -1 || priority < minNonEmptyPriority) {
            minNonEmptyPriority = priority;
        }
    }

    @Override
    public T dequeue() {
        if (size == 0) throw new IllegalStateException("Queue is empty");

        DynamicArray<T> bucket = buckets.get(minNonEmptyPriority);

        T value = bucket.remove(bucket.size() - 1);
        size--;

        if (bucket.size() == 0) {
            updateMinPriority();
        }

        return value;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void ensureBucket(int priority) {
        while (buckets.size() <= priority) {
            buckets.add(new FactorArray<>(2), buckets.size());
        }
    }

    private void updateMinPriority() {
        minNonEmptyPriority = -1;
        for (int p = 0; p < buckets.size(); p++) {
            if (buckets.get(p).size() > 0) {
                minNonEmptyPriority = p;
                break;
            }
        }
    }
}
