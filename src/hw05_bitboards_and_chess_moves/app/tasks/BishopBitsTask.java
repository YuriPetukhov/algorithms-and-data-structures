package hw05_bitboards_and_chess_moves.app.tasks;

import hw05_bitboards_and_chess_moves.core.model.PieceType;

public final class BishopBitsTask extends ChessBitsTask {
    public BishopBitsTask() {
        super("bishop_bits", "Bishop - BITS", PieceType.BISHOP);
    }
}
