package hw04_dynamic_arrays_and_priority_queue.demo;

import hw04_dynamic_arrays_and_priority_queue.benchmark.DynamicArrayBenchmarkRunner;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class DynamicArrayDemo {
    public static void main(String[] args) {
        System.out.println("\nLanguage: Java");
        System.out.println("HW04: Dynamic arrays and priority queue: DynamicArray");

        System.out.println("\n=== DynamicArray Benchmark ===");

        DynamicArrayBenchmarkRunner.runArraysBenchmark(
                List.of(100, 1_000, 10_000),
                5,
                TimeUnit.MICROSECONDS
        );
    }
}
