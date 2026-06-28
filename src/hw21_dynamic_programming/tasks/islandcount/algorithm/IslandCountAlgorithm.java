package hw21_dynamic_programming.tasks.islandcount.algorithm;

import hw21_dynamic_programming.algorithms.api.Algorithm;
import hw21_dynamic_programming.tasks.islandcount.algorithm.IslandCounter;

import java.util.Objects;

public final class IslandCountAlgorithm implements Algorithm<int[][], Integer> {

    public static final String ID = "island-count";
    private final IslandCounter counter;

    public IslandCountAlgorithm() {
        this(new IslandCounter());
    }

    public IslandCountAlgorithm(IslandCounter counter) {
        this.counter = Objects.requireNonNull(counter, "Counter must not be null.");
    }

    @Override
    public String id() {
        return ID;
    }

    @Override
    public Class<int[][]> inputType() {
        return int[][].class;
    }

    @Override
    public Class<Integer> resultType() {
        return Integer.class;
    }

    @Override
    public Integer execute(int[][] input) {
        return counter.count(input);
    }
}
