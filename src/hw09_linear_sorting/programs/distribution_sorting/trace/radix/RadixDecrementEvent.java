package hw09_linear_sorting.programs.distribution_sorting.trace.radix;

public record RadixDecrementEvent(
        int digit,
        int newValue,
        int exp
) implements RadixTraceEvent {
}