package hw09_linear_sorting.programs.distribution_sorting.trace.bucket;

public sealed interface BucketTraceEvent permits
        BucketPhaseEvent,
        BucketReadEvent,
        BucketPlaceEvent,
        BucketSortStartEvent,
        BucketSortStepEvent,
        BucketFlushEvent {
}
