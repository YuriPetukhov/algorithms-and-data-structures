package hw09_linear_sorting.programs.distribution_sorting.trace.radix;

import java.util.ArrayList;
import java.util.List;

public final class RadixTraceRecorder {

    private final List<RadixTraceEvent> events = new ArrayList<>();

    public void phase(String phase, int exp) {
        events.add(new RadixPhaseEvent(phase, exp));
    }

    public void read(int inputIndex, int value, int digit, int exp) {
        events.add(new RadixReadEvent(inputIndex, value, digit, exp));
    }

    public void count(int digit, int newCount, int exp) {
        events.add(new RadixCountEvent(digit, newCount, exp));
    }

    public void prefix(int digit, int newValue, int exp) {
        events.add(new RadixPrefixEvent(digit, newValue, exp));
    }

    public void place(int inputIndex, int value, int digit, int outputIndex, int exp) {
        events.add(new RadixPlacementEvent(inputIndex, value, digit, outputIndex, exp));
    }

    public void writeOutput(int outputIndex, int value, int exp) {
        events.add(new RadixOutputWriteEvent(outputIndex, value, exp));
    }

    public void decrement(int digit, int newValue, int exp) {
        events.add(new RadixDecrementEvent(digit, newValue, exp));
    }

    public void copyBack(int inputIndex, int value, int exp) {
        events.add(new RadixCopyBackEvent(inputIndex, value, exp));
    }

    public List<RadixTraceEvent> snapshot() {
        return List.copyOf(events);
    }
}