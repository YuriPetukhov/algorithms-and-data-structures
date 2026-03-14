package hw09_linear_sorting.programs.distribution_sorting.trace.counting;

public record CountingCopyBackEvent(
        int inputIndex,
        int value
) implements CountingTraceEvent {
}
