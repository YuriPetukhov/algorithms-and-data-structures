package hw06_sorting_algorithms.visual.ui.frame;

import hw06_sorting_algorithms.visual.platform.compare.CompareCapable;
import hw06_sorting_algorithms.visual.platform.Player;
import hw06_sorting_algorithms.visual.platform.ProgramBundle;
import hw06_sorting_algorithms.visual.platform.compare.CompareRequest;
import hw06_sorting_algorithms.visual.scene.Scene;
import hw06_sorting_algorithms.visual.ui.compare.ComparePanel;
import hw06_sorting_algorithms.visual.ui.compare.CompareRunner;
import hw06_sorting_algorithms.visual.ui.components.ControlsPanel;
import hw06_sorting_algorithms.visual.ui.host.ProgramHost;
import hw06_sorting_algorithms.visual.engine.PlaybackEngine;
import hw06_sorting_algorithms.visual.ui.status.StatusPresenter;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public final class VisualizerFrame extends JFrame implements PlaybackEngine.Listener {

    private final ControlsPanel controls;
    private final JPanel sceneHost = new JPanel(new BorderLayout());
    private final JPanel compareHost = new JPanel(new BorderLayout());

    private final ProgramHost host;
    private final StatusPresenter statusPresenter = new StatusPresenter();
    private final PlaybackEngine playback;

    private final ComparePanel comparePanel = new ComparePanel();
    private final CompareRunner compareRunner = new CompareRunner();

    private Object lockedInput;
    private boolean compareComputed;

    private final CardLayout compareCardsLayout = new CardLayout();
    private final JPanel compareCards = new JPanel(compareCardsLayout);
    private JSplitPane split;
    private boolean demoMode;

    public VisualizerFrame(String title, List<ProgramBundle<?, ?>> programs) {
        super(title);

        if (programs == null || programs.isEmpty()) {
            throw new IllegalArgumentException("VisualizerFrame requires at least one ProgramBundle");
        }

        this.controls = new ControlsPanel(programs);
        this.playback = new PlaybackEngine(this::delayMsFromSpeed, this);
        this.host = new ProgramHost(controls.controllerHost(), sceneHost);

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setContentPane(buildRoot());
        pack();
        setMinimumSize(new Dimension(1280, 660));
        setLocationByPlatform(true);

        controls.setListener(new ControlsPanel.Listener() {
            @Override public void onProgramChanged(ProgramBundle<?, ?> selectedProgram) { switchProgram(selectedProgram); }
            @Override public void onBuild() { buildPlayer(); }
            @Override public void onStep() { playback.stepOnce(); }
            @Override public void onPlay() { playback.play(); }
            @Override public void onPause() { playback.pause(); refreshFromPlayback(); }
            @Override public void onReset() { resetWithRebuild(); }
            @Override public void onSpeedChanged(int speedValue) { playback.updateDelay(); }
            @Override public void onModeChanged(String mode) { setMode(mode); }
        });

        switchProgram(programs.get(0));
    }

    private JComponent buildRoot() {
        JPanel root = new JPanel(new BorderLayout(12, 12));
        root.setBorder(BorderFactory.createEmptyBorder(12, 12, 12, 12));
        root.add(controls, BorderLayout.NORTH);

        compareCards.add(comparePanel, "compare");
        compareCards.add(new JPanel(), "empty");
        compareHost.add(compareCards, BorderLayout.CENTER);

        compareHost.setMinimumSize(new Dimension(360, 0));
        compareHost.setPreferredSize(new Dimension(420, 0));
        compareHost.setMinimumSize(new Dimension(360, 0));
        compareHost.setPreferredSize(new Dimension(420, 0));

        split = new JSplitPane(
                JSplitPane.HORIZONTAL_SPLIT,
                sceneHost,
                compareHost
        );
        split.setResizeWeight(1.0);
        split.setOneTouchExpandable(true);
        split.setContinuousLayout(true);

        JPanel center = new JPanel(new BorderLayout());
        center.add(split, BorderLayout.CENTER);
        root.add(center, BorderLayout.CENTER);

        SwingUtilities.invokeLater(() -> split.setDividerLocation(0.68));

        return root;
    }

    private int delayMsFromSpeed() {
        int stepsPerSecond = Math.max(1, controls.speedValue());
        return Math.max(1, 1000 / stepsPerSecond);
    }

    private void refreshFromPlayback() {
        Player<?> currentPlayer = playback.player();
        if (currentPlayer == null) {
            controls.setStatusText(statusPresenter.noPlayer());
        } else {
            Object state = currentPlayer.state();
            setSceneStateRaw(host.scene(), state);
            controls.setStatusText(statusPresenter.format(currentPlayer, state));
        }
        updateButtons();
    }

    private void switchProgram(ProgramBundle<?, ?> bundle) {
        if (bundle == null) return;

        playback.stop();
        playback.setPlayer(null);

        host.mount(bundle);

        lockedInput = null;
        compareComputed = false;
        comparePanel.clearResults();

        if (demoMode) {
            comparePanel.clear();
            comparePanel.setVisible(false);
            compareCardsLayout.show(compareCards, "empty");
            return;
        }

        if (bundle instanceof CompareCapable<?> compareCapableProgram) {
            comparePanel.setProgram(compareCapableProgram);
            comparePanel.setVisible(true);
        } else {
            comparePanel.clear();
            comparePanel.setVisible(false);
        }

        setTitle(bundle.programName());
        controls.setStatusText(statusPresenter.noPlayer());
        updateButtons();
    }

    private void buildPlayer() {
        playback.stop();

        try {
            Object input = host.controller().buildInput();
            lockedInput = input;

            compareComputed = false;
            comparePanel.clearResults();

            Player<?> player = buildPlayerRaw(host.bundle(), input);
            playback.setPlayer(player);

            setTitle(host.bundle().programName());
            updateButtons();

        } catch (Exception exception) {
            showErrorDialog("Build error", exception);
        }
    }

    private void setMode(String mode) {
        String m = (mode == null) ? "" : mode.trim().toLowerCase();
        demoMode = m.contains("demo");

        if (demoMode) {
            compareCardsLayout.show(compareCards, "empty");
            comparePanel.setVisible(false);
            comparePanel.setEnabledToggle(false);
            comparePanel.clearResults();

            SwingUtilities.invokeLater(() -> split.setDividerLocation(1.0));
        } else {
            compareCardsLayout.show(compareCards, "compare");
            comparePanel.setEnabledToggle(true);

            SwingUtilities.invokeLater(() -> split.setDividerLocation(0.68));
        }
    }

    @Override
    public void onNoPlayer() {
        controls.setStatusText(statusPresenter.noPlayer());
        updateButtons();
    }

    @Override
    public void onState(Object state) {
        setSceneStateRaw(host.scene(), state);
        controls.setStatusText(statusPresenter.format(playback.player(), state));
        updateButtons();
    }

    @Override
    public void onDone() {
        Player<?> currentPlayer = playback.player();
        Object state = currentPlayer == null ? null : currentPlayer.state();
        controls.setStatusText(statusPresenter.format(currentPlayer, state));

        updateButtons();
        maybeCompare();
    }

    private void maybeCompare() {
        if (compareComputed) return;
        if (lockedInput == null) return;
        if (!comparePanel.enabled()) return;
        if (!(host.bundle() instanceof CompareCapable<?> compareCapableProgram)) return;
        if (demoMode) return;

        CompareRequest request;
        try {
            request = comparePanel.buildRequest();
        } catch (Exception exception) {
            showErrorDialog("Compare error", exception);
            return;
        }

        compareComputed = true;
        comparePanel.setEnabledToggle(false);

        compareRunner.runAsync(compareCapableProgram, lockedInput, request,
                report -> {
                    comparePanel.setEnabledToggle(true);
                    comparePanel.showReport(report);
                },
                exception -> {
                    compareComputed = false;
                    comparePanel.setEnabledToggle(true);
                    showErrorDialog("Compare error", exception);
                }
        );
    }

    private void updateButtons() {
        Player<?> currentPlayer = playback.player();
        boolean running = playback.isRunning();

        boolean hasPlayer = currentPlayer != null;
        boolean hasNext   = hasPlayer && currentPlayer.hasNext();

        controls.setButtonsEnabled(
                !running,
                hasPlayer && !running && hasNext,
                hasPlayer && !running && hasNext,
                running,
                hasPlayer && !running
        );
    }

    private void resetWithRebuild() {
        playback.stop();

        if (lockedInput == null) {
            playback.setPlayer(null);
            controls.setStatusText(statusPresenter.noPlayer());
            updateButtons();
            return;
        }

        try {
            Player<?> player = buildPlayerRaw(host.bundle(), lockedInput);
            playback.setPlayer(player);

            compareComputed = false;
            comparePanel.clearResults();

            updateButtons();
        } catch (Exception exception) {
            showErrorDialog("Reset error", exception);
        }
    }

    private void showErrorDialog(String title, Exception exception) {
        String message = exception.getMessage();
        String text = (message == null || message.isBlank()) ? exception.toString() : message;

        JOptionPane.showMessageDialog(this, text, title, JOptionPane.ERROR_MESSAGE);
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    private static Player<?> buildPlayerRaw(ProgramBundle bundle, Object input) {
        return bundle.buildPlayer(input);
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    private static void setSceneStateRaw(Scene scene, Object state) {
        scene.setState(state);
    }
}