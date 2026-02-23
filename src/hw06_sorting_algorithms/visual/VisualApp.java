package hw06_sorting_algorithms.visual;

import hw06_sorting_algorithms.visual.registry.ProgramsBuilder;
import hw06_sorting_algorithms.visual.ui.frame.VisualizerFrame;

import javax.swing.*;

public class VisualApp {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            VisualizerFrame frame = new VisualizerFrame(
                    "Algorithm Visualizer",
                    ProgramsBuilder.all()
            );
            frame.setVisible(true);
        });
    }
}