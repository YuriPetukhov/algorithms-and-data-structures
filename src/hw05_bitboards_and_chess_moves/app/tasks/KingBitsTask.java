package hw05_bitboards_and_chess_moves.app.tasks;

import hw05_bitboards_and_chess_moves.core.model.PieceType;

public final class KingBitsTask extends ChessBitsTask {
    public KingBitsTask() {
        super("king_bits", "King - BITS", PieceType.KING);
    }
}