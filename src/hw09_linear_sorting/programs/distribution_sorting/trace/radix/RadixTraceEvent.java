package hw09_linear_sorting.programs.distribution_sorting.trace.radix;

public sealed interface RadixTraceEvent permits
        RadixPhaseEvent,
        RadixReadEvent,
        RadixCountEvent,
        RadixPrefixEvent,
        RadixPlacementEvent,
        RadixOutputWriteEvent,
        RadixDecrementEvent,
        RadixCopyBackEvent {
}