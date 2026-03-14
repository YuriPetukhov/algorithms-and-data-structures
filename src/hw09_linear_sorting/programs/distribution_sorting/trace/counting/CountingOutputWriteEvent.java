package hw09_linear_sorting.programs.distribution_sorting.trace.counting;

public record CountingOutputWriteEvent(
        int outputIndex,
        int value
) implements CountingTraceEvent {
}
