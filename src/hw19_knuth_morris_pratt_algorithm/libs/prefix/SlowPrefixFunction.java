package hw19_knuth_morris_pratt_algorithm.libs.prefix;

public class SlowPrefixFunction implements PrefixFunction {
    @Override
    public int[] compute(String pattern) {
        int[] prefix = new int[pattern.length()];

        for (int i = 0; i < pattern.length(); i++) {
            prefix[i] = calculatePrefixForPosition(pattern, i);
        }

        return prefix;
    }

    private int calculatePrefixForPosition(String pattern, int position) {
        int maxLength = 0;

        for (int length = 1; length <= position; length++) {
            if (isPrefixEqualsSuffix(pattern, position, length)) {
                maxLength = length;
            }
        }

        return maxLength;
    }

    private boolean isPrefixEqualsSuffix(
            String pattern,
            int position,
            int length
    ) {
        int suffixStart = position - length + 1;

        for (int i = 0; i < length; i++) {
            if (pattern.charAt(i) != pattern.charAt(suffixStart + i)) {
                return false;
            }
        }

        return true;
    }

    @Override
    public String name() {
        return "Slow prefix function";
    }
}