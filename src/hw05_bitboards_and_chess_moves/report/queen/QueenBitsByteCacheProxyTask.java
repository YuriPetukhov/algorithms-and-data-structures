package hw05_bitboards_and_chess_moves.report.queen;

import hw05_bitboards_and_chess_moves.core.model.PieceType;
import hw05_bitboards_and_chess_moves.report.PopcountProxyBitsTask;

public final class QueenBitsByteCacheProxyTask extends PopcountProxyBitsTask {
    public QueenBitsByteCacheProxyTask() {
        super("queen_bits_byte_cache", "Queen - BITS (byte_cache)", PieceType.QUEEN, "byte_cache");
    }
}

