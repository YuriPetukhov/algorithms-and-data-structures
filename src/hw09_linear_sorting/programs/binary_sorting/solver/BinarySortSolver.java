package hw09_linear_sorting.programs.binary_sorting.solver;

import hw09_linear_sorting.programs.binary_sorting.spi.BinarySortJob;
import hw09_linear_sorting.programs.binary_sorting.spi.BinarySortResult;

public interface BinarySortSolver {

    BinarySortResult solve(BinarySortJob job) throws Exception;
}
