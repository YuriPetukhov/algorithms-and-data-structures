package hw19_knuth_morris_pratt_algorithm.libs.prefix;

public interface PrefixFunction {
    int[] compute(String pattern);

    String name();
}