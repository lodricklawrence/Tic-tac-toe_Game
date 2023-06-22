package csu07419.ticTacToe;

import javax.swing.*;
public class TicTacToeApp {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                TicTacToeGUI game = new TicTacToeGUI();
                game.setVisible(true);
            }

        });
    }
}
