package hw05_bitboards_and_chess_moves.report.king;

import hw05_bitboards_and_chess_moves.core.model.PieceType;
import hw05_bitboards_and_chess_moves.report.PopcountProxyBitsTask;

public final class KingBitsJdkProxyTask extends PopcountProxyBitsTask {
    public KingBitsJdkProxyTask() {
        super("king_bits_jdk", "King - BITS (jdk)", PieceType.KING, "jdk");
    }
}
