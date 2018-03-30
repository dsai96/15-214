package edu.cmu.cs.cs214.hw4.core;

/**
 * 
 * @author dsai96 This class defines all the error messages you could get when
 *         checking its a valid move
 */
public enum Error {

  BOUNDS {
    public String toString() {
      return "Tile placement not on board";
    }
  },

  COLLINEARITY {
    public String toString() {
      return "Tiles aren't placed collinearly";
    }
  },

  ADJCENCY {
    public String toString() {
      return "Tiles aren't connected to a tile on the board already";
    }
  },

  FULL {
    public String toString() {
      return "Square already has a tile";
    }
  },

  NOTINRACK {
    public String toString() {
      return "Player rack doesn't contain letter being placed";
    }
  },

  SAMESQUARE {
    public String toString() {
      return "Tiles are being placed into the same square in this turn";
    }
  },

  NOTCLUSTERED {
    public String toString() {
      return "Tiles are not being placed in a cluster form";
    }
  }

}
