package edu.cmu.cs.cs214.hw4.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JToggleButton;

import edu.cmu.cs.cs214.hw4.core.ScrabbleGame;
import edu.cmu.cs.cs214.hw4.tiles.LetterTile;


public class RackListener implements ActionListener {

    private final LetterTile tile;
    private final ScrabbleGame game;
    private final JToggleButton btn;
    /**
     * Creates a new square listener to get click events at a specific game grid
     * coordinate.
     */
    public RackListener(ScrabbleGame game, LetterTile lT, JToggleButton j) {
        this.game = game;
        this.tile = lT;
        this.btn = j;
    }

    @Override
    public void actionPerformed(ActionEvent evt) {
    }

}
