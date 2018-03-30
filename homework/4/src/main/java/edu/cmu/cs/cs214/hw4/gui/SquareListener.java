package edu.cmu.cs.cs214.hw4.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import edu.cmu.cs.cs214.hw4.core.ScrabbleGame;

public class SquareListener implements ActionListener {

    private final int row;
    private final int col;
    private final ScrabbleGUI gui;

    /**
     * Creates a new square listener to get click events at a specific game grid
     * coordinate.
     *
     * @param row
     *          The row of the button.
     * @param col
     *          The column of the button.
     * @param game
     *          The Tic-Tac-Toe game interface.
     */
    public SquareListener(final int row, final int col, final ScrabbleGUI gui) {
      this.row = row;
      this.col = col;
      this.gui = gui;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    }
}
