package hw19_knuth_morris_pratt_algorithm.libs.automaton;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

public class PrefixAutomatonBuilder {
    public FiniteAutomaton build(String pattern) {
        Set<Character> alphabet = buildAlphabet(pattern);

        @SuppressWarnings("unchecked")
        Map<Character, Integer>[] transitions = new Map[pattern.length() + 1];

        for (int state = 0; state <= pattern.length(); state++) {
            transitions[state] = new HashMap<>();

            for (char symbol : alphabet) {
                transitions[state].put(
                        symbol,
                        calculateNextState(pattern, state, symbol)
                );
            }
        }

        return new FiniteAutomaton(transitions, pattern.length());
    }

    private Set<Character> buildAlphabet(String pattern) {
        Set<Character> alphabet = new LinkedHashSet<>();

        for (int i = 0; i < pattern.length(); i++) {
            alphabet.add(pattern.charAt(i));
        }

        return alphabet;
    }

    private int calculateNextState(
            String pattern,
            int state,
            char symbol
    ) {
        String candidate = pattern.substring(0, state) + symbol;
        int maxLength = Math.min(pattern.length(), candidate.length());

        for (int length = maxLength; length >= 0; length--) {
            if (isSuffixEqualsPrefix(candidate, pattern, length)) {
                return length;
            }
        }

        return 0;
    }

    private boolean isSuffixEqualsPrefix(
            String text,
            String pattern,
            int length
    ) {
        if (length == 0) {
            return true;
        }

        int textStart = text.length() - length;

        for (int i = 0; i < length; i++) {
            if (text.charAt(textStart + i) != pattern.charAt(i)) {
                return false;
            }
        }

        return true;
    }
}