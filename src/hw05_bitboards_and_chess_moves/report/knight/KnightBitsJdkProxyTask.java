package hw05_bitboards_and_chess_moves.report.knight;

import hw05_bitboards_and_chess_moves.core.model.PieceType;
import hw05_bitboards_and_chess_moves.report.PopcountProxyBitsTask;

public final class KnightBitsJdkProxyTask extends PopcountProxyBitsTask {
    public KnightBitsJdkProxyTask() {
        super("knight_bits_jdk", "Knight - BITS (jdk)", PieceType.KNIGHT, "jdk");
    }
}
