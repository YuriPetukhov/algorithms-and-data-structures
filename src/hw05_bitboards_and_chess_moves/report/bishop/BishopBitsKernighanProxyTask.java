package hw05_bitboards_and_chess_moves.report.bishop;

import hw05_bitboards_and_chess_moves.core.model.PieceType;
import hw05_bitboards_and_chess_moves.report.PopcountProxyBitsTask;

public final class BishopBitsKernighanProxyTask extends PopcountProxyBitsTask {
    public BishopBitsKernighanProxyTask() {
        super("bishop_bits_kernighan", "Bishop - BITS (kernighan)", PieceType.BISHOP, "kernighan");
    }
}

