package cs3500.marblesolitaire.model.hw04;

/**
 * The class that extends the AbstractMarbleSolitaire interface.
 * The class represents the game European Marble Solitaire (an octagon)
 * and its structure are inherited from the abstract class
 * with rows, columns, armthickness and a board.
 * You can set up a move, get current score, build the board, and more from the inherited methods.
 */
public class EuropeanSolitaireModel extends AbstractMarbleSolitaire {

  /**
   * This constructor generates the default board for the game, with an empty middle slot.
   */
  public EuropeanSolitaireModel() {
    super();
  }

  /**
   * A constructor that generates the board.
   * With the given rows and columns with an arm thickness of 3.
   */
  public EuropeanSolitaireModel(int rows, int columns) {
    super(rows, columns);
  }

  /**
   * A constructor with a given armThickness, which will determine its rows and columns.
   */
  public EuropeanSolitaireModel(int armThickness) {
    super(armThickness);
    if (armThickness % 2 == 0) {
      throw new IllegalArgumentException("Arm thickness is not an odd number");
    }
  }

  /**
   * A constructor that generates the game with the given rows, columns, and arm thickness.
   */
  public EuropeanSolitaireModel(int armThickness, int rows, int columns) {
    super(armThickness, rows, columns);
    if (armThickness % 2 == 0) {
      throw new IllegalArgumentException("Arm thickness is not an odd number");
    }
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
    return (row < this.armThickness - 1 && col < this.armThickness - 1 - row)
            || (row < this.armThickness - 1 && col > 2 * this.armThickness - 2 + row)
            || (row > 2 * this.armThickness - 2 && col < row - 2 * this.armThickness - 1 + 3)
            || (row > 2 * this.armThickness - 2 && col > (2 + 3) * (this.armThickness - 1) - row);
  }

}
