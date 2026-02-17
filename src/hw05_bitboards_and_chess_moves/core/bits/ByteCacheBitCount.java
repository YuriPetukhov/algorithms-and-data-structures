package hw05_bitboards_and_chess_moves.core.bits;

public final class ByteCacheBitCount implements BitCountStrategy {
    private static final int[] T = build();

    private static int[] build() {
        int[] a = new int[256];
        for (int i = 0; i < 256; i++) a[i] = Integer.bitCount(i);
        return a;
    }

    @Override public String id() { return "byte_cache"; }

    @Override public int count(long n) {
        return T[(int)(n & 0xFF)] +
                T[(int)((n >>> 8)  & 0xFF)] +
                T[(int)((n >>> 16) & 0xFF)] +
                T[(int)((n >>> 24) & 0xFF)] +
                T[(int)((n >>> 32) & 0xFF)] +
                T[(int)((n >>> 40) & 0xFF)] +
                T[(int)((n >>> 48) & 0xFF)] +
                T[(int)((n >>> 56) & 0xFF)];
    }
}
