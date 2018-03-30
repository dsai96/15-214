package edu.cmu.cs.cs214.hw4.core;

import static org.junit.Assert.*;

import org.junit.Test;

import edu.cmu.cs.cs214.hw4.tiles.Bag;
import edu.cmu.cs.cs214.hw4.tiles.LetterTile;

public class PlayerTest {

  @Test
  public void test() {
    Bag b1 = new Bag();
    Player p1 = new Player("Kanye", b1);
    LetterTile l = p1.getRack()[0];
    p1.removeTileFromRack(l);
    assertTrue(p1.getRack()[0] == null);
  }
  
  @Test
  public void exchangeTest() {
    Bag b = new Bag();
    Player p1 = new Player("Kanye", b);
    assertTrue(b.getAllTiles().size() == 91);
    p1.exchangeTiles();
    System.out.println(b.getAllTiles().size() );
    assertTrue(p1.getRack()[0] != null);
    assertTrue(b.getAllTiles().size() == 91);
  }
  
  @Test
  public void getTileTest() {
    Bag b = new Bag();
    Player p1 = new Player("Kanye", b);
    assertTrue(b.getAllTiles().size() == 91);
    p1.getTile();
    assertTrue(b.getAllTiles().size() == 90);
    p1.getTile();
    p1.getTile();
    p1.getTile();
    p1.getTile();
    assertTrue(b.getAllTiles().size() == 86);
  }
  
  
  @Test
  public void replenishTest() {
    Bag b = new Bag();
    Player p1 = new Player("Kanye", b);
    assertTrue(b.getAllTiles().size() == 91);
    p1.replenishRack();
    assertTrue(b.getAllTiles().size() == 91);
    p1.getEmptyRackIndeces().add(0);
    p1.replenishRack();
    assertTrue(b.getAllTiles().size() == 90);
    p1.getEmptyRackIndeces().add(6); 
    p1.getEmptyRackIndeces().add(0);
    p1.replenishRack();
    assertTrue(b.getAllTiles().size() == 88);
  }
  
  
  

}
