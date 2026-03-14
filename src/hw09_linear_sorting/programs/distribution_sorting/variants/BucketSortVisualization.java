package hw09_linear_sorting.programs.distribution_sorting.variants;

import hw06_sorting_algorithms.visual.platform.Player;
import hw06_sorting_algorithms.visual.scene.Scene;
import hw06_sorting_algorithms.visual.ui.status.UiState;
import hw09_linear_sorting.programs.distribution_sorting.player.bucket.BucketSceneAdapter;
import hw09_linear_sorting.programs.distribution_sorting.player.bucket.BucketTracePlayer;
import hw09_linear_sorting.programs.distribution_sorting.spi.DistributionSortingParams;
import hw09_linear_sorting.programs.distribution_sorting.spi.DistributionVisualization;
import hw09_linear_sorting.programs.distribution_sorting.trace.TracingBucketSortRunner;
import hw09_linear_sorting.programs.distribution_sorting.trace.bucket.BucketTraceRecorder;

import java.util.Arrays;

public final class BucketSortVisualization implements DistributionVisualization {

    private final DistributionSortingParams params;

    public BucketSortVisualization(DistributionSortingParams params) {
        this.params = params;
    }

    @Override
    public Scene<? extends UiState> createScene() {
        return new BucketSceneAdapter();
    }

    @Override
    public Player<? extends UiState> buildPlayer(int[] input) {
        int[] workingCopy = Arrays.copyOf(input, input.length);
        BucketTraceRecorder recorder = new BucketTraceRecorder();

        TracingBucketSortRunner.sort(workingCopy, recorder, params);

        return new BucketTracePlayer(input, recorder.snapshot(), params);
    }
}
