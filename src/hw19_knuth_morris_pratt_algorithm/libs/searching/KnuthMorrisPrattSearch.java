package hw19_knuth_morris_pratt_algorithm.libs.searching;

import hw19_knuth_morris_pratt_algorithm.libs.prefix.FastPrefixFunction;
import hw19_knuth_morris_pratt_algorithm.libs.prefix.PrefixFunction;

public class KnuthMorrisPrattSearch implements SubstringSearch {
    private final PrefixFunction prefixFunction;

    public KnuthMorrisPrattSearch() {
        this(new FastPrefixFunction());
    }

    public KnuthMorrisPrattSearch(PrefixFunction prefixFunction) {
        this.prefixFunction = prefixFunction;
    }

    @Override
    public SearchResult search(String text, String pattern) {
        int[] prefix = prefixFunction.compute(pattern);
        int comparisons = 0;
        int j = 0;

        for (int i = 0; i < text.length(); i++) {
            while (j > 0) {
                comparisons++;

                if (text.charAt(i) == pattern.charAt(j)) {
                    break;
                }

                j = prefix[j - 1];
            }

            comparisons++;

            if (text.charAt(i) == pattern.charAt(j)) {
                j++;
            }

            if (j == pattern.length()) {
                return new SearchResult(i - pattern.length() + 1, comparisons);
            }
        }

        return new SearchResult(-1, comparisons);
    }

    @Override
    public String name() {
        return "Knuth-Morris-Pratt";
    }
}