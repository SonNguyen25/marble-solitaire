package cs3500.marblesolitaire.model.hw04;

/**
 * The class that extends the AbstractMarbleSolitaire interface.
 * The class represents the game Triangle Marble Solitaire
 * and its structure, with rows, columns, dimensions and a board. You can set up a move,
 * get current score, build the board, and more from the methods available
 * in this class.
 */
public class TriangleSolitaireModel extends AbstractMarbleSolitaire {

  /**
   * This constructor generates the default board for the game, with an empty slot.
   */
  public TriangleSolitaireModel() {
    this(5, 0, 0);
  }

  /**
   * A constructor that generates the game with the given rows, columns, and dimensions.
   */
  public TriangleSolitaireModel(int dimensions, int rows, int columns) {
    super(dimensions, rows, columns);
  }

  /**
   * A constructor with a given dimensions, which will determine its rows and columns.
   */
  public TriangleSolitaireModel(int dimensions) {
    this(dimensions, 0, 0);
  }

  /**
   * A constructor that generates the board.
   * With the given rows and columns with an arm thickness of 3.
   */
  public TriangleSolitaireModel(int rows, int columns) {
    this(5, rows, columns);
  }

  /**
   * Check for invalid position.
   *
   * @param row represents the row position
   * @param col represents the column position
   * @return whether the position is valid
   */
  @Override
  protected boolean isInvalidPos(int row, int col) {
    return col > row || row > this.armThickness - 1;
  }

  /**
   * Return the size of this board. The size is roughly the longest dimension of a board
   *
   * @return the size as an integer
   */
  @Override
  public int getBoardSize() {
    return this.armThickness;
  }


  /**
   * Generate the board for the game.
   */
  @Override
  protected void makeBoard() {
    for (int i = 0; i < this.armThickness; i++) {
      for (int j = 0; j < this.armThickness; j++) {
        if (j <= i) {
          makeBoardHelper(i, j);
        } else {
          board[i][j] = SlotState.Invalid;
        }
      }
    }
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
  @Override
  protected boolean checkOutsideOfBoard(int fromRow, int fromCol, int toRow, int toCol) {
    return !(toRow < 0 || toRow >= this.armThickness || toCol < 0
            || toCol >= this.board[toRow].length
            || fromRow < 0 || fromRow >= this.armThickness || fromCol < 0
            || fromCol >= this.board[toRow].length);
  }

  /**
   * Check if the move is on the correct path (diagonally and same row).
   *
   * @param fromRow represents the row of the current location
   * @param fromCol represents the column of the current location
   * @param toRow   represents the row of the target location
   * @param toCol   represents the column of the target location
   * @return a boolean as a result.
   */
  @Override
  protected boolean checkOnRightPath(int fromRow, int fromCol, int toRow, int toCol) {
    if (fromRow != toRow) {
      return (Math.abs(fromRow - toRow) == 2 && Math.abs(fromCol - toCol) == 2);
    }
    else {
      return fromRow == toRow;
    }
  }

  /**
   * Check if there is any moves left.
   *
   * @param i represents the row
   * @param j represents the column
   * @return the answer to is there any moves left.
   */
  @Override
  protected boolean anyMoveLeft(int i, int j) {
    return (checkValidMove(i, j, i + 2, j)
            || checkValidMove(i, j, i - 2, j)
            || checkValidMove(i, j, i, j - 2)
            || checkValidMove(i, j, i, j + 2)
            || checkValidMove(i, j, i + 2, j + 2)
            || checkValidMove(i, j, i - 2, j + 2)
            || checkValidMove(i, j, i + 2, j - 2)
            || checkValidMove(i, j, i - 2, j - 2));
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
      for (int j = 0; j <= i; j++) {
        if (this.board[i][j].equals(SlotState.Marble)) {
          score += 1;
        }
      }
    }
    return score;
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
      for (int j = 0; j <= i; j++) {
        if (this.board[i][j] == SlotState.Marble) {
          if (anyMoveLeft(i, j)) {
            return false;
          }
        }
      }
    }
    return true;
  }


}
