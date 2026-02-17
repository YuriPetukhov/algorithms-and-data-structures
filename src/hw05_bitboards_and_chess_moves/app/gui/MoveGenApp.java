package hw05_bitboards_and_chess_moves.app.gui;

import javax.swing.*;

public final class MoveGenApp {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ChessBoardFrame().setVisible(true));
    }
}
