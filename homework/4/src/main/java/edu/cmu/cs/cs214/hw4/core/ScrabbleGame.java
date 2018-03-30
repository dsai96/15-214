package edu.cmu.cs.cs214.hw4.core;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import javax.swing.JToggleButton;

//import edu.cmu.cs.cs214.hw4.gui.ScrabbleListener;
import edu.cmu.cs.cs214.hw4.tiles.Bag;
import edu.cmu.cs.cs214.hw4.tiles.LetterTile;
import edu.cmu.cs.cs214.hw4.tiles.SpecialTile;
import edu.cmu.cs.cs214.hw4.tiles.SpecialTileShop;

public class ScrabbleGame {

  /**
   * xValuesOfPlaces is the set of x values of the tiles being placed. This is
   * used to check if the length is one later to check if the word is being
   * placed horizantally, or vertically with the yValuesOfPlaced. If the neither
   * set is length 1, then the tiles being placed are not collinear.
   */
  private Board board = new Board();
  private final SpecialTileShop shop = new SpecialTileShop();
  private boolean isFirstMove;
  private Board prev;
  private final int numOfTiles = 7;
  private Player currentPlayer;
  private final int numOfPlayers;
  private final Set<String> dictionary;
  private List<Player> players = new ArrayList<>();
  private final Bag b = new Bag();
  private List<LetterTile> playedLetters = new ArrayList<LetterTile>();
  private Set<Integer> rowValuesOfPlaces = new HashSet<Integer>();
  private Set<Integer> colValuesOfPlaces = new HashSet<Integer>();
  private List<TilePlacement> tilesToPlace = new ArrayList<TilePlacement>();
  private int roundScore;
  private final int specialTileCost = 25;

  public ScrabbleGame(List<String> playersNames, Set<String> dictionary) {
    this.dictionary = dictionary;
    for (int i = 0; i < playersNames.size(); i++) {
      players.add(new Player(playersNames.get(i), b));
    }
    this.numOfPlayers = players.size();
    startNewGame();
  }

  public void startNewGame() {
    isFirstMove = true;
    Collections.shuffle(players);
    currentPlayer = players.get(0);
  }
 
  
  public void changeTurn() {
    int i = 0;
    for (int j = 0; j < players.size(); j++) {
      if (players.get(j) == currentPlayer)
        i = j;
    }
    // adds another turn of a player who has to lose a turn
    if (currentPlayer.getTurnsLost() < 0) {
      System.out.println(currentPlayer);
      return;
    }
    if (i < players.size() - 1) {
      currentPlayer = players.get(i + 1);
    } else {
      currentPlayer = players.get(0);
    }
    // skips the turn of a player who has to lose a turn
    if (currentPlayer.getTurnsLost() > 0) {
      currentPlayer.setTurnsLost(currentPlayer.getTurnsLost() - 1);
      changeTurn();
    }
  }

  public void activateSpecialTiles() {
    for (int i = 0; i < board.size; i++) {
      for (int j = 0; j < board.size; j++) {
        if (board.boardArr[i][j].getSpecial() != null) {
          System.out.println(i + ", " + j);
          board.boardArr[i][j].getSpecial().apply(this);
          board.boardArr[i][j].setSpecial(null);
        }
      }
    }
  }

  /**
   * allows the current player to buy a special tile after checking for score
   * and availability
   * 
   * @param st
   *          the tile wanted to buy
   */
  public void buySpecialTile(SpecialTile st) {
    if (!shop.getAllST().contains(st)) {
      throw new IllegalStateException("There are no more" + st.toString() + "tiles to buy!");
    }
    if (currentPlayer.getScore() < specialTileCost) {
      throw new IllegalStateException("You only have" + currentPlayer.getScore() + "points!");
    }
    st.setOwner(currentPlayer);
    currentPlayer.getBoughtST().add(st);
    currentPlayer.setScore(currentPlayer.getScore() - specialTileCost);
    shop.getAllST().remove(st);
  }

  public void placeSpecialTile(SpecialTile st, int row, int col) {
    if (!board.isInBounds(row, col))
      throw new IllegalArgumentException("You can't place tile out of bounds");
    if (!board.isEmpty(row, col))
      throw new IllegalStateException("You can't place tile on a Letter Tile");
    if (board.boardArr[row][col].getSpecial() != null)
      new IllegalStateException("There is already a ST in this location");
    board.boardArr[row][col].setSpecial(st);
  }

  public static Set<String> makeDictionary(File f){
    Set<String> d = new HashSet<String>();
    Scanner s = null;
    try {
      try {
        s = new Scanner(new BufferedReader(new FileReader(f)));
      } catch (FileNotFoundException e) {
        e.printStackTrace();
      }
      while (s.hasNext()) {
        String line = s.nextLine();
        d.add(line);
      }
    } finally {
      if (s != null)
        s.close();
    }
    return d;
  }

  /**
   * @return whether the tiles of the whole word placed are in a valid position
   */
  public boolean isValidMove(List<TilePlacement> tiles) {
    // playedLetters.clear();
    currentPlayer.getEmptyRackIndeces().clear();
    rowValuesOfPlaces.clear();
    colValuesOfPlaces.clear();
    boolean adjacent = false;
    boolean sameSquare = false;
    for (TilePlacement t : tiles) {
      int row = t.getRow();
      int col = t.getCol();
      rowValuesOfPlaces.add(row);
      colValuesOfPlaces.add(col);
      // check if all row,col are on board
      if (!board.isInBounds(row, col)) {
        System.out.println(Error.BOUNDS.toString());
        return false;
      }
      // checks if square had tile already
      if (!board.isEmpty(row, col)) {
        System.out.println(row + ", " + col);

        System.out.println(Error.FULL.toString());
        return false;
      }
      // checks if the letter is in the players rack and adds to removed tiles
      // list
      if (Arrays.asList(currentPlayer.getRack()).contains(t.getLetter())) {
        playedLetters.add(t.getLetter());
        currentPlayer.removeTileFromRack(t.getLetter());
      } else {
        System.out.println(Error.NOTINRACK.toString());
        return false;
      }
      if (isAdjacent(t))
        adjacent = true;
    }
    // check for placing tiles in same row/col in that round
    for (int i = 0; i < tiles.size(); i++) {
      for (int j = 0; j < i; j++) {
        if (tiles.get(i).getCol() == tiles.get(j).getCol() && tiles.get(i).getRow() == tiles.get(j).getRow())
          sameSquare = true;
      }
    }
    if (sameSquare) {
      System.out.println(Error.SAMESQUARE.toString());
      return false;
    }
    // check collinearity after entire word is placed
    if (!(colValuesOfPlaces.size() == 1 || rowValuesOfPlaces.size() == 1)) {
      System.out.println(Error.COLLINEARITY.toString());
      return false;
    }
    if (!adjacent) {
      System.out.println(Error.ADJCENCY.toString());
      return false;
    }
    // checks for clustered form
    if (!isClustered()) {
      System.out.println(Error.NOTCLUSTERED.toString());
      return false;
    }
    return true;
  }

  /**
   * 
   * @return if the move is clustered meaning that the extremes are bounded by
   *         squares with tiles or tiles about to be placed
   */
  private boolean isClustered() {
    boolean vertical = colValuesOfPlaces.size() == 1;
    if (vertical) {
      int maxIndex = Collections.max(rowValuesOfPlaces);
      int minIndex = Collections.min(rowValuesOfPlaces);
      int colConstant = colValuesOfPlaces.iterator().next();
      for (int i = minIndex; i <= maxIndex; i++) {
        // there is a empty square and i is NOT in set
        if ((board.isEmpty(i, colConstant)) && !rowValuesOfPlaces.contains(i))
          return false;
      }
      return true;
    } else {
      int maxIndex = Collections.max(colValuesOfPlaces);
      int minIndex = Collections.min(colValuesOfPlaces);
      int rowConstant = rowValuesOfPlaces.iterator().next();
      for (int i = minIndex; i <= maxIndex; i++) {
        if ((board.isEmpty(rowConstant, i)) && !(colValuesOfPlaces.contains(i))) {
          return false;
        }
      }
      return true;
    }
  }

  /**
   * 
   * @param tiles
   *          that need to be put onto the board
   */
  public void placeTiles(List<TilePlacement> tiles) {

    if (isValidMove(tiles)) {
      isFirstMove = false;
      saveBoardInstance();
      for (TilePlacement tile : tiles) {
        int row = tile.getRow();
        int col = tile.getCol();
        // iterate through board and place lettertile
        for (int i = 0; i < board.size; i++) {
          for (int j = 0; j < board.size; j++) {
            if (row == i && col == j) {
              board.boardArr[i][j].setLetter(tile.getLetter());
            }
          }
        }
      }
    } else {
      putBackLetters();
    }
  }

  /**
   * if the move is not valid then put the letters back on the rack
   */
  private void putBackLetters() {
    assert (playedLetters.size() == numOfTiles - currentPlayer.getRack().length);
    for (int j = 0; j < playedLetters.size(); j++) {
      for (int i = 0; i < numOfTiles; i++) {
        if (currentPlayer.getRack()[i] == null) {
          currentPlayer.getRack()[i] = playedLetters.get(j);
          break;
        }
      }
    }
    playedLetters.clear();
  }

  /**
   * created a new instance of the board to return back to when the challenge
   * succeeded
   */
  public void returnToPrevInstance() {
    putBackLetters();
    board.setBoardArr(prev.boardArr);
    ;
  }

  /**
   * 
   * @param p
   *          is the player challenging the current player check n+1 words that
   *          could be created use the extremes int[][] to check if that word is
   *          a word either in the forward or backward direction if at any point
   *          the letters don't form a word, break and take off all tiles and
   */
  public void challenge(Player p) {
    boolean isHorizantal = (rowValuesOfPlaces.size() == 1);
    int[][] extremes = findOppositeDirectionWord(rowValuesOfPlaces.iterator().next(),
        colValuesOfPlaces.iterator().next(), isHorizantal);
    int startRow = extremes[0][0];
    int startCol = extremes[0][1];
    int endRow = extremes[1][0];
    int endCol = extremes[1][1];
    // check the current word placed
    boolean isWord = isWord(startRow, startCol, endRow, endCol);
    if (!isWord) {
      currentPlayer.setTurnsLost(currentPlayer.getTurnsLost() + 1);
      returnToPrevInstance();
    } else {
      int score = calculateScore(extremes);
      for (int i = 0; i < tilesToPlace.size(); i++) {
        extremes = findOppositeDirectionWord(tilesToPlace.get(i).getRow(), tilesToPlace.get(i).getCol(), !isHorizantal);
        startRow = extremes[0][0];
        startCol = extremes[0][1];
        endRow = extremes[1][0];
        endCol = extremes[1][1];
        // checks if the opposite direction word is a word
        isWord = isWord(startRow, startCol, endRow, endCol);
        if (!isWord) {
          currentPlayer.setTurnsLost(currentPlayer.getTurnsLost() + 1);
          returnToPrevInstance();
        } else {
          score += calculateScore(extremes);
        }
      }
      // adjusting the score accordingly
      int repeatedScore = 0;
      for (int i = 0; i < tilesToPlace.size(); i++) {
        repeatedScore += tilesToPlace.get(i).getLetter().getPointValue();
      }
      score -= repeatedScore;
      if (score < 0)
        score = 0;
      setRoundScore(score);
      if (tilesToPlace.size() == 7) {
        currentPlayer.setScore(currentPlayer.getScore() + 50);
      }
      currentPlayer.setScore(currentPlayer.getScore() + score);
      p.setTurnsLost(currentPlayer.getTurnsLost() + 1);
    }
  }

  /**
   * call this function when there is no challenge
   */
  void findWordsandCalculateMoveScore() {
    // board = prev;
    // prev = null;
    currentPlayer.replenishRack();
    boolean isHorizantal = (rowValuesOfPlaces.size() == 1);
    int[][] extremes = findOppositeDirectionWord(rowValuesOfPlaces.iterator().next(),
        colValuesOfPlaces.iterator().next(), isHorizantal);
    int score = calculateScore(extremes);
    for (int i = 0; i < tilesToPlace.size(); i++) {
      int[][] currWord = findOppositeDirectionWord(tilesToPlace.get(i).getRow(), tilesToPlace.get(i).getCol(),
          !isHorizantal);
      int currScore = calculateScore(currWord);
      score += currScore;
    }
    // remove the score for the tiles placed which are hit twice
    int repeatedScore = 0;
    for (int i = 0; i < tilesToPlace.size(); i++) {
      repeatedScore += tilesToPlace.get(i).getLetter().getPointValue();
    }
    score -= repeatedScore;
    if (score < 0)
      score = 0;
    setRoundScore(score);
    if (tilesToPlace.size() == 7) {
      currentPlayer.setScore(currentPlayer.getScore() + 50);
    }
    currentPlayer.setScore(currentPlayer.getScore() + score);
  }

  /**
   * 
   * @param startRow
   *          of the word
   * @param startCol
   *          of the word
   * @param endRow
   *          of the word
   * @param endCol
   *          of the word
   * @return if those indeces between altogether create a word
   */
  public boolean isWord(int startRow, int startCol, int endRow, int endCol) {
    assert (startRow == endRow || startCol == endCol);
    // one letter word
    if (startRow == endRow && startCol == endCol)
      return true;
    String word = "";
    if (startRow == endRow) {
      for (int i = startCol; i <= endCol; i++) {
        word += board.boardArr[startRow][i].getLetter().getName();
      }
    } else {
      for (int i = startRow; i <= endRow; i++) {
        word += board.boardArr[i][startCol].getLetter().getName();
      }
    }
    String reversed = new StringBuffer(word).reverse().toString();
    return (dictionary.contains(word.toLowerCase()) || dictionary.contains(reversed.toLowerCase()));
  }

  private int calculateScore(int[][] extremes) {
    int score = 0;
    int wordMultiplier = 1;
    // horizantal word, so check horizantally for the score
    if (extremes[0][0] == extremes[1][0]) {
      int row = extremes[1][0];
      for (int i = extremes[0][1]; i <= extremes[1][1]; i++) {
        int currScore = board.boardArr[row][i].getLetter().getPointValue()
            * board.boardArr[row][i].getLetterMultiplier();
        score += currScore;
        wordMultiplier *= board.boardArr[row][i].getWordMultiplier();
      }
    }
    // vertical word so checking vertically for score
    else {
      int col = extremes[1][1];
      for (int i = extremes[0][0]; i <= extremes[1][0]; i++) {
        int currScore = board.boardArr[i][col].getLetter().getPointValue()
            * board.boardArr[i][col].getLetterMultiplier();
        score += currScore;
        wordMultiplier *= board.boardArr[i][col].getWordMultiplier();
      }
    }
    score *= wordMultiplier;
    return score;
  }

  /**
   * 
   * @param row
   *          of the word
   * @param col
   *          of the word
   * @param isHorizantal
   *          checks if we are checking horizantally
   * @return the int[][] of indeces for start/end for row/col of the word
   *         detected
   */
  private int[][] findOppositeDirectionWord(int row, int col, boolean isHorizantal) {
    int[][] startEndCoords = new int[2][2];
    if (isHorizantal) {
      int rightCol = col;
      while ((board.isInBounds(row, rightCol)) && !(board.isEmpty(row, rightCol))) {
        rightCol++;
      }
      int leftCol = col;
      while ((board.isInBounds(row, leftCol)) && !(board.isEmpty(row, leftCol))) {
        leftCol--;
      }
      startEndCoords[0][0] = row;
      startEndCoords[0][1] = leftCol + 1;
      startEndCoords[1][0] = row;
      startEndCoords[1][1] = rightCol - 1;
    } else {
      // while there is a letter tile and you're in bounds of board
      int bottomRow = row;
      while ((board.isInBounds(bottomRow, col)) && !(board.isEmpty(bottomRow, col))) {
        bottomRow++;
      }
      int topRow = row;
      while ((board.isInBounds(topRow, col)) && !(board.isEmpty(topRow, col))) {
        topRow--;
      }
      startEndCoords[0][0] = topRow + 1;
      startEndCoords[0][1] = col;
      startEndCoords[1][0] = bottomRow - 1;
      startEndCoords[1][1] = col;
    }
    return startEndCoords;
  }

  /**
   * 
   * @param tile
   *          placed
   * @return if the tile is bordering at least one tile already on the board
   */
  private boolean isAdjacent(TilePlacement t) {
    boolean foundAdjacent = false;
    int row = t.getRow();
    int col = t.getCol();
    if (row - 1 >= 0) {
      if (!board.isEmpty(row - 1, col))
        foundAdjacent = true;
    }
    if (row + 1 < board.size) {
      if (!board.isEmpty(row + 1, col))
        foundAdjacent = true;
    }
    if (col + 1 < board.size) {
      if (!board.isEmpty(row, col + 1))
        foundAdjacent = true;
    }
    if (col - 1 >= 0) {
      if (!board.isEmpty(row, col - 1))
        foundAdjacent = true;
    }
    return foundAdjacent;
  }

  private void saveBoardInstance() {
    prev = board.getDefensiveCopy();
  }

  public Board getBoard() {
    return board;
  }

  public void setBoard(Board board) {
    this.board = board;
  }

  public Board getPrev() {
    return prev;
  }

  public void setPrev(Board prev) {
    this.prev = prev;
  }

  public Player getCurrentPlayer() {
    return currentPlayer;
  }

  public void setCurrentPlayer(Player currentPlayer) {
    this.currentPlayer = currentPlayer;
  }

  public List<LetterTile> getPlayedLetters() {
    return playedLetters;
  }

  public void setPlayedLetters(List<LetterTile> playedLetters) {
    this.playedLetters = playedLetters;
  }

  public List<TilePlacement> getTilesToPlace() {
    return tilesToPlace;
  }

  public void setTilesToPlace(List<TilePlacement> tilesToPlace) {
    this.tilesToPlace = tilesToPlace;
  }

  public Bag getB() {
    return b;
  }

  public List<Player> getPlayers() {
    return players;
  }

  public void setPlayers(List<Player> players) {
    this.players = players;
  }

  public int getRoundScore() {
    return roundScore;
  }

  public void setRoundScore(int roundScore) {
    this.roundScore = roundScore;
  }

  
}
