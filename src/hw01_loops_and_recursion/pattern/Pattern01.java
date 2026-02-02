package hw01_loops_and_recursion.pattern;

import hw01_loops_and_recursion.node.ChooseNode;
import hw01_loops_and_recursion.node.Node;
import hw01_loops_and_recursion.node.SolidNode;

import java.util.List;

public final class Pattern01 implements Pattern {
    private final char fillChar;
    private final char emptyChar;

    public Pattern01(char fillChar, char emptyChar) {
        this.fillChar = fillChar;
        this.emptyChar = emptyChar;
    }

    @Override
    public Node build() {

        return new ChooseNode(
                (row, col, ctx) -> (col <= row ? 0 : 1),
                List.of(
                        new SolidNode(emptyChar),
                        new SolidNode(fillChar)
                )
        );
    }
}
