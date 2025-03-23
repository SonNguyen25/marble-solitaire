package cs3500.marblesolitaire;


import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import cs3500.marblesolitaire.controller.MarbleSolitaireController;
import cs3500.marblesolitaire.controller.MarbleSolitaireControllerImpl;
import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;
import cs3500.marblesolitaire.view.MarbleSolitaireView;

/**
 * Class that allows the users to start the game Marble Solitaire.
 */
public final class MarbleSolitaire {

  /**
   * The main method of the game, accepts command line arguments.
   * @param args represents user inputs.
   */
  public static void main(String[] args) {
    MarbleSolitaireModel model = null;
    MarbleSolitaireView view = null;

    Types european = new European();
    Types english = new English();
    Types triangle = new Triangle();

    //Add types interface and classes for easier implementation of other game modes (if needed).
    //Instead of writing a long new switch case, if there are any new models, you can just add
    //a new class that extends AbstractTypes and fill in some short methods.

    List<Types> gameTypes = new ArrayList<Types>();
    gameTypes.add(english);
    gameTypes.add(european);
    gameTypes.add(triangle);

    for (Types t : gameTypes) {
      if (t.condition(args)) {
        Map<MarbleSolitaireModel, MarbleSolitaireView> modelView = t.action(args);
        for (Map.Entry<MarbleSolitaireModel, MarbleSolitaireView> entry : modelView.entrySet()) {
          model = entry.getKey();
          view = entry.getValue();
        }

      }
    }
    Readable rd = new InputStreamReader(System.in);
    MarbleSolitaireController controller = new MarbleSolitaireControllerImpl(model, view, rd);
    controller.playGame();
  }
}