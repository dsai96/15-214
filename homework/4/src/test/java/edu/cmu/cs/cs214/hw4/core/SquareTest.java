package edu.cmu.cs.cs214.hw4.core;

import static org.junit.Assert.*;

import org.junit.Test;

import edu.cmu.cs.cs214.hw4.tiles.LetterTile;

public class SquareTest {

  @Test
  public void basicTest() {
    Character ch = new Character('Q');
    LetterTile Q = new LetterTile(ch , 10);
    Square s = new Square(1, 1, Q);
    Square s2 = new Square(1, 2, Q);
    Square s3 = new Square(1, 1, Q);
    assertTrue(s.equals(s3));
    assertTrue(!s.equals(s2));
    assertTrue(s.toString().equals("[Q-10] - wm:1 - lm:1"));
    assertTrue(s.hashCode() != s2.hashCode());
  }
  
  @Test
  public void defCopyTest() {
    Character ch = new Character('Q');
    LetterTile Q = new LetterTile(ch , 10);
    Square s = new Square(1, 1, Q);
    Square s2 = s.getDefensiveCopy();
    assertTrue(!s.equals(s2));
    assertTrue(s.hashCode() == s2.hashCode());
  }
  
  

}
