package hw05_bitboards_and_chess_moves.report.rook;

import hw05_bitboards_and_chess_moves.core.model.PieceType;
import hw05_bitboards_and_chess_moves.report.PopcountProxyBitsTask;

public final class RookBitsJdkProxyTask extends PopcountProxyBitsTask {
    public RookBitsJdkProxyTask() {
        super("rook_bits_jdk", "Rook - BITS (jdk)", PieceType.ROOK, "jdk");
    }
}
