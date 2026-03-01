package hw05_bitboards_and_chess_moves.core.movegen;

import hw05_bitboards_and_chess_moves.core.board.BoardMasks;
import hw05_bitboards_and_chess_moves.core.model.PieceType;

public final class RookMoveGenerator implements MoveGenerator {

    @Override
    public PieceType type() {
        return PieceType.ROOK;
    }

    @Override
    public long attacks(int square, long occupancy) {
        int file = square & 7;
        int rank = square >>> 3;

        long mask = BoardMasks.RANK[rank] | BoardMasks.FILE[file];
        return mask & ~BoardMasks.bit(square);
    }
}
