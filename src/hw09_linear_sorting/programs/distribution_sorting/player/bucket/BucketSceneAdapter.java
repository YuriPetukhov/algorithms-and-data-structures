package hw09_linear_sorting.programs.distribution_sorting.player.bucket;

import hw06_sorting_algorithms.visual.scene.Scene;
import hw09_linear_sorting.programs.distribution_sorting.ui.BucketScene;

import javax.swing.*;

public final class BucketSceneAdapter implements Scene<BucketPlaybackState> {

    private final BucketScene scene = new BucketScene();

    @Override
    public JComponent component() {
        return scene;
    }

    @Override
    public void setState(BucketPlaybackState state) {
        scene.setState(state);
    }
}
