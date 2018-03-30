package edu.cmu.cs.cs214.hw4.tiles;

import edu.cmu.cs.cs214.hw4.core.Player;
import edu.cmu.cs.cs214.hw4.core.ScrabbleGame;

/**
 * 
 * @author dsai96 Adds another turn to the current player
 */
public class ExtraTurn implements SpecialTile {

  private Player owner;

  public ExtraTurn() {

  }

  @Override
  public Player getOwner() {
    return owner;
  }

  @Override
  public void apply(ScrabbleGame s) {
    System.out.println(s.getCurrentPlayer().getTurnsLost());
    s.getCurrentPlayer().setTurnsLost(-1);
  }

  @Override
  public void setOwner(Player p) {
    this.owner = p;
  }

  public String toString() {
    if (owner == null)
      return "Unassigned owner's ExtraTurnTile";
    return String.format("%s's ExtraTurnTile", owner);
  }

}
