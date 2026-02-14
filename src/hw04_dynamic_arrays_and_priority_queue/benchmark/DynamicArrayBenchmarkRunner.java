package hw04_dynamic_arrays_and_priority_queue.benchmark;

import hw04_dynamic_arrays_and_priority_queue.arrays.ArrayFactory;
import hw04_dynamic_arrays_and_priority_queue.arrays.DynamicArray;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class DynamicArrayBenchmarkRunner {

    public enum Op {
        ADD_END,
        ADD_BEGIN,
        ADD_MIDDLE,
        REMOVE_END,
        REMOVE_BEGIN,
        REMOVE_MIDDLE
    }

    public record Impl(String name, ArraySupplier supplier) {}
    @FunctionalInterface public interface ArraySupplier { DynamicArray<Integer> create(); }

    public static void runArraysBenchmark(
            List<Integer> sizes,
            int runs,
            TimeUnit unit
    ) {
        List<Impl> impls = List.of(
                new Impl("single", ArrayFactory::single),
                new Impl("vector+100", () -> ArrayFactory.vector(100)),
                new Impl("factor*2", () -> ArrayFactory.factor(2)),
                new Impl("matrix#100", () -> ArrayFactory.matrix(100)),
                new Impl("arraylist", ArrayFactory::arrayList)
        );

        for (Op op : Op.values()) {
            System.out.println();
            System.out.println("Operation: " + op);
            printTableHeader(impls);

            for (int n : sizes) {
                Map<String, Long> row = new LinkedHashMap<>();
                for (Impl impl : impls) {
                    long bestNanos = benchOne(impl.supplier(), op, n, runs);
                    long v = unit.convert(bestNanos, TimeUnit.NANOSECONDS);
                    row.put(impl.name(), v);
                }
                printRow(n, row);
            }
        }
    }

    private static long benchOne(ArraySupplier supplier, Op op, int n, int runs) {
        long best = Long.MAX_VALUE;

        for (int r = 0; r < runs; r++) {
            DynamicArray<Integer> a = supplier.create();

            long t = switch (op) {
                case ADD_END -> timeAddEnd(a, n);
                case ADD_BEGIN -> timeAddBegin(a, n);
                case ADD_MIDDLE -> timeAddMiddle(a, n);
                case REMOVE_END -> timeRemoveEnd(a, n);
                case REMOVE_BEGIN -> timeRemoveBegin(a, n);
                case REMOVE_MIDDLE -> timeRemoveMiddle(a, n);
            };

            if (t < best) best = t;
        }
        return best;
    }

    private static long timeAddEnd(DynamicArray<Integer> a, int n) {
        long start = System.nanoTime();
        for (int i = 0; i < n; i++) a.add(i, a.size());
        return System.nanoTime() - start;
    }

    private static long timeAddBegin(DynamicArray<Integer> a, int n) {
        long start = System.nanoTime();
        for (int i = 0; i < n; i++) a.add(i, 0);
        return System.nanoTime() - start;
    }

    private static long timeAddMiddle(DynamicArray<Integer> a, int n) {
        long start = System.nanoTime();
        for (int i = 0; i < n; i++) a.add(i, a.size() / 2);
        return System.nanoTime() - start;
    }

    private static long timeRemoveEnd(DynamicArray<Integer> a, int n) {
        for (int i = 0; i < n; i++) a.add(i, a.size());
        long start = System.nanoTime();
        for (int i = 0; i < n; i++) a.remove(a.size() - 1);
        return System.nanoTime() - start;
    }

    private static long timeRemoveBegin(DynamicArray<Integer> a, int n) {
        for (int i = 0; i < n; i++) a.add(i, a.size());
        long start = System.nanoTime();
        for (int i = 0; i < n; i++) a.remove(0);
        return System.nanoTime() - start;
    }

    private static long timeRemoveMiddle(DynamicArray<Integer> a, int n) {
        for (int i = 0; i < n; i++) a.add(i, a.size());
        long start = System.nanoTime();
        for (int i = 0; i < n; i++) a.remove(a.size() / 2);
        return System.nanoTime() - start;
    }

    private static void printTableHeader(List<Impl> impls) {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("%10s", "N"));
        for (Impl impl : impls) sb.append(" | ").append(String.format("%12s", impl.name()));
        System.out.println(sb);
        System.out.println("-".repeat(sb.length()));
    }

    private static void printRow(int n, Map<String, Long> values) {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("%10d", n));
        for (long v : values.values()) sb.append(" | ").append(String.format("%12d", v));
        System.out.println(sb);
    }
}
