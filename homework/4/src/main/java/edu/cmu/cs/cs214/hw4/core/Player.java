package edu.cmu.cs.cs214.hw4.core;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import edu.cmu.cs.cs214.hw4.tiles.Bag;
import edu.cmu.cs.cs214.hw4.tiles.LetterTile;
import edu.cmu.cs.cs214.hw4.tiles.SpecialTile;

public class Player {
  private int score = 0;
  private final static int numOfTiles = 7;
  /**
   * emptyRackIndeces are empty indeces of a rack when letters removed
   */
  private List<Integer> emptyRackIndeces = new ArrayList<Integer>();
  private List<SpecialTile> boughtST = new ArrayList<>();
  private final String name;
  private int turnsLost = 0;
  private LetterTile[] rack = new LetterTile[numOfTiles];
  private Bag bag;

  public Player(String name, Bag b) {
    this.name = name;
    this.bag = b;
    for (int i = 0; i < numOfTiles; i++) {
      rack[i] = getTile();
    }
  }

  /**
   * swaps all 7 tiles for new (could be the same ones) tiles
   */
  public void exchangeTiles() {
    for (int i = 0; i < numOfTiles; i++) {
      bag.getAllTiles().add(rack[i]);
      rack[i] = null;
      emptyRackIndeces.add(i);
    }
    Collections.shuffle(bag.getAllTiles());
    replenishRack();
  }

  /**
   * adds the required amount of tiles to make a rack full again
   */
  public void replenishRack() {
    for (int i = 0; i < emptyRackIndeces.size(); i++) {
      rack[emptyRackIndeces.get(i)] = getTile();
    }
    emptyRackIndeces.clear();
  }

  LetterTile getTile() {
    return bag.getAllTiles().remove(bag.getAllTiles().size() - 1);
  }

  /**
   * 
   * @param l
   *          is the list of LetterTiles that need to be placed back on the rack
   *          when a challenge is successful
   */
  public void putLettersBackOnRack(List<LetterTile> l) {
    assert (l.size() == emptyRackIndeces.size());
    for (int i = 0; i < emptyRackIndeces.size(); i++) {
      rack[emptyRackIndeces.get(i)] = l.get(i);
    }
    emptyRackIndeces.clear();
  }

  public boolean equals(Object o) {
    if (!(o instanceof Player))
      return false;
    Player other = (Player) o;
    return equals(other.getName() == (name));
  }

  public String toString() {
    return getName();
  }

  /**
   * 
   * @param l
   *          is the lettertile to remove from Rack
   */
  public void removeTileFromRack(LetterTile l) {
    for (int i = 0; i < numOfTiles; i++) {
      if (rack[i] != null && rack[i].equals(l)) {
        rack[i] = null;
        emptyRackIndeces.add(i);
        break;
      }
    }
  }

  public String rackToString() {
    return String.format("[%s, %s, %s, %s, %s, %s, %s]", rack[0], rack[1], rack[2], rack[3], rack[4], rack[5], rack[6]);
  }

  public static int getNumoftiles() {
    return numOfTiles;
  }

  public int getScore() {
    return score;
  }

  public void setScore(int score) {
    this.score = score;
  }

  public String getName() {
    return name;
  }

  public LetterTile[] getRack() {
    return rack;
  }

  public void setRack(LetterTile[] rack) {
    this.rack = rack;
  }

  public List<Integer> getEmptyRackIndeces() {
    return emptyRackIndeces;
  }

  public void setEmptyRackIndeces(List<Integer> emptyRackIndeces) {
    this.emptyRackIndeces = emptyRackIndeces;
  }

  public int getTurnsLost() {
    return turnsLost;
  }

  public void setTurnsLost(int turnsLost) {
    this.turnsLost = turnsLost;
  }

  public List<SpecialTile> getBoughtST() {
    return boughtST;
  }

  public void setBoughtST(List<SpecialTile> boughtST) {
    this.boughtST = boughtST;
  }

}
