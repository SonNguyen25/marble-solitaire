package cs3500.marblesolitaire.view;


import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;
import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelState;

/**
 * Represents the view of the game of Triangle Marble Solitaire.
 * With functions to output the view in Strings and render them to the chosen outputs if wanted.
 */
public class TriangleSolitaireTextView extends AbstractSolitaireTextView {

  /**
   * Constructor for this class taking in the model of the game.
   *
   * @param model represents the game model.
   */
  public TriangleSolitaireTextView(MarbleSolitaireModel model) {
    super(model);
  }

  /**
   * Constructor for this class taking in the state of the game.
   *
   * @param state represents the game state.
   */
  public TriangleSolitaireTextView(MarbleSolitaireModelState state) {
    super(state);
  }

  /**
   * Constructor initiating the view with the board and the user's chosen output.
   *
   * @param state represents the state of the board
   * @param ap    represents the chosen output
   */
  public TriangleSolitaireTextView(MarbleSolitaireModelState state, Appendable ap) {
    super(state, ap);
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
      for (int k = 0; k < state.getBoardSize() - i - 1; k++) {
        board += " ";
      }
      for (int j = 0; j <= i; j++) {
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
}

