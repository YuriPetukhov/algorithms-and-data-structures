package hw06_sorting_algorithms.programs.sorting.ui;

import hw06_sorting_algorithms.programs.sorting.player.SortPlaybackState;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;

public final class BarsScene extends JPanel {

    private static final int DEFAULT_WIDTH = 720;
    private static final int DEFAULT_HEIGHT = 320;

    private static final int PADDING = 16;
    private static final int TITLE_BASELINE_Y = 22;

    private static final int MAX_VALUE_LABELS = 40;

    private SortPlaybackState playbackState = new SortPlaybackState(new int[0], null, null, "");

    public BarsScene() {
        setOpaque(true);
        setBackground(Color.WHITE);
        setPreferredSize(new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT));
    }

    public void setState(SortPlaybackState playbackState) {
        if (playbackState == null) throw new IllegalArgumentException("playbackState is null");
        this.playbackState = playbackState;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);

        int[] array = playbackState.array();
        if (array == null || array.length == 0) {
            drawCenteredText(graphics, "No data");
            return;
        }

        int panelWidth = getWidth();
        int panelHeight = getHeight();

        drawTitle(graphics);

        int chartTop = TITLE_BASELINE_Y + 10;
        int chartLeft = PADDING;
        int chartRight = panelWidth - PADDING;
        int chartBottom = panelHeight - PADDING;

        int chartWidth = Math.max(1, chartRight - chartLeft);
        int chartHeight = Math.max(1, chartBottom - chartTop);

        int minimumValue = Arrays.stream(array).min().orElse(0);
        int maximumValue = Arrays.stream(array).max().orElse(0);
        int valueRange = Math.max(1, maximumValue - minimumValue);

        int barWidth = Math.max(1, chartWidth / array.length);

        Integer highlightIndexA = playbackState.highlightIndexA();
        Integer highlightIndexB = playbackState.highlightIndexB();

        for (int index = 0; index < array.length; index++) {
            int value = array[index];

            int normalizedHeight = (int) Math.round((value - (double) minimumValue) * chartHeight / valueRange);
            int barHeight = Math.max(1, normalizedHeight);

            int barLeftX = chartLeft + index * barWidth;
            int barTopY = chartBottom - barHeight;

            boolean isHighlighted =
                    (highlightIndexA != null && highlightIndexA == index) ||
                            (highlightIndexB != null && highlightIndexB == index);

            graphics.setColor(isHighlighted ? Color.RED : Color.GRAY);
            int fillWidth = Math.max(1, barWidth - 1);
            graphics.fillRect(barLeftX, barTopY, fillWidth, barHeight);

            if (array.length <= MAX_VALUE_LABELS) {
                drawBarValueLabel(graphics, value, barLeftX, barTopY, chartTop, chartBottom);
            }
        }

        graphics.setColor(Color.BLACK);
        graphics.drawRect(chartLeft, chartTop, chartWidth - 1, chartHeight - 1);
    }

    private void drawTitle(Graphics graphics) {
        graphics.setColor(Color.BLACK);
        String titleText = playbackState.lastEvent() == null ? "" : playbackState.lastEvent();
        graphics.drawString(titleText, PADDING, TITLE_BASELINE_Y);
    }

    private static void drawBarValueLabel(
            Graphics graphics,
            int value,
            int barLeftX,
            int barTopY,
            int chartTop,
            int chartBottom
    ) {
        graphics.setColor(Color.BLACK);

        String label = Integer.toString(value);
        int labelX = barLeftX + 2;

        int labelY = Math.min(chartBottom - 2, barTopY - 2);
        if (labelY < chartTop + 12) labelY = chartBottom - 2;

        graphics.drawString(label, labelX, labelY);
    }

    private void drawCenteredText(Graphics graphics, String text) {
        FontMetrics metrics = graphics.getFontMetrics();
        int x = (getWidth() - metrics.stringWidth(text)) / 2;
        int y = (getHeight() + metrics.getAscent()) / 2;
        graphics.setColor(Color.BLACK);
        graphics.drawString(text, x, y);
    }
}