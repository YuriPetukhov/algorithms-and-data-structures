package hw21_dynamic_programming.console.format;

import java.util.StringJoiner;

public final class TextFormatters {

    private TextFormatters() {
    }

    public static String integerArray(int[] values) {
        StringJoiner joiner = new StringJoiner(" ");
        for (int value : values) {
            joiner.add(Integer.toString(value));
        }
        return joiner.toString();
    }

    public static String integerMatrix(int[][] matrix) {
        StringJoiner rows = new StringJoiner(System.lineSeparator());
        for (int[] row : matrix) {
            rows.add(integerArray(row));
        }
        return rows.toString();
    }
}
