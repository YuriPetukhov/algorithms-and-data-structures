package hw09_linear_sorting.programs.distribution_sorting.trace.counting;

public record CountingPrefixEvent(
        int countIndex,
        int newValue
) implements CountingTraceEvent {
}
