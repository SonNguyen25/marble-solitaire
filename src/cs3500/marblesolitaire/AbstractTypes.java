package cs3500.marblesolitaire;

import java.util.HashMap;
import java.util.Map;

/**
 * Abstract class for Types to help reduce code duplication
 * in the main class and other types classes.
 * @param <T> represents the model of any types.
 * @param <V> represents the view of any types.
 */
public abstract class AbstractTypes<T, V> implements Types<T, V> {
  /**
   * Check if user requested the game type.
   *
   * @param arr represents input.
   * @return a boolean to confirm.
   */
  @Override
  public abstract boolean condition(String[] arr);

  /**
   * Produce the correct version of the game for the player.
   * @param args represents input.
   * @return the HashMap contains the game model and the view.
   */
  @Override
  public Map<T, V> action(String[] args) {
    Map<T, V> results = new HashMap<>();
    T game = null;
    if (args.length == 1) {
      game = returnDefaultModel();

    } else {
      switch (args[1]) {
        case "-size":
          if (args.length == 3) {
            game = returnModelOneArgument(Integer.parseInt(args[2]));
          } else if (args[3].equals("-hole")) {
            game = returnModel(Integer.parseInt(args[2]),
                    Integer.parseInt(args[4]),
                    Integer.parseInt(args[5]));
          }
          break;
        case "-hole":
          if (args.length == 4) {
            game = returnModelTwoArguments(Integer.parseInt(args[2]),
                    Integer.parseInt(args[3]));
          } else if (args[4].equals("-size")) {
            game = returnModel(Integer.parseInt(args[5]),
                    Integer.parseInt(args[2]),
                    Integer.parseInt(args[3]));
          }
          break;
        default:
          game = returnDefaultModel();
      }
    }
    results.put(game, returnView(game));
    return results;
  }

  /**
   * Return the default model depends on the type.
   * @return the default model.
   */
  protected abstract T returnDefaultModel();

  /**
   * Return the model depends on the type with 3 arguments.
   * @param armThickness represents the arm thickness
   * @param row represents the row of the empty position
   * @param column represents the column of the empty position
   * @return the model.
   */
  protected abstract T returnModel(int armThickness, int row, int column);

  /**
   * Return the model depends on the type with 1 argument.
   * @param armThickness represents the arm thickness of the model.
   * @return the model.
   */
  protected abstract T returnModelOneArgument(int armThickness);

  /**
   * Return the model depends on the type with 2 arguments.
   * @param row represents the row of empty position.
   * @param column represents the column of empty position.
   * @return the model.
   */
  protected abstract T returnModelTwoArguments(int row, int column);

  /**
   * Return the view depends on the type of the model given.
   * @param model represents the the model.
   * @return the view.
   */
  protected abstract V returnView(T model);
}
