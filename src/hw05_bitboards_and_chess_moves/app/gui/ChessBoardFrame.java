package hw05_bitboards_and_chess_moves.app.gui;

import hw05_bitboards_and_chess_moves.core.model.PieceType;
import hw05_bitboards_and_chess_moves.core.movegen.MoveGenRegistry;
import hw05_bitboards_and_chess_moves.core.movegen.MoveGenerator;
import hw05_bitboards_and_chess_moves.core.service.MovesFacade;

import javax.swing.*;
import java.awt.*;
import java.util.EnumMap;

public final class ChessBoardFrame extends JFrame {

    private final JComboBox<PieceType> pieceBox = new JComboBox<>(PieceType.values());
    private final JSpinner squareSpinner = new JSpinner(new SpinnerNumberModel(0, 0, 63, 1));

    private final JLabel infoLabel = new JLabel();

    private final JPanel boardPanel = new JPanel(new GridLayout(10, 10, 2, 2));
    private final JLabel[] cells = new JLabel[64];
    private final MovesFacade moves = new MovesFacade();

    private static final Color DARK = new Color(160, 160, 160);
    private static final Color MOVE = new Color(255, 140, 140);
    private static final Color PIECE = new Color(220, 40, 40);


    public ChessBoardFrame() {
        super("Chess Bitboards");

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        MoveGenRegistry registry = MoveGenRegistry.serviceLoaderRegistry();
        EnumMap<PieceType, MoveGenerator> m = new EnumMap<>(PieceType.class);
        for (PieceType t : PieceType.values()) {
            m.put(t, registry.get(t));
        }

        JPanel root = new JPanel(new BorderLayout(12, 12));
        root.setBorder(BorderFactory.createEmptyBorder(12, 12, 12, 12));

        root.add(buildTopPanel(), BorderLayout.NORTH);

        JPanel centerWrapper = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.NORTH;
        centerWrapper.add(buildBoard(), gbc);
        root.add(centerWrapper, BorderLayout.CENTER);

        setContentPane(root);
        pack();

        setSize(600, 600);
        setMinimumSize(new Dimension(600, 600));

        setLocationByPlatform(true);
        refresh();

    }

    private JPanel buildControls() {
        JPanel controls = new JPanel(new FlowLayout(FlowLayout.CENTER, 16, 0));

        JLabel pieceLabel = new JLabel("Piece:");
        pieceLabel.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 16));

        JLabel squareLabel = new JLabel("Square:");
        squareLabel.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 16));

        pieceBox.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 16));
        squareSpinner.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 16));

        JButton randomBtn = new JButton("Random");
        randomBtn.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 14));

        JButton closeBtn = new JButton("Close");
        closeBtn.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 14));

        pieceBox.addActionListener(e -> refresh());
        squareSpinner.addChangeListener(e -> refresh());

        randomBtn.addActionListener(e -> {
            int sq = (int) (Math.random() * 64);
            squareSpinner.setValue(sq);
        });

        closeBtn.addActionListener(e -> dispose());

        controls.add(pieceLabel);
        controls.add(pieceBox);
        controls.add(squareLabel);
        controls.add(squareSpinner);
        controls.add(randomBtn);
        controls.add(closeBtn);

        return controls;
    }


    private JComponent buildTopPanel() {

        JPanel top = new JPanel();
        top.setLayout(new BoxLayout(top, BoxLayout.Y_AXIS));

        JPanel infoPanel = new JPanel(new BorderLayout());
        infoLabel.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 16));
        infoLabel.setHorizontalAlignment(SwingConstants.CENTER);
        infoLabel.setBorder(BorderFactory.createEmptyBorder(0, 4, 0, 4));

        infoPanel.add(infoLabel, BorderLayout.CENTER);
        infoPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 8, 0));

        top.add(infoPanel);
        top.add(buildControls());

        return top;
    }


    private JComponent buildBoard() {
        boardPanel.add(headerCell(""));

        for (int file = 0; file < 8; file++) {
            boardPanel.add(headerCell(String.valueOf((char) ('A' + file))));
        }

        boardPanel.add(headerCell(""));

        for (int rank = 7; rank >= 0; rank--) {

            boardPanel.add(headerCell(String.valueOf(rank + 1)));

            for (int file = 0; file < 8; file++) {
                int sq = (rank << 3) | file;

                JLabel cell = getjLabel(sq);

                cells[sq] = cell;
                boardPanel.add(cell);
            }

            boardPanel.add(headerCell(""));
        }

        for (int i = 0; i < 10; i++) {
            boardPanel.add(headerCell(""));
        }


        return boardPanel;
    }

    private JLabel getjLabel(int sq) {
        JLabel cell = new JLabel(String.valueOf(sq), SwingConstants.CENTER);
        cell.setOpaque(true);
        cell.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 16));
        cell.setPreferredSize(new Dimension(52, 44));

        cell.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                squareSpinner.setValue(sq);
            }
        });
        return cell;
    }

    private JLabel headerCell(String text) {
        JLabel l = new JLabel(text, SwingConstants.CENTER);
        l.setOpaque(true);
        l.setBackground(Color.WHITE);
        l.setForeground(Color.BLACK);
        l.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 16));
        l.setPreferredSize(new Dimension(52, 30));
        return l;
    }

    private void refresh() {
        PieceType piece = (PieceType) pieceBox.getSelectedItem();
        if (piece == null) piece = PieceType.KING;

        int square = (Integer) squareSpinner.getValue();

        long occupancy = 0L;
        var res = moves.moves(piece, square, occupancy);
        long mask = res.mask();
        int count = res.count();

        infoLabel.setText("Moves: " + count
                + " | Mask: " + Long.toUnsignedString(mask)
                + " | popcount = " + moves.bitCountId());

        infoLabel.setBorder(BorderFactory.createEmptyBorder(0, 4, 0, 4));


        for (int sq = 0; sq < 64; sq++) {
            JLabel cell = cells[sq];
            if (cell == null) continue;

            int file = sq & 7;
            int rank = sq >>> 3;
            boolean dark = ((rank + file) & 1) == 1;

            Color base = dark ? DARK : Color.WHITE;

            boolean isPiece = (sq == square);
            boolean isMove = (mask & (1L << sq)) != 0;

            cell.setText(String.valueOf(sq));
            cell.setBackground(base);
            cell.setForeground(Color.BLACK);

            if (isMove) {
                cell.setBackground(MOVE);
                cell.setForeground(Color.WHITE);
            }
            if (isPiece) {
                cell.setBackground(PIECE);
                cell.setForeground(Color.WHITE);
            }
        }
    }
}
