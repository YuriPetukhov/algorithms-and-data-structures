package hw05_bitboards_and_chess_moves.core.movegen;

import hw05_bitboards_and_chess_moves.core.model.PieceType;

public final class BishopMoveGenerator implements MoveGenerator {

    @Override
    public PieceType type() {
        return PieceType.BISHOP;
    }

    @Override
    public long attacks(int square, long occupancy) {
        return Rays.rayAttacks(square,  1,  1, occupancy)
                | Rays.rayAttacks(square, -1,  1, occupancy)
                | Rays.rayAttacks(square,  1, -1, occupancy)
                | Rays.rayAttacks(square, -1, -1, occupancy);
    }
}
