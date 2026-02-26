package hw06_sorting_algorithms.programs.sorting.ui;

import hw06_sorting_algorithms.programs.sorting.player.SortPlaybackState;

import javax.swing.*;
import java.awt.*;

public final class HeapTreeScene extends JComponent {

    private int[] a = new int[0];
    private Integer hiA = null;
    private Integer hiB = null;
    private String lastEvent = "";

    public void setState(SortPlaybackState state) {
        if (state == null || state.array() == null) {
            a = new int[0];
            hiA = hiB = null;
            lastEvent = "";
        } else {
            a = state.array();
            hiA = state.highlightIndexA();
            hiB = state.highlightIndexB();
            lastEvent = state.lastEvent() == null ? "" : state.lastEvent();
        }
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g0) {
        super.paintComponent(g0);

        Graphics2D g = (Graphics2D) g0.create();
        try {
            g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            int w = getWidth();
            int h = getHeight();

            g.setColor(Color.DARK_GRAY);
            g.drawString(lastEvent, 10, 16);

            int top = 24;
            int treeH = (int) ((h - top) * 0.72);
            int arrH  = (h - top) - treeH;

            paintHeapTree(g, 0, top, w, treeH);
            paintArrayRow(g, 0, top + treeH, w, arrH);

        } finally {
            g.dispose();
        }
    }

    private void paintHeapTree(Graphics2D g, int x, int y, int w, int h) {
        if (a.length == 0) {
            g.setColor(Color.GRAY);
            g.drawString("Empty", x + 10, y + 20);
            return;
        }

        int n = a.length;

        int levels = 1;
        while ((1 << levels) - 1 < n) levels++;

        int nodeR = Math.max(10, Math.min(22, w / 28));
        int topPad = 20;
        int levelGap = Math.max(28, (h - topPad - nodeR * 2) / Math.max(1, levels - 1));

        g.setColor(new Color(180, 180, 180));
        for (int i = 0; i < n; i++) {
            Point p = nodeCenter(i, x, y, w, topPad, levelGap);

            int left = 2 * i + 1;
            int right = left + 1;

            if (left < n) {
                Point c = nodeCenter(left, x, y, w, topPad, levelGap);
                g.drawLine(p.x, p.y, c.x, c.y);
            }
            if (right < n) {
                Point c = nodeCenter(right, x, y, w, topPad, levelGap);
                g.drawLine(p.x, p.y, c.x, c.y);
            }
        }

        for (int i = 0; i < n; i++) {
            Point p = nodeCenter(i, x, y, w, topPad, levelGap);
            boolean marked = isMarked(i);

            g.setColor(marked ? Color.ORANGE : new Color(235, 235, 235));
            g.fillOval(p.x - nodeR, p.y - nodeR, nodeR * 2, nodeR * 2);

            g.setColor(Color.DARK_GRAY);
            g.drawOval(p.x - nodeR, p.y - nodeR, nodeR * 2, nodeR * 2);

            String text = Integer.toString(a[i]);
            FontMetrics fm = g.getFontMetrics();
            int tx = p.x - fm.stringWidth(text) / 2;
            int ty = p.y + fm.getAscent() / 2 - 2;
            g.drawString(text, tx, ty);
        }
    }

    private void paintArrayRow(Graphics2D g, int x, int y, int w, int h) {
        g.setColor(Color.GRAY);
        g.drawLine(x, y, x + w, y);

        if (a.length == 0) return;

        int n = a.length;

        int cellW = Math.max(18, Math.min(44, (w - 20) / Math.min(n, 30)));
        int startX = x + 10;
        int cy = y + h / 2;

        for (int i = 0; i < n && startX + i * cellW < x + w - 10; i++) {
            int cx = startX + i * cellW;
            boolean marked = isMarked(i);

            g.setColor(marked ? Color.ORANGE : new Color(245, 245, 245));
            g.fillRect(cx, cy - 14, cellW - 2, 28);

            g.setColor(Color.DARK_GRAY);
            g.drawRect(cx, cy - 14, cellW - 2, 28);

            String text = Integer.toString(a[i]);
            FontMetrics fm = g.getFontMetrics();
            g.drawString(text, cx + (cellW - fm.stringWidth(text)) / 2 - 1, cy + fm.getAscent() / 2 - 2);
        }
    }

    private boolean isMarked(int i) {
        return (hiA != null && hiA == i) || (hiB != null && hiB == i);
    }

    private static int levelOf(int i) {
        int v = i + 1;
        int level = 0;
        while ((v >>= 1) != 0) level++;
        return level;
    }

    private static Point nodeCenter(int i, int x, int y, int w, int topPad, int levelGap) {
        int level = levelOf(i);
        int levelStart = (1 << level) - 1;
        int posInLevel = i - levelStart;
        int nodesOnLevel = 1 << level;

        int cx = x + (int) ((posInLevel + 0.5) * w / nodesOnLevel);
        int cy = y + topPad + level * levelGap;
        return new Point(cx, cy);
    }
}