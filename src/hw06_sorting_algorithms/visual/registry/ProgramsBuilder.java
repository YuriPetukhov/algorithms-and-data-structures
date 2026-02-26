package hw06_sorting_algorithms.visual.registry;

import hw06_sorting_algorithms.programs.sorting.SortingHeapTreeProgramBuilder;
import hw06_sorting_algorithms.visual.platform.ProgramBundle;
import hw06_sorting_algorithms.programs.sorting.SortingProgramBuilder;

import java.util.List;

public final class ProgramsBuilder {
    private ProgramsBuilder() {}

    public static List<ProgramBundle<?, ?>> all() {
        return List.of(
                new SortingProgramBuilder().build(),
                new SortingHeapTreeProgramBuilder().build()
        );
    }
}