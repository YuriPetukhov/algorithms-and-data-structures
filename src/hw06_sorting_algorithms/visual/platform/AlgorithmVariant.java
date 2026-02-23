package hw06_sorting_algorithms.visual.platform;

public interface AlgorithmVariant<I> {
    String id();
    String algorithmName();

    void runPlain(I input);
}