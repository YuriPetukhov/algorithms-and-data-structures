package hw09_linear_sorting.programs.distribution_sorting.trace.radix;

public record RadixCountEvent(
        int digit,
        int newCount,
        int exp
) implements RadixTraceEvent {
}