package hw06_sorting_algorithms.visual.ui.components;

import hw06_sorting_algorithms.visual.platform.ProgramBundle;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public final class ControlsPanel extends JPanel {

    public interface Listener {
        void onProgramChanged(ProgramBundle<?, ?> selectedProgram);
        void onBuild();
        void onStep();
        void onPlay();
        void onPause();
        void onReset();
        void onSpeedChanged(int speedValue);
        void onModeChanged(String mode);
    }

    private static final int SPEED_MIN = 1;
    private static final int SPEED_MAX = 120;
    private static final int SPEED_DEFAULT = 30;
    private static final int SPEED_SLIDER_WIDTH = 220;

    private final JComboBox<ProgramBundle<?, ?>> programComboBox;
    private final JLabel statusLabel = new JLabel();

    private final JButton buildButton = new JButton("Build");
    private final JButton stepButton  = new JButton("Step");
    private final JButton playButton  = new JButton("Play");
    private final JButton pauseButton = new JButton("Pause");
    private final JButton resetButton = new JButton("Reset");

    JComboBox<String> modeCombo;

    private final JSlider speedSlider = new JSlider(SPEED_MIN, SPEED_MAX, SPEED_DEFAULT);
    private final JLabel speedValueLabel = new JLabel(SPEED_DEFAULT + "/s");

    private final JPanel controllerHostPanel = new JPanel(new BorderLayout());

    private Listener listener = new Listener() {
        @Override public void onProgramChanged(ProgramBundle<?, ?> selectedProgram) {}
        @Override public void onBuild() {}
        @Override public void onStep() {}
        @Override public void onPlay() {}
        @Override public void onPause() {}
        @Override public void onReset() {}
        @Override public void onSpeedChanged(int speedValue) {}
        @Override public void onModeChanged(String mode) {}
    };

    public ControlsPanel(List<ProgramBundle<?, ?>> programs) {
        if (programs == null) throw new IllegalArgumentException("programs is null");

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.modeCombo = new JComboBox<>(new String[] { "Compare", "Heap Tree" });
        this.modeCombo.setSelectedItem("Compare");

        this.programComboBox = new JComboBox<>(programs.toArray(new ProgramBundle[0]));
        this.programComboBox.setRenderer((list, value, index, isSelected, cellHasFocus) -> {
            JLabel label = new JLabel(value == null ? "" : value.programName());
            if (isSelected) {
                label.setOpaque(true);
                label.setBackground(list.getSelectionBackground());
                label.setForeground(list.getSelectionForeground());
            }
            return label;
        });

        add(buildStatusRow());
        add(Box.createVerticalStrut(8));
        add(buildPlaybackControlsRow());
        add(Box.createVerticalStrut(10));
        add(buildProgramRow());

        wireUiEvents();
    }

    public void setListener(Listener listener) {
        this.listener = (listener == null) ? this.listener : listener;
    }

    public JPanel controllerHost() {
        return controllerHostPanel;
    }

    public int speedValue() {
        return speedSlider.getValue();
    }

    public void setStatusText(String text) {
        statusLabel.setText(text == null ? "" : text);
    }

    public void setButtonsEnabled(boolean buildEnabled, boolean stepEnabled, boolean playEnabled, boolean pauseEnabled, boolean resetEnabled) {
        buildButton.setEnabled(buildEnabled);
        stepButton.setEnabled(stepEnabled);
        playButton.setEnabled(playEnabled);
        pauseButton.setEnabled(pauseEnabled);
        resetButton.setEnabled(resetEnabled);
    }

    public void setSelectedProgram(ProgramBundle<?, ?> program) {
        programComboBox.setSelectedItem(program);
    }

    public ProgramBundle<?, ?> selectedProgram() {
        return (ProgramBundle<?, ?>) programComboBox.getSelectedItem();
    }

    public String selectedMode() {
        Object v = modeCombo.getSelectedItem();
        return v == null ? "" : v.toString();
    }

    private JComponent buildStatusRow() {
        statusLabel.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 14));
        statusLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        return statusLabel;
    }

    private JComponent buildPlaybackControlsRow() {
        JPanel controlsPanel = new JPanel(new GridBagLayout());
        controlsPanel.setAlignmentX(Component.LEFT_ALIGNMENT);

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridy = 0;
        constraints.insets = new Insets(0, 0, 0, 10);
        constraints.anchor = GridBagConstraints.WEST;

        constraints.gridx = 0; controlsPanel.add(stepButton, constraints);
        constraints.gridx = 1; controlsPanel.add(playButton, constraints);
        constraints.gridx = 2; controlsPanel.add(pauseButton, constraints);
        constraints.gridx = 3; controlsPanel.add(resetButton, constraints);

        JLabel speedLabel = new JLabel("Speed:");
        speedLabel.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 12));

        Dimension sliderSize = new Dimension(SPEED_SLIDER_WIDTH, speedSlider.getPreferredSize().height);
        speedSlider.setMinimumSize(sliderSize);
        speedSlider.setPreferredSize(sliderSize);
        speedSlider.setMaximumSize(sliderSize);

        constraints.gridx = 4;
        controlsPanel.add(speedLabel, constraints);

        constraints.gridx = 5;
        constraints.fill = GridBagConstraints.NONE;
        constraints.weightx = 0.0;
        controlsPanel.add(speedSlider, constraints);

        constraints.gridx = 6;
        constraints.insets = new Insets(0, 0, 0, 0);
        controlsPanel.add(speedValueLabel, constraints);

        constraints.gridx = 7;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.weightx = 1.0;
        controlsPanel.add(Box.createHorizontalGlue(), constraints);

        return controlsPanel;
    }

    private JComponent buildProgramRow() {
        JPanel buildRowPanel = new JPanel(new BorderLayout(10, 0));
        buildRowPanel.setAlignmentX(Component.LEFT_ALIGNMENT);

        JPanel leftPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 8, 0));
        leftPanel.add(new JLabel("Program:"));
        leftPanel.add(programComboBox);
        leftPanel.add(new JLabel("Mode:"));
        leftPanel.add(modeCombo);

        buildRowPanel.add(leftPanel, BorderLayout.WEST);
        buildRowPanel.add(controllerHostPanel, BorderLayout.CENTER);
        buildRowPanel.add(buildButton, BorderLayout.EAST);

        return buildRowPanel;
    }

    private void wireUiEvents() {
        programComboBox.addActionListener(e -> listener.onProgramChanged(selectedProgram()));

        buildButton.addActionListener(e -> listener.onBuild());
        stepButton.addActionListener(e -> listener.onStep());
        playButton.addActionListener(e -> listener.onPlay());
        pauseButton.addActionListener(e -> listener.onPause());
        resetButton.addActionListener(e -> listener.onReset());

        speedSlider.addChangeListener(e -> {
            int speedValue = speedSlider.getValue();
            speedValueLabel.setText(speedValue + "/s");
            listener.onSpeedChanged(speedValue);
        });

        modeCombo.addActionListener(e -> {
            Object v = modeCombo.getSelectedItem();
            listener.onModeChanged(v == null ? "" : v.toString());
        });
    }
}