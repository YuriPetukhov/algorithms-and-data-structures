package hw09_linear_sorting.programs.distribution_sorting.variants;

import hw06_sorting_algorithms.visual.platform.Player;
import hw06_sorting_algorithms.visual.scene.Scene;
import hw06_sorting_algorithms.visual.ui.status.UiState;
import hw09_linear_sorting.programs.distribution_sorting.player.counting.CountingSceneAdapter;
import hw09_linear_sorting.programs.distribution_sorting.player.counting.CountingTracePlayer;
import hw09_linear_sorting.programs.distribution_sorting.spi.DistributionSortingParams;
import hw09_linear_sorting.programs.distribution_sorting.spi.DistributionVisualization;
import hw09_linear_sorting.programs.distribution_sorting.trace.TracingCountingSortRunner;
import hw09_linear_sorting.programs.distribution_sorting.trace.counting.CountingTraceRecorder;

import java.util.Arrays;

public final class CountingSortVisualization implements DistributionVisualization {

    private final DistributionSortingParams params;

    public CountingSortVisualization(DistributionSortingParams params) {
        this.params = params;
    }

    @Override
    public Scene<? extends UiState> createScene() {
        return new CountingSceneAdapter();
    }

    @Override
    public Player<? extends UiState> buildPlayer(int[] input) {
        int[] workingCopy = Arrays.copyOf(input, input.length);
        CountingTraceRecorder recorder = new CountingTraceRecorder();

        TracingCountingSortRunner.sort(workingCopy, recorder, params);

        return new CountingTracePlayer(input, recorder.snapshot(), params);
    }
}
