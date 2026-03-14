package hw09_linear_sorting.programs.distribution_sorting.player.radix;

import hw06_sorting_algorithms.visual.scene.Scene;
import hw09_linear_sorting.programs.distribution_sorting.ui.RadixScene;

import javax.swing.*;

public final class RadixSceneAdapter implements Scene<RadixPlaybackState> {

    private final RadixScene scene = new RadixScene();

    @Override
    public JComponent component() {
        return scene;
    }

    @Override
    public void setState(RadixPlaybackState state) {
        scene.setState(state);
    }
}