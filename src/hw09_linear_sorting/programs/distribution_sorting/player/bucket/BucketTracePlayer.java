package hw09_linear_sorting.programs.distribution_sorting.player.bucket;

import hw06_sorting_algorithms.visual.platform.Player;
import hw09_linear_sorting.programs.distribution_sorting.spi.DistributionSortingParams;
import hw09_linear_sorting.programs.distribution_sorting.trace.bucket.*;

import java.util.Arrays;
import java.util.List;

public final class BucketTracePlayer implements Player<BucketPlaybackState> {

    private final int[] initialInput;
    private final int bucketCount;
    private final List<BucketTraceEvent> events;

    private BucketPlaybackState state;
    private int position;

    public BucketTracePlayer(
            int[] input,
            List<BucketTraceEvent> events,
            DistributionSortingParams params
    ) {
        if (input == null) {
            throw new IllegalArgumentException("input is null");
        }
        if (events == null) {
            throw new IllegalArgumentException("events is null");
        }
        if (params == null) {
            throw new IllegalArgumentException("params is null");
        }

        this.initialInput = Arrays.copyOf(input, input.length);
        this.bucketCount = Math.max(1, (int) Math.sqrt(Math.max(1, input.length)));
        this.events = List.copyOf(events);

        reset();
    }

    @Override
    public void reset() {
        this.state = new BucketPlaybackState(initialInput, bucketCount);
        this.position = 0;
        this.state.setPhase("Initial");
        this.state.setLastEvent("Ready");
    }

    @Override
    public boolean hasNext() {
        return position < events.size();
    }

    @Override
    public int position() {
        return position;
    }

    @Override
    public int totalEvents() {
        return events.size();
    }

    @Override
    public BucketPlaybackState state() {
        return state;
    }

    @Override
    public BucketPlaybackState step() {
        if (!hasNext()) {
            return state;
        }

        BucketTraceEvent event = events.get(position++);
        apply(event);
        return state;
    }

    private void apply(BucketTraceEvent event) {
        state.clearHighlights();

        switch (event) {
            case BucketPhaseEvent e -> {
                state.setPhase(e.phase());
                state.setLastEvent("Phase: " + e.phase());
            }

            case BucketReadEvent e -> {
                state.setHighlightInputIndex(e.inputIndex());
                state.setLastEvent("Read input[" + e.inputIndex() + "] = " + e.value());
            }

            case BucketPlaceEvent e -> {
                state.setHighlightInputIndex(e.inputIndex());
                state.setBucketSnapshot(e.bucketIndex(), e.bucketSnapshot());
                state.setLastEvent(
                        "Place " + e.value() + " into bucket " + e.bucketIndex()
                );
            }

            case BucketSortStartEvent e -> {
                state.setBucketSnapshot(e.bucketIndex(), e.bucketSnapshot());
                state.setLastEvent("Sorting bucket " + e.bucketIndex());
            }

            case BucketSortStepEvent e -> {
                state.setBucketSnapshot(e.bucketIndex(), e.bucketSnapshot());
                state.setLastEvent(
                        "Bucket " + e.bucketIndex() + ": " + e.detail()
                );
            }

            case BucketFlushEvent e -> {
                state.setBucketSnapshot(e.bucketIndex(), e.bucketSnapshotAfterRemoval());
                state.writeOutput(e.outputIndex(), e.value());
                state.setLastEvent(
                        "Write output[" + e.outputIndex() + "] = " + e.value()
                                + " from bucket " + e.bucketIndex()
                );
            }
        }
    }
}
