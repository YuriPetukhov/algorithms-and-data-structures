package hw09_linear_sorting.programs.distribution_sorting.ui;

import hw06_sorting_algorithms.visual.platform.ProgramController;
import hw09_linear_sorting.programs.distribution_sorting.registry.DistributionVariantRegistry;
import hw09_linear_sorting.programs.distribution_sorting.spi.DistributionSortingParams;
import hw09_linear_sorting.programs.distribution_sorting.spi.DistributionSortingVariant;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.function.Predicate;

public final class DistributionSortingController implements ProgramController<int[]> {

    private static final String SORTING_PREFIX_RU = "Сортировка:";
    private static final int MAX_VISUAL_SIZE = 30;

    private final DistributionVariantRegistry registry = new DistributionVariantRegistry();

    private Predicate<DistributionSortingVariant> variantFilter = v -> true;
    private List<DistributionSortingVariant> visibleVariants = List.of();

    private final JSpinner sizeSpinner = new JSpinner(
            new SpinnerNumberModel(30, 0, MAX_VISUAL_SIZE, 1)
    );
    private final JTextField minField = new JTextField("0", 4);
    private final JTextField maxField = new JTextField("30", 4);
    private final JTextField seedField = new JTextField("", 6);

    private final JComboBox<DistributionSortingVariant> variantCombo = new JComboBox<>();
    private final JPanel rootPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));

    private Runnable changeListener = () -> {};

    public DistributionSortingController() {
        refreshVariants();
        buildPanel();
        configureAlgorithmComboBox();
        refreshCombo();
    }

    public List<DistributionSortingVariant> variants() {
        return visibleVariants;
    }

    public DistributionSortingVariant selectedVariant() {
        return (DistributionSortingVariant) variantCombo.getSelectedItem();
    }

    public DistributionSortingParams params() {
        int minValue = Integer.parseInt(minField.getText().trim());
        int maxValue = Integer.parseInt(maxField.getText().trim());
        return new DistributionSortingParams(minValue, maxValue);
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
        int minValue = Integer.parseInt(minField.getText().trim());
        int maxValue = Integer.parseInt(maxField.getText().trim());

        if (size < 0) {
            throw new IllegalArgumentException("size must be >= 0");
        }
        if (minValue > maxValue) {
            throw new IllegalArgumentException("min must be <= max");
        }

        DistributionSortingVariant variant =
                (DistributionSortingVariant) variantCombo.getSelectedItem();
        if (variant == null) {
            throw new IllegalStateException("No distribution algorithm selected");
        }

        long seed = resolveSeedValue();
        Random random = new Random(seed);

        int[] input = new int[size];
        for (int index = 0; index < size; index++) {
            input[index] = minValue + random.nextInt(maxValue - minValue + 1);
        }

        return input;
    }

    public void setVariantFilter(Predicate<DistributionSortingVariant> filter) {
        this.variantFilter = (filter == null) ? v -> true : filter;
        rebuildVariantCombo();
    }

    private void buildPanel() {
        rootPanel.add(new JLabel("Size:"));
        rootPanel.add(sizeSpinner);

        rootPanel.add(new JLabel("Min:"));
        rootPanel.add(minField);

        rootPanel.add(new JLabel("Max:"));
        rootPanel.add(maxField);

        rootPanel.add(new JLabel("Seed:"));
        rootPanel.add(seedField);

        rootPanel.add(new JLabel("Alg:"));
        rootPanel.add(variantCombo);

        sizeSpinner.addChangeListener(e -> changeListener.run());
        minField.addActionListener(e -> changeListener.run());
        maxField.addActionListener(e -> changeListener.run());
        seedField.addActionListener(e -> changeListener.run());

        variantCombo.addActionListener(e -> changeListener.run());
    }

    private void configureAlgorithmComboBox() {
        variantCombo.setRenderer((list, variant, index, isSelected, cellHasFocus) -> {
            String labelText = variant == null
                    ? ""
                    : shortenSortingDisplayName(variant.displayName());

            JLabel label = new JLabel(labelText);
            if (isSelected) {
                label.setOpaque(true);
                label.setBackground(list.getSelectionBackground());
                label.setForeground(list.getSelectionForeground());
            }
            return label;
        });

        DistributionSortingVariant prototype = longestVariantLabel();
        if (prototype != null) {
            variantCombo.setPrototypeDisplayValue(prototype);
        }

        variantCombo.setPreferredSize(
                new Dimension(280, variantCombo.getPreferredSize().height)
        );
    }

    private void refreshVariants() {
        List<DistributionSortingVariant> filtered = new ArrayList<>();

        for (DistributionSortingVariant variant : registry.list()) {
            if (variantFilter.test(variant)) {
                filtered.add(variant);
            }
        }

        this.visibleVariants = List.copyOf(filtered);
    }

    private void refreshCombo() {
        variantCombo.removeAllItems();

        for (DistributionSortingVariant variant : visibleVariants) {
            variantCombo.addItem(variant);
        }

        if (variantCombo.getItemCount() > 0 && variantCombo.getSelectedItem() == null) {
            variantCombo.setSelectedIndex(0);
        }
    }

    private void rebuildVariantCombo() {
        DistributionSortingVariant previous =
                (DistributionSortingVariant) variantCombo.getSelectedItem();

        refreshVariants();

        variantCombo.removeAllItems();
        for (DistributionSortingVariant variant : visibleVariants) {
            variantCombo.addItem(variant);
        }

        if (previous != null) {
            variantCombo.setSelectedItem(previous);
        }

        if (variantCombo.getSelectedItem() == null && variantCombo.getItemCount() > 0) {
            variantCombo.setSelectedIndex(0);
        }
    }

    private DistributionSortingVariant longestVariantLabel() {
        if (visibleVariants.isEmpty()) {
            return null;
        }

        DistributionSortingVariant longest = visibleVariants.get(0);
        int maxLength = shortenSortingDisplayName(longest.displayName()).length();

        for (DistributionSortingVariant variant : visibleVariants) {
            int length = shortenSortingDisplayName(variant.displayName()).length();
            if (length > maxLength) {
                maxLength = length;
                longest = variant;
            }
        }

        return longest;
    }

    private long resolveSeedValue() {
        String seedText = seedField.getText().trim();
        return seedText.isEmpty() ? System.nanoTime() : Long.parseLong(seedText);
    }

    private static String shortenSortingDisplayName(String displayName) {
        if (displayName == null) {
            return "";
        }
        return displayName.startsWith(SORTING_PREFIX_RU)
                ? displayName.substring(SORTING_PREFIX_RU.length()).trim()
                : displayName;
    }
}