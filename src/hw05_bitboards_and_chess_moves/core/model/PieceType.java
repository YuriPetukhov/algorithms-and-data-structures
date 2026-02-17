package hw05_bitboards_and_chess_moves.core.model;

public enum PieceType {
    KING,
    KNIGHT,
    ROOK,
    BISHOP,
    QUEEN;

    public static PieceType parse(String s) {
        if (s == null) throw new IllegalArgumentException("piece type is null");
        String t = s.trim().toUpperCase();
        return PieceType.valueOf(t);
    }
}
