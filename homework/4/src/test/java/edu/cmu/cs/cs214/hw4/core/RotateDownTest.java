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
import edu.cmu.cs.cs214.hw4.tiles.NegativePoints;
import edu.cmu.cs.cs214.hw4.tiles.RotateDown;
import edu.cmu.cs.cs214.hw4.tiles.SpecialTile;

public class RotateDownTest {

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
  public void RotateDownTest() {
    // initialization
    LetterTile Q = new LetterTile('Q', 10);
    LetterTile A = new LetterTile('A', 10);
    Board board = game.getBoard();
    board.boardArr[2][7].setLetter(Q);
    board.boardArr[1][7].setLetter(A);
    board.boardArr[0][7].setLetter(A);
    board.boardArr[14][7].setLetter(Q);
    Board oldBoard = board.getDefensiveCopy();
    game.placeSpecialTile(new RotateDown(), 0, 6);
    board.boardArr[0][6].setLetter(Q);
    game.activateSpecialTiles();
    assertTrue(game.getBoard().boardArr[0][7].getLetter().equals(oldBoard.getBoardArr()[14][7].getLetter()));
    assertTrue(game.getBoard().boardArr[1][7].getLetter().equals(oldBoard.getBoardArr()[0][7].getLetter()));
    assertTrue(game.getBoard().boardArr[2][7].getLetter().equals(oldBoard.getBoardArr()[1][7].getLetter()));
  }

}
