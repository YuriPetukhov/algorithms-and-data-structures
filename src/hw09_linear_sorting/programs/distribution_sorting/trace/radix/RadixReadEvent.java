package hw09_linear_sorting.programs.distribution_sorting.trace.radix;

public record RadixReadEvent(
        int inputIndex,
        int value,
        int digit,
        int exp
) implements RadixTraceEvent {
}