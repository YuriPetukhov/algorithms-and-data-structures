package hw05_bitboards_and_chess_moves.core.bits;

public final class KernighanBitCount implements BitCountStrategy {
    @Override public String id() { return "kernighan"; }
    @Override public int count(long x) {
        long n = x;
        int c = 0;
        while (n != 0) {
            n &= (n - 1);
            c++;
        }
        return c;
    }
}
