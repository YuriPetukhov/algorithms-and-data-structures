package hw09_linear_sorting.programs.distribution_sorting.trace.bucket;

public record BucketReadEvent(
        int inputIndex,
        int value
) implements BucketTraceEvent {
}
