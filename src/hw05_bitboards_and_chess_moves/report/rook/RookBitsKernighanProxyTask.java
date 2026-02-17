package hw05_bitboards_and_chess_moves.report.rook;

import hw05_bitboards_and_chess_moves.core.model.PieceType;
import hw05_bitboards_and_chess_moves.report.PopcountProxyBitsTask;

public final class RookBitsKernighanProxyTask extends PopcountProxyBitsTask {
    public RookBitsKernighanProxyTask() {
        super("rook_bits_kernighan", "Rook - BITS (kernighan)", PieceType.ROOK, "kernighan");
    }
}

