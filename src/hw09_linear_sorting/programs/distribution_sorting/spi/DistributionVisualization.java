package hw09_linear_sorting.programs.distribution_sorting.spi;

import hw06_sorting_algorithms.visual.platform.Player;
import hw06_sorting_algorithms.visual.scene.Scene;
import hw06_sorting_algorithms.visual.ui.status.UiState;

public interface DistributionVisualization {
    Scene<? extends UiState> createScene();
    Player<? extends UiState> buildPlayer(int[] input);
}
