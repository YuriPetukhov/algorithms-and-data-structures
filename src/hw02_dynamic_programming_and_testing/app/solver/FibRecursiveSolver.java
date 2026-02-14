package hw02_dynamic_programming_and_testing.app.solver;

import hw02_dynamic_programming_and_testing.app.core.Solver;

import java.math.BigInteger;

public class FibRecursiveSolver implements Solver<Integer, BigInteger> {

    public BigInteger solve(Integer n) {
        return fib(n);
    }

    private BigInteger fib(int n) {
        if (n == 0) return BigInteger.ZERO;
        if (n == 1) return BigInteger.ONE;
        return fib(n - 1).add(fib(n - 2));
    }
}
