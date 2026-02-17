package hw05_bitboards_and_chess_moves.report.bishop;

import hw05_bitboards_and_chess_moves.core.model.PieceType;
import hw05_bitboards_and_chess_moves.report.PopcountProxyBitsTask;

public final class BishopBitsByteCacheProxyTask extends PopcountProxyBitsTask {
    public BishopBitsByteCacheProxyTask() {
        super("bishop_bits_byte_cache", "Bishop - BITS (byte_cache)", PieceType.BISHOP, "byte_cache");
    }
}

