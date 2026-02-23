package hw06_sorting_algorithms.visual.platform;

import hw06_sorting_algorithms.visual.scene.Scene;

public interface ProgramBundle<I, S> {
    String id();
    String programName();

    ProgramController<I> controller();
    Scene<S> scene();
    Player<S> buildPlayer(I input);

    default boolean supportsBenchmark() { return false; }
}