package hw09_linear_sorting.programs.binary_sorting.variants;

import hw09_linear_sorting.programs.binary_sorting.solver.BinarySortSolver;
import hw09_linear_sorting.programs.binary_sorting.spi.BinarySortingParams;

public interface BinarySortingVariant {

    String id();
    String displayName();

    BinarySortSolver build(BinarySortingParams params);
}
