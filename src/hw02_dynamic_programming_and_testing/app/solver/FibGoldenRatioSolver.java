package hw02_dynamic_programming_and_testing.app.solver;

import hw02_dynamic_programming_and_testing.app.core.Solver;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;
import java.math.RoundingMode;

public class FibGoldenRatioSolver implements Solver<Long, BigInteger> {

    private static final int GUARD_DIGITS = 25;

    public BigInteger solve(Long n) {
        if (n == 0) return BigInteger.ZERO;

        int digits = estimateFibDigits(n);
        MathContext mc = new MathContext(digits + GUARD_DIGITS, RoundingMode.HALF_UP);

        BigDecimal sqrt5 = sqrt(BigDecimal.valueOf(5), mc);
        BigDecimal phi = BigDecimal.ONE.add(sqrt5).divide(BigDecimal.valueOf(2), mc);

        BigDecimal term = pow(phi, n, mc).divide(sqrt5, mc);
        BigDecimal withHalf = term.add(BigDecimal.valueOf(0.5), mc);
        BigDecimal floored = withHalf.setScale(0, RoundingMode.FLOOR);

        return floored.toBigIntegerExact();
    }

    private static int estimateFibDigits(long n) {
        double sqrt5 = Math.sqrt(5.0);
        double phi = (1.0 + sqrt5) / 2.0;
        double x = n * Math.log10(phi) - Math.log10(sqrt5);
        return (int) Math.floor(x) + 1;
    }

    private static BigDecimal pow(BigDecimal a, long n, MathContext mc) {
        BigDecimal res = BigDecimal.ONE;
        BigDecimal base = a;
        long e = n;
        while (e > 0) {
            if ((e & 1L) == 1L) res = res.multiply(base, mc);
            e >>>= 1;
            if (e != 0) base = base.multiply(base, mc);
        }
        return res;
    }

    private static BigDecimal sqrt(BigDecimal x, MathContext mc) {
        MathContext mc2 = new MathContext(mc.getPrecision() + 10, mc.getRoundingMode());
        BigDecimal two = BigDecimal.valueOf(2);

        BigDecimal g = new BigDecimal(Math.sqrt(x.doubleValue()), mc2);
        if (g.signum() == 0) g = BigDecimal.ONE;

        for (int i = 0; i < 60; i++) {
            BigDecimal prev = g;
            g = prev.add(x.divide(prev, mc2)).divide(two, mc2);
            if (g.equals(prev)) break;
        }
        return g.round(mc);
    }
}
