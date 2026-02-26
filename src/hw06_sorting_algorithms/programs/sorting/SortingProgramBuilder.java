package hw06_sorting_algorithms.programs.sorting;

import hw06_sorting_algorithms.programs.sorting.ui.SortingController;
import hw06_sorting_algorithms.visual.platform.ProgramBuilder;
import hw06_sorting_algorithms.visual.platform.ProgramBundle;
import hw06_sorting_algorithms.visual.programs.sorting.BarsSceneAdapter;

public final class SortingProgramBuilder implements ProgramBuilder {

    @Override
    public ProgramBundle<?, ?> build() {
        SortingController controller = new SortingController();
        BarsSceneAdapter sceneAdapter = new BarsSceneAdapter();

        return new SortingProgramBundle(controller, sceneAdapter);
    }
}