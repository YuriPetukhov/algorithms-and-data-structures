package hw09_linear_sorting.programs.distribution_sorting.trace.radix;

public record RadixPhaseEvent(
        String phase,
        int exp
) implements RadixTraceEvent {
}