package cs3500.marblesolitaire.model.hw04;

import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;

/**
 * This class is an abstract implementation of the MarbleSolitaireModel interface. This class
 * contains methods for moving and getting the score and if the game
 * is over and any related helper methods. Also, this abstract class helps remove any redundancy
 * in any of the other models.
 */
public class AbstractMarbleSolitaire implements MarbleSolitaireModel {
  protected final int rows;
  protected final int columns;
  protected final int armThickness;
  protected final SlotState[][] board;

  /**
   * This constructor generates the default board for the game, with an empty middle slot.
   */
  public AbstractMarbleSolitaire() {
    this.rows = 3;
    this.columns = 3;
    this.armThickness = 3;
    this.board = new SlotState[this.getBoardSize()][this.getBoardSize()];
    this.makeBoard();
    if (board == null) {
      throw new IllegalArgumentException("Null board");
    }
  }

  /**
   * A constructor with a given armThickness, which will determine its rows and columns.
   */
  public AbstractMarbleSolitaire(int armThickness) {
    if (armThickness <= 1) {
      throw new IllegalArgumentException("Invalid arguments!");
    }
    this.armThickness = armThickness;
    this.rows = (armThickness - 1) + (armThickness / 2);
    this.columns = (armThickness - 1) + (armThickness / 2);
    this.board = new SlotState[this.getBoardSize()][this.getBoardSize()];
    this.makeBoard();
  }

  /**
   * A constructor that generates the board.
   * With the given rows and columns with an arm thickness of 3.
   */
  public AbstractMarbleSolitaire(int rows, int columns) {
    this.armThickness = 3;
    if (isInvalidPos(rows, columns) || rows < 0 || columns < 0) {
      throw new IllegalArgumentException("Invalid empty cell position (" + rows + "," + columns
              + ")");
    }
    this.rows = rows;
    this.columns = columns;
    this.board = new SlotState[this.getBoardSize()][this.getBoardSize()];
    this.makeBoard();
    if (board == null) {
      throw new IllegalArgumentException("Null board");
    }
  }

  /**
   * A constructor that generates the game with the given rows, columns, and arm thickness.
   */
  public AbstractMarbleSolitaire(int armThickness, int rows, int columns) {
    if (armThickness <= 1) {
      throw new IllegalArgumentException("Invalid arguments!");
    }
    this.armThickness = armThickness;
    if (rows < 0 || columns < 0 || isInvalidPos(rows, columns)) {
      throw new IllegalArgumentException("Invalid empty cell position (" + rows + "," + columns
              + ")");
    }
    this.rows = rows;
    this.columns = columns;
    this.board = new SlotState[this.getBoardSize()][this.getBoardSize()];
    this.makeBoard();
    if (board == null) {
      throw new IllegalArgumentException("Null board");
    }
  }

  /**
   * Generate the board for the game.
   */
  protected void makeBoard() {
    for (int i = 0; i < this.getBoardSize(); i++) {
      for (int j = 0; j < this.getBoardSize(); j++) {
        makeBoardHelper(i, j);
      }
    }
  }

  /**
   * Help to check for what SlotState should be put on that position in the board.
   *
   * @param i represents the x position of the board.
   * @param j represents the y position of the board.
   */
  protected void makeBoardHelper(int i, int j) {
    if (!isInvalidPos(i, j)) {
      if (i == this.rows && j == this.columns) { //middle slot is the empty slot
        this.board[i][j] = SlotState.Empty;
      } else {
        this.board[i][j] = SlotState.Marble; //the rest are marbles
      }
    } else {
      this.board[i][j] = SlotState.Invalid; //the rest of the board are invalid spaces
    }
  }

  /**
   * Check for invalid position.
   *
   * @param row represents the row position
   * @param col represents the column position
   * @return whether the position is valid
   */
  protected boolean isInvalidPos(int row, int col) {
    return (row < this.armThickness - 1 && col < this.armThickness - 1)
            || (row < this.armThickness - 1 && col > 2 * this.armThickness - 2)
            || (row > 2 * this.armThickness - 2 && col < this.armThickness - 1)
            || (row > 2 * this.armThickness - 2 && col > 2 * this.armThickness - 2);
  }


  /**
   * Move a single marble from a given position to another given position.
   * A move is valid only if the from and to positions are valid. Specific
   * implementations may place additional constraints on the validity of a move.
   *
   * @param fromRow the row number of the position to be moved from
   *                (starts at 0)
   * @param fromCol the column number of the position to be moved from
   *                (starts at 0)
   * @param toRow   the row number of the position to be moved to
   *                (starts at 0)
   * @param toCol   the column number of the position to be moved to
   *                (starts at 0)
   * @throws IllegalArgumentException if the move is not possible
   */
  @Override
  public void move(int fromRow, int fromCol, int toRow, int toCol)
          throws IllegalArgumentException {
    if (!checkValidMove(fromRow, fromCol, toRow, toCol)) {
      throw new IllegalArgumentException("Move is not possible!");
    } else {
      this.board[fromRow][fromCol] = SlotState.Empty;
      this.board[(fromRow + toRow) / 2][(toCol + fromCol) / 2] = SlotState.Empty;
      this.board[toRow][toCol] = SlotState.Marble;
    }
  }

  /**
   * Determine and return if the game is over or not. A game is over if no
   * more moves can be made.
   *
   * @return true if the game is over, false otherwise
   */
  @Override
  public boolean isGameOver() {
    for (int i = 0; i < this.getBoardSize(); i++) {
      for (int j = 0; j < this.getBoardSize(); j++) {
        if (this.board[i][j] == SlotState.Marble) {
          if (anyMoveLeft(i, j)) {
            return false;
          }
        }
      }
    }
    return true;
  }

  /**
   * Check if there is any moves left.
   *
   * @param i represents the row
   * @param j represents the column
   * @return the answer to is there any moves left.
   */
  protected boolean anyMoveLeft(int i, int j) {
    return (checkValidMove(i, j, i + 2, j) || checkValidMove(i, j, i, j + 2)
            || checkValidMove(i, j, i - 2, j) || checkValidMove(i, j, i, j - 2));
  }

  /**
   * Check if the move made is valid.
   *
   * @param fromRow represents the row of the current location
   * @param fromCol represents the column of the current location
   * @param toRow   represents the row of the target location
   * @param toCol   represents the column of the target location
   * @return a boolean as a result.
   */
  protected boolean checkValidMove(int fromRow, int fromCol, int toRow, int toCol) {
    //check if moves are outside the board
    if (!checkOutsideOfBoard(fromRow, fromCol, toRow, toCol)) {
      return false;
    }
    //check if there is a marble at the current position and the after position is empty
    //also check to see if there is a marble in between moves
    else if (!(this.board[fromRow][fromCol].equals(SlotState.Marble))
            || !(this.board[(fromRow + toRow) / 2][(toCol + fromCol) / 2].equals(SlotState.Marble))
            || !(this.board[toRow][toCol].equals(SlotState.Empty))) {
      return false;
    }
    //check if the before and after position is valid
    else if (isInvalidPos(fromRow, fromCol) || isInvalidPos(toRow, toCol)) {
      return false;
    }
    //check distance is 2
    else if (fromRow == toRow) {
      if (Math.abs(fromCol - toCol) != 2) {
        return false;
      }
    }
    else if (fromCol == toCol) {
      if (Math.abs(fromRow - toRow) != 2) {
        return false;
      }
    }
    //check if moves are on the same row/column
    else if (!checkOnRightPath(fromRow, fromCol, toRow, toCol)) {
      return false;
    }
    return true;
  }

  /**
   * Check if the move is outside of board.
   *
   * @param fromRow represents the row of the current location
   * @param fromCol represents the column of the current location
   * @param toRow   represents the row of the target location
   * @param toCol   represents the column of the target location
   * @return a boolean as a result.
   */
  protected boolean checkOutsideOfBoard(int fromRow, int fromCol, int toRow, int toCol) {
    return !(toRow < 0 || toRow >= this.getBoardSize() || toCol < 0 || toCol >= this.getBoardSize()
            || fromRow < 0 || fromRow >= this.getBoardSize() || fromCol < 0
            || fromCol >= this.getBoardSize());
  }

  /**
   * Check if the move is on the correct path (same row, same column).
   *
   * @param fromRow represents the row of the current location
   * @param fromCol represents the column of the current location
   * @param toRow   represents the row of the target location
   * @param toCol   represents the column of the target location
   * @return a boolean as a result.
   */
  protected boolean checkOnRightPath(int fromRow, int fromCol, int toRow, int toCol) {
    return fromRow == toRow && fromCol == toCol;
  }


  /**
   * Return the size of this board. The size is roughly the longest dimension of a board
   *
   * @return the size as an integer
   */
  @Override
  public int getBoardSize() {
    return 3 * armThickness - 2;
  }

  /**
   * Get the state of the slot at a given position on the board.
   *
   * @param row the row of the position sought, starting at 0
   * @param col the column of the position sought, starting at 0
   * @return the state of the slot at the given row and column
   * @throws IllegalArgumentException if the row or the column are beyond
   *                                  the dimensions of the board
   */
  @Override
  public SlotState getSlotAt(int row, int col) throws IllegalArgumentException {
    if (row >= this.getBoardSize() || col >= this.getBoardSize() || row < 0 || col < 0) {
      throw new IllegalArgumentException("Row or column " +
              "are beyond the dimensions of the board");
    }
    return this.board[row][col];
  }

  /**
   * Get the current score of the game.
   *
   * @return the score
   */
  @Override
  public int getScore() {
    int score = 0;
    for (int i = 0; i < this.getBoardSize(); i++) {
      for (int j = 0; j < this.getBoardSize(); j++) {
        if (this.board[i][j].equals(SlotState.Marble)) {
          score += 1;
        }
      }
    }
    return score;
  }
}
