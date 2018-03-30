package edu.cmu.cs.cs214.hw4.core;

import static org.junit.Assert.assertTrue;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import edu.cmu.cs.cs214.hw4.tiles.Bag;
import edu.cmu.cs.cs214.hw4.tiles.LetterTile;
import edu.cmu.cs.cs214.hw4.tiles.Reverse;
import edu.cmu.cs.cs214.hw4.tiles.SpecialTile;

public class ReverseTileTest {

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
  public void test() throws FileNotFoundException {
    LetterTile Q = new LetterTile('Q', 10);
    game.getBoard().boardArr[7][7].setLetter(Q);
    SpecialTile rev = new Reverse();
    game.placeSpecialTile(rev, 0, 0);
    List<Player> originalOrder = game.getPlayers();
    game.getBoard().boardArr[0][0] = new Square(1, 1, Q);
    game.activateSpecialTiles();
    Player originalCurrentPlayer = game.getCurrentPlayer();
    List<Player> newOrder = game.getPlayers();
    Collections.reverse(originalOrder);
    Player newCurrentPlayer = game.getCurrentPlayer();
    assertTrue(newCurrentPlayer == originalCurrentPlayer);
    assertTrue(newOrder.equals(originalOrder));
  }

  @Test
  public void getOwnertest() throws FileNotFoundException {
    LetterTile Q = new LetterTile('Q', 10);
    game.getBoard().boardArr[7][7].setLetter(Q);
    SpecialTile rev = new Reverse();
    assertTrue(rev.getOwner() == null);
    assertTrue(rev.toString().equals("Unassigned owner's ReverseTile"));
  }

}
