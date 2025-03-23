package cs3500.marblesolitaire.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;
import cs3500.marblesolitaire.view.MarbleSolitaireView;

/**
 * Represents an implementation of a controller for MarbleSolitaire.
 * Contains methods such as how to play the game and any related methods.
 */
public class MarbleSolitaireControllerImpl implements MarbleSolitaireController {
  private final Readable in;
  private final MarbleSolitaireModel model;
  private final MarbleSolitaireView view;
  //contains the moves made by users
  private final List<Integer> moves;

  /**
   * Constructor of a controller for MarbleSolitaire, required to be provided with the model of the
   * game, the view of the game, and the input that the user will play the game with.
   *
   * @param model represents the model user choose to play
   * @param view  represents the view set up for the user
   * @param in    represents the input source
   */
  public MarbleSolitaireControllerImpl(MarbleSolitaireModel model,
                                       MarbleSolitaireView view, Readable in) {
    if (model == null || view == null || in == null) {
      throw new IllegalArgumentException("Null arguments!");
    }
    this.model = model;
    this.view = view;
    this.in = in;
    this.moves = new ArrayList<Integer>();
  }


  /**
   * Play a new game of Marble Solitaire.
   *
   * @throws IllegalStateException only if the controller is unable
   *                               to successfully read input or transmit output
   */
  @Override
  public void playGame() throws IllegalStateException {
    Scanner input = new Scanner(this.in);
    if (input == null) {
      throw new IllegalArgumentException();
    }

    //Write board and score
    try {
      this.view.renderBoard();
      this.view.renderMessage("\nScore: " + this.model.getScore());
    } catch (IOException e) {
      throw new IllegalStateException("Could not write, there's an error with input/output!");
    }
    this.getNextMove(input);

    if (model.isGameOver()) {
      try {
        this.view.renderMessage("\nGame over!\n");
        this.view.renderBoard();
        this.view.renderMessage("\nScore: " + this.model.getScore());
      } catch (IOException e) {
        throw new IllegalStateException("Could not write, there's an error with input/output!");
      }
    }
  }


  /**
   * Get the next move from the inputs.
   *
   * @param input represent the input of moves.
   */
  private void getNextMove(Scanner input) {
    while (!model.isGameOver()) {
      if (!input.hasNext()) {
        throw new IllegalStateException("No Inputs or Ending");
      }
      String curValue = input.next();
      if (curValue.equals("q") || curValue.equals("Q")) {
        try {
          this.view.renderMessage("\nGame quit!\nState of game when quit:\n");
          this.view.renderBoard();
          this.view.renderMessage("\nScore: " + model.getScore());
        } catch (IOException e) {
          throw new IllegalStateException("Could not write, there's an error with input/output!");
        }
        break;
      } else {
        this.checkIntegerInput(curValue);
        this.applyMove();
      }
    }
  }

  /**
   * Check if moves are integers.
   *
   * @param curValue represent the moves in String
   */
  private void checkIntegerInput(String curValue) {
    try {
      this.moves.add(Integer.parseInt(curValue));
    } catch (IllegalArgumentException e) {
      try {
        view.renderMessage("\nInvalid move. Play again.");
      } catch (IOException a) {
        throw new IllegalStateException("Could not write, there's an error with input/output!");
      }
    }
  }


  /**
   * Apply the move into the game.
   */
  private void applyMove() {
    while (this.moves.size() >= 4) {
      try {
        this.model.move(this.moves.get(0) - 1, this.moves.get(1) - 1,
                this.moves.get(2) - 1, this.moves.get(3) - 1);
      } catch (IllegalArgumentException e) {
        try {
          view.renderMessage("\nInvalid move. Play again.");
        } catch (IOException a) {
          throw new IllegalStateException("Could not write, there's an error with input/output!");
        }
      }
      for (int i = 0; i < 4; i++) {
        this.moves.remove(0);
      }
      try {
        this.view.renderMessage("\n");
        this.view.renderBoard();
        this.view.renderMessage("\nScore: " + model.getScore());
      } catch (IOException e) {
        throw new IllegalStateException("Could not write, there's an error with input/output!");
      }
    }
  }
}


