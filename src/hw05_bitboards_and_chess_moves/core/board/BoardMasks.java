package hw05_bitboards_and_chess_moves.core.board;

public final class BoardMasks {
    private BoardMasks() {}

    public static final long[] FILE = new long[8];
    public static final long[] RANK = new long[8];
    public static final long[] DIAG = new long[15];
    public static final long[] ANTI = new long[15];

    public static final long FILE_A = 0x0101010101010101L;
    public static final long FILE_B = FILE_A << 1;
    public static final long FILE_G = FILE_A << 6;
    public static final long FILE_H = FILE_A << 7;

    public static final long NOT_FILE_A  = ~FILE_A;
    public static final long NOT_FILE_H  = ~FILE_H;
    public static final long NOT_FILE_AB = ~(FILE_A | FILE_B);
    public static final long NOT_FILE_GH = ~(FILE_G | FILE_H);

    static {
        for (int r = 0; r < 8; r++) {
            long rm = 0L;
            for (int f = 0; f < 8; f++) rm |= 1L << (r * 8 + f);
            RANK[r] = rm;
        }
        for (int f = 0; f < 8; f++) {
            long fm = 0L;
            for (int r = 0; r < 8; r++) fm |= 1L << (r * 8 + f);
            FILE[f] = fm;
        }

        for (int sq = 0; sq < 64; sq++) {
            int file = sq & 7;
            int rank = sq >>> 3;

            int d = (rank - file) + 7;
            int a = rank + file;

            long bit = 1L << sq;
            DIAG[d] |= bit;
            ANTI[a] |= bit;
        }
    }

    public static long bit(int square) {
        return 1L << square;
    }
}

