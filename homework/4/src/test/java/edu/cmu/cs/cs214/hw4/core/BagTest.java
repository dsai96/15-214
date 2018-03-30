package edu.cmu.cs.cs214.hw4.core;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import edu.cmu.cs.cs214.hw4.tiles.Bag;

public class BagTest {

  @Test
  public void test() {
    Bag b = new Bag();
    assertTrue(b.getAllTiles().size() == 98);
    
  }
  
  @Test
  public void exchangeTest() {
    Bag b1 = new Bag();
    assertTrue(b1.getAllTiles().size() == 98);
    Player p1 = new Player("Kanye", b1);
    assertTrue(b1.getAllTiles().size() == 91);
    assertTrue(p1.getRack().length == 7);
//    b1.exchangeTiles(p1);
    assertTrue(p1.getRack().length == 7);
    assertTrue(b1.getAllTiles().size() == 91);
  }
  
  
  
}
