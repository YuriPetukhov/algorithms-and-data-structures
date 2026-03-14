package hw09_linear_sorting.programs.distribution_sorting.trace.counting;

public sealed interface CountingTraceEvent permits
        CountingPhaseEvent,
        CountingReadEvent,
        CountingCountEvent,
        CountingPrefixEvent,
        CountingPlacementEvent,
        CountingDecrementEvent,
        CountingOutputWriteEvent,
        CountingCopyBackEvent {
}
