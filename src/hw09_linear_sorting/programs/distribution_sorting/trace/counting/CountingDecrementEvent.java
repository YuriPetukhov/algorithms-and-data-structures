package hw09_linear_sorting.programs.distribution_sorting.trace.counting;

public record CountingDecrementEvent(
        int countIndex,
        int newValue
) implements CountingTraceEvent {
}
