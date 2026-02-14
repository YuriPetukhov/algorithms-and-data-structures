package hw04_dynamic_arrays_and_priority_queue.demo;

import hw04_dynamic_arrays_and_priority_queue.benchmark.SparseArrayBenchmarkRunner;
import hw04_dynamic_arrays_and_priority_queue.sparsearray.SparseArray;
import hw04_dynamic_arrays_and_priority_queue.sparsearray.impl.IndexedSparseArray;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class SparseArrayDemo {

    public static void main(String[] args) {
        System.out.println("\nLanguage: Java");
        System.out.println("HW04: Dynamic arrays and priority queue: SparseArray");

        SparseArray<Integer> a = new IndexedSparseArray<>();

        a.put(0, 111);
        a.put(1_000_000, 222);
        a.put(5, 333);
        a.put(42, 444);

        System.out.println("\nsize=" + a.size());
        System.out.println("get(0)        = " + a.get(0));
        System.out.println("get(5)        = " + a.get(5));
        System.out.println("get(42)       = " + a.get(42));
        System.out.println("get(1_000_000)= " + a.get(1_000_000));
        System.out.println("get(7)        = " + a.get(7));

        a.put(42, 999);
        System.out.println("\nafter update put(42,999): get(42) = " + a.get(42));

        System.out.println("\nremove(5) -> " + a.remove(5));
        System.out.println("after remove: get(5) = " + a.get(5));
        System.out.println("size=" + a.size());

        if (a.get(7) != null) throw new IllegalStateException("expected null at 7");
        if (a.get(42) == null) throw new IllegalStateException("expected value at 42");

        System.out.println("\nOK");

        System.out.println("\n=== SparseArray Benchmark ===");
        SparseArrayBenchmarkRunner.run(
                List.of(10_000, 100_000, 1_000_000),
                5,
                TimeUnit.MICROSECONDS
        );
    }
}
