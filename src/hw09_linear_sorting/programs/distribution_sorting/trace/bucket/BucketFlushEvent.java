package hw09_linear_sorting.programs.distribution_sorting.trace.bucket;

import java.util.List;

public record BucketFlushEvent(
        int bucketIndex,
        int outputIndex,
        int value,
        List<Integer> bucketSnapshotAfterRemoval
) implements BucketTraceEvent {

    public BucketFlushEvent {
        bucketSnapshotAfterRemoval = List.copyOf(bucketSnapshotAfterRemoval);
    }
}
