package hw02_dynamic_programming_and_testing.app.util;

public class MatrixPower {

    public Matrix2x2 pow(Matrix2x2 base, int exp) {

        Matrix2x2 result = Matrix2x2.identity();
        Matrix2x2 a = base;
        int e = exp;

        while (e > 0) {
            if ((e & 1) == 1) result = result.multiply(a);
            a = a.multiply(a);
            e >>= 1;
        }
        return result;
    }
}
