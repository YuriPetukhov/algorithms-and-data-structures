package hw05_bitboards_and_chess_moves.report.queen;

import hw05_bitboards_and_chess_moves.core.model.PieceType;
import hw05_bitboards_and_chess_moves.report.PopcountProxyBitsTask;

public final class QueenBitsJdkProxyTask extends PopcountProxyBitsTask {
    public QueenBitsJdkProxyTask() {
        super("queen_bits_jdk", "Queen - BITS (jdk)", PieceType.QUEEN, "jdk");
    }
}
