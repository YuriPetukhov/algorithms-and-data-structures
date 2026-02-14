package hw02_dynamic_programming_and_testing.app.solver;

import hw02_dynamic_programming_and_testing.app.core.Solver;
import hw02_dynamic_programming_and_testing.app.util.Matrix2x2;
import hw02_dynamic_programming_and_testing.app.util.MatrixPower;

import java.math.BigInteger;

public class FibMatrixSolver implements Solver<Integer, BigInteger> {

    private final MatrixPower power = new MatrixPower();

    public BigInteger solve(Integer n) {
        if (n == 0) return BigInteger.ZERO;

        Matrix2x2 q = new Matrix2x2(
                BigInteger.ONE, BigInteger.ONE,
                BigInteger.ONE, BigInteger.ZERO
        );

        Matrix2x2 qn = power.pow(q, n - 1);
        return qn.a00();
    }
}
