package hw18_boyer_moore_algorithm.libs.searching;

import java.util.HashMap;
import java.util.Map;

public class BoyerMooreSearch implements SubstringSearch {
    @Override
    public SearchResult search(String text, String pattern) {
        Map<Character, Integer> badCharacter = buildBadCharacterTable(pattern);
        int[] goodSuffix = buildGoodSuffixTable(pattern);

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
            int badCharShift = j - badCharacter.getOrDefault(badChar, -1);
            int goodSuffixShift = goodSuffix[j];

            shift += Math.max(1, Math.max(badCharShift, goodSuffixShift));
        }

        return new SearchResult(-1, comparisons);
    }

    private Map<Character, Integer> buildBadCharacterTable(String pattern) {
        Map<Character, Integer> table = new HashMap<>();

        for (int i = 0; i < pattern.length(); i++) {
            table.put(pattern.charAt(i), i);
        }

        return table;
    }

    private int[] buildGoodSuffixTable(String pattern) {
        int length = pattern.length();
        int[] shift = new int[length];
        int[] borderPosition = new int[length + 1];

        int i = length;
        int j = length + 1;

        borderPosition[i] = j;

        while (i > 0) {
            while (j <= length && pattern.charAt(i - 1) != pattern.charAt(j - 1)) {
                if (shift[j - 1] == 0) {
                    shift[j - 1] = j - i;
                }

                j = borderPosition[j];
            }

            i--;
            j--;
            borderPosition[i] = j;
        }

        j = borderPosition[0];

        for (i = 0; i < length; i++) {
            if (shift[i] == 0) {
                shift[i] = j;
            }

            if (i == j) {
                j = borderPosition[j];
            }
        }

        return shift;
    }

    @Override
    public String name() {
        return "Boyer-Moore";
    }
}