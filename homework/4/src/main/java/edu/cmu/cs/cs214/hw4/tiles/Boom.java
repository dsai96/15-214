package edu.cmu.cs.cs214.hw4.tiles;

import edu.cmu.cs.cs214.hw4.core.Player;
import edu.cmu.cs.cs214.hw4.core.ScrabbleGame;

public class Boom implements SpecialTile {
  private Player owner;

  Boom() {
  }

  @Override
  public void setOwner(Player p) {
    this.owner = p;
  }

  @Override
  public Player getOwner() {
    return owner;
  }

  @Override
  public void apply(ScrabbleGame g) {
  }

  public String toString() {
    if (owner == null)
      return "Unassigned owner's BoomTile";
    return String.format("%s's BoomTile", owner);
  }

}
