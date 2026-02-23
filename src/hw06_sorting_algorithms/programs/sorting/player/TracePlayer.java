package hw06_sorting_algorithms.programs.sorting.player;

import hw06_sorting_algorithms.programs.sorting.trace.CompareEvent;
import hw06_sorting_algorithms.programs.sorting.trace.SwapEvent;
import hw06_sorting_algorithms.programs.sorting.trace.TraceEvent;
import hw06_sorting_algorithms.programs.sorting.trace.WriteEvent;
import hw06_sorting_algorithms.visual.platform.Player;

import java.util.Arrays;
import java.util.List;

public final class TracePlayer implements Player<SortPlaybackState> {

    private final int[] initial;
    private final List<TraceEvent> trace;

    private int tracePosition;
    private int[] currentArray;

    private Integer highlightIndexA;
    private Integer highlightIndexB;
    private String lastEvent;

    public TracePlayer(int[] initialArray, List<TraceEvent> trace) {
        if (initialArray == null) throw new IllegalArgumentException("initialArray is null");
        if (trace == null) throw new IllegalArgumentException("trace is null");
        this.initial = Arrays.copyOf(initialArray, initialArray.length);
        this.trace = List.copyOf(trace);
        reset();
    }

    public void reset() {
        this.currentArray = Arrays.copyOf(initial, initial.length);
        this.tracePosition = 0;
        this.highlightIndexA = null;
        this.highlightIndexB = null;
        this.lastEvent = "reset";
    }

    public boolean hasNext() {
        return tracePosition < trace.size();
    }

    public int position() {
        return tracePosition;
    }

    public int totalEvents() {
        return trace.size();
    }

    public SortPlaybackState state() {
        return new SortPlaybackState(Arrays.copyOf(currentArray, currentArray.length), highlightIndexA, highlightIndexB, lastEvent);
    }

    public SortPlaybackState step() {
        if (!hasNext()) {
            lastEvent = "done";
            highlightIndexA = highlightIndexB = null;
            return state();
        }

        TraceEvent traceEvent = trace.get(tracePosition++);
        if (traceEvent instanceof CompareEvent c) {
            highlightIndexA = c.i();
            highlightIndexB = c.j();
            lastEvent = "compare(" + c.i() + "," + c.j() + ") : " + c.ai() + " ? " + c.aj();
        } else if (traceEvent instanceof SwapEvent s) {
            highlightIndexA = s.i();
            highlightIndexB = s.j();
            int t = currentArray[s.i()];
            currentArray[s.i()] = currentArray[s.j()];
            currentArray[s.j()] = t;
            lastEvent = "swap(" + s.i() + "," + s.j() + ")";
        } else if (traceEvent instanceof WriteEvent w) {
            highlightIndexA = w.i();
            highlightIndexB = null;
            currentArray[w.i()] = w.value();
            lastEvent = "write(" + w.i() + "," + w.value() + ")";
        } else {
            highlightIndexA = highlightIndexB = null;
            lastEvent = "unknown";
        }

        return state();
    }
}