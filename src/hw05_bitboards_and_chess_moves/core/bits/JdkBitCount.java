package hw05_bitboards_and_chess_moves.core.bits;

public final class JdkBitCount implements BitCountStrategy {
    @Override public String id() { return "jdk"; }
    @Override public int count(long x) { return Long.bitCount(x); }
}
