package hw06_sorting_algorithms.visual.scene;

import javax.swing.*;

public interface Scene<S> {
    JComponent component();
    void setState(S state);
}
