package hw09_linear_sorting.programs.distribution_sorting.trace.counting;

public record CountingCountEvent(
        int countIndex,
        int newCount
) implements CountingTraceEvent {
}
