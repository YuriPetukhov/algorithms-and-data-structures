package hw05_bitboards_and_chess_moves.report.king;

import hw05_bitboards_and_chess_moves.core.model.PieceType;
import hw05_bitboards_and_chess_moves.report.PopcountProxyBitsTask;

public final class KingBitsKernighanProxyTask extends PopcountProxyBitsTask {
    public KingBitsKernighanProxyTask() {
        super("king_bits_kernighan", "King - BITS (kernighan)", PieceType.KING, "kernighan");
    }
}

