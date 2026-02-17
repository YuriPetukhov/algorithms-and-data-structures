package hw05_bitboards_and_chess_moves.report.king;

import hw05_bitboards_and_chess_moves.core.model.PieceType;
import hw05_bitboards_and_chess_moves.report.PopcountProxyBitsTask;

public final class KingBitsByteCacheProxyTask extends PopcountProxyBitsTask {
    public KingBitsByteCacheProxyTask() {
        super("king_bits_byte_cache", "King - BITS (byte_cache)", PieceType.KING, "byte_cache");
    }
}

