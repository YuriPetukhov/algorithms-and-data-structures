package hw05_bitboards_and_chess_moves.app.tasks;

import hw02_dynamic_programming_and_testing.app.core.MeasurableTask;
import hw05_bitboards_and_chess_moves.core.bits.BitCountSelector;
import hw05_bitboards_and_chess_moves.core.bits.BitCountStrategy;
import hw05_bitboards_and_chess_moves.core.model.PieceType;
import hw05_bitboards_and_chess_moves.core.service.MovesFacade;

public class ChessBitsTask implements MeasurableTask<Integer, Long> {

    private final String id;
    private final String displayName;
    private final PieceType type;

    private final MovesFacade moves = new MovesFacade();

    private final BitCountStrategy bitCount = BitCountSelector.fromConfig();

    public ChessBitsTask(String id, String displayName, PieceType type) {
        this.id = id;
        this.displayName = displayName;
        this.type = type;
    }

    @Override
    public String id() {
        return id;
    }

    @Override
    public String displayName() {
        return displayName + " (popcount=" + bitCount.id() + ")";
    }

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

        if (square < 0 || square > 63)
            throw new IllegalArgumentException("square must be in range 0..63");

        return square;
    }

    @Override
    public Long compute(Integer square) {
        return moves.moves(type, square, 0L).mask();
    }

    @Override
    public String format(Long mask) {
        int count = bitCount.count(mask);
        return count + "\n" + Long.toUnsignedString(mask);
    }
}
