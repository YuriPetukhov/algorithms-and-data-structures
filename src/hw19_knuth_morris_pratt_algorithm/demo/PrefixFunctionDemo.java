package hw19_knuth_morris_pratt_algorithm.demo;

import hw19_knuth_morris_pratt_algorithm.libs.prefix.FastPrefixFunction;
import hw19_knuth_morris_pratt_algorithm.libs.prefix.PrefixFunction;
import hw19_knuth_morris_pratt_algorithm.libs.prefix.SlowPrefixFunction;
import hw19_knuth_morris_pratt_algorithm.visualization.SearchConsoleVisualizer;

import java.util.List;

public class PrefixFunctionDemo {
    public static void main(String[] args) {
        String pattern = "ababcabab";

        List<PrefixFunction> functions = List.of(
                new SlowPrefixFunction(),
                new FastPrefixFunction()
        );

        for (PrefixFunction function : functions) {
            int[] prefix = function.compute(pattern);

            SearchConsoleVisualizer.title(function.name());
            SearchConsoleVisualizer.prefixFunction(pattern, prefix);
            SearchConsoleVisualizer.emptyLine();
        }
    }
}