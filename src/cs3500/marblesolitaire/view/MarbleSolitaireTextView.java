package cs3500.marblesolitaire.view;

import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;
import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelState;

/**
 * Represents the view of the game.
 * With functions to output the view in Strings and render them to the chosen outputs if wanted.
 */
public class MarbleSolitaireTextView extends AbstractSolitaireTextView {

  /**
   * Constructor for this class taking in the model of the game.
   *
   * @param model represents the game model.
   */
  public MarbleSolitaireTextView(MarbleSolitaireModel model) {
    super(model);
  }

  /**
   * Constructor for this class taking in the state of the game.
   *
   * @param state represents the game state.
   */
  public MarbleSolitaireTextView(MarbleSolitaireModelState state) {
    super(state);
  }

  /**
   * Constructor initiating the view with the board and the user's chosen output.
   * @param state represents the state of the board
   * @param ap represents the chosen output
   */
  public MarbleSolitaireTextView(MarbleSolitaireModelState state, Appendable ap) {
    super(state, ap);
  }

}
