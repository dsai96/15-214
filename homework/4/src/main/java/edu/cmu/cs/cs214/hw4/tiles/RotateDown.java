package edu.cmu.cs.cs214.hw4.tiles;

import edu.cmu.cs.cs214.hw4.core.Board;
import edu.cmu.cs.cs214.hw4.core.Player;
import edu.cmu.cs.cs214.hw4.core.ScrabbleGame;
import edu.cmu.cs.cs214.hw4.core.Square;

public class RotateDown implements SpecialTile {
  private Player owner;
  private final int size = 15;

  public RotateDown() {
  }

  @Override
  public void setOwner(Player p) {
    this.owner = p;
  }

  @Override
  public Player getOwner() {
    return owner;
  }

  /**
   * Moves the bottom row to the top row, and shifts every other row down one
   */
  @Override
  public void apply(ScrabbleGame g) {
    Square[][] curr = g.getBoard().getBoardArr();
    Square[][] newBoard = new Board().getBoardArr();
    for (int i = 0; i < size - 1; i++) {
      for (int j = 0; j < size; j++) {
        LetterTile newS = curr[i][j].getLetter();
        newBoard[i + 1][j].setLetter(newS);
        ;
      }
    }
    // makes the first row of new Board
    for (int i = 0; i < size - 1; i++) {
      newBoard[0][i].setLetter(curr[size - 1][i].getLetter());
    }
    g.getBoard().setBoardArr(newBoard);
  }

  public String toString() {
    if (owner == null)
      return "Unassigned owner's RotateDown";
    return String.format("%s's RotateDown", owner);
  }

}
