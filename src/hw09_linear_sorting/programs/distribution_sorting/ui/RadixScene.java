package hw09_linear_sorting.programs.distribution_sorting.ui;

import hw09_linear_sorting.programs.distribution_sorting.player.radix.RadixPlaybackState;

import javax.swing.*;
import java.awt.*;

public final class RadixScene extends JPanel {

    private static final int CELL_W = 40;
    private static final int CELL_H = 30;
    private static final int GAP = 6;

    private RadixPlaybackState state;

    public RadixScene() {
        setBackground(new Color(26, 26, 30));
        setDoubleBuffered(true);
    }

    public void setState(RadixPlaybackState state) {
        this.state = state;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);

        if (state == null) {
            return;
        }

        Graphics2D g = (Graphics2D) graphics.create();
        try {
            g.setRenderingHint(
                    RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON
            );

            drawHeader(g);
            drawInputArray(g, 20, 100);
            drawDigitTable(g, 20, 220);
            drawOutputArray(g, 20, 370);
            drawFooter(g);

        } finally {
            g.dispose();
        }
    }

    private void drawHeader(Graphics2D g) {
        g.setColor(Color.WHITE);
        g.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
        g.drawString("Radix Sort Visualization", 20, 30);

        g.setColor(Color.LIGHT_GRAY);
        g.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 15));
        g.drawString("Phase: " + state.phase(), 20, 52);
        g.drawString("Pass exp = " + state.currentExp(), 20, 72);
    }

    private void drawInputArray(Graphics2D g, int x, int y) {
        drawArrayBlock(
                g,
                "Input",
                state.inputArray(),
                state.highlightInputIndex(),
                x,
                y,
                new Color(100, 149, 237)
        );
    }

    private void drawDigitTable(Graphics2D g, int x, int y) {
        g.setColor(Color.WHITE);
        g.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 16));
        g.drawString("Digit Count / Prefix", x, y - 10);

        int[] count = state.countArray();
        Integer hi = state.highlightCountIndex();

        int labelWidth = 110;

        drawTableCell(g, x, y, labelWidth, CELL_H, "", new Color(240, 210, 100), false);
        for (int d = 0; d < 10; d++) {
            drawTableCell(g, x + labelWidth + d * CELL_W, y, CELL_W, CELL_H, String.valueOf(d),
                    new Color(240, 210, 100), hi != null && hi == d);
        }

        drawTableCell(g, x, y + CELL_H, labelWidth, CELL_H, "Count", new Color(240, 210, 100), false);
        for (int d = 0; d < 10; d++) {
            drawTableCell(g, x + labelWidth + d * CELL_W, y + CELL_H, CELL_W, CELL_H,
                    String.valueOf(count[d]), Color.WHITE, hi != null && hi == d);
        }
    }

    private void drawOutputArray(Graphics2D g, int x, int y) {
        g.setColor(Color.WHITE);
        g.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 16));
        g.drawString("Output", x, y - 10);

        int[] array = state.outputArray();
        boolean[] written = state.outputWritten();

        int cellsPerRow = Math.max(1, (getWidth() - x - 20) / (CELL_W + GAP));
        Integer safeHighlight = safeIndex(state.highlightOutputIndex(), array.length);

        for (int i = 0; i < array.length; i++) {
            int row = i / cellsPerRow;
            int col = i % cellsPerRow;

            int cellX = x + col * (CELL_W + GAP);
            int cellY = y + row * (CELL_H + GAP + 16);

            boolean isWritten = written != null && i < written.length && written[i];

            if (isWritten) {
                Color fill = (safeHighlight != null && i == safeHighlight)
                        ? Color.RED
                        : new Color(60, 179, 113);

                g.setColor(fill);
                g.fillRoundRect(cellX, cellY, CELL_W, CELL_H, 8, 8);

                g.setColor(Color.BLACK);
                g.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 12));
                drawCenteredString(g, String.valueOf(array[i]), cellX, cellY, CELL_W, CELL_H);
            } else {
                g.setColor(new Color(80, 80, 80));
                g.drawRoundRect(cellX, cellY, CELL_W, CELL_H, 8, 8);
            }

            g.setColor(Color.LIGHT_GRAY);
            g.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 10));
            g.drawString(String.valueOf(i), cellX + 2, cellY + CELL_H + 10);
        }
    }

    private void drawArrayBlock(
            Graphics2D g,
            String title,
            int[] array,
            Integer highlightIndex,
            int x,
            int y,
            Color baseColor
    ) {
        g.setColor(Color.WHITE);
        g.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 16));
        g.drawString(title, x, y - 10);

        int cellsPerRow = Math.max(1, (getWidth() - x - 20) / (CELL_W + GAP));
        Integer safeHighlight = safeIndex(highlightIndex, array.length);

        for (int i = 0; i < array.length; i++) {
            int row = i / cellsPerRow;
            int col = i % cellsPerRow;

            int cellX = x + col * (CELL_W + GAP);
            int cellY = y + row * (CELL_H + GAP + 16);

            Color fill = (safeHighlight != null && i == safeHighlight)
                    ? Color.RED
                    : baseColor;

            g.setColor(fill);
            g.fillRoundRect(cellX, cellY, CELL_W, CELL_H, 8, 8);

            g.setColor(Color.BLACK);
            g.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 12));
            drawCenteredString(g, String.valueOf(array[i]), cellX, cellY, CELL_W, CELL_H);

            g.setColor(Color.LIGHT_GRAY);
            g.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 10));
            g.drawString(String.valueOf(i), cellX + 2, cellY + CELL_H + 10);
        }
    }

    private void drawTableCell(Graphics2D g, int x, int y, int w, int h, String text, Color fill, boolean highlight) {
        g.setColor(highlight ? Color.RED : fill);
        g.fillRect(x, y, w, h);

        g.setColor(Color.GRAY);
        g.drawRect(x, y, w, h);

        g.setColor(Color.BLACK);
        g.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 12));
        drawCenteredString(g, text, x, y, w, h);
    }

    private void drawFooter(Graphics2D g) {
        g.setColor(Color.WHITE);
        g.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 14));
        g.drawString("Event: " + state.lastEvent(), 20, getHeight() - 20);
    }

    private void drawCenteredString(Graphics2D g, String text, int x, int y, int width, int height) {
        FontMetrics metrics = g.getFontMetrics(g.getFont());
        int textX = x + (width - metrics.stringWidth(text)) / 2;
        int textY = y + ((height - metrics.getHeight()) / 2) + metrics.getAscent();
        g.drawString(text, textX, textY);
    }

    private Integer safeIndex(Integer index, int length) {
        if (index == null) {
            return null;
        }
        return index >= 0 && index < length ? index : null;
    }
}