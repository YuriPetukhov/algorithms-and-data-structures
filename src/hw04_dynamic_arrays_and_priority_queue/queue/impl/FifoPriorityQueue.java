package hw04_dynamic_arrays_and_priority_queue.queue.impl;

import hw04_dynamic_arrays_and_priority_queue.arrays.DynamicArray;
import hw04_dynamic_arrays_and_priority_queue.arrays.impl.FactorArray;
import hw04_dynamic_arrays_and_priority_queue.queue.PriorityQueue;

public class FifoPriorityQueue<T> implements PriorityQueue<T> {

    private final DynamicArray<DynamicArray<T>> buckets;
    private int size;
    private int minNonEmptyPriority = -1;

    public FifoPriorityQueue() {
        buckets = new FactorArray<>(2);
        size = 0;
    }

    @Override
    public void enqueue(int priority, T item) {
        if (priority < 0)
            throw new IllegalArgumentException("priority must be >= 0");

        ensureBucket(priority);

        buckets.get(priority).add(item, buckets.get(priority).size());
        size++;

        if (minNonEmptyPriority == -1 || priority < minNonEmptyPriority) {
            minNonEmptyPriority = priority;
        }
    }

    @Override
    public T dequeue() {
        if (isEmpty())
            throw new IllegalStateException("Queue is empty");

        DynamicArray<T> bucket = buckets.get(minNonEmptyPriority);
        T value = bucket.remove(0);
        size--;

        if (bucket.size() == 0) {
            updateMinPriority();
        }

        return value;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }

    private void ensureBucket(int priority) {
        while (buckets.size() <= priority) {
            buckets.add(new FactorArray<>(2), buckets.size());
        }
    }

    private void updateMinPriority() {
        minNonEmptyPriority = -1;

        for (int i = 0; i < buckets.size(); i++) {
            if (buckets.get(i).size() > 0) {
                minNonEmptyPriority = i;
                break;
            }
        }
    }
}


