package hw09_linear_sorting.programs.distribution_sorting;

import hw06_sorting_algorithms.visual.platform.ProgramBuilder;
import hw06_sorting_algorithms.visual.platform.ProgramBundle;
import hw09_linear_sorting.programs.distribution_sorting.ui.DistributionSortingController;

public final class DistributionProgramBuilder implements ProgramBuilder {

    @Override
    public ProgramBundle<?, ?> build() {
        DistributionSortingController controller = new DistributionSortingController();
        return new DistributionSortingProgramBundle(controller);
    }
}