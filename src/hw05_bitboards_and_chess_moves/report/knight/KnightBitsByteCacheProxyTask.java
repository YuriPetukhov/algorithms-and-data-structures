package hw05_bitboards_and_chess_moves.report.knight;

import hw05_bitboards_and_chess_moves.core.model.PieceType;
import hw05_bitboards_and_chess_moves.report.PopcountProxyBitsTask;

public final class KnightBitsByteCacheProxyTask extends PopcountProxyBitsTask {
    public KnightBitsByteCacheProxyTask() {
        super("knight_bits_byte_cache", "Knight - BITS (byte_cache)", PieceType.KNIGHT, "byte_cache");
    }
}

