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

        long n1 = (from & BoardMasks.NOT_FILE_H)  << 17;
        long n2 = (from & BoardMasks.NOT_FILE_A)  << 15;
        long n3 = (from & BoardMasks.NOT_FILE_GH) << 10;
        long n4 = (from & BoardMasks.NOT_FILE_AB) << 6;

        long s1 = (from & BoardMasks.NOT_FILE_H)  >>> 15;
        long s2 = (from & BoardMasks.NOT_FILE_A)  >>> 17;
        long s3 = (from & BoardMasks.NOT_FILE_GH) >>> 6;
        long s4 = (from & BoardMasks.NOT_FILE_AB) >>> 10;

        return n1 | n2 | n3 | n4 | s1 | s2 | s3 | s4;
    }
}
