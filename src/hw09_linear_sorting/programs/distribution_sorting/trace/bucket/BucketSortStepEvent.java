package hw09_linear_sorting.programs.distribution_sorting.trace.bucket;

import java.util.List;

public record BucketSortStepEvent(
        int bucketIndex,
        List<Integer> bucketSnapshot,
        String detail
) implements BucketTraceEvent {

    public BucketSortStepEvent {
        bucketSnapshot = List.copyOf(bucketSnapshot);
        detail = detail == null ? "" : detail;
    }
}
