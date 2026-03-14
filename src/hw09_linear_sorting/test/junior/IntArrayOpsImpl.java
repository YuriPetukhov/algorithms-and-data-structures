package hw09_linear_sorting.test.junior;

import hw06_sorting_algorithms.libs.sorting.ops.IntArrayOps;

public final class IntArrayOpsImpl implements IntArrayOps {

    public static final IntArrayOps DEFAULT = new IntArrayOpsImpl();

    private IntArrayOpsImpl() {
    }

    @Override
    public boolean gt(int[] a, int i, int j) {
        return a[i] > a[j];
    }

    @Override
    public void swap(int[] a, int i, int j) {
        int t = a[i];
        a[i] = a[j];
        a[j] = t;
    }

    @Override
    public void write(int[] a, int i, int value) {
        a[i] = value;
    }
}