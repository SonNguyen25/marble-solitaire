package cs3500.marblesolitaire.view;

import java.io.IOException;

import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;
import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelState;

/**
 * Abstract class to reduce code duplications for building the view by abstracting methods such as
 * toString, renderBoard, renderMessage, any related helpers and the constructor.
 */
public class AbstractSolitaireTextView implements MarbleSolitaireView {

  protected final MarbleSolitaireModelState state;
  protected final Appendable out;

  /**
   * Constructor for this class taking in the model of the game.
   *
   * @param model represents the game model.
   */
  public AbstractSolitaireTextView(MarbleSolitaireModel model) {
    if (model == null) {
      throw new IllegalArgumentException("Model is null");
    }
    this.state = model;
    this.out = System.out;
  }

  /**
   * Constructor for this class taking in the state of the game.
   *
   * @param state represents the game state.
   */
  public AbstractSolitaireTextView(MarbleSolitaireModelState state) {
    if (state == null) {
      throw new IllegalArgumentException();
    }
    this.state = state;
    this.out = System.out;
  }

  /**
   * Constructor initiating the view with the board and the user's chosen output.
   *
   * @param state represents the state of the board
   * @param ap    represents the chosen output
   */
  public AbstractSolitaireTextView(MarbleSolitaireModelState state, Appendable ap) {
    if (state == null || ap == null) {
      throw new IllegalArgumentException();
    }
    this.state = state;
    this.out = ap;
  }

  /**
   * Convert the board with State Slot to strings.
   *
   * @return the board in strings
   */
  @Override
  public String toString() {
    String board = "";
    for (int i = 0; i < state.getBoardSize(); i++) {
      for (int j = 0; j < state.getBoardSize(); j++) {
        board += convert(i, j);
        if (j + 1 != state.getBoardSize()) {
          board += " ";
        }
      }
      board = board.stripTrailing(); // clear the extra spaces at the end
      if (i + 1 != state.getBoardSize()) {
        board += "\n";
      }
    }
    return board;
  }

  /**
   * Helper method to convert the Slot State into strings to be displayed on the game board.
   *
   * @param i represents the row position
   * @param j represents the column position
   * @return the converted string.
   */
  protected String convert(int i, int j) {
    switch (state.getSlotAt(i, j)) {
      case Marble:
        return "O";
      case Empty:
        return "_";
      case Invalid:
        return " ";
      default:
        return ""; //If it is not any of those cases above do nothing.
    }
  }

  /**
   * Transmits the state of the marble solitaire board to a specified destination.
   *
   * @throws IOException if any transmission fails
   */
  @Override
  public void renderBoard() throws IOException {
    this.out.append(this.toString());
  }


  /**
   * Can be used to show an arbitrary message,
   * allowing this view to show messages determined by whoever uses it.
   *
   * @param message represents the message to be transmitted
   * @throws IOException if any transmission fails
   */
  @Override
  public void renderMessage(String message) throws IOException {
    if (this.out == null || message == null) {
      throw new IllegalStateException("Null Appendable");
    }
    this.out.append(message);
  }

}
