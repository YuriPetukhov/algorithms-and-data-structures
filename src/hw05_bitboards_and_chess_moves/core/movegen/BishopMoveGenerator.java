package hw05_bitboards_and_chess_moves.core.movegen;

import hw05_bitboards_and_chess_moves.core.board.BoardMasks;
import hw05_bitboards_and_chess_moves.core.model.PieceType;

public final class BishopMoveGenerator implements MoveGenerator {

    @Override
    public PieceType type() {
        return PieceType.BISHOP;
    }

    @Override
    public long attacks(int square, long occupancy) {
        int file = square & 7;
        int rank = square >>> 3;

        int d = (rank - file) + 7;
        int a = rank + file;

        long mask = BoardMasks.DIAG[d] | BoardMasks.ANTI[a];
        return mask & ~BoardMasks.bit(square);
    }
}
