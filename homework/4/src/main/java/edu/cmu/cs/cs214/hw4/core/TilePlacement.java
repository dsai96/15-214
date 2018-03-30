package edu.cmu.cs.cs214.hw4.core;

import edu.cmu.cs.cs214.hw4.tiles.LetterTile;

/**
 * 
 * @author dsai96 This is the representation of tiles that need be row and col
 *         and the letterTile
 */
public class TilePlacement {
  private final int row;
  private final int col;
  private final LetterTile letter;

  public TilePlacement(int row, int col, LetterTile letter) {
    this.row = row;
    this.col = col;
    this.letter = letter;
  }

  public int getRow() {
    return row;
  }

  public int getCol() {
    return col;
  }

  public LetterTile getLetter() {
    return letter;
  }

  public boolean equals(Object o) {
    if (!(o instanceof TilePlacement)) {
      return false;
    }
    TilePlacement other = (TilePlacement) o;
    return (other.row == row && other.col == col && other.letter == letter);
  }

  @Override
  public int hashCode() {
    int result = 17;
    result = 31 * result + (int) (letter.getName());
    result = 31 * result + col;
    result = 31 * result + row;
    return result;
  }

}
