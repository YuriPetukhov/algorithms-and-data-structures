package hw05_bitboards_and_chess_moves.core.bits;

public final class ShiftBitCount implements BitCountStrategy {
    @Override public String id() { return "shift"; }
    @Override public int count(long x) {
        long n = x;
        int c = 0;
        for (int i = 0; i < 64; i++) {
            c += (int)(n & 1L);
            n >>>= 1;
        }
        return c;
    }
}
