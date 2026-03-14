package hw09_linear_sorting.programs.distribution_sorting.player.radix;

import hw06_sorting_algorithms.visual.platform.Player;
import hw09_linear_sorting.programs.distribution_sorting.spi.DistributionSortingParams;
import hw09_linear_sorting.programs.distribution_sorting.trace.radix.*;

import java.util.Arrays;
import java.util.List;

public final class RadixTracePlayer implements Player<RadixPlaybackState> {

    private final int[] initialInput;
    private final List<RadixTraceEvent> events;

    private RadixPlaybackState state;
    private int position;

    public RadixTracePlayer(
            int[] input,
            List<RadixTraceEvent> events,
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
        this.events = List.copyOf(events);

        reset();
    }

    @Override
    public void reset() {
        this.state = new RadixPlaybackState(initialInput);
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
    public RadixPlaybackState state() {
        return state;
    }

    @Override
    public RadixPlaybackState step() {
        if (!hasNext()) {
            return state;
        }

        RadixTraceEvent event = events.get(position++);
        apply(event);
        return state;
    }

    private void apply(RadixTraceEvent event) {
        state.clearHighlights();

        switch (event) {
            case RadixPhaseEvent e -> {
                state.setCurrentExp(e.exp());
                state.setPhase(e.phase());

                if ("Counting digits".equals(e.phase()) || "Building prefix sums".equals(e.phase())) {
                    Arrays.fill(state.countArray(), 0);
                }
                if ("Copying output to main array".equals(e.phase())) {
                    // output остается видимым
                }
                if ("Counting digits".equals(e.phase())) {
                    state.clearOutput();
                }

                state.setLastEvent("Phase: " + e.phase() + " | exp=" + e.exp());
            }

            case RadixReadEvent e -> {
                state.setCurrentExp(e.exp());
                state.setCurrentDigit(e.digit());
                state.setHighlightInputIndex(e.inputIndex());
                state.setHighlightCountIndex(e.digit());
                state.setLastEvent(
                        "Read input[" + e.inputIndex() + "] = " + e.value()
                                + ", digit=" + e.digit()
                                + ", exp=" + e.exp()
                );
            }

            case RadixCountEvent e -> {
                state.setCurrentExp(e.exp());
                state.setCurrentDigit(e.digit());
                state.setCount(e.digit(), e.newCount());
                state.setLastEvent(
                        "Count[" + e.digit() + "] = " + e.newCount()
                                + " | exp=" + e.exp()
                );
            }

            case RadixPrefixEvent e -> {
                state.setCurrentExp(e.exp());
                state.setCurrentDigit(e.digit());
                state.setCount(e.digit(), e.newValue());
                state.setLastEvent(
                        "Prefix count[" + e.digit() + "] = " + e.newValue()
                                + " | exp=" + e.exp()
                );
            }

            case RadixPlacementEvent e -> {
                state.setCurrentExp(e.exp());
                state.setCurrentDigit(e.digit());
                state.setHighlightInputIndex(e.inputIndex());
                state.setHighlightOutputIndex(e.outputIndex());
                state.setHighlightCountIndex(e.digit());
                state.setLastEvent(
                        "Place value " + e.value()
                                + " from input[" + e.inputIndex() + "] to output[" + e.outputIndex() + "]"
                                + " by digit " + e.digit()
                                + " | exp=" + e.exp()
                );
            }

            case RadixOutputWriteEvent e -> {
                state.setCurrentExp(e.exp());
                state.writeOutput(e.outputIndex(), e.value());
                state.setLastEvent(
                        "Write output[" + e.outputIndex() + "] = " + e.value()
                                + " | exp=" + e.exp()
                );
            }

            case RadixDecrementEvent e -> {
                state.setCurrentExp(e.exp());
                state.setCurrentDigit(e.digit());
                state.setCount(e.digit(), e.newValue());
                state.setLastEvent(
                        "Decrement count[" + e.digit() + "] to " + e.newValue()
                                + " | exp=" + e.exp()
                );
            }

            case RadixCopyBackEvent e -> {
                state.setCurrentExp(e.exp());
                state.writeInput(e.inputIndex(), e.value());
                state.setLastEvent(
                        "Copy input[" + e.inputIndex() + "] = " + e.value()
                                + " | exp=" + e.exp()
                );
            }
        }
    }
}