package hw05_bitboards_and_chess_moves.core.movegen;

import hw05_bitboards_and_chess_moves.core.model.PieceType;

public interface MoveGenerator {

    PieceType type();
    long attacks(int square, long occupancy);
}

