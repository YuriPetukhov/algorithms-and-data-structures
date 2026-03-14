package hw09_linear_sorting.programs.distribution_sorting.trace.counting;

import java.util.ArrayList;
import java.util.List;

public final class CountingTraceRecorder {

    private final List<CountingTraceEvent> events = new ArrayList<>();

    public void phase(String phase) {
        events.add(new CountingPhaseEvent(phase));
    }

    public void read(int inputIndex, int value) {
        events.add(new CountingReadEvent(inputIndex, value));
    }

    public void count(int countIndex, int newCount) {
        events.add(new CountingCountEvent(countIndex, newCount));
    }

    public void prefix(int countIndex, int newValue) {
        events.add(new CountingPrefixEvent(countIndex, newValue));
    }

    public void place(int inputIndex, int value, int outputIndex) {
        events.add(new CountingPlacementEvent(inputIndex, value, outputIndex));
    }

    public void decrement(int countIndex, int newValue) {
        events.add(new CountingDecrementEvent(countIndex, newValue));
    }

    public void writeOutput(int outputIndex, int value) {
        events.add(new CountingOutputWriteEvent(outputIndex, value));
    }

    public void copyBack(int inputIndex, int value) {
        events.add(new CountingCopyBackEvent(inputIndex, value));
    }

    public List<CountingTraceEvent> snapshot() {
        return List.copyOf(events);
    }
}
