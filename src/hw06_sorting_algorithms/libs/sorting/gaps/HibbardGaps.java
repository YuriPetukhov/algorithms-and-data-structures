package hw06_sorting_algorithms.libs.sorting.gaps;

import java.util.ArrayList;
import java.util.List;

public final class HibbardGaps implements GapSequence {
    @Override
    public String name() {
        return "Hibbard (2^k-1)";
    }

    @Override
    public int[] gaps(int n) {
        List<Integer> list = new ArrayList<>();
        int k = 1;
        while (true) {
            long h = (1L << k) - 1;
            if (h >= n) break;
            list.add((int) h);
            k++;
        }
        int[] res = new int[list.size()];
        for (int i = 0; i < res.length; i++) {
            res[i] = list.get(res.length - 1 - i);
        }
        if (n > 1 && (res.length == 0 || res[res.length - 1] != 1)) {
            int[] res2 = new int[res.length + 1];
            System.arraycopy(res, 0, res2, 0, res.length);
            res2[res2.length - 1] = 1;
            return res2;
        }
        return res;
    }

    @Override public String toString() { return display(); }
}
