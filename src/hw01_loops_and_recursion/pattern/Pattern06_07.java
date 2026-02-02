package hw01_loops_and_recursion.pattern;

import hw01_loops_and_recursion.node.ChooseNode;
import hw01_loops_and_recursion.node.Node;
import hw01_loops_and_recursion.node.SolidNode;

import java.util.List;

public final class Pattern06_07 implements Pattern {

    private final int startRow;
    private final int startColumn;
    private final int innerSquareSize;
    private final char fillChar;
    private final char emptyChar;

    public Pattern06_07(int startRow, int startColumn, int innerSquareSize, char fillChar, char emptyChar) {
        this.startRow = startRow;
        this.startColumn = startColumn;
        this.innerSquareSize = innerSquareSize;
        this.fillChar = fillChar;
        this.emptyChar = emptyChar;
    }

    @Override
    public Node build() {
        Node fill = new SolidNode(fillChar);
        Node empty = new SolidNode(emptyChar);

        int endRow = startRow + innerSquareSize - 1;
        int endCol = startColumn + innerSquareSize - 1;

        return new ChooseNode(
                (row, col, ctx) -> {
                    boolean inside =
                            row >= startRow && row <= endRow &&
                                    col >= startColumn && col <= endCol;

                    return inside ? 0 : 1;
                },
                List.of(fill, empty)
        );
    }
}
