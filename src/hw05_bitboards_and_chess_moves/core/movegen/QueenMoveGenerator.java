package hw05_bitboards_and_chess_moves.core.movegen;

import hw05_bitboards_and_chess_moves.core.model.PieceType;

public final class QueenMoveGenerator implements MoveGenerator {

    private final RookMoveGenerator rook = new RookMoveGenerator();
    private final BishopMoveGenerator bishop = new BishopMoveGenerator();

    @Override
    public PieceType type() {
        return PieceType.QUEEN;
    }

    @Override
    public long attacks(int square, long occupancy) {
        return rook.attacks(square, occupancy) | bishop.attacks(square, occupancy);
    }
}
