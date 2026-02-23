package hw06_sorting_algorithms.visual.platform.compare;

public record CompareSettings(int warmupRuns, int runs) {
    public CompareSettings {
        if (warmupRuns < 0) throw new IllegalArgumentException("warmupRuns must be >= 0");
        if (runs <= 0) throw new IllegalArgumentException("runs must be > 0");
    }
}
