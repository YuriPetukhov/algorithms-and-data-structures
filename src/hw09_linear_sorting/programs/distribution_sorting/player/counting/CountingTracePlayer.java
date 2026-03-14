package hw09_linear_sorting.programs.distribution_sorting.player.counting;

import hw06_sorting_algorithms.visual.platform.Player;
import hw09_linear_sorting.programs.distribution_sorting.spi.DistributionSortingParams;
import hw09_linear_sorting.programs.distribution_sorting.trace.counting.*;

import java.util.Arrays;
import java.util.List;

public final class CountingTracePlayer implements Player<CountingPlaybackState> {

    private final int[] initialInput;
    private final int countArraySize;
    private final List<CountingTraceEvent> events;

    private CountingPlaybackState state;
    private int position;

    public CountingTracePlayer(
            int[] input,
            List<CountingTraceEvent> events,
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
        this.countArraySize = Math.max(1, params.maxValue() - params.minValue() + 1);
        this.events = List.copyOf(events);

        reset();
    }

    @Override
    public void reset() {
        this.state = new CountingPlaybackState(initialInput, countArraySize);
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
    public CountingPlaybackState state() {
        return state;
    }

    @Override
    public CountingPlaybackState step() {
        if (!hasNext()) {
            return state;
        }

        CountingTraceEvent event = events.get(position++);
        apply(event);
        return state;
    }

    private void apply(CountingTraceEvent event) {
        state.clearHighlights();

        switch (event) {
            case CountingPhaseEvent e -> {
                state.setPhase(e.phase());
                state.setLastEvent("Phase: " + e.phase());
            }

            case CountingReadEvent e -> {
                state.setHighlightInputIndex(e.inputIndex());
                state.setLastEvent("Read input[" + e.inputIndex() + "] = " + e.value());
            }

            case CountingCountEvent e -> {
                state.setCount(e.countIndex(), e.newCount());
                state.setLastEvent("Count[" + e.countIndex() + "] = " + e.newCount());
            }

            case CountingPrefixEvent e -> {
                state.setCount(e.countIndex(), e.newValue());
                state.setLastEvent("Prefix count[" + e.countIndex() + "] = " + e.newValue());
            }

            case CountingPlacementEvent e -> {
                state.setHighlightInputIndex(e.inputIndex());
                state.setHighlightOutputIndex(e.outputIndex());
                state.setLastEvent(
                        "Place value " + e.value()
                                + " from input[" + e.inputIndex() + "] to output[" + e.outputIndex() + "]"
                );
            }

            case CountingOutputWriteEvent e -> {
                state.writeOutput(e.outputIndex(), e.value());
                state.setLastEvent("Write output[" + e.outputIndex() + "] = " + e.value());
            }

            case CountingDecrementEvent e -> {
                state.setCount(e.countIndex(), e.newValue());
                state.setLastEvent("Decrement count[" + e.countIndex() + "] to " + e.newValue());
            }

            case CountingCopyBackEvent e -> {
                state.writeInput(e.inputIndex(), e.value());
                state.setLastEvent("Copy input[" + e.inputIndex() + "] = " + e.value());
            }
        }
    }
}
