package hw05_bitboards_and_chess_moves.app.tasks;

import hw05_bitboards_and_chess_moves.core.model.PieceType;

public class QueenBitsTask extends ChessBitsTask {
    public QueenBitsTask() {
        super("queen_bits", "Queen - BITS", PieceType.QUEEN);
    }
}
