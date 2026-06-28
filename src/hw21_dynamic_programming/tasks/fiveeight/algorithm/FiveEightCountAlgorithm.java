package hw21_dynamic_programming.tasks.fiveeight.algorithm;

import hw21_dynamic_programming.algorithms.api.Algorithm;
import hw21_dynamic_programming.tasks.fiveeight.algorithm.FiveEightCounter;

import java.math.BigInteger;
import java.util.Objects;

public final class FiveEightCountAlgorithm implements Algorithm<Integer, BigInteger> {

    public static final String ID = "five-eight-count";
    private final FiveEightCounter counter;

    public FiveEightCountAlgorithm() {
        this(new FiveEightCounter());
    }

    public FiveEightCountAlgorithm(FiveEightCounter counter) {
        this.counter = Objects.requireNonNull(counter, "Counter must not be null.");
    }

    @Override
    public String id() {
        return ID;
    }

    @Override
    public Class<Integer> inputType() {
        return Integer.class;
    }

    @Override
    public Class<BigInteger> resultType() {
        return BigInteger.class;
    }

    @Override
    public BigInteger execute(Integer input) {
        return counter.count(input);
    }
}
