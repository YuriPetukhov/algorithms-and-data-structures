package hw06_sorting_algorithms.libs.sorting.gaps;

import java.util.ArrayList;
import java.util.List;

public final class CiuraGaps implements GapSequence {

    private static final int[] BASE = {701, 301, 132, 57, 23, 10, 4, 1};

    @Override public String name() { return "Ciura"; }

    @Override
    public int[] gaps(int n) {
        List<Integer> list = new ArrayList<>();
        for (int g : BASE) if (g < n) list.add(g);
        if (list.isEmpty()) list.add(1);
        return list.stream().mapToInt(Integer::intValue).toArray();
    }

    @Override public String toString() { return display(); }
}
