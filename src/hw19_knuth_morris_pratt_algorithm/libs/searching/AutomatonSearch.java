package hw19_knuth_morris_pratt_algorithm.libs.searching;

import hw19_knuth_morris_pratt_algorithm.libs.automaton.FiniteAutomaton;
import hw19_knuth_morris_pratt_algorithm.libs.automaton.PrefixAutomatonBuilder;

public class AutomatonSearch implements SubstringSearch {
    private final PrefixAutomatonBuilder builder = new PrefixAutomatonBuilder();

    @Override
    public SearchResult search(String text, String pattern) {
        FiniteAutomaton automaton = builder.build(pattern);
        int comparisons = 0;

        for (int i = 0; i < text.length(); i++) {
            comparisons++;
            automaton.accept(text.charAt(i));

            if (automaton.isTerminal()) {
                return new SearchResult(i - pattern.length() + 1, comparisons);
            }
        }

        return new SearchResult(-1, comparisons);
    }

    @Override
    public String name() {
        return "Finite automaton";
    }
}