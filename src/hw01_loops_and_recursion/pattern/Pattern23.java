package hw01_loops_and_recursion.pattern;

import hw01_loops_and_recursion.node.ChooseNode;
import hw01_loops_and_recursion.node.Node;
import hw01_loops_and_recursion.node.SolidNode;

import java.util.List;

public final class Pattern23 implements Pattern {

    private final int gapRowDots;
    private final int gapColDots;
    private final char fillChar;
    private final char emptyChar;

    public Pattern23(int gapRowDots, int gapColDots, char fillChar, char emptyChar) {
        if (gapRowDots < 0) throw new IllegalArgumentException("gapRowDots must be >= 0");
        if (gapColDots < 0) throw new IllegalArgumentException("gapColDots must be >= 0");
        this.gapRowDots = gapRowDots;
        this.gapColDots = gapColDots;
        this.fillChar = fillChar;
        this.emptyChar = emptyChar;
    }

    @Override
    public Node build() {
        Node fill = new SolidNode(fillChar);
        Node empty = new SolidNode(emptyChar);

        return new ChooseNode(
                (row, col, ctx) -> {
                    int stepRow = gapRowDots + 1;
                    int stepCol = gapColDots + 1;

                    boolean inside =
                            (row % stepRow == 0) &&
                                    (col % stepCol == 0);

                    return inside ? 0 : 1;
                },
                List.of(fill, empty)
        );
    }
}
