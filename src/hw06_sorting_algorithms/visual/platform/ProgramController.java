package hw06_sorting_algorithms.visual.platform;

import javax.swing.*;

public interface ProgramController<I> {
    JComponent component();
    I buildInput();

    default void onChange(Runnable r) {}
}
