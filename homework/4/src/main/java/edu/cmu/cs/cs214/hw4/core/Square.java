package edu.cmu.cs.cs214.hw4.core;

import edu.cmu.cs.cs214.hw4.tiles.LetterTile;
import edu.cmu.cs.cs214.hw4.tiles.SpecialTile;
/**
 * 
 * @author dsai96
 * Square class is used in the board to represent the boxes
 */
public class Square {
  
  private final int wordMultiplier;
  private final int letterMultiplier;
  private LetterTile letter;
  private SpecialTile special;
 
  public Square(int wordMultiplier, int letterMultiplier, LetterTile letter) {
    this.wordMultiplier = wordMultiplier;
    this.letterMultiplier = letterMultiplier;
    this.letter = letter;
  }
    
  public SpecialTile getSpecial() {
    return special;
  }

  public void setSpecial(SpecialTile special) {
    this.special = special;
  }

  public int getWordMultiplier() {
    return wordMultiplier;
  }

  public int getLetterMultiplier() {
    return letterMultiplier;
  }

  public LetterTile getLetter() {
    return letter;
  }
  
  public Square getDefensiveCopy() {
    return new Square(wordMultiplier, letterMultiplier, letter == null ? null : letter.getDefensiveCopy());
  }

  public void setLetter(LetterTile letter) {
    this.letter = letter;
  }

  @Override
  public boolean equals(Object o) {
    if (!(o instanceof Square)) {
      return false;
    }
    Square other = (Square) o;
    return (other.letter.getName() == this.letter.getName() &&
        other.letter.getPointValue() == letter.getPointValue() &&
        other.letterMultiplier == this.letterMultiplier &&
        other.wordMultiplier == this.wordMultiplier);
  }
  
  @Override
  public int hashCode() {
    int result = 17; 
    result = 31 * result + (int) (getLetter().getName());
    result = 31 * result + letterMultiplier;
    result = 31 * result + wordMultiplier;
    return result;  
    }
  
  @Override
  public String toString() {
    if (letter != null) {
      return String.format("[%s, %s, %s]", letter.toString(), wordMultiplier, letterMultiplier);
    }
    else {
      return String.format("[null, %s, %s]", wordMultiplier, letterMultiplier);
    }
  }
}
