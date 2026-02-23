package hw06_sorting_algorithms.programs.sorting.trace;

import hw06_sorting_algorithms.libs.sorting.ops.IntArrayOps;

public final class TracingIntArrayOps implements IntArrayOps {

    private final TraceRecorder rec;

    public TracingIntArrayOps(TraceRecorder rec) {
        if (rec == null) throw new IllegalArgumentException("rec is null");
        this.rec = rec;
    }

    @Override
    public boolean gt(int[] a, int i, int j) {
        rec.compare(i, j, a[i], a[j]);
        return a[i] > a[j];
    }

    @Override
    public void swap(int[] a, int i, int j) {
        rec.swap(i, j);
        int t = a[i];
        a[i] = a[j];
        a[j] = t;
    }

    @Override
    public void write(int[] a, int i, int value) {
        rec.write(i, value);
        a[i] = value;
    }
}
