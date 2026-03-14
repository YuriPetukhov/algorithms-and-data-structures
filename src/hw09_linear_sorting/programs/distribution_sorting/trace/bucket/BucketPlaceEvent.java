package hw09_linear_sorting.programs.distribution_sorting.trace.bucket;
import java.util.List;

public record BucketPlaceEvent(
        int inputIndex,
        int value,
        int bucketIndex,
        List<Integer> bucketSnapshot
) implements BucketTraceEvent {

    public BucketPlaceEvent {
        bucketSnapshot = List.copyOf(bucketSnapshot);
    }
}
