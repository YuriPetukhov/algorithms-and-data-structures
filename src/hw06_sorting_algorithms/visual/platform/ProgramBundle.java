package hw06_sorting_algorithms.visual.platform;

import hw06_sorting_algorithms.visual.scene.Scene;
import hw06_sorting_algorithms.visual.ui.status.UiState;

public interface ProgramBundle<I, S extends UiState> {
    String id();
    String programName();

    ProgramController<I> controller();
    Scene<? extends UiState> scene();
    Player<? extends UiState> buildPlayer(I input);

    default boolean supportsBenchmark() { return false; }
}