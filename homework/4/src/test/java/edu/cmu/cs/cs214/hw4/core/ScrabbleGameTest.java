package edu.cmu.cs.cs214.hw4.core;

import static org.junit.Assert.assertTrue;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import edu.cmu.cs.cs214.hw4.tiles.Bag;
import edu.cmu.cs.cs214.hw4.tiles.LetterTile;

public class ScrabbleGameTest {
  private LetterTile P;
  private LetterTile A;
  private LetterTile C;
  private LetterTile T;
  private LetterTile H;
  private LetterTile E;
  private ScrabbleGame game;
  private Board board;
  private Bag b;

  @Before
  public void setup() throws FileNotFoundException {
    b = new Bag();
    P = new LetterTile('P', 2);
    A = new LetterTile('A', 1);
    C = new LetterTile('C', 2);
    T = new LetterTile('T', 1);
    H = new LetterTile('H', 2);
    E = new LetterTile('E', 1);
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
  public void changeTurnTest() throws FileNotFoundException {
    Player curr = game.getCurrentPlayer();
    assertTrue(curr == game.getPlayers().get(0));
    game.changeTurn();
    curr = game.getCurrentPlayer();
    assertTrue(curr == game.getPlayers().get(1));
    game.changeTurn();
    curr = game.getCurrentPlayer();
    assertTrue(curr == game.getPlayers().get(2));
    game.changeTurn();
    curr = game.getCurrentPlayer();
    assertTrue(curr == game.getPlayers().get(0));
    //current player lost a turn in the future
    game.getCurrentPlayer().setTurnsLost(game.getCurrentPlayer().getTurnsLost() + 1);
    assertTrue(game.getCurrentPlayer().getTurnsLost() == 1);
    game.changeTurn();
    game.changeTurn();
    game.changeTurn();
    assertTrue(game.getPlayers().get(0).getTurnsLost() == 0);
    assertTrue(game.getCurrentPlayer() == game.getPlayers().get(1));
  }

  /**
   * 
   * @throws FileNotFoundException if the .txt file is not found to create a dictionary
   * This test case is checking a lot of dependencies. That is why it is so long. 
   * There are two rounds and it makes sure tiles are cleared appropriately and also 
   * score is added to the correct player.
   */                                         
  @Test
  public void moveValidationTest() throws FileNotFoundException {
    board.boardArr[4][3].setLetter(T);
    board.boardArr[0][7].setLetter(T);
    board.boardArr[4][0].setLetter(P);
    List<TilePlacement> tiles = game.getTilesToPlace();
    LetterTile[] newRack = { P, A, T, H, E, P, E };
    //replace the rack from before so we know what letters are available
    //Round 1
    game.getCurrentPlayer().setRack(newRack);
    TilePlacement t4 = new TilePlacement(4, 1, E);
    TilePlacement t5 = new TilePlacement(4, 2, E);
    TilePlacement t6 = new TilePlacement(4, 4, H);
    tiles.add(t4);
    tiles.add(t5);
    tiles.add(t6);
    game.placeTiles(tiles);
    assertTrue(board.boardArr[4][4].getLetter()==(t6.getLetter()));
    assertTrue(game.getCurrentPlayer().getRack()[3] == (null));
    assertTrue(game.getCurrentPlayer().getRack()[0] != (null));
    assertTrue(game.getCurrentPlayer().getRack()[6] == (null));
    game.findWordsandCalculateMoveScore();
    assertTrue(74 == b.getAllTiles().size());
    assertTrue(game.getCurrentPlayer().getRack()[3] != (null));
    tiles.clear();
    assertTrue(game.getCurrentPlayer().getScore() == 16);
    game.changeTurn();

    //Round 2
    LetterTile D = game.getCurrentPlayer().getRack()[0];
    LetterTile E = game.getCurrentPlayer().getRack()[6];
    LetterTile F = game.getCurrentPlayer().getRack()[3];
    assertTrue(game.getBoard().boardArr[4][4].getLetter() != (null));
    t4 = new TilePlacement(4, 5, D);
    t5 = new TilePlacement(5, 5, E);
    t6 = new TilePlacement(6, 5, F);
    tiles.add(t4);
    tiles.add(t5);
    tiles.add(t6);
    game.placeTiles(tiles);
    game.findWordsandCalculateMoveScore();
    //replenish the rack in the call above
    assertTrue(game.getCurrentPlayer().getRack()[0] != (null));
    assertTrue(71 == b.getAllTiles().size());
    tiles.clear();
  }

  /**
   * 
   * @throws FileNotFoundException if the .txt file is not found to create a dictionary
   * checks if the letters that are placed horizantally create a word
   */
  @Test
  public void horizantalIsWordTest() throws FileNotFoundException {
    board.boardArr[0][0].setLetter(P);
    board.boardArr[0][1].setLetter(A);
    board.boardArr[0][2].setLetter(C);
    board.boardArr[0][3].setLetter(T);
    assertTrue(game.isWord(0, 0, 0, 3));
    board.boardArr[0][4].setLetter(T);
    assertTrue(!game.isWord(0, 0, 0, 4));
  }

  /**
   * 
   * @throws FileNotFoundException if the .txt file is not found to create a dictionary
   * checks if the letters that are placed vertically create a word
   */
  @Test
  public void verticalIsWordTest() throws FileNotFoundException {
    board.boardArr[1][0].setLetter(P);
    board.boardArr[2][0].setLetter(A);
    board.boardArr[3][0].setLetter(C);
    board.boardArr[4][0].setLetter(T);
    assertTrue(game.isWord(1, 0, 4, 0));
    board.boardArr[5][0].setLetter(T);
    assertTrue(!game.isWord(1, 0, 5, 0));
  }

  /**
   * 
   * @throws FileNotFoundException if the .txt file is not found to create a dictionary
   * checks if the letters that are placed in reverse order vertically create a word
   */
  @Test
  public void reverseVerticalIsWordTest() throws FileNotFoundException {
    board.boardArr[1][3].setLetter(P);
    board.boardArr[2][3].setLetter(A);
    board.boardArr[3][3].setLetter(C);
    board.boardArr[4][3].setLetter(T);
    assertTrue(game.isWord(1, 3, 4, 3));
    board.boardArr[5][3].setLetter(T);
    assertTrue(!game.isWord(1, 3, 5, 3));
  }

  /**
   * 
   * @throws FileNotFoundException if the .txt file is not found to create a dictionary
   * checks if the letters that are placed horizantally create a word in the dictionary
   * In this case, they do so it allows the state to stay the same and challenger lose a turn.
   */
   @Test
   public void challengeSuccessTest() throws FileNotFoundException {
   //one of the words placed isnt a word
   board.boardArr[4][3].setLetter(T);
   board.boardArr[4][0].setLetter(T);
   List<TilePlacement> tiles = game.getTilesToPlace();
   LetterTile[] newRack = { P, A, T, H, E, P, E };
   game.getCurrentPlayer().setRack(newRack);
   TilePlacement t4 = new TilePlacement(4, 1, E);
   TilePlacement t5 = new TilePlacement(4, 2, E);
   TilePlacement t6 = new TilePlacement(4, 4, H);
   tiles.add(t4);
   tiles.add(t5);
   tiles.add(t6);
   game.placeTiles(tiles);
   game.challenge(game.getCurrentPlayer());
   assertTrue(game.getCurrentPlayer().getScore() == 14);
   }

   /**
    * 
    * @throws FileNotFoundException if the .txt file is not found to create a dictionary
    * checks if the letters that are placed horizantally create a word in the dictionary
    * In this case, they don't so it reverts the state of the game back and current player
    * lose a turn.
    */
   
  @Test
  public void challengeFailTest() throws FileNotFoundException {
    // checking opposite direction words validity
    board.boardArr[4][3].setLetter(T);
    board.boardArr[4][0].setLetter(T);
    List<TilePlacement> tiles = game.getTilesToPlace();
    LetterTile[] newRack = { P, A, T, H, E, P, E };
    game.getCurrentPlayer().setRack(newRack);
    TilePlacement t4 = new TilePlacement(4, 1, E);
    TilePlacement t5 = new TilePlacement(4, 2, P);
    TilePlacement t6 = new TilePlacement(4, 4, H);
    tiles.add(t4);
    tiles.add(t5);
    tiles.add(t6);
    game.placeTiles(tiles);
    LetterTile[] oldRackAfterPlacement = game.getCurrentPlayer().getRack();
    game.challenge(game.getCurrentPlayer());
    //after challenge failed, board reverts back to old one
    assertTrue(board.boardArr[4][1].getLetter() == null);
    assertTrue(game.getCurrentPlayer().getRack() == oldRackAfterPlacement);

  }

   @Test
   public void collinearityFailedTest() {
   Player player = game.getCurrentPlayer();
   Board board = game.getBoard();
   String s = player.rackToString();
   board.boardArr[1][1].setLetter(P);
   board.boardArr[1][3].setLetter(P);
   board.boardArr[0][4].setLetter(P);
   LetterTile A = player.getRack()[0];
   LetterTile B = player.getRack()[1];
   LetterTile C = player.getRack()[2];
   TilePlacement t1 = new TilePlacement(7, 1, A);
   TilePlacement t2 = new TilePlacement(0, 2, B);
   TilePlacement t3 = new TilePlacement(0, 3, C);
   List<TilePlacement> tiles = game.getTilesToPlace();
   tiles.add(t1);
   tiles.add(t2);
   tiles.add(t3);
   game.placeTiles(tiles);
   assertTrue(player.rackToString().equals(s));
   }
  
   @Test
   public void clusterFailedTest() {
   Player player = game.getCurrentPlayer();
   String s = player.rackToString();
   board.boardArr[1][1].setLetter(P);
   board.boardArr[1][3].setLetter(P);
   board.boardArr[5][8].setLetter(P);
   LetterTile A = player.getRack()[0];
   LetterTile B = player.getRack()[1];
   LetterTile C = player.getRack()[2];
   TilePlacement t1 = new TilePlacement(5, 10, A);
   TilePlacement t2 = new TilePlacement(5, 9, B);
   TilePlacement t3 = new TilePlacement(5, 6, C);
   List<TilePlacement> tiles = game.getTilesToPlace();
   tiles.add(t1);
   tiles.add(t2);
   tiles.add(t3);
   game.placeTiles(tiles);
   assertTrue(player.rackToString().equals(s));
   }
   
   @Test
   public void adjacencyFailedTest() {
   Player player = game.getCurrentPlayer();
   String s = player.rackToString();
   board.boardArr[1][1].setLetter(P);
   board.boardArr[1][3].setLetter(P);
   board.boardArr[5][8].setLetter(P);
   LetterTile A = player.getRack()[0];
   LetterTile C = player.getRack()[2];
   TilePlacement t1 = new TilePlacement(5, 5, A);
   TilePlacement t3 = new TilePlacement(5, 6, C);
   List<TilePlacement> tiles = game.getTilesToPlace();
   tiles.add(t1);
   tiles.add(t3);
   game.placeTiles(tiles);
   assertTrue(player.rackToString().equals(s));
   }
   
   

}
