import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class TicTacToe {
    int boardWidth = 600;
    int boardHeight = 700;

    JFrame frame = new JFrame("Tic-Tac-Toe by Araf");
    JLabel textLabel = new JLabel();
    JPanel textPanel = new JPanel();
    JPanel boardPanel = new JPanel();

    JButton[][] board = new JButton[3][3];
    String playerX = "X";
    String playerO = "O";
    String currentPlayer = playerX;

    boolean gameOver = false;
    int turns = 0;

    TicTacToe() {
        frame.setVisible(true);
        frame.setSize(boardWidth, boardHeight);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new BorderLayout());
        frame.setResizable(false);

        textLabel.setBackground(Color.darkGray);
        textLabel.setOpaque(true);
        textLabel.setForeground(Color.WHITE);
        textLabel.setFont(new Font("Arial", Font.BOLD, 50));
        textLabel.setHorizontalAlignment(JLabel.CENTER);
        textLabel.setText("Tic-Tac-Toe by Araf");

        textPanel.setLayout(new BorderLayout());
        textPanel.add(textLabel);
        frame.add(textPanel, BorderLayout.NORTH);

        boardPanel.setLayout(new GridLayout(3, 3));
        boardPanel.setBackground(Color.darkGray);
        frame.add(boardPanel, BorderLayout.CENTER);

        for(int r = 0; r < 3; r++) {
            for(int c = 0; c < 3; c++) {
                JButton tile = new JButton();
                board[r][c] = tile;
                boardPanel.add(tile);

                tile.setBackground(Color.darkGray);
                tile.setForeground(Color.WHITE);
                tile.setFont(new Font("Arial", Font.BOLD, 120));
                tile.setFocusable(false);

                tile.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (!gameOver) {
                            JButton button = (JButton) e.getSource();
                            if (button.getText() == "") {
                                button.setText(currentPlayer);
                                turns++;

                                if (checkWinner()) {
                                    gameOver = true;
                                } else if (turns == 9) {
                                    gameOver = true;
                                    for(int r = 0; r < 3; r++) {
                                        for(int c = 0; c < 3; c++) {
                                            setTie(board[r][c]);
                                        }
                                    }
                                    textLabel.setText("Tie!");
                                } else {
                                    currentPlayer = (currentPlayer == playerX) ? playerO : playerX;
                                }
                            }
                        }
                    }
                });
            }
        }
        JButton playAgainButton = new JButton("Play Again");
        playAgainButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                playAgain();
            }
        });
        frame.add(playAgainButton, BorderLayout.SOUTH);
    }

    void playAgain() {
        gameOver = false;
        turns = 0;
        currentPlayer = playerX;
        textLabel.setText("Tic-Tac-Toe by Araf");

        for(int r = 0; r < 3; r++) {
            for(int c = 0; c < 3; c++) {
                board[r][c].setText("");
                board[r][c].setForeground(Color.WHITE);
                board[r][c].setBackground(Color.darkGray);
            }
        }
    }

    boolean checkWinner() {
        //horizontal
        for(int r = 0; r < 3; r++) {
            if(board[r][0].getText() == "") continue;

            if(board[r][0].getText() == board[r][1].getText() && board[r][1].getText() == board[r][2].getText()) {
                for(int i = 0; i < 3; i++) {
                    setWinner(board[r][i]);
                }
                return true;
            }
        }
        //vertical
        for(int c = 0; c < 3; c++) {
            if (board[0][c].getText() == "") continue;

            if (board[0][c].getText() == board[1][c].getText() && board[1][c].getText() == board[2][c].getText()) {
                for (int i = 0; i < 3; i++) {
                    setWinner(board[i][c]);
                }
                return true;
            }
        }
        //diagonally
        if(board[0][0].getText() == board[1][1].getText() && board[1][1].getText() == board[2][2].getText() && board[0][0].getText() != "") {
            for(int i = 0; i < 3; i++) {
                setWinner(board[i][i]);
            }
            return true;
        }
        //anti-diagonally
        if(board[0][2].getText() == board[1][1].getText() && board[1][1].getText() == board[2][0].getText() && board[0][2].getText() != "") {
            setWinner(board[0][2]);
            setWinner(board[1][1]);
            setWinner(board[2][0]);
            return true;
        }

        return false;
    }
    void setWinner(JButton tile) {
        tile.setForeground(Color.green);
        tile.setBackground(Color.gray);
        textLabel.setText(currentPlayer + " is the winner!");
    }
    void setTie(JButton tile) {
        tile.setForeground(Color.orange);
        tile.setBackground(Color.gray);
    }

    public static void main(String[] args) {
        new TicTacToe();
    }
}
