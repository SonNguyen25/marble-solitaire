import java.io.IOException;

import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;

/**
 * This class is created for testing purposes.
 * Specifically, to test inputs got delivered correctly.
 */
public class MockModel implements MarbleSolitaireModel {
  private final Appendable ap;

  /**
   * Constructor for a mock model used for testing.
   * @param ap represents the chosen output.
   */
  public MockModel(Appendable ap) {
    if (ap == null) {
      throw new IllegalArgumentException();
    }
    this.ap = ap;
  }

  /**
   * Output the info in the appendable.
   * @return info in the appendable as a string.
   */
  @Override
  public String toString() {
    return this.ap.toString();
  }

  /**
   * Append the input received as a specific format for testing.
   * @param fromRow the row number of the position to be moved from
   *                (starts at 0)
   * @param fromCol the column number of the position to be moved from
   *                (starts at 0)
   * @param toRow   the row number of the position to be moved to
   *                (starts at 0)
   * @param toCol   the column number of the position to be moved to
   *                (starts at 0)
   * @throws IllegalArgumentException if moves invalid
   */
  @Override
  public void move(int fromRow, int fromCol, int toRow, int toCol) throws IllegalArgumentException {
    try {
      ap.append(String.format("%d %d %d %d", fromRow, fromCol, toRow, toCol));
    } catch (IOException e) {
      throw new IllegalStateException();
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
    return false;
  }

  /**
   * Return the size of this board. The size is roughly the longest dimension of a board
   *
   * @return the size as an integer
   */
  @Override
  public int getBoardSize() {
    return 0;
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
    return null;
  }

  /**
   * Return the number of marbles currently on the board.
   *
   * @return the number of marbles currently on the board
   */
  @Override
  public int getScore() {
    return 0;
  }
}
