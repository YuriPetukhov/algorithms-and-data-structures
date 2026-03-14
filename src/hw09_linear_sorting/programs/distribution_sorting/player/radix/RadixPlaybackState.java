package hw09_linear_sorting.programs.distribution_sorting.player.radix;

import hw06_sorting_algorithms.visual.ui.status.UiState;

import java.util.Arrays;

public final class RadixPlaybackState implements UiState {

    private final int[] inputArray;
    private final int[] countArray;
    private final int[] outputArray;
    private final boolean[] outputWritten;

    private Integer highlightInputIndex;
    private Integer highlightCountIndex;
    private Integer highlightOutputIndex;

    private int currentExp;
    private int currentDigit;

    private String phase;
    private String lastEvent;

    public RadixPlaybackState(int[] source) {
        this.inputArray = Arrays.copyOf(source, source.length);
        this.countArray = new int[10];
        this.outputArray = new int[source.length];
        this.outputWritten = new boolean[source.length];
        this.phase = "";
        this.lastEvent = "";
        this.currentExp = 1;
        this.currentDigit = -1;
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

    public boolean[] outputWritten() {
        return outputWritten;
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

    public int currentExp() {
        return currentExp;
    }

    public int currentDigit() {
        return currentDigit;
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

    public void setCurrentExp(int currentExp) {
        this.currentExp = currentExp;
    }

    public void setCurrentDigit(int currentDigit) {
        this.currentDigit = currentDigit;
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

    public void clearOutput() {
        Arrays.fill(outputArray, 0);
        Arrays.fill(outputWritten, false);
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