package hw05_bitboards_and_chess_moves.core.service;

import hw05_bitboards_and_chess_moves.core.bits.BitCountSelector;
import hw05_bitboards_and_chess_moves.core.bits.BitCountStrategy;
import hw05_bitboards_and_chess_moves.core.model.Moves;
import hw05_bitboards_and_chess_moves.core.model.PieceType;
import hw05_bitboards_and_chess_moves.core.movegen.MoveGenRegistry;
import hw05_bitboards_and_chess_moves.core.movegen.MoveGenerator;

public final class MovesFacade {

    private final MoveGenRegistry registry;
    private final BitCountStrategy bitCount;

    public MovesFacade() {
        this(MoveGenRegistry.serviceLoaderRegistry(), BitCountSelector.fromConfig());
    }

    public MovesFacade(MoveGenRegistry registry, BitCountStrategy bitCount) {
        this.registry = registry;
        this.bitCount = bitCount;
    }

    public Moves moves(PieceType type, int square) {
        return moves(type, square, 0L);
    }

    public Moves moves(PieceType type, int square, long occupancy) {
        if (type == null) throw new IllegalArgumentException("type is null");
        if (square < 0 || square > 63) throw new IllegalArgumentException("square must be 0..63");

        MoveGenerator gen = registry.get(type);
        long mask = gen.attacks(square, occupancy);
        int count = bitCount.count(mask);
        return new Moves(mask, count);
    }

    public String bitCountId() {
        return bitCount.id();
    }
}
