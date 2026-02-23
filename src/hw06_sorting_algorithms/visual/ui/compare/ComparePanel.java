package hw06_sorting_algorithms.visual.ui.compare;

import hw06_sorting_algorithms.visual.platform.AlgorithmVariant;
import hw06_sorting_algorithms.visual.platform.compare.CompareCapable;
import hw06_sorting_algorithms.visual.platform.compare.CompareReport;
import hw06_sorting_algorithms.visual.platform.compare.CompareRequest;
import hw06_sorting_algorithms.visual.platform.compare.CompareRow;
import hw06_sorting_algorithms.visual.platform.compare.CompareSettings;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public final class ComparePanel extends JPanel {

    private final JToggleButton enableToggle = new JToggleButton("Compare");
    private final JTextField warmupRunsField = new JTextField("1", 3);
    private final JTextField measuredRunsField = new JTextField("5", 3);

    private final JPanel variantsPanel = new JPanel();
    private final Map<String, JCheckBox> checkboxesByVariantId = new LinkedHashMap<>();

    private final DefaultTableModel resultsTableModel = new DefaultTableModel(
            new Object[]{"Algorithm", "Best (ms)", "Status"},
            0
    ) {
        @Override public boolean isCellEditable(int row, int column) { return false; }
    };

    private final JTable resultsTable = new JTable(resultsTableModel);

    private CompareCapable<?> compareProgram;

    public ComparePanel() {
        setLayout(new GridBagLayout());
        setBorder(BorderFactory.createTitledBorder("Compare"));

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.weightx = 1.0;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.anchor = GridBagConstraints.NORTHWEST;

        JPanel topRow = new JPanel(new FlowLayout(FlowLayout.LEFT, 6, 0));
        topRow.add(enableToggle);
        topRow.add(new JLabel("Warmup:"));
        topRow.add(warmupRunsField);
        topRow.add(new JLabel("Runs:"));
        topRow.add(measuredRunsField);

        constraints.gridy = 0;
        constraints.weighty = 0;
        constraints.insets = new Insets(6, 6, 6, 6);
        add(topRow, constraints);

        variantsPanel.setLayout(new BoxLayout(variantsPanel, BoxLayout.Y_AXIS));
        JScrollPane variantsScrollPane = new JScrollPane(variantsPanel);
        variantsScrollPane.setBorder(BorderFactory.createTitledBorder("Algorithms"));

        constraints.gridy = 1;
        constraints.weighty = 0.35;
        constraints.fill = GridBagConstraints.BOTH;
        constraints.insets = new Insets(0, 6, 6, 6);
        add(variantsScrollPane, constraints);

        resultsTable.setFillsViewportHeight(true);
        JScrollPane resultsScrollPane = new JScrollPane(resultsTable);
        resultsScrollPane.setBorder(BorderFactory.createTitledBorder("Results"));

        constraints.gridy = 2;
        constraints.weighty = 0.65;
        constraints.insets = new Insets(0, 6, 6, 6);
        add(resultsScrollPane, constraints);

        constraints.gridy = 3;
        constraints.weighty = 0;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        add(Box.createVerticalStrut(0), constraints);
    }

    public void setProgram(CompareCapable<?> compareProgram) {
        this.compareProgram = compareProgram;

        checkboxesByVariantId.clear();
        variantsPanel.removeAll();
        clearResults();

        if (compareProgram != null) {
            for (AlgorithmVariant<?> algorithmVariant : compareProgram.variants()) {
                JCheckBox checkBox = new JCheckBox(algorithmVariant.algorithmName(), true);
                checkboxesByVariantId.put(algorithmVariant.id(), checkBox);
                variantsPanel.add(checkBox);
            }

            CompareSettings defaultSettings = compareProgram.defaultSettings();
            warmupRunsField.setText(Integer.toString(defaultSettings.warmupRuns()));
            measuredRunsField.setText(Integer.toString(defaultSettings.runs()));
        }

        variantsPanel.revalidate();
        variantsPanel.repaint();
        setVisible(compareProgram != null);
    }

    public void clear() {
        setProgram(null);
    }

    public boolean enabled() {
        return compareProgram != null && enableToggle.isSelected();
    }

    public void setEnabledToggle(boolean enabled) {
        enableToggle.setEnabled(enabled);
    }

    public void clearResults() {
        resultsTableModel.setRowCount(0);
    }

    public CompareRequest buildRequest() {
        if (compareProgram == null) throw new IllegalStateException("compare is not available");

        Set<String> selectedVariantIds = checkboxesByVariantId.entrySet().stream()
                .filter(entry -> entry.getValue().isSelected())
                .map(Map.Entry::getKey)
                .collect(Collectors.toCollection(LinkedHashSet::new));

        CompareSettings defaultSettings = compareProgram.defaultSettings();
        CompareSettings settings = new CompareSettings(
                parseIntOrDefault(warmupRunsField.getText(), defaultSettings.warmupRuns()),
                parseIntOrDefault(measuredRunsField.getText(), defaultSettings.runs())
        );

        return new CompareRequest(selectedVariantIds, settings);
    }

    public void showReport(CompareReport report) {
        clearResults();

        for (CompareRow row : report.rows()) {
            String bestMillis = row.bestNanos() < 0
                    ? "-"
                    : String.format("%.3f", row.bestNanos() / 1_000_000.0);

            String status = row.status() == null ? "" : row.status();
            resultsTableModel.addRow(new Object[]{row.name(), bestMillis, status});
        }

        String inputLabel = report.inputLabel();
        if (inputLabel != null && !inputLabel.isBlank()) {
            setBorder(BorderFactory.createTitledBorder("Compare — " + inputLabel));
        } else {
            setBorder(BorderFactory.createTitledBorder("Compare"));
        }
    }

    private static int parseIntOrDefault(String text, int defaultValue) {
        try {
            return Integer.parseInt(text.trim());
        } catch (Exception ignored) {
            return defaultValue;
        }
    }
}