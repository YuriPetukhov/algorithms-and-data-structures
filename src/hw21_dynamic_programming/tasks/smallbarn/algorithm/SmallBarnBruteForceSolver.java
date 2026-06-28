package hw21_dynamic_programming.tasks.smallbarn.algorithm;

public final class SmallBarnBruteForceSolver {

    public int solve(boolean[][] blocked) {
        int rows = blocked.length;
        int columns = blocked[0].length;
        int[][] occupiedPrefix = buildPrefix(blocked);
        int bestArea = 0;

        for (int top = 0; top < rows; top++) {
            for (int left = 0; left < columns; left++) {
                for (int bottom = top; bottom < rows; bottom++) {
                    for (int right = left; right < columns; right++) {
                        if (occupiedCount(occupiedPrefix, top, left, bottom, right) == 0) {
                            int area = (bottom - top + 1) * (right - left + 1);
                            bestArea = Math.max(bestArea, area);
                        }
                    }
                }
            }
        }
        return bestArea;
    }

    private static int[][] buildPrefix(boolean[][] blocked) {
        int rows = blocked.length;
        int columns = blocked[0].length;
        int[][] prefix = new int[rows + 1][columns + 1];

        for (int row = 0; row < rows; row++) {
            for (int column = 0; column < columns; column++) {
                int occupied = blocked[row][column] ? 1 : 0;
                prefix[row + 1][column + 1] = occupied
                        + prefix[row][column + 1]
                        + prefix[row + 1][column]
                        - prefix[row][column];
            }
        }
        return prefix;
    }

    private static int occupiedCount(
            int[][] prefix,
            int top,
            int left,
            int bottom,
            int right
    ) {
        return prefix[bottom + 1][right + 1]
                - prefix[top][right + 1]
                - prefix[bottom + 1][left]
                + prefix[top][left];
    }
}
