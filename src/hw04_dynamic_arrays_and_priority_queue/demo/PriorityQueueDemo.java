package hw04_dynamic_arrays_and_priority_queue.demo;

import hw04_dynamic_arrays_and_priority_queue.benchmark.PriorityQueueBenchmarkRunner;
import hw04_dynamic_arrays_and_priority_queue.queue.PriorityQueue;
import hw04_dynamic_arrays_and_priority_queue.queue.impl.ScanFifoPriorityQueue;
import hw04_dynamic_arrays_and_priority_queue.queue.impl.FifoPriorityQueue;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class PriorityQueueDemo {

    public static void main(String[] args) {
        System.out.println("\nLanguage: Java");
        System.out.println("HW04: Dynamic arrays and priority queue: PriorityQueue");

        demoPriorityQueue(
                "Optimized (LIFO inside priority)",
                new FifoPriorityQueue<>()
        );

        demoPriorityQueue(
                "Scan FIFO (remove(0))",
                new ScanFifoPriorityQueue<>()
        );

        System.out.println("\n=== PriorityQueue Benchmark ===");

        PriorityQueueBenchmarkRunner.run(
                List.of(10_000,100_000,1_000_000),
                10,
                5,
                TimeUnit.MICROSECONDS
        );
    }

    private static void demoPriorityQueue(String name, PriorityQueue<String> pq) {
        System.out.println("\n=== " + name + " ===");

        pq.enqueue(2, "low-1");
        pq.enqueue(0, "high-1");
        pq.enqueue(1, "mid-1");
        pq.enqueue(0, "high-2");
        pq.enqueue(2, "low-2");

        System.out.println("Dequeuing:");

        while (!pq.isEmpty()) {
            System.out.println("  -> " + pq.dequeue());
        }
    }

}
