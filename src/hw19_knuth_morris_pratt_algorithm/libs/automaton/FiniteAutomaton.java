package hw19_knuth_morris_pratt_algorithm.libs.automaton;

import java.util.Map;
import java.util.Set;

public class FiniteAutomaton {
    private final Map<Character, Integer>[] transitions;
    private final int terminalState;
    private int currentState;

    public FiniteAutomaton(
            Map<Character, Integer>[] transitions,
            int terminalState
    ) {
        this.transitions = transitions;
        this.terminalState = terminalState;
        this.currentState = 0;
    }

    public void reset() {
        currentState = 0;
    }

    public int currentState() {
        return currentState;
    }

    public int terminalState() {
        return terminalState;
    }

    public boolean isTerminal() {
        return currentState == terminalState;
    }

    public Set<Character> alphabet() {
        return transitions[0].keySet();
    }

    public void accept(char symbol) {
        currentState = transition(currentState, symbol);
    }

    public int transition(int state, char symbol) {
        return transitions[state].getOrDefault(symbol, 0);
    }

    public int stateCount() {
        return transitions.length;
    }
}