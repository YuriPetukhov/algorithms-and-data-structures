package hw18_boyer_moore_algorithm.libs.searching;

public class PrefixShiftSearch implements SubstringSearch {
    @Override
    public SearchResult search(String text, String pattern) {
        int[] prefix = buildPrefixFunction(pattern);
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

    private int[] buildPrefixFunction(String pattern) {
        int[] prefix = new int[pattern.length()];

        for (int i = 1; i < pattern.length(); i++) {
            int j = prefix[i - 1];

            while (j > 0 && pattern.charAt(i) != pattern.charAt(j)) {
                j = prefix[j - 1];
            }

            if (pattern.charAt(i) == pattern.charAt(j)) {
                j++;
            }

            prefix[i] = j;
        }

        return prefix;
    }

    @Override
    public String name() {
        return "Prefix shift";
    }
}