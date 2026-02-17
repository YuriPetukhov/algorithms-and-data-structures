package hw05_bitboards_and_chess_moves.core.movegen;

import hw05_bitboards_and_chess_moves.core.model.PieceType;

import java.util.EnumMap;
import java.util.Map;

public final class MoveGenRegistry {

    private final Map<PieceType, MoveGenerator> gens;

    private MoveGenRegistry(Map<PieceType, MoveGenerator> gens) {
        this.gens = gens;
    }

    public MoveGenerator get(PieceType type) {
        MoveGenerator g = gens.get(type);
        if (g == null) throw new IllegalArgumentException("No generator for type: " + type);
        return g;
    }

    public static MoveGenRegistry serviceLoaderRegistry() {
        EnumMap<PieceType, MoveGenerator> m = new EnumMap<>(PieceType.class);

        for (MoveGenerator g : java.util.ServiceLoader.load(MoveGenerator.class)) {
            PieceType t = g.type();
            if (m.put(t, g) != null) {
                throw new IllegalStateException("Duplicate generator for type: " + t);
            }
        }

        if (m.isEmpty()) throw new IllegalStateException("No MoveGenerator implementations found via ServiceLoader");
        return new MoveGenRegistry(Map.copyOf(m));
    }

}
