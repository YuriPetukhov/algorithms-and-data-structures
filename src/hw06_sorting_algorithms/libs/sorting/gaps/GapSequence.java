package hw06_sorting_algorithms.libs.sorting.gaps;
public interface GapSequence {
    String name();
    int[] gaps(int n);

    default String display() { return name(); }
}
