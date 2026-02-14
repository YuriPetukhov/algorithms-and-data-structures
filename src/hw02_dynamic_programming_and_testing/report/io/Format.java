package hw02_dynamic_programming_and_testing.report.io;

import java.util.List;

public final class Format {

    private Format() {}

    public static int[] columnWidths(List<List<String>> rows) {
        int cols = rows.get(0).size();
        int[] w = new int[cols];

        for (List<String> r : rows) {
            for (int i = 0; i < cols; i++) {
                w[i] = Math.max(w[i], r.get(i).length());
            }
        }
        return w;
    }

    public static void printRow(List<String> row, int[] w) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < row.size(); i++) {
            sb.append(padLeft(row.get(i), w[i]));
            if (i != row.size() - 1) sb.append(" | ");
        }
        System.out.println(sb);
    }

    public static void printSep(int[] w) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < w.length; i++) {
            sb.append("-".repeat(w[i]));
            if (i != w.length - 1) sb.append("-+-");
        }
        System.out.println(sb);
    }

    public static String padLeft(String s, int width) {
        if (s.length() >= width) return s;
        return " ".repeat(width - s.length()) + s;
    }

    public static String formatNumber(long x) {
        String s = Long.toString(x);
        StringBuilder sb = new StringBuilder();
        int len = s.length();
        for (int i = 0; i < len; i++) {
            if (i > 0 && (len - i) % 3 == 0) sb.append(' ');
            sb.append(s.charAt(i));
        }
        return sb.toString();
    }
}
