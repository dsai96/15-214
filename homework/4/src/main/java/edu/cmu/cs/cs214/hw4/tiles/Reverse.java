package edu.cmu.cs.cs214.hw4.tiles;

import java.util.Collections;

import edu.cmu.cs.cs214.hw4.core.Player;
import edu.cmu.cs.cs214.hw4.core.ScrabbleGame;

public class Reverse implements SpecialTile {

  private Player owner;

  public Reverse() {
   }
  
  @Override
  public Player getOwner() {
    return owner;
  }
  
  @Override
  public void setOwner(Player p) {
    this.owner = p;
  }

  @Override 
  public void apply(ScrabbleGame s) {
    Collections.reverse(s.getPlayers());
  }
  
  public String toString() {
    if (owner == null) return "Unassigned owner's ReverseTile";
    return String.format("%s's ReverseTile", owner);
  }



}
