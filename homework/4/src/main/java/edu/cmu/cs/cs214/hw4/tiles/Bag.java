package edu.cmu.cs.cs214.hw4.tiles;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 
 * @author dsai96 The bag full of all LetterTiles initiated
 */

public class Bag {

  private List<LetterTile> allTiles = new ArrayList<LetterTile>();

  public List<LetterTile> getAllTiles() {
    return allTiles;
  }

  public Bag() {
    populateBag();
    Collections.shuffle(allTiles);
  }

  private void populateBag() {
    createTile(9, 'A', 1);
    createTile(2, 'B', 3);
    createTile(2, 'C', 3);
    createTile(4, 'D', 2);
    createTile(12, 'E', 1);
    createTile(2, 'F', 4);
    createTile(3, 'G', 2);
    createTile(2, 'H', 4);
    createTile(9, 'I', 1);
    createTile(1, 'J', 8);
    createTile(1, 'K', 5);
    createTile(4, 'L', 1);
    createTile(2, 'M', 3);
    createTile(6, 'N', 1);
    createTile(8, 'O', 1);
    createTile(2, 'P', 3);
    createTile(1, 'Q', 10);
    createTile(6, 'R', 1);
    createTile(4, 'S', 1);
    createTile(6, 'T', 1);
    createTile(4, 'U', 1);
    createTile(2, 'V', 4);
    createTile(2, 'W', 4);
    createTile(1, 'X', 8);
    createTile(2, 'Y', 4);
    createTile(1, 'Z', 10);
  }

  private void createTile(int count, char letter, int score) {
    LetterTile tile = new LetterTile(letter, score);
    for (int i = 0; i < count; i++) {
      allTiles.add(tile);
    }
  }

}
