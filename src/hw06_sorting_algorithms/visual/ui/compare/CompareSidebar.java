package hw06_sorting_algorithms.visual.ui.compare;

import javax.swing.*;
import java.awt.*;

public final class CompareSidebar extends JPanel {
    private final CardLayout cardsLayout = new CardLayout();
    private final JPanel cards = new JPanel(cardsLayout);
    private final ComparePanel comparePanel = new ComparePanel();

    public CompareSidebar() {
        super(new BorderLayout());
        cards.add(comparePanel, "compare");
        cards.add(new JPanel(), "empty");
        add(cards, BorderLayout.CENTER);
        showEmpty();
    }

    public ComparePanel panel() { return comparePanel; }

    public void showEmpty() {
        cardsLayout.show(cards, "empty");
        comparePanel.setVisible(false);
        comparePanel.setEnabledToggle(false);
    }

    public void showCompare() {
        cardsLayout.show(cards, "compare");
        comparePanel.setVisible(true);
        comparePanel.setEnabledToggle(true);
    }
}
