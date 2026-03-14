package hw09_linear_sorting.programs.distribution_sorting.trace.counting;

public record CountingPlacementEvent(
        int inputIndex,
        int value,
        int outputIndex
) implements CountingTraceEvent {
}
