package hw21_dynamic_programming.test.adapter.support;

import hw21_dynamic_programming.tasks.barn.model.BarnInput;
import hw21_dynamic_programming.tasks.barn.model.Cell;

import java.util.ArrayList;
import java.util.List;

public final class TaskInputParsers {

    private TaskInputParsers() {
    }

    public static int[][] digitTriangle(String rawInput) {
        TokenCursor cursor = new TokenCursor(rawInput);
        int height = cursor.nextInt("tree height N");
        int[][] tree = new int[height][];
        for (int row = 0; row < height; row++) {
            tree[row] = new int[row + 1];
            for (int column = 0; column <= row; column++) {
                tree[row][column] = cursor.nextInt(
                        "tree[" + row + "][" + column + "]"
                );
            }
        }
        cursor.requireEnd();
        return tree;
    }

    public static int[][] squareIntegerMatrix(String rawInput) {
        TokenCursor cursor = new TokenCursor(rawInput);
        int size = cursor.nextInt("matrix size N");
        int[][] matrix = new int[size][size];
        for (int row = 0; row < size; row++) {
            for (int column = 0; column < size; column++) {
                matrix[row][column] = cursor.nextInt(
                        "matrix[" + row + "][" + column + "]"
                );
            }
        }
        cursor.requireEnd();
        return matrix;
    }

    public static boolean[][] denseBlockedMatrix(String rawInput) {
        TokenCursor cursor = new TokenCursor(rawInput);
        int width = cursor.nextInt("width N");
        int height = cursor.nextInt("height M");
        boolean[][] blocked = new boolean[height][width];
        for (int row = 0; row < height; row++) {
            for (int column = 0; column < width; column++) {
                int value = cursor.nextInt(
                        "matrix[" + row + "][" + column + "]"
                );
                if (value != 0 && value != 1) {
                    throw new IllegalArgumentException(
                            "Matrix cell must contain 0 or 1, got: " + value
                    );
                }
                blocked[row][column] = value == 1;
            }
        }
        cursor.requireEnd();
        return blocked;
    }

    public static BarnInput sparseBarnInput(String rawInput) {
        TokenCursor cursor = new TokenCursor(rawInput);
        int width = cursor.nextInt("width N");
        int height = cursor.nextInt("height M");
        int blockedCount = cursor.nextInt("blocked cell count T");
        List<Cell> cells = new ArrayList<>(Math.max(0, blockedCount));
        for (int index = 0; index < blockedCount; index++) {
            int x = cursor.nextInt("X of blocked cell " + index);
            int y = cursor.nextInt("Y of blocked cell " + index);
            cells.add(new Cell(x, y));
        }
        cursor.requireEnd();
        return new BarnInput(width, height, cells);
    }

    public static int[] sizedIntegerSequence(String rawInput) {
        TokenCursor cursor = new TokenCursor(rawInput);
        int size = cursor.nextInt("sequence size N");
        int[] values = new int[size];
        for (int index = 0; index < size; index++) {
            values[index] = cursor.nextInt("A[" + index + "]");
        }
        cursor.requireEnd();
        return values;
    }

    public static int singleInteger(String rawInput, String label) {
        TokenCursor cursor = new TokenCursor(rawInput);
        int value = cursor.nextInt(label);
        cursor.requireEnd();
        return value;
    }
}
