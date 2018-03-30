package edu.cmu.cs.cs214.hw4.tiles;

public class LetterTile {
  private final char name;
  private final int pointValue; 
  
  public LetterTile(char name, int pointValue)  {
    this.pointValue = pointValue;
    this.name = name;
  }
  
  public int getPointValue() {
    return pointValue;
  }

  public char getName() {
    return name;
  } 
  
  public LetterTile getDefensiveCopy() {
    return new LetterTile(name, pointValue);
  }
  
  @Override
  public boolean equals(Object o) {
    if (!(o instanceof LetterTile)) {
      return false;
    }
    LetterTile other = (LetterTile) o;
    return (other.pointValue == this.pointValue &&
        other.name == this.name);
  }
  
  
  @Override
  public int hashCode() {
    int result = 17; 
    result = 31 * result + pointValue;
    result = 31 * result + (int) name;
    return result;  
    }
  
  public String toString() {
    return String.format("%s-%s", name, pointValue);
  }
}
