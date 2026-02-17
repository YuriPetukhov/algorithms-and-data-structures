package hw05_bitboards_and_chess_moves.core.board;

public final class BoardMasks {

    private BoardMasks() {
    }

    public static final long FILE_A = 0x0101010101010101L;
    public static final long FILE_B = FILE_A << 1;
    public static final long FILE_G = FILE_A << 6;
    public static final long FILE_H = FILE_A << 7;
    public static final long NOT_FILE_A = ~FILE_A;
    public static final long NOT_FILE_H = ~FILE_H;
    public static final long NOT_FILE_AB = ~(FILE_A | FILE_B);
    public static final long NOT_FILE_GH = ~(FILE_G | FILE_H);
}

