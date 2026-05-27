import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class tic_tac_toe extends JFrame {
    private final JButton[] buttons = new JButton[9];
    private final JButton refreshButton = new JButton("Refresh");
    private boolean xTurn = true;  // X starts the game
    private boolean gameOver = false;  // Flag to check if the game is over

    public tic_tac_toe() {
        setLayout(null);
        setBounds(600,200,400,400);
        setTitle("Tic-Tac-Toe");
        setSize(320, 380);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        JPanel boardPanel = new JPanel();
        boardPanel.setLayout(new GridLayout(3, 3));
        for (int i = 0; i < 9; i++) {
            buttons[i] = new JButton("");
            buttons[i].setFont(new Font("Arial", Font.BOLD, 40));
            buttons[i].setFocusPainted(false);
            buttons[i].addActionListener(new ButtonClickListener(i));
            boardPanel.add(buttons[i]);
        }

        // Refresh button
        refreshButton.setFont(new Font("Arial", Font.BOLD, 16));
        refreshButton.setEnabled(false); // Initially disabled
        refreshButton.addActionListener(e -> resetGame());
        add(boardPanel, BorderLayout.CENTER);
        add(refreshButton, BorderLayout.SOUTH);
        setVisible(true);
    }
    private class ButtonClickListener implements ActionListener {
        private final int index;

        public ButtonClickListener(int index) {
            this.index = index;
        }
        public void actionPerformed(ActionEvent e) {
            if (gameOver || !buttons[index].getText().equals("")) {
                return;
            }
            buttons[index].setText(xTurn ? "X" : "O");
            buttons[index].setForeground(xTurn ? Color.RED : Color.BLUE);
            if (checkWinner()) {
                gameOver = true;
                JOptionPane.showMessageDialog(null, (xTurn ? "X" : "O") + " wins!");
                refreshButton.setEnabled(true);
            } else if (isBoardFull()) {
                gameOver = true;
                JOptionPane.showMessageDialog(null, "Draw..!");
                refreshButton.setEnabled(true);
            } else {
                xTurn = !xTurn;
            }
        }
    }
    private boolean checkWinner() {
        for (int i = 0; i < 3; i++) {
            if (buttons[i * 3].getText().equals(buttons[i * 3 + 1].getText()) &&
                    buttons[i * 3].getText().equals(buttons[i * 3 + 2].getText()) &&
                    !buttons[i * 3].getText().equals("")) {
                return true;
            }
            if (buttons[i].getText().equals(buttons[i + 3].getText()) &&
                    buttons[i].getText().equals(buttons[i + 6].getText()) &&
                    !buttons[i].getText().equals("")) {
                return true;
            }
        }
        if (buttons[0].getText().equals(buttons[4].getText()) &&
                buttons[0].getText().equals(buttons[8].getText()) &&
                !buttons[0].getText().equals("")) {
            return true;
        }
        if (buttons[2].getText().equals(buttons[4].getText()) &&
                buttons[2].getText().equals(buttons[6].getText()) &&
                !buttons[2].getText().equals("")) {
            return true;
        }

        return false;
    }
    private boolean isBoardFull() {
        for (JButton button : buttons) {
            if (button.getText().equals("")) {
                return false;
            }
        }
        return true;
    }
    private void resetGame() {
        for (JButton button : buttons) {
            button.setText("");
            button.setForeground(Color.BLACK);
        }
        xTurn = true;
        gameOver = false;
        refreshButton.setEnabled(false);
    }
    public static void main(String[] args) {
        new tic_tac_toe();
    }
}
