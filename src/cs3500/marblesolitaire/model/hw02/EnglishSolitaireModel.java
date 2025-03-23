package cs3500.marblesolitaire.model.hw02;


import cs3500.marblesolitaire.model.hw04.AbstractMarbleSolitaire;
//PLANNING (from lectures):
//how to represent a 2 dimensional board.
//1. what data structure;
//    - a two-dimensional array,
//    - one dimensional array and do the math
//    - a cell object that stores its own neighbors
//    - hashmap ( similar to one dimensional array )
//    - two-dimensional arraylist/linked list
// 2. what about the marbles

/**
 * The class that extends the AbstractMarbleSolitaire interface.
 * The class represents the game English Marble Solitaire
 * and its structure are inherited from the abstract class
 * with rows, columns, armthickness and a board.
 * You can set up a move, get current score, build the board, and more from the inherited methods.
 */
public class EnglishSolitaireModel extends AbstractMarbleSolitaire {

  /**
   * This constructor generates the default board for the game, with an empty middle slot.
   */
  public EnglishSolitaireModel() {
    super();
  }

  /**
   * A constructor that generates the board.
   * With the given rows and columns with an arm thickness of 3.
   */
  public EnglishSolitaireModel(int rows, int columns) {
    super(rows, columns);
  }

  /**
   * A constructor with a given armThickness, which will determine its rows and columns.
   */
  public EnglishSolitaireModel(int armThickness) {
    super(armThickness);
    if (armThickness % 2 == 0) {
      throw new IllegalArgumentException("Arm thickness is not an odd number");
    }
  }

  /**
   * A constructor that generates the game with the given rows, columns, and arm thickness.
   */
  public EnglishSolitaireModel(int armThickness, int rows, int columns) {
    super(armThickness, rows, columns);
    if (armThickness % 2 == 0) {
      throw new IllegalArgumentException("Arm thickness is not an odd number");
    }
  }

}
