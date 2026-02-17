package hw05_bitboards_and_chess_moves.core.movegen;

public final class Rays {

    private Rays() {}

    public static long rayAttacks(int square, int df, int dr, long occupancy) {
        int file = square & 7;
        int rank = square >>> 3;

        long attacks = 0L;

        int f = file + df;
        int r = rank + dr;

        while (f >= 0 && f < 8 && r >= 0 && r < 8) {
            int sq = (r << 3) | f;
            long bit = 1L << sq;
            attacks |= bit;

            if ((occupancy & bit) != 0) {
                break;
            }

            f += df;
            r += dr;
        }

        return attacks;
    }
}
