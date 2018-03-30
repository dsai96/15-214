package edu.cmu.cs.cs214.hw4.tiles;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author dsai96 The shop where players can buy special tiles from
 */
public class SpecialTileShop {

  private List<SpecialTile> allST = new ArrayList<>();

  public List<SpecialTile> getAllST() {
    return allST;
  }

  public void setAllST(List<SpecialTile> allST) {
    this.allST = allST;
  }

  public SpecialTileShop() {
    int freqCount = 5;
    for (int i = 0; i < freqCount; i++) {
      allST.add(new Reverse());
      allST.add(new Boom());
      allST.add(new ExtraTurn());
      allST.add(new NegativePoints());
    }
  }

}
