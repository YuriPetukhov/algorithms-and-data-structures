package hw18_boyer_moore_algorithm.libs.searching;

public class BruteForceSearch implements SubstringSearch {
    @Override
    public SearchResult search(String text, String pattern) {
        int comparisons = 0;

        for (int i = 0; i <= text.length() - pattern.length(); i++) {
            int j = 0;

            while (j < pattern.length()) {
                comparisons++;

                if (text.charAt(i + j) != pattern.charAt(j)) {
                    break;
                }

                j++;
            }

            if (j == pattern.length()) {
                return new SearchResult(i, comparisons);
            }
        }

        return new SearchResult(-1, comparisons);
    }

    @Override
    public String name() {
        return "Brute force";
    }
}