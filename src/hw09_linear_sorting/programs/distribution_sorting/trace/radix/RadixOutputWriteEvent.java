package hw09_linear_sorting.programs.distribution_sorting.trace.radix;

public record RadixOutputWriteEvent(
        int outputIndex,
        int value,
        int exp
) implements RadixTraceEvent {
}