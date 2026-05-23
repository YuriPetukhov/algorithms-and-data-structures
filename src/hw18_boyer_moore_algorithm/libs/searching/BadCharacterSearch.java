package hw18_boyer_moore_algorithm.libs.searching;

import java.util.HashMap;
import java.util.Map;

public class BadCharacterSearch implements SubstringSearch {
    @Override
    public SearchResult search(String text, String pattern) {
        Map<Character, Integer> lastOccurrence = buildLastOccurrence(pattern);
        int comparisons = 0;
        int shift = 0;

        while (shift <= text.length() - pattern.length()) {
            int j = pattern.length() - 1;

            while (j >= 0) {
                comparisons++;

                if (pattern.charAt(j) != text.charAt(shift + j)) {
                    break;
                }

                j--;
            }

            if (j < 0) {
                return new SearchResult(shift, comparisons);
            }

            char badChar = text.charAt(shift + j);
            int last = lastOccurrence.getOrDefault(badChar, -1);
            shift += Math.max(1, j - last);
        }

        return new SearchResult(-1, comparisons);
    }

    private Map<Character, Integer> buildLastOccurrence(String pattern) {
        Map<Character, Integer> lastOccurrence = new HashMap<>();

        for (int i = 0; i < pattern.length(); i++) {
            lastOccurrence.put(pattern.charAt(i), i);
        }

        return lastOccurrence;
    }

    @Override
    public String name() {
        return "Suffix shift";
    }
}