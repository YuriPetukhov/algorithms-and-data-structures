package hw05_bitboards_and_chess_moves.report.king;

import hw05_bitboards_and_chess_moves.core.model.PieceType;
import hw05_bitboards_and_chess_moves.report.PopcountProxyBitsTask;

public final class KingBitsShiftProxyTask extends PopcountProxyBitsTask {
    public KingBitsShiftProxyTask() {
        super("king_bits_shift", "King - BITS (shift)", PieceType.KING, "shift");
    }
}