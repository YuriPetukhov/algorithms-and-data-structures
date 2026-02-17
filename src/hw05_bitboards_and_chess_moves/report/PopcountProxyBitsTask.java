package hw05_bitboards_and_chess_moves.report;

import hw02_dynamic_programming_and_testing.app.core.MeasurableTask;
import hw05_bitboards_and_chess_moves.core.bits.BitCountSelector;
import hw05_bitboards_and_chess_moves.core.bits.BitCountStrategy;
import hw05_bitboards_and_chess_moves.core.model.PieceType;
import hw05_bitboards_and_chess_moves.core.movegen.MoveGenRegistry;
import hw05_bitboards_and_chess_moves.core.movegen.MoveGenerator;

public class PopcountProxyBitsTask implements MeasurableTask<Integer, Long> {

    private final String id;
    private final String displayName;
    private final PieceType pieceType;
    private final BitCountStrategy bitCount;

    private final MoveGenRegistry registry = MoveGenRegistry.serviceLoaderRegistry();

    public PopcountProxyBitsTask(
            String id,
            String displayName,
            PieceType pieceType,
            String popcountId
    ) {
        this.id = id;
        this.displayName = displayName;
        this.pieceType = pieceType;
        this.bitCount = BitCountSelector.fromId(popcountId);
    }

    @Override public String id() { return id; }

    @Override public String displayName() { return displayName; }

    @Override
    public Integer parse(String input) {
        if (input == null) throw new IllegalArgumentException("Input is null");
        String t = input.trim();
        if (t.isEmpty()) throw new IllegalArgumentException("Empty input");

        int square;
        try {
            square = Integer.parseInt(t);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Expected integer square, got: " + t);
        }

        if (square < 0 || square > 63) {
            throw new IllegalArgumentException("square must be in range 0..63");
        }
        return square;
    }

    @Override
    public Long compute(Integer square) {
        MoveGenerator gen = registry.get(pieceType);
        return gen.attacks(square, 0L);
    }

    @Override
    public String format(Long mask) {
        int count = bitCount.count(mask);
        return count + "\n" + Long.toUnsignedString(mask);
    }
}
