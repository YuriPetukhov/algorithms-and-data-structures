package hw05_bitboards_and_chess_moves.report.bishop;

import hw05_bitboards_and_chess_moves.core.model.PieceType;
import hw05_bitboards_and_chess_moves.report.PopcountProxyBitsTask;

public final class BishopBitsJdkProxyTask extends PopcountProxyBitsTask {
    public BishopBitsJdkProxyTask() {
        super("bishop_bits_jdk", "Bishop - BITS (jdk)", PieceType.BISHOP, "jdk");
    }
}
