package hw05_bitboards_and_chess_moves.core.movegen;

import hw05_bitboards_and_chess_moves.core.board.BoardMasks;
import hw05_bitboards_and_chess_moves.core.model.PieceType;

public final class KingMoveGenerator implements MoveGenerator {

    @Override
    public PieceType type() {
        return PieceType.KING;
    }

    @Override
    public long attacks(int square, long occupancy) {
        long from = 1L << square;

        long east  = (from & ~BoardMasks.FILE_H) << 1;
        long west  = (from & ~BoardMasks.FILE_A) >>> 1;
        long north = from << 8;
        long south = from >>> 8;

        long ne = (from & ~BoardMasks.FILE_H) << 9;
        long nw = (from & ~BoardMasks.FILE_A) << 7;
        long se = (from & ~BoardMasks.FILE_H) >>> 7;
        long sw = (from & ~BoardMasks.FILE_A) >>> 9;

        return east | west | north | south | ne | nw | se | sw;
    }
}
