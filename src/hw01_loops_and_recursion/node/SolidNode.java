package hw01_loops_and_recursion.node;

import hw01_loops_and_recursion.context.PatternContext;

public final class SolidNode implements Node {
    private final char ch;
    public SolidNode(char ch) { this.ch = ch; }

    @Override
    public char charAt(int row, int col, PatternContext ctx) {
        return ch;
    }
}
