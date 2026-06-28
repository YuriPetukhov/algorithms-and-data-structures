package hw21_dynamic_programming.tasks.fractionsum.algorithm;

public final class FractionSumSolver {

    public long[] solve(int a, int b, int c, int d) {
        long numerator = (long) a * d + (long) c * b;
        long denominator = (long) b * d;

        long gcd = gcd(numerator, denominator);
        numerator /= gcd;
        denominator /= gcd;

        if (denominator < 0) {
            numerator = -numerator;
            denominator = -denominator;
        }

        return new long[]{numerator, denominator};
    }

    private long gcd(long x, long y) {
        while (y != 0) {
            long remainder = x % y;
            x = y;
            y = remainder;
        }
        return Math.abs(x);
    }
}
