package hw01_loops_and_recursion.pattern;

import hw01_loops_and_recursion.node.ChooseNode;
import hw01_loops_and_recursion.node.Node;
import hw01_loops_and_recursion.node.SolidNode;

import java.util.List;

public final class Pattern05 implements Pattern {

    private final int startCol;
    private final int blockLength;
    private final int shiftStep;
    private final char fillChar;
    private final char emptyChar;

    public Pattern05(int startCol, int blockLength, int shiftStep, char fillChar, char emptyChar) {
        this.startCol = startCol;
        this.blockLength = blockLength;
        this.shiftStep = shiftStep;
        this.fillChar = fillChar;
        this.emptyChar = emptyChar;
    }

    @Override
    public Node build() {
        Node fill = new SolidNode(fillChar);
        Node empty = new SolidNode(emptyChar);

        return new ChooseNode(
                (row, col, ctx) -> {
                    int size = ctx.size();

                    int blockStart = startCol + row * shiftStep;
                    int blockEndExclusive = blockStart + blockLength;

                    int left = Math.max(blockStart, 0);
                    int right = Math.min(blockEndExclusive, size);

                    boolean inside = (col >= left && col < right);
                    return inside ? 0 : 1;
                },
                List.of(fill, empty)
        );
    }
}
