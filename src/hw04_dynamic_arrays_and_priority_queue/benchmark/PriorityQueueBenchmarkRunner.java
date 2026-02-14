package hw04_dynamic_arrays_and_priority_queue.benchmark;

import hw04_dynamic_arrays_and_priority_queue.queue.PriorityQueue;
import hw04_dynamic_arrays_and_priority_queue.queue.impl.ScanFifoPriorityQueue;
import hw04_dynamic_arrays_and_priority_queue.queue.impl.FifoPriorityQueue;

import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class PriorityQueueBenchmarkRunner {

    public enum Op {
        ENQUEUE_ONLY,
        ENQ_DEQ
    }

    public record Impl(String name, Supplier supplier) {}
    @FunctionalInterface public interface Supplier {
        PriorityQueue<Integer> create();
    }

    public static void run(
            List<Integer> sizes,
            int priorities,
            int runs,
            TimeUnit unit
    ) {
        List<Impl> impls = List.of(
                new Impl("pq_fifo", FifoPriorityQueue::new),
                new Impl("pq_lifo_fast", ScanFifoPriorityQueue::new)
        );

        for (Op op : Op.values()) {
            System.out.println();
            System.out.println("PQ Operation: " + op + " | priorities=" + priorities);
            printHeader(impls);

            for (int n : sizes) {
                StringBuilder row = new StringBuilder();
                row.append(String.format("%10d", n));

                for (Impl impl : impls) {
                    long best = benchOne(impl.supplier(), op, n, priorities, runs);
                    long v = unit.convert(best, TimeUnit.NANOSECONDS);
                    row.append(" | ").append(String.format("%12d", v));
                }

                System.out.println(row);
            }
        }
    }

    private static long benchOne(Supplier supplier, Op op, int n, int priorities, int runs) {
        long best = Long.MAX_VALUE;

        for (int r = 0; r < runs; r++) {
            PriorityQueue<Integer> pq = supplier.create();

            Random rnd = new Random(123456789L);
            int checksum = 0;

            long start = System.nanoTime();

            if (op == Op.ENQUEUE_ONLY) {
                for (int i = 0; i < n; i++) {
                    int pr = rnd.nextInt(priorities);
                    pq.enqueue(pr, i);
                }
            } else {
                for (int i = 0; i < n; i++) {
                    int pr = rnd.nextInt(priorities);
                    pq.enqueue(pr, i);
                }
                for (int i = 0; i < n; i++) {
                    checksum += pq.dequeue();
                }
            }

            long time = System.nanoTime() - start;

            if (checksum == 42) {
                System.out.print("");
            }

            if (time < best) best = time;
        }

        return best;
    }

    private static void printHeader(List<Impl> impls) {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("%10s", "N"));
        for (Impl impl : impls) sb.append(" | ").append(String.format("%12s", impl.name()));
        System.out.println(sb);
        System.out.println("-".repeat(sb.length()));
    }
}
