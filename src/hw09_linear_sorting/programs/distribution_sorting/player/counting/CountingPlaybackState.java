package hw09_linear_sorting.programs.distribution_sorting.player.counting;

import hw06_sorting_algorithms.visual.ui.status.UiState;

import java.util.Arrays;

public final class CountingPlaybackState implements UiState {

    private final int[] inputArray;
    private final int[] countArray;
    private final int[] outputArray;

    private Integer highlightInputIndex;
    private Integer highlightCountIndex;
    private Integer highlightOutputIndex;

    private String phase;
    private String lastEvent;

    private final boolean[] outputWritten;

    public CountingPlaybackState(int[] source, int countArraySize) {
        this.inputArray = Arrays.copyOf(source, source.length);
        this.countArray = new int[countArraySize];
        this.outputArray = new int[source.length];
        this.phase = "";
        this.lastEvent = "";
        this.outputWritten = new boolean[source.length];

    }

    public int[] inputArray() {
        return inputArray;
    }

    public int[] countArray() {
        return countArray;
    }

    public int[] outputArray() {
        return outputArray;
    }

    public Integer highlightInputIndex() {
        return highlightInputIndex;
    }

    public Integer highlightCountIndex() {
        return highlightCountIndex;
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

    public void setHighlightCountIndex(Integer highlightCountIndex) {
        this.highlightCountIndex = highlightCountIndex;
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
        highlightCountIndex = null;
        highlightOutputIndex = null;
    }

    public void setCount(int index, int value) {
        countArray[index] = value;
        highlightCountIndex = index;
    }

    public void writeOutput(int index, int value) {
        outputArray[index] = value;
        outputWritten[index] = true;
        highlightOutputIndex = index;
    }


    public void writeInput(int index, int value) {
        inputArray[index] = value;
        highlightInputIndex = index;
    }

    public boolean[] outputWritten() {
        return outputWritten;
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
