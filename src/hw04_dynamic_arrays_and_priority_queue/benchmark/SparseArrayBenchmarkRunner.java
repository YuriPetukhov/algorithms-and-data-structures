package hw04_dynamic_arrays_and_priority_queue.benchmark;

import hw04_dynamic_arrays_and_priority_queue.sparsearray.SparseArray;
import hw04_dynamic_arrays_and_priority_queue.sparsearray.impl.IndexedSparseArray;

import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class SparseArrayBenchmarkRunner {

    public enum Op {
        PUT_N,
        PUT_GET_N,
        PUT_REMOVE_N
    }

    public record Impl(String name, Supplier supplier) {}
    @FunctionalInterface public interface Supplier { SparseArray<Integer> create(); }

    public static void run(List<Integer> sizes, int runs, TimeUnit unit) {

        List<Impl> impls = List.of(
                new Impl("indexed", IndexedSparseArray::new)
        );

        for (Op op : Op.values()) {
            System.out.println();
            System.out.println("SparseArray Operation: " + op);
            printHeader(impls);

            for (int n : sizes) {
                StringBuilder row = new StringBuilder();
                row.append(String.format("%10d", n));

                for (Impl impl : impls) {
                    long best = benchOne(impl.supplier(), op, n, runs);
                    long v = unit.convert(best, TimeUnit.NANOSECONDS);
                    row.append(" | ").append(String.format("%12d", v));
                }

                System.out.println(row);
            }
        }
    }

    private static long benchOne(Supplier supplier, Op op, int n, int runs) {
        long best = Long.MAX_VALUE;

        for (int r = 0; r < runs; r++) {
            SparseArray<Integer> a = supplier.create();

            Random rnd = new Random(123456789L);

            int base = 1_000_000_000;
            int checksum = 0;

            long start = System.nanoTime();

            if (op == Op.PUT_N) {
                for (int i = 0; i < n; i++) {
                    int idx = base + i * 2 + (rnd.nextInt(2));
                    a.put(idx, i);
                }
            } else if (op == Op.PUT_GET_N) {
                int[] keys = new int[n];
                for (int i = 0; i < n; i++) {
                    int idx = base + i * 2;
                    keys[i] = idx;
                    a.put(idx, i);
                }
                for (int i = 0; i < n; i++) {
                    Integer v = a.get(keys[i]);
                    checksum += (v == null ? 0 : v);
                }
            } else {
                int[] keys = new int[n];
                for (int i = 0; i < n; i++) {
                    int idx = base + i * 2;
                    keys[i] = idx;
                    a.put(idx, i);
                }
                for (int i = 0; i < n; i++) {
                    Integer v = a.remove(keys[i]);
                    checksum += (v == null ? 0 : v);
                }
            }

            long time = System.nanoTime() - start;

            if (checksum == 42) System.out.print("");

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
