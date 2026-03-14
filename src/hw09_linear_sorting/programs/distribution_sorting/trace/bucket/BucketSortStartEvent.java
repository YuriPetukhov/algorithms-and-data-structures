package hw09_linear_sorting.programs.distribution_sorting.trace.bucket;

import java.util.List;

public record BucketSortStartEvent(
        int bucketIndex,
        List<Integer> bucketSnapshot
) implements BucketTraceEvent {

    public BucketSortStartEvent {
        bucketSnapshot = List.copyOf(bucketSnapshot);
    }
}
