package hw01_loops_and_recursion.node;

import hw01_loops_and_recursion.context.PatternContext;

public interface Node {
    char charAt(int row, int col, PatternContext ctx);
}
