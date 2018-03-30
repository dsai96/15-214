package edu.cmu.cs.cs214.hw4.core;

import static org.junit.Assert.*;

import org.junit.Test;

import edu.cmu.cs.cs214.hw4.tiles.LetterTile;

public class LetterTileTest {

  @Test
  public void basicTest() {
    Character ch = new Character('Q');
    LetterTile Q = new LetterTile(ch , 10);
    Character ch2 = new Character('A');
    LetterTile A = new LetterTile(ch2 , 1);
    Character ch3 = new Character('A');
    LetterTile A2 = new LetterTile(ch3 , 1);
    assertTrue(!A2.equals(Q));
    assertTrue(A2.equals(A));
    assert(A2.toString().equals("A-1"));
    assert(A2.hashCode() == A.hashCode());
    assert(Q.hashCode() == A.hashCode());
  }
  
  @Test
  public void getDefCopyTest() {
    Character ch = new Character('Q');
    LetterTile Q = new LetterTile(ch , 10);
    LetterTile Qcopy = Q.getDefensiveCopy();
    assertTrue(Q != (Qcopy));
    assertTrue(Q.equals(Qcopy));
  }

}
