package hw15_demukron_algorithm.libs.graphs.demukron;

import java.util.List;

public final class IntLevelConverter {
    private IntLevelConverter() {
    }

    public static int[][] toIntArray(List<List<Integer>> levels) {
        int[][] result = new int[levels.size()][];

        for (int levelIndex = 0; levelIndex < levels.size(); levelIndex++) {
            List<Integer> level = levels.get(levelIndex);
            result[levelIndex] = new int[level.size()];

            for (int vertexIndex = 0; vertexIndex < level.size(); vertexIndex++) {
                result[levelIndex][vertexIndex] = level.get(vertexIndex);
            }
        }

        return result;
    }
}