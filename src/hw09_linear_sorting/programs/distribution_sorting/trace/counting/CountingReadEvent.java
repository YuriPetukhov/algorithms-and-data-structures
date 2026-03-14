package hw09_linear_sorting.programs.distribution_sorting.trace.counting;

public record CountingReadEvent(
        int inputIndex,
        int value
) implements CountingTraceEvent {
}
