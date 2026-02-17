package hw05_bitboards_and_chess_moves.report.queen;

import hw05_bitboards_and_chess_moves.core.model.PieceType;
import hw05_bitboards_and_chess_moves.report.PopcountProxyBitsTask;

public final class QueenBitsShiftProxyTask extends PopcountProxyBitsTask {
    public QueenBitsShiftProxyTask() {
        super("queen_bits_shift", "Queen - BITS (shift)", PieceType.QUEEN, "shift");
    }
}