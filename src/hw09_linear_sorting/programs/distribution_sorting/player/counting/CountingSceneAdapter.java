package hw09_linear_sorting.programs.distribution_sorting.player.counting;

import hw06_sorting_algorithms.visual.scene.Scene;
import hw09_linear_sorting.programs.distribution_sorting.ui.CountingScene;

import javax.swing.*;

public final class CountingSceneAdapter implements Scene<CountingPlaybackState> {

    private final CountingScene scene = new CountingScene();

    @Override
    public JComponent component() {
        return scene;
    }

    @Override
    public void setState(CountingPlaybackState state) {
        scene.setState(state);
    }
}
