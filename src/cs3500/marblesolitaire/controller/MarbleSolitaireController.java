package cs3500.marblesolitaire.controller;

/**
 * Represent a controller for a game of Marble Solitaire.
 */
public interface MarbleSolitaireController {


  /**
   * Play a new game of Marble Solitaire.
   * @throws IllegalStateException only if the controller is unable
   *         to successfully read input or transmit output
   */
  void playGame() throws IllegalStateException;
}
