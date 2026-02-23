package hw06_sorting_algorithms.libs.sorting.gaps;

import java.util.ArrayList;
import java.util.List;

public final class HalvingGaps implements GapSequence {

    @Override public String name() { return "Standard (n/2)"; }

    @Override
    public int[] gaps(int n) {
        List<Integer> list = new ArrayList<>();
        for (int gap = n / 2; gap > 0; gap /= 2) {
            list.add(gap);
        }
        return list.stream().mapToInt(Integer::intValue).toArray();
    }

    @Override public String toString() { return display(); }
}
