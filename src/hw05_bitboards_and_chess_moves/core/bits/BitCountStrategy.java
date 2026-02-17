package hw05_bitboards_and_chess_moves.core.bits;

public interface BitCountStrategy {
    String id();
    int count(long x);
}

