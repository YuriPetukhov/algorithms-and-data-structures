package hw05_bitboards_and_chess_moves.report.rook;

import hw05_bitboards_and_chess_moves.core.model.PieceType;
import hw05_bitboards_and_chess_moves.report.PopcountProxyBitsTask;

public final class RookBitsByteCacheProxyTask extends PopcountProxyBitsTask {
    public RookBitsByteCacheProxyTask() {
        super("rook_bits_byte_cache", "Rook - BITS (byte_cache)", PieceType.ROOK, "byte_cache");
    }
}

