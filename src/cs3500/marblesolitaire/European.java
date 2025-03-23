package cs3500.marblesolitaire;

import cs3500.marblesolitaire.model.hw04.EuropeanSolitaireModel;
import cs3500.marblesolitaire.view.MarbleSolitaireTextView;

/**
 * Class European represents the triangle version of the game, contains methods to check if the
 * user requested the version and the action to implement the version of the game.
 */
public class European extends AbstractTypes<EuropeanSolitaireModel, MarbleSolitaireTextView> {

  /**
   * Check if user requested the game type.
   * @param arr represents input.
   * @return a boolean to confirm.
   */
  @Override
  public boolean condition(String[] arr) {
    return arr[0].equals("european");
  }

  /**
   * Return the default model depends on the type.
   *
   * @return the default model.
   */
  @Override
  protected EuropeanSolitaireModel returnDefaultModel() {
    return new EuropeanSolitaireModel();
  }

  /**
   * Return the model depends on the type with 3 arguments.
   *
   * @param armThickness represents the arm thickness
   * @param row          represents the row of the empty position
   * @param column       represents the column of the empty position
   * @return the model.
   */
  @Override
  protected EuropeanSolitaireModel returnModel(int armThickness, int row, int column) {
    return new EuropeanSolitaireModel(armThickness, row, column);
  }

  /**
   * Return the model depends on the type with 1 argument.
   *
   * @param armThickness represents the arm thickness of the model.
   * @return the model.
   */
  @Override
  protected EuropeanSolitaireModel returnModelOneArgument(int armThickness) {
    return new EuropeanSolitaireModel(armThickness);
  }

  /**
   * Return the model depends on the type with 2 arguments.
   *
   * @param row    represents the row of empty position.
   * @param column represents the column of empty position.
   * @return the model.
   */
  @Override
  protected EuropeanSolitaireModel returnModelTwoArguments(int row, int column) {
    return new EuropeanSolitaireModel(row, column);
  }

  /**
   * Return the view depends on the type of the model given.
   *
   * @param model represents the the model.
   * @return the view.
   */
  @Override
  protected MarbleSolitaireTextView returnView(EuropeanSolitaireModel model) {
    return new MarbleSolitaireTextView(model);
  }

}
