package hw09_linear_sorting.programs.distribution_sorting.player.bucket;

import hw06_sorting_algorithms.visual.ui.status.UiState;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public final class BucketPlaybackState implements UiState {

    private final int[] inputArray;
    private final int[] outputArray;
    private final List<List<Integer>> buckets;

    private Integer highlightInputIndex;
    private Integer highlightBucketIndex;
    private Integer highlightOutputIndex;

    private String phase;
    private String lastEvent;

    public BucketPlaybackState(int[] source, int bucketCount) {
        this.inputArray = Arrays.copyOf(source, source.length);
        this.outputArray = new int[source.length];
        this.buckets = new ArrayList<>(Math.max(1, bucketCount));

        for (int i = 0; i < Math.max(1, bucketCount); i++) {
            this.buckets.add(new ArrayList<>());
        }

        this.phase = "";
        this.lastEvent = "";
    }

    public int[] inputArray() {
        return inputArray;
    }

    public int[] outputArray() {
        return outputArray;
    }

    public List<List<Integer>> buckets() {
        return buckets;
    }

    public Integer highlightInputIndex() {
        return highlightInputIndex;
    }

    public Integer highlightBucketIndex() {
        return highlightBucketIndex;
    }

    public Integer highlightOutputIndex() {
        return highlightOutputIndex;
    }

    public String phase() {
        return phase;
    }

    public String lastEvent() {
        return lastEvent;
    }

    public void setHighlightInputIndex(Integer highlightInputIndex) {
        this.highlightInputIndex = highlightInputIndex;
    }

    public void setHighlightBucketIndex(Integer highlightBucketIndex) {
        this.highlightBucketIndex = highlightBucketIndex;
    }

    public void setHighlightOutputIndex(Integer highlightOutputIndex) {
        this.highlightOutputIndex = highlightOutputIndex;
    }

    public void setPhase(String phase) {
        this.phase = phase != null ? phase : "";
    }

    public void setLastEvent(String lastEvent) {
        this.lastEvent = lastEvent != null ? lastEvent : "";
    }

    public void clearHighlights() {
        highlightInputIndex = null;
        highlightBucketIndex = null;
        highlightOutputIndex = null;
    }

    public void setBucketSnapshot(int bucketIndex, List<Integer> snapshot) {
        if (bucketIndex < 0 || bucketIndex >= buckets.size()) {
            return;
        }

        List<Integer> bucket = buckets.get(bucketIndex);
        bucket.clear();
        bucket.addAll(snapshot);

        highlightBucketIndex = bucketIndex;
    }

    public void writeOutput(int index, int value) {
        outputArray[index] = value;
        highlightOutputIndex = index;
    }

    public void writeInput(int index, int value) {
        inputArray[index] = value;
        highlightInputIndex = index;
    }

    @Override
    public String statusLine() {
        int size = inputArray == null ? 0 : inputArray.length;
        String eventText = lastEvent == null ? "" : lastEvent;
        return eventText + " | n=" + size;
    }

    @Override
    public String toString() {
        return statusLine();
    }
}
