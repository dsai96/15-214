package edu.cmu.cs.cs214.hw4.gui;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JToggleButton;

import edu.cmu.cs.cs214.hw4.core.*;
import edu.cmu.cs.cs214.hw4.tiles.LetterTile;

public class ScrabbleGUI {

  private ScrabbleGame g;
  //The following are needed to keep track of Tiles being clicked
  private JToggleButton selectedButton;
  private LetterTile selectedTile;
  public boolean isSelected = false;
  
  private ScrabbleListener listener;
  private final List<ScrabbleListener> scrabbleListeners = new ArrayList<>();

  public ScrabbleGUI(ScrabbleGame game) {
    this.g = game;
  }
  
  public ScrabbleGame getG() {
    return g;
  }

  public void createPlayerNuetralPanel() {
    
  }
  
  
 public void createPlayerlPanel() {
    
  }
  
//  public void addToPlaceTiles(LetterTile t, int row, int col) {
//    TilePlacement tp = new TilePlacement(row, col, t);
//    tilesToPlace.add(tp);
//  }
//  
//  
  public void addGameChangeListener(final ScrabbleListener listener) {
    scrabbleListeners.add(listener);
  }
//  
//  public void tempAddToBoard(int row, int col) {
//    
//  }
//  
//  private void notifyMoveMade(final int col, final int row) {
//    for (final ScrabbleListener listener : scrabbleListeners) {
//      listener.squareChanged(row, col);
//    }  
  
  
  
  
  
}
