  package edu.cmu.cs.cs214.hw4.gui;

  import java.awt.BorderLayout;
  import java.awt.Component;
  import java.awt.GridLayout;

  import javax.swing.JButton;
  import javax.swing.JFrame;
  import javax.swing.JLabel;
  import javax.swing.JOptionPane;
  import javax.swing.JPanel;
  import javax.swing.SwingUtilities;

  import edu.cmu.cs.cs214.hw4.core.*;

  /**
   * A panel that displays the Scrabble board and current player label.
   */
  @SuppressWarnings("serial")
  public class GameBoardPanel extends JPanel implements ScrabbleListener {

    private final int boardSize = 15;
    private final ScrabbleGUI gui;
    private final JButton[][] squares;
    private final JLabel currentPlayerLabel;

    /**
     * Creates a new panel for displaying the game.
     * 
     * @param g
     *          scrabble game interface.
     */
    public GameBoardPanel(final ScrabbleGUI gui) {
      this.gui = gui;
      gui.addGameChangeListener(this);
      currentPlayerLabel = new JLabel();
      squares = new JButton[boardSize][boardSize];

      setLayout(new BorderLayout());
      add(currentPlayerLabel, BorderLayout.NORTH);
      add(createBoardPanel(), BorderLayout.CENTER);
    }

    private JPanel createBoardPanel() {
      final JPanel panel = new JPanel();
      panel.setLayout(new GridLayout(boardSize, boardSize));

      // Create all of the squares and display them.
      for (int row = 0; row < boardSize; row++) {
        for (int col = 0; col < boardSize; col++) {
          squares[row][col] = new JButton();
          squares[row][col].setText(col + "," + row);
          squares[row][col].addActionListener(new SquareListener(row, col, gui));
          panel.add(squares[row][col]);
        }
      }
      return panel;
    }

    @Override
    public void squareChanged(final int row, final int col) {
      final JButton button = squares[row][col];
      final Square s = gui.getG().getBoard().getBoardArr()[row][col];
      if (s != null) {
        button.setText("hi");
      } else {
        button.setText(" no  ");
      }
    }

    @Override
    public void currentPlayerChanged(final Player player) {
      currentPlayerLabel.setText("Current player: " + player);
    }

    @Override
    public void gameEnded(final Player winner) {
      final JFrame frame = (JFrame) SwingUtilities.getRoot(this);

      if (winner != null) {
        showDialog(frame, "Winner!", winner + " just won the game!");
      } else {
        showDialog(frame, "Stalemate", "The game has ended in a stalemate.");
      }

      // Append the 'start new game' command to the end of the
      // EventQueue. This is necessary because we need to wait
      // for all of the buttons to finish dispatching before
      // we reset the game's state. (If you are confused about
      // this, try calling 'game.startNewGame()' without the
      // 'invokeLater' and see what happens).
      SwingUtilities.invokeLater(gui.getG()::startNewGame);
    }

    private static void showDialog(final Component component, final String title,
        final String message) {
      JOptionPane.showMessageDialog(component, message, title, JOptionPane.INFORMATION_MESSAGE);
    }
  }

