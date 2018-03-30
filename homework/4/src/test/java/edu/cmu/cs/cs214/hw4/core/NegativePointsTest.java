package edu.cmu.cs.cs214.hw4.core;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import edu.cmu.cs.cs214.hw4.tiles.Bag;
import edu.cmu.cs.cs214.hw4.tiles.LetterTile;
import edu.cmu.cs.cs214.hw4.tiles.NegativePoints;
import edu.cmu.cs.cs214.hw4.tiles.SpecialTile;

public class NegativePointsTest {

  private ScrabbleGame game;
  private Board board;
  
  @Before
  public void setup() throws FileNotFoundException {
    Bag b = new Bag();
    List<String> players = new ArrayList<>();
    players.add("Kanye");
    players.add("Kim");
    players.add("North");
    Set<String> dict = new HashSet<String>();
    game = new ScrabbleGame(players, dict);
    board = game.getBoard();
  }
  
    @Test
    public void NegativeTileTest() throws FileNotFoundException {
      //initialization
      LetterTile Q = new LetterTile('Q' , 10);
      LetterTile A = game.getCurrentPlayer().getRack()[0];
      LetterTile B = game.getCurrentPlayer().getRack()[1];
      LetterTile C = game.getCurrentPlayer().getRack()[2]; 
      Board board = game.getBoard();
      board.boardArr[7][7].setLetter(Q);
      board.boardArr[0][7].setLetter(Q);
      game.getCurrentPlayer().setScore(100);
      SpecialTile negpts = new NegativePoints();
      game.buySpecialTile(negpts);
      assertTrue(game.getCurrentPlayer().getScore() == 75);
      game.placeSpecialTile(negpts, 0, 6);
      TilePlacement t1 = new TilePlacement(0, 6, A);
      TilePlacement t2 = new TilePlacement(0, 5, B);
      TilePlacement t3 = new TilePlacement(0, 4, C);
      List<TilePlacement> tiles = game.getTilesToPlace();
      tiles.add(t1);
      tiles.add(t2);
      tiles.add(t3);
      game.placeTiles(tiles);
      game.findWordsandCalculateMoveScore();      
      game.activateSpecialTiles();
      assertTrue(game.getCurrentPlayer().getScore() == 75);
      tiles.clear();
    }
    
}