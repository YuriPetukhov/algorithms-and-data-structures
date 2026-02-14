package hw02_dynamic_programming_and_testing.app.solver;

import hw02_dynamic_programming_and_testing.app.core.Solver;

import java.math.BigInteger;

public class FibIterativeSolver implements Solver<Integer, BigInteger> {

    public BigInteger solve(Integer n) {
        BigInteger a = BigInteger.ZERO;
        BigInteger b = BigInteger.ONE;

        for (int i = 0; i < n; i++) {
            BigInteger next = a.add(b);
            a = b;
            b = next;
        }

        return a;
    }
}
