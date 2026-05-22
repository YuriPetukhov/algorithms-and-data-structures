package hw17_dijkstra_algorithm.libs.structures;

public class BinaryHeapPriorityQueue<T> implements PriorityQueue<T> {
    private static final int DEFAULT_CAPACITY = 16;

    private HeapNode<T>[] heap;
    private int size;

    @SuppressWarnings("unchecked")
    public BinaryHeapPriorityQueue() {
        this.heap = (HeapNode<T>[]) new HeapNode[DEFAULT_CAPACITY];
    }

    @Override
    public void enqueue(int priority, T item) {
        ensureCapacity(size + 1);

        heap[size] = new HeapNode<>(priority, item);
        siftUp(size);
        size++;
    }

    @Override
    public T dequeue() {
        if (isEmpty()) {
            throw new IllegalStateException("Priority queue is empty");
        }

        T result = heap[0].item;

        size--;
        heap[0] = heap[size];
        heap[size] = null;

        if (!isEmpty()) {
            siftDown(0);
        }

        return result;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }

    private void siftUp(int index) {
        while (index > 0) {
            int parent = (index - 1) / 2;

            if (heap[parent].priority <= heap[index].priority) {
                break;
            }

            swap(parent, index);
            index = parent;
        }
    }

    private void siftDown(int index) {
        while (true) {
            int left = index * 2 + 1;
            int right = index * 2 + 2;
            int smallest = index;

            if (left < size && heap[left].priority < heap[smallest].priority) {
                smallest = left;
            }

            if (right < size && heap[right].priority < heap[smallest].priority) {
                smallest = right;
            }

            if (smallest == index) {
                break;
            }

            swap(index, smallest);
            index = smallest;
        }
    }

    @SuppressWarnings("unchecked")
    private void ensureCapacity(int requiredCapacity) {
        if (requiredCapacity <= heap.length) {
            return;
        }

        HeapNode<T>[] next = (HeapNode<T>[]) new HeapNode[heap.length * 2];
        System.arraycopy(heap, 0, next, 0, heap.length);
        heap = next;
    }

    private void swap(int first, int second) {
        HeapNode<T> tmp = heap[first];
        heap[first] = heap[second];
        heap[second] = tmp;
    }

    private record HeapNode<T>(int priority, T item) {
    }
}