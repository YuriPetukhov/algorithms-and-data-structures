package hw21_dynamic_programming.tasks.islandcount.algorithm;

import java.util.ArrayDeque;
import java.util.Queue;

public final class IslandCounter {

    private static final int[][] MOVES = {
            {1, 0}, {-1, 0}, {0, 1}, {0, -1}
    };

    public int count(int[][] matrix) {
        int rows = matrix.length;
        int cols = rows == 0 ? 0 : matrix[0].length;
        boolean[][] visited = new boolean[rows][cols];
        int islands = 0;

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                if (matrix[row][col] == 1 && !visited[row][col]) {
                    islands++;
                    bfs(matrix, visited, row, col);
                }
            }
        }
        return islands;
    }

    private void bfs(
            int[][] matrix,
            boolean[][] visited,
            int startRow,
            int startCol
    ) {
        Queue<int[]> queue = new ArrayDeque<>();
        queue.add(new int[]{startRow, startCol});
        visited[startRow][startCol] = true;

        while (!queue.isEmpty()) {
            int[] cell = queue.remove();
            for (int[] move : MOVES) {
                int nextRow = cell[0] + move[0];
                int nextCol = cell[1] + move[1];

                if (nextRow >= 0
                        && nextRow < matrix.length
                        && nextCol >= 0
                        && nextCol < matrix[nextRow].length
                        && matrix[nextRow][nextCol] == 1
                        && !visited[nextRow][nextCol]) {
                    visited[nextRow][nextCol] = true;
                    queue.add(new int[]{nextRow, nextCol});
                }
            }
        }
    }
}
