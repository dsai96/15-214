package edu.cmu.cs.cs214.hw4.tiles;

import edu.cmu.cs.cs214.hw4.core.Player;
import edu.cmu.cs.cs214.hw4.core.ScrabbleGame;
import edu.cmu.cs.cs214.hw4.core.Square;

public class NegativePoints implements SpecialTile {

  private Player owner;

  public NegativePoints() {
  }

  @Override
  public boolean equals(Object o){
    if (!(o instanceof NegativePoints)) {
      return false;
    }
    NegativePoints other = (NegativePoints) o;
    return (other.toString() == this.toString());
  }

  @Override
  public void setOwner(Player p) {
    this.owner = p;
  }
  
  @Override
  public Player getOwner() {
    return owner;
  }

  public String toString() {
    if (owner == null) return "Unassigned owner's NegativeTile";
    return String.format("%s's NegativeTile", owner);
  }

  @Override
  public void apply(ScrabbleGame g) {
    if (g.getRoundScore() >= g.getCurrentPlayer().getScore()) {
      g.getCurrentPlayer().setScore(0);
    } else {
      g.getCurrentPlayer().setScore(g.getCurrentPlayer().getScore() - g.getRoundScore());
    }
    
  }

}
