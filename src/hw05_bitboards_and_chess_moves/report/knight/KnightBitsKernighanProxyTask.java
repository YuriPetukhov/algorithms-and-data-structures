package hw05_bitboards_and_chess_moves.report.knight;

import hw05_bitboards_and_chess_moves.core.model.PieceType;
import hw05_bitboards_and_chess_moves.report.PopcountProxyBitsTask;

public final class KnightBitsKernighanProxyTask extends PopcountProxyBitsTask {
    public KnightBitsKernighanProxyTask() {
        super("knight_bits_kernighan", "Knight - BITS (kernighan)", PieceType.KNIGHT, "kernighan");
    }
}

