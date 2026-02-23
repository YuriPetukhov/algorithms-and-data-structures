package hw06_sorting_algorithms.libs.sorting.gaps;

import java.util.ArrayList;
import java.util.List;

public final class KnuthGaps implements GapSequence {

    @Override public String name() { return "Knuth (1,4,13,...)"; }

    @Override
    public int[] gaps(int n) {
        List<Integer> list = new ArrayList<>();
        int h = 1;
        while (h < n) h = 3 * h + 1;
        for (h = (h - 1) / 3; h >= 1; h = (h - 1) / 3) {
            list.add(h);
        }
        return list.stream().mapToInt(Integer::intValue).toArray();
    }

    @Override public String toString() { return display(); }
}
