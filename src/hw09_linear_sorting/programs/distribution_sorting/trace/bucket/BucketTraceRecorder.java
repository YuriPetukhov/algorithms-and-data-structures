package hw09_linear_sorting.programs.distribution_sorting.trace.bucket;

import java.util.ArrayList;
import java.util.List;

public final class BucketTraceRecorder {

    private final List<BucketTraceEvent> events = new ArrayList<>();

    public void phase(String phase) {
        events.add(new BucketPhaseEvent(phase));
    }

    public void read(int inputIndex, int value) {
        events.add(new BucketReadEvent(inputIndex, value));
    }

    public void place(int inputIndex, int value, int bucketIndex, List<Integer> bucketSnapshot) {
        events.add(new BucketPlaceEvent(inputIndex, value, bucketIndex, bucketSnapshot));
    }

    public void sortStart(int bucketIndex, List<Integer> bucketSnapshot) {
        events.add(new BucketSortStartEvent(bucketIndex, bucketSnapshot));
    }

    public void sortStep(int bucketIndex, List<Integer> bucketSnapshot, String detail) {
        events.add(new BucketSortStepEvent(bucketIndex, bucketSnapshot, detail));
    }

    public void flush(int bucketIndex, int outputIndex, int value, List<Integer> bucketSnapshotAfterRemoval) {
        events.add(new BucketFlushEvent(bucketIndex, outputIndex, value, bucketSnapshotAfterRemoval));
    }

    public List<BucketTraceEvent> snapshot() {
        return List.copyOf(events);
    }
}