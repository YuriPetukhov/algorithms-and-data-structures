package hw19_knuth_morris_pratt_algorithm.libs.prefix;

public class FastPrefixFunction implements PrefixFunction {
    @Override
    public int[] compute(String pattern) {
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
        return "Fast prefix function";
    }
}