package hw06_sorting_algorithms.visual.programs.sorting;

import hw06_sorting_algorithms.programs.sorting.player.SortPlaybackState;
import hw06_sorting_algorithms.programs.sorting.ui.HeapTreeScene;
import hw06_sorting_algorithms.visual.scene.Scene;

import javax.swing.*;

public final class HeapTreeSceneAdapter implements Scene<SortPlaybackState> {

    private final HeapTreeScene view = new HeapTreeScene();

    @Override
    public JComponent component() {
        return view;
    }

    @Override
    public void setState(SortPlaybackState state) {
        view.setState(state);
    }
}