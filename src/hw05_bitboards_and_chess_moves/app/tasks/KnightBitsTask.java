package hw05_bitboards_and_chess_moves.app.tasks;

import hw05_bitboards_and_chess_moves.core.model.PieceType;

public class KnightBitsTask extends ChessBitsTask {

    public KnightBitsTask() {
        super("knight_bits", "Knight - BITS", PieceType.KNIGHT);
    }
}
