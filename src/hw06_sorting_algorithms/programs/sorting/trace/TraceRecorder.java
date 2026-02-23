package hw06_sorting_algorithms.programs.sorting.trace;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class TraceRecorder {

    private final ArrayList<TraceEvent> events = new ArrayList<>();

    public void compare(int i, int j, int ai, int aj) {
        events.add(new CompareEvent(i, j, ai, aj));
    }

    public void swap(int i, int j) {
        events.add(new SwapEvent(i, j));
    }

    public void write(int i, int value) {
        events.add(new WriteEvent(i, value));
    }

    public List<TraceEvent> snapshot() {
        return Collections.unmodifiableList(new ArrayList<>(events));
    }
}
