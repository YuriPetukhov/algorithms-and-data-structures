package hw10_search_trees.benchmark;

import java.util.Arrays;
import java.util.Random;

public final class TreeBenchmarkDataFactory {

    private TreeBenchmarkDataFactory() {
    }

    public static TreeBenchmarkData randomOrder(int n, long seed) {
        Random random = new Random(seed);

        int[] insertData = new int[n];
        for (int i = 0; i < n; i++) {
            insertData[i] = random.nextInt(n * 10);
        }

        int m = Math.max(1, n / 10);

        int[] searchData = new int[m];
        int[] removeData = new int[m];

        for (int i = 0; i < m; i++) {
            searchData[i] = insertData[random.nextInt(insertData.length)];
            removeData[i] = insertData[random.nextInt(insertData.length)];
        }

        return new TreeBenchmarkData(insertData, searchData, removeData);
    }

    public static TreeBenchmarkData sortedOrder(int n, long seed) {
        Random random = new Random(seed);

        int[] insertData = new int[n];
        for (int i = 0; i < n; i++) {
            insertData[i] = i;
        }

        int m = Math.max(1, n / 10);

        int[] searchData = new int[m];
        int[] removeData = new int[m];

        for (int i = 0; i < m; i++) {
            searchData[i] = random.nextInt(n);
            removeData[i] = random.nextInt(n);
        }

        return new TreeBenchmarkData(insertData, searchData, removeData);
    }

    public static int[] copy(int[] source) {
        return Arrays.copyOf(source, source.length);
    }
}
