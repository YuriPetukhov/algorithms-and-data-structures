package hw05_bitboards_and_chess_moves.core.movegen;

import hw05_bitboards_and_chess_moves.core.board.BoardMasks;
import hw05_bitboards_and_chess_moves.core.model.PieceType;

public final class KnightMoveGenerator implements MoveGenerator {

    @Override
    public PieceType type() {
        return PieceType.KNIGHT;
    }

    @Override
    public long attacks(int square, long occupancy) {
        long from = 1L << square;

        long l1 = (from & BoardMasks.NOT_FILE_A)  << 15;
        long l2 = (from & BoardMasks.NOT_FILE_AB) << 6;
        long r1 = (from & BoardMasks.NOT_FILE_H)  << 17;
        long r2 = (from & BoardMasks.NOT_FILE_GH) << 10;

        long l3 = (from & BoardMasks.NOT_FILE_A)  >>> 17;
        long l4 = (from & BoardMasks.NOT_FILE_AB) >>> 10;
        long r3 = (from & BoardMasks.NOT_FILE_H)  >>> 15;
        long r4 = (from & BoardMasks.NOT_FILE_GH) >>> 6;

        return l1 | l2 | r1 | r2 | l3 | l4 | r3 | r4;
    }
}
