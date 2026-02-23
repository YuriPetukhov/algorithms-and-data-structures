package hw06_sorting_algorithms.programs.sorting.ui;

import hw06_sorting_algorithms.libs.sorting.gaps.CiuraGaps;
import hw06_sorting_algorithms.libs.sorting.gaps.GapSequence;
import hw06_sorting_algorithms.libs.sorting.gaps.GapSequenceRegistry;
import hw06_sorting_algorithms.libs.sorting.gaps.HalvingGaps;
import hw06_sorting_algorithms.programs.sorting.registry.SortingVariantRegistry;
import hw06_sorting_algorithms.programs.sorting.spi.SortingParams;
import hw06_sorting_algorithms.programs.sorting.spi.SortingVariant;
import hw06_sorting_algorithms.visual.platform.ProgramController;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.Random;

public final class SortingController implements ProgramController<int[]> {

    private static final String SORTING_PREFIX_RU = "Сортировка:";

    private static final int MAX_VISUAL_SIZE = 30;
    private final JSpinner sizeSpinner = new JSpinner(new SpinnerNumberModel(20, 0, MAX_VISUAL_SIZE, 1));
    private final JTextField minField  = new JTextField("0", 4);
    private final JTextField maxField  = new JTextField("99", 4);
    private final JTextField seedField = new JTextField("", 6);

    private final List<SortingVariant> sortingVariants;
    private final JComboBox<SortingVariant> algorithmComboBox;

    private final JPanel gapPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 8, 0));
    private final JLabel gapLabel = new JLabel("Gap:");
    private final JComboBox<GapSequence> gapSequenceComboBox =
            new JComboBox<>(GapSequenceRegistry.get().all().toArray(new GapSequence[0]));

    private final JPanel rootPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));

    private Runnable changeListener = () -> {};

    public SortingController() {
        this.sortingVariants = new SortingVariantRegistry().list();
        if (sortingVariants.isEmpty()) throw new IllegalStateException("No sorting variants found");

        this.algorithmComboBox = new JComboBox<>(sortingVariants.toArray(new SortingVariant[0]));

        rootPanel.add(new JLabel("Size:"));
        rootPanel.add(sizeSpinner);

        rootPanel.add(new JLabel("Min:"));
        rootPanel.add(minField);

        rootPanel.add(new JLabel("Max:"));
        rootPanel.add(maxField);

        rootPanel.add(new JLabel("Seed:"));
        rootPanel.add(seedField);

        rootPanel.add(new JLabel("Alg:"));
        rootPanel.add(algorithmComboBox);

        gapPanel.add(gapLabel);
        gapPanel.add(gapSequenceComboBox);
        rootPanel.add(gapPanel);

        configureGapComboBox();
        configureAlgorithmComboBox();

        algorithmComboBox.addActionListener(e -> {
            updateGapPanelVisibility();
            changeListener.run();
        });

        gapSequenceComboBox.addActionListener(e -> changeListener.run());

        updateGapPanelVisibility();
    }

    private void configureGapComboBox() {
        gapSequenceComboBox.setPrototypeDisplayValue(new CiuraGaps());
        gapSequenceComboBox.setPreferredSize(new Dimension(140, gapSequenceComboBox.getPreferredSize().height));
    }

    private void configureAlgorithmComboBox() {
        algorithmComboBox.setRenderer((list, sortingVariant, index, isSelected, cellHasFocus) -> {
            String labelText = sortingVariant == null ? "" : shortenSortingDisplayName(sortingVariant.displayName());

            JLabel label = new JLabel(labelText);
            if (isSelected) {
                label.setOpaque(true);
                label.setBackground(list.getSelectionBackground());
                label.setForeground(list.getSelectionForeground());
            }
            return label;
        });

        algorithmComboBox.setPrototypeDisplayValue(longestVariantLabel());
        algorithmComboBox.setPreferredSize(new Dimension(280, algorithmComboBox.getPreferredSize().height));
    }

    private void updateGapPanelVisibility() {
        SortingVariant selectedVariant = (SortingVariant) algorithmComboBox.getSelectedItem();
        boolean shouldShowGapPanel = selectedVariant != null && selectedVariant.supportsGaps();

        gapPanel.setVisible(shouldShowGapPanel);
        gapSequenceComboBox.setEnabled(shouldShowGapPanel);

        rootPanel.revalidate();
        rootPanel.repaint();
    }

    @Override
    public void onChange(Runnable listener) {
        this.changeListener = (listener == null) ? () -> {} : listener;
    }

    @Override
    public JComponent component() {
        return rootPanel;
    }

    @Override
    public int[] buildInput() {
        int size = (Integer) sizeSpinner.getValue();
        int minimumValue = Integer.parseInt(minField.getText().trim());
        int maximumValue = Integer.parseInt(maxField.getText().trim());

        if (size < 0) throw new IllegalArgumentException("size must be >= 0");
        if (minimumValue > maximumValue) throw new IllegalArgumentException("min must be <= max");

        long seed = resolveSeedValue();
        Random random = new Random(seed);

        int[] input = new int[size];
        for (int index = 0; index < size; index++) {
            input[index] = minimumValue + random.nextInt(maximumValue - minimumValue + 1);
        }
        return input;
    }

    private long resolveSeedValue() {
        String seedText = seedField.getText().trim();
        return seedText.isEmpty() ? System.nanoTime() : Long.parseLong(seedText);
    }

    public SortingVariant selectedVariant() {
        return (SortingVariant) algorithmComboBox.getSelectedItem();
    }

    public List<SortingVariant> variants() {
        return sortingVariants;
    }

    public GapSequence selectedGap() {
        GapSequence selectedSequence = (GapSequence) gapSequenceComboBox.getSelectedItem();
        return selectedSequence != null ? selectedSequence : new HalvingGaps();
    }

    public SortingParams params() {
        return new SortingParams(selectedGap());
    }

    private SortingVariant longestVariantLabel() {
        SortingVariant longest = sortingVariants.get(0);
        int maxLength = longest.displayName().length();

        for (SortingVariant sortingVariant : sortingVariants) {
            int length = sortingVariant.displayName().length();
            if (length > maxLength) {
                maxLength = length;
                longest = sortingVariant;
            }
        }

        return longest;
    }

    private static String shortenSortingDisplayName(String displayName) {
        if (displayName == null) return "";
        return displayName.startsWith(SORTING_PREFIX_RU)
                ? displayName.substring(SORTING_PREFIX_RU.length()).trim()
                : displayName;
    }
}