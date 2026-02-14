package hw02_dynamic_programming_and_testing.app.util;

import java.math.BigInteger;

public record Matrix2x2(BigInteger a00, BigInteger a01, BigInteger a10, BigInteger a11) {

    public static Matrix2x2 identity() {
        return new Matrix2x2(BigInteger.ONE, BigInteger.ZERO, BigInteger.ZERO, BigInteger.ONE);
    }

    public Matrix2x2 multiply(Matrix2x2 o) {
        BigInteger r00 = a00.multiply(o.a00).add(a01.multiply(o.a10));
        BigInteger r01 = a00.multiply(o.a01).add(a01.multiply(o.a11));
        BigInteger r10 = a10.multiply(o.a00).add(a11.multiply(o.a10));
        BigInteger r11 = a10.multiply(o.a01).add(a11.multiply(o.a11));
        return new Matrix2x2(r00, r01, r10, r11);
    }
}
