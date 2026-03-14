package hw09_linear_sorting.programs.distribution_sorting.trace.radix;

public record RadixPlacementEvent(
        int inputIndex,
        int value,
        int digit,
        int outputIndex,
        int exp
) implements RadixTraceEvent {
}