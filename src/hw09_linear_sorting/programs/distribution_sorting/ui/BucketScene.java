package hw09_linear_sorting.programs.distribution_sorting.ui;


import hw09_linear_sorting.programs.distribution_sorting.player.bucket.BucketPlaybackState;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public final class BucketScene extends JPanel {

    private static final int CELL_W = 36;
    private static final int CELL_H = 28;
    private static final int GAP = 6;

    private BucketPlaybackState state;

    public BucketScene() {
        setBackground(new Color(26, 26, 30));
        setDoubleBuffered(true);
    }

    public void setState(BucketPlaybackState state) {
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
            drawInputArray(g, 20, 90);
            drawBuckets(g, 20, 230);
            drawOutputArray(g, 20, getHeight() - 80);
            drawFooter(g);

        } finally {
            g.dispose();
        }
    }

    private void drawHeader(Graphics2D g) {
        g.setColor(Color.WHITE);
        g.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
        g.drawString("Bucket Sort Visualization", 20, 28);

        g.setColor(Color.LIGHT_GRAY);
        g.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 15));
        g.drawString("Phase: " + state.phase(), 20, 50);
    }

    private void drawFooter(Graphics2D g) {
        g.setColor(Color.WHITE);
        g.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 14));
        g.drawString("Event: " + state.lastEvent(), 20, getHeight() - 20);
    }

    private void drawInputArray(Graphics2D g, int x, int y) {
        drawArrayRow(
                g,
                "Input",
                state.inputArray(),
                state.highlightInputIndex(),
                x,
                y,
                new Color(100, 149, 237)
        );
    }

    private void drawOutputArray(Graphics2D g, int x, int y) {
        drawArrayRow(
                g,
                "Output",
                state.outputArray(),
                state.highlightOutputIndex(),
                x,
                y,
                new Color(60, 179, 113)
        );
    }

    private void drawArrayRow(
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

        if (array == null || array.length == 0) {
            g.setColor(Color.GRAY);
            g.drawString("(empty)", x, y + 20);
            return;
        }

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

    private void drawBuckets(Graphics2D g, int x, int y) {
        g.setColor(Color.WHITE);
        g.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 16));
        g.drawString("Buckets", x, y - 10);

        List<List<Integer>> buckets = state.buckets();
        if (buckets == null || buckets.isEmpty()) {
            g.setColor(Color.GRAY);
            g.drawString("(no buckets)", x, y + 20);
            return;
        }

        int bucketWidth = Math.max(140, (getWidth() - 40) / Math.max(1, buckets.size()) - 10);
        int bucketHeight = 140;

        for (int bucketIndex = 0; bucketIndex < buckets.size(); bucketIndex++) {
            int bucketX = x + bucketIndex * (bucketWidth + 10);
            int bucketY = y;

            boolean highlighted = state.highlightBucketIndex() != null
                    && state.highlightBucketIndex() == bucketIndex;

            drawBucketBox(
                    g,
                    bucketX,
                    bucketY,
                    bucketWidth,
                    bucketHeight,
                    bucketIndex,
                    buckets.get(bucketIndex),
                    highlighted
            );
        }
    }

    private void drawBucketBox(
            Graphics2D g,
            int x,
            int y,
            int width,
            int height,
            int bucketIndex,
            List<Integer> values,
            boolean highlighted
    ) {
        g.setColor(highlighted ? Color.RED : new Color(186, 85, 211));
        g.fillRoundRect(x, y, width, height, 12, 12);

        g.setColor(Color.BLACK);
        g.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 14));
        g.drawString("Bucket " + bucketIndex, x + 10, y + 20);

        g.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 12));
        g.drawString("size = " + values.size(), x + 10, y + 38);

        int innerX = x + 10;
        int innerY = y + 50;
        int innerW = width - 20;

        int cols = Math.max(1, innerW / (CELL_W + 4));

        for (int i = 0; i < values.size(); i++) {
            int row = i / cols;
            int col = i % cols;

            int cellX = innerX + col * (CELL_W + 4);
            int cellY = innerY + row * (CELL_H + 4);

            if (cellY + CELL_H > y + height - 8) {
                break;
            }

            g.setColor(new Color(255, 235, 59));
            g.fillRoundRect(cellX, cellY, CELL_W, CELL_H, 8, 8);

            g.setColor(Color.BLACK);
            g.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 12));
            drawCenteredString(g, String.valueOf(values.get(i)), cellX, cellY, CELL_W, CELL_H);
        }
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
