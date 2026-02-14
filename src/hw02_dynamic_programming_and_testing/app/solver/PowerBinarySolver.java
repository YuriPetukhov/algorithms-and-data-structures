package hw02_dynamic_programming_and_testing.app.solver;

import hw02_dynamic_programming_and_testing.app.core.PowerInput;
import hw02_dynamic_programming_and_testing.app.core.Solver;

public class PowerBinarySolver implements Solver<PowerInput, Double> {

    @Override
    public Double solve(PowerInput input) {
        double base = input.base();
        long exp = input.exp();

        double res = 1.0;

        int msb = 63 - Long.numberOfLeadingZeros(exp);

        for (int i = msb; i >= 0; i--) {
            res *= res;
            if (((exp >>> i) & 1L) == 1L) {
                res *= base;
            }
        }

        return res;
    }
}
