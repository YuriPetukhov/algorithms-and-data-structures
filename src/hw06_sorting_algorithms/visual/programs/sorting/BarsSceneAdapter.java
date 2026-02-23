package hw06_sorting_algorithms.visual.programs.sorting;

import hw06_sorting_algorithms.programs.sorting.player.SortPlaybackState;
import hw06_sorting_algorithms.programs.sorting.ui.BarsScene;
import hw06_sorting_algorithms.visual.scene.Scene;

import javax.swing.*;

public final class BarsSceneAdapter implements Scene<SortPlaybackState> {
    private final BarsScene scene = new BarsScene();

    @Override public JComponent component() { return scene; }
    @Override public void setState(SortPlaybackState state) { scene.setState(state); }
}
