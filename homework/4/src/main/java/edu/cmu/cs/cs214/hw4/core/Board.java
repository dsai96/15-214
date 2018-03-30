package edu.cmu.cs.cs214.hw4.core;

import java.util.Arrays;

public class Board {
  final int size = 15;
  Square[][] boardArr = new Square[size][size];

  /**
   * constructs initial board with squares(wordMult, letterMult, letterTile)
   */
  public Board() {
    int[][] tripleWord = new int[][] { { 14, 14 }, { 0, 0 }, { 0, 7 }, { 7, 0 }, { 14, 0 }, { 0, 14 }, { 7, 14 },
        { 14, 7 } };
    int[][] doubleWord = new int[][] { { 1, 1 }, { 2, 2 }, { 3, 3 }, { 4, 4 }, { 10, 10 }, { 11, 11 }, { 12, 12 },
        { 13, 13 }, { 1, 13 }, { 2, 12 }, { 3, 11 }, { 4, 10 }, { 10, 4 }, { 11, 3 }, { 12, 2 }, { 13, 1 } };
    int[][] tripleLetter = new int[][] { { 1, 5 }, { 1, 9 }, { 5, 1 }, { 5, 5 }, { 5, 9 }, { 5, 13 }, { 9, 1 },
        { 9, 5 }, { 9, 9 }, { 9, 13 }, { 13, 5 }, { 13, 9 } };
    int[][] doubleLetter = new int[][] { { 0, 3 }, { 0, 11 }, { 2, 6 }, { 2, 8 }, { 3, 7 }, { 3, 0 }, { 3, 14 },
        { 6, 2 }, { 6, 6 }, { 6, 8 }, { 6, 12 }, { 7, 3 }, { 7, 11 }, { 8, 2 }, { 8, 6 }, { 8, 8 }, { 8, 12 },
        { 11, 0 }, { 11, 7 }, { 11, 14 }, { 12, 6 }, { 12, 8 }, { 14, 3 }, { 14, 11 } };

    for (int i = 0; i < size; i++) {
      for (int j = 0; j < size; j++) {
        int[] curr = new int[] { i, j };
        if (contains(curr, tripleLetter)) {
          boardArr[i][j] = new Square(1, 3, null);
        }
        if (contains(curr, doubleLetter)) {
          boardArr[i][j] = new Square(1, 2, null);
        }
        if (contains(curr, doubleWord)) {
          boardArr[i][j] = new Square(2, 1, null);
        }
        if (contains(curr, tripleWord)) {
          boardArr[i][j] = new Square(3, 1, null);
        }
        if (boardArr[i][j] == null) {
          boardArr[i][j] = new Square(1, 1, null);
        }
      }
    }
  }

  /**
   * 
   * @param lint
   *          array
   * @param list
   *          2d int array
   * @return if l is in list
   */
  public boolean contains(int[] l, int[][] list) {
    for (int i = 0; i < list.length; i++) {
      if (Arrays.equals(list[i], l))
        return true;
    }
    return false;
  }

  /**
   * 
   * @return creates a new copy of the board to save
   */
  public Board getDefensiveCopy() {
    Board copy = new Board();
    for (int i = 0; i < size; i++) {
      for (int j = 0; j < size; j++) {
        copy.boardArr[i][j] = boardArr[i][j].getDefensiveCopy();
      }
    }
    return copy;
  }

  /**
   * 
   * @param row
   *          of the board
   * @param col
   *          of the board
   * @return if the col and row are in bounds of the board
   */
  public boolean isInBounds(int row, int col) {
    return ((row < boardArr.length && row >= 0) && (col < boardArr.length && col >= 0));
  }

  public boolean isEmpty(int row, int col) {
    return (boardArr[row][col].getLetter() == null);
  }

  public Square[][] getBoardArr() {
    return boardArr;
  }

  public void setBoardArr(Square[][] boardArr) {
    this.boardArr = boardArr;
  }

  @Override
  public String toString() {
    String result = "[";
    for (int i = 0; i < size; i++) {
      result += "[ ";
      for (int j = 0; j < size; j++) {
        result += boardArr[i][j].toString() + ",";
      }
      result += " ]" + "\n";
    }
    result += " ]";
    return result;
  }
}
