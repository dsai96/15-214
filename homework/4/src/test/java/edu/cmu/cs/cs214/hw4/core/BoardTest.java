package edu.cmu.cs.cs214.hw4.core;

import static org.junit.Assert.*;

import org.junit.Test;

import edu.cmu.cs.cs214.hw4.tiles.LetterTile;

public class BoardTest {

  @Test
  public void test() {
    Board b = new Board();
    assertTrue(b.getDefensiveCopy() != b);
    Character ch = new Character('Q');
    LetterTile Q = new LetterTile(ch , 10);
    Square s = new Square(1, 1, Q);
    b.boardArr[0][0] = s;
//    System.out.println(b.getDefensiveCopy());
//    System.out.println(b);
    assertTrue(b.getDefensiveCopy().boardArr[0][0].equals(b.boardArr[0][0]));
    assertTrue(!b.getDefensiveCopy().equals(b));
    int[] t = new int[]{ 0, 14 };
    int[][] hi = new int[][]{{ 14, 14 }, { 0, 0 }, { 0, 7 }, { 7, 0 }, { 14, 0}, { 0, 14 }};
    assertTrue(b.contains(t, hi));
  }

  @Test
  public void isInBoundsTest() {
    Board b = new Board();
    assertTrue(b.isInBounds(3, 3));
    assertTrue(b.isInBounds(0, 0));
    assertTrue(b.isInBounds(14, 14));
    assertTrue(b.isInBounds(0, 14));
    assertTrue(b.isInBounds(14, 0));
    assertTrue(b.isInBounds(3, 14));
    assertTrue(b.isInBounds(3, 0));
    assertTrue(b.isInBounds(3, 13));
    assertTrue(!b.isInBounds(-1, 3));
    assertTrue(!b.isInBounds(15, 3));
    assertTrue(!b.isInBounds(-1, 14));
    assertTrue(!b.isInBounds(-1, 0));
    assertTrue(!b.isInBounds(2, -1));
    assertTrue(!b.isInBounds(3, 15));
    assertTrue(!b.isInBounds(2, 34));
  }
  
  @Test
  public void isEmptyTest() {
    Board b = new Board();
    LetterTile Q = new LetterTile('Q', 10);
    Square s = new Square(1, 1, Q);
    b.boardArr[0][0] = s;
    b.boardArr[10][2] = s;
    assertTrue(!b.isEmpty(0,0));
    assertTrue(!b.isEmpty(10,2));
    assertTrue(b.isEmpty(14, 14));
    assertTrue(b.isEmpty(0, 14));
    assertTrue(b.isEmpty(14, 0));
  }
  
  
}
