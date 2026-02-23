package hw06_sorting_algorithms.libs.sorting.ops;

public final class PlainIntArrayOps implements IntArrayOps {

    public static final PlainIntArrayOps INSTANCE = new PlainIntArrayOps();
    private PlainIntArrayOps() {}

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
