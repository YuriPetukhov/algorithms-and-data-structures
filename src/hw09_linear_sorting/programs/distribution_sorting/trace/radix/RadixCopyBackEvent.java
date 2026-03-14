package hw09_linear_sorting.programs.distribution_sorting.trace.radix;

public record RadixCopyBackEvent(
        int inputIndex,
        int value,
        int exp
) implements RadixTraceEvent {
}