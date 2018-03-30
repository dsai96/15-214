package edu.cmu.cs.cs214.hw4.gui;

import java.awt.BorderLayout;

import javax.swing.ButtonGroup;
import javax.swing.JPanel;
import javax.swing.JToggleButton;

import edu.cmu.cs.cs214.hw4.core.Player;
import edu.cmu.cs.cs214.hw4.core.ScrabbleGame;
import edu.cmu.cs.cs214.hw4.tiles.LetterTile;

/**
 * A panel that displays the Scrabble board and current player label.
 */
@SuppressWarnings("serial")
public class PlayerPanel extends JPanel implements ScrabbleListener {

  private final ScrabbleGame game;

  /**
   * Creates a new panel for displaying the game.
   * 
   * @param g
   *          scrabble game interface.
   */
  public PlayerPanel(final ScrabbleGame g) {
    game = g;
    add(createBoardPanel());
    setLayout(new BorderLayout());
  }

  private JPanel createBoardPanel() {
    final JPanel panel = new JPanel();
    LetterTile[] racktiles = game.getCurrentPlayer().getRack();
    ButtonGroup buttonTiles = new ButtonGroup();
    for (int i = 0; i < racktiles.length; i++) {
      char name = racktiles[i].getName();
      JToggleButton btn = new JToggleButton("");
      btn.setText(name + "");
      buttonTiles.add(btn);
    }
    return panel;

  }

  @Override
  public void squareChanged(int row, int col) {
    // TODO Auto-generated method stub

  }

  @Override
  public void currentPlayerChanged(Player player) {
    // TODO Auto-generated method stub

  }

  @Override
  public void gameEnded(Player winner) {
    // TODO Auto-generated method stub

  }
}
