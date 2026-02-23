package hw06_sorting_algorithms.visual.platform;

public interface Player<S> {
    void reset();
    boolean hasNext();
    int position();
    int totalEvents();

    S state();
    S step();
}