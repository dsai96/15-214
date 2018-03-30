package edu.cmu.cs.cs214.hw4.gui;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;

import edu.cmu.cs.cs214.hw4.core.ScrabbleGame;

/**
 * 
 * @author dsai96
 * run this file to start up Scrabble
 */
public class Main {
  
  private static final String gameName = "Scrabble with Stuff";
  private static String dictionaryPath = "src/main/java/resources/words.txt";
  
  /**
   * 
   * @param args to stay on the EDT we invoke Later
   */
  public static void main(final String[] args) {
    SwingUtilities.invokeLater(Main::createAndShowGameBoard);
  }
  
  /**
   * 
   * @param frame is used to start up dialogs
   * @return a list of Strings of player names
   */
  private static List<String> getPlayerNames(JFrame frame) {
    List<String> playerNames = new ArrayList<String>();
    String numOfPlayers = (String) JOptionPane.showInputDialog(frame,
        "Number of Players:\n",
        JOptionPane.PLAIN_MESSAGE);
    for (int i = 0; i < Integer.parseInt(numOfPlayers); i++) {
      String s = (String) JOptionPane.showInputDialog(frame,
          "Player Name:\n", 
          "Add Player",
          JOptionPane.PLAIN_MESSAGE);
      if ((s != null) && (s.length() > 0)) {
        playerNames.add(s);
      }
    }
    return playerNames;
  }
  
  /**
   *  sets up the board panel
   */
  private static void createAndShowGameBoard() {
    final JFrame frame = new JFrame(gameName);
    frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    List<String> players = getPlayerNames(frame);
    File dictFile = new File(ScrabbleGame.class.getResource(dictionaryPath).getFile());
    Set<String> dictionary = ScrabbleGame.makeDictionary(dictFile);
    final ScrabbleGame game = new ScrabbleGame(players, dictionary);
    final ScrabbleGUI gui = new ScrabbleGUI(game);
//         Create and set up the content pane.
    final GameBoardPanel gamePanel = new GameBoardPanel(gui);
    gamePanel.setOpaque(true);
    frame.add(gamePanel);
    frame.pack();
    frame.setVisible(true);
  }
  
}
