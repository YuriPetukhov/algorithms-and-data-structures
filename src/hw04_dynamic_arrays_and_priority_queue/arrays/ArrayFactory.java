package hw04_dynamic_arrays_and_priority_queue.arrays;

import hw04_dynamic_arrays_and_priority_queue.arrays.impl.*;

public final class ArrayFactory {

    public static <T> DynamicArray<T> single() {
        return new SingleArray<>();
    }

    public static <T> DynamicArray<T> vector(int step) {
        return new VectorArray<>(step);
    }

    public static <T> DynamicArray<T> factor(int factor) {
        return new FactorArray<>(factor);
    }

    public static <T> DynamicArray<T> matrix(int block) {
        return new MatrixArray<>(block);
    }

    public static <T> DynamicArray<T> arrayList() {
        return new ArrayListWrapper<>();
    }
}

