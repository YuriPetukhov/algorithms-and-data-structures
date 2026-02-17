package hw05_bitboards_and_chess_moves.report.bishop;

import hw05_bitboards_and_chess_moves.core.model.PieceType;
import hw05_bitboards_and_chess_moves.report.PopcountProxyBitsTask;

public final class BishopBitsShiftProxyTask extends PopcountProxyBitsTask {
    public BishopBitsShiftProxyTask() {
        super("bishop_bits_shift", "Bishop - BITS (shift)", PieceType.BISHOP, "shift");
    }
}