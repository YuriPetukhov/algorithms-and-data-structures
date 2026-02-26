package hw06_sorting_algorithms.programs.sorting;

import hw06_sorting_algorithms.programs.sorting.ui.SortingController;
import hw06_sorting_algorithms.visual.platform.ProgramBuilder;
import hw06_sorting_algorithms.visual.platform.ProgramBundle;
import hw06_sorting_algorithms.visual.programs.sorting.HeapTreeSceneAdapter;

public final class SortingHeapTreeProgramBuilder implements ProgramBuilder {

    @Override
    public ProgramBundle<?, ?> build() {
        SortingController controller = new SortingController();
        HeapTreeSceneAdapter sceneAdapter = new HeapTreeSceneAdapter();

        return new SortingProgramBundle(
                "sorting-heap-tree",
                "Sorting (Heap Tree)",
                controller,
                sceneAdapter
        );
    }
}