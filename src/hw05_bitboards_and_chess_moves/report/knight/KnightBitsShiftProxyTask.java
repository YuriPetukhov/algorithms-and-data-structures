package hw05_bitboards_and_chess_moves.report.knight;

import hw05_bitboards_and_chess_moves.core.model.PieceType;
import hw05_bitboards_and_chess_moves.report.PopcountProxyBitsTask;

public final class KnightBitsShiftProxyTask extends PopcountProxyBitsTask {
    public KnightBitsShiftProxyTask() {
        super("knight_bits_shift", "Knight - BITS (shift)", PieceType.KNIGHT, "shift");
    }
}