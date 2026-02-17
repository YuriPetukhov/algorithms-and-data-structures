package hw05_bitboards_and_chess_moves.report.rook;

import hw05_bitboards_and_chess_moves.core.model.PieceType;
import hw05_bitboards_and_chess_moves.report.PopcountProxyBitsTask;

public final class RookBitsShiftProxyTask extends PopcountProxyBitsTask {
    public RookBitsShiftProxyTask() {
        super("rook_bits_shift", "Rook - BITS (shift)", PieceType.ROOK, "shift");
    }
}