package hw21_dynamic_programming.tasks.fiveeight.algorithm;

import java.math.BigInteger;

public final class FiveEightCounter {

    public BigInteger count(int n) {
        if (n == 1) {
            return BigInteger.TWO;
        }

        BigInteger endOneRun = BigInteger.TWO;
        BigInteger endTwoRun = BigInteger.ZERO;

        for (int length = 2; length <= n; length++) {
            BigInteger nextOneRun = endOneRun.add(endTwoRun);
            BigInteger nextTwoRun = endOneRun;
            endOneRun = nextOneRun;
            endTwoRun = nextTwoRun;
        }
        return endOneRun.add(endTwoRun);
    }
}
