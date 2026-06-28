package hw21_dynamic_programming.tasks.barn.algorithm;

public final class FreeHeightCalculator {

    public int[][] calculate(boolean[][] blocked) {
        int rows = blocked.length;
        int columns = blocked[0].length;
        int[][] heights = new int[rows][columns];

        for (int row = 0; row < rows; row++) {
            for (int column = 0; column < columns; column++) {
                if (blocked[row][column]) {
                    heights[row][column] = 0;
                } else {
                    heights[row][column] = row == 0
                            ? 1
                            : heights[row - 1][column] + 1;
                }
            }
        }
        return heights;
    }
}
