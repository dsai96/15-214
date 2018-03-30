package edu.cmu.cs.cs214.hw4.tiles;

import edu.cmu.cs.cs214.hw4.core.Player;
import edu.cmu.cs.cs214.hw4.core.ScrabbleGame;

public interface SpecialTile {
  public Player getOwner();

  void apply(ScrabbleGame s);

  public String toString();

  void setOwner(Player p);

  boolean equals(Object o);
}

