package hw02_dynamic_programming_and_testing.app.solver;


import hw02_dynamic_programming_and_testing.app.core.PowerInput;
import hw02_dynamic_programming_and_testing.app.core.Solver;

public class PowerIterativeSolver implements Solver<PowerInput, Double> {

    @Override
    public Double solve(PowerInput input) {
        double base = input.base();
        long exp = input.exp();

        double r = 1.0;

        for (long i = 0; i < exp; i++) {
            r *= base;
        }

        return r;
    }
}
