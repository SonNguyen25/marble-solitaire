package cs3500.marblesolitaire;

import java.util.Map;


/**
 * Interface represents the types of the game, make it easier and more clean
 * to implement another type in the main.
 */
public interface Types<T, V> {

  /**
   * Check if user requested the game type.
   * @param arr represents input.
   * @return a boolean to confirm.
   */
  boolean condition(String[] arr);

  /**
   * Produce the correct version of the game for the player.
   * @param args represents input.
   * @return the HashMap contains the game model and the view.
   */
  Map<T, V> action(String[] args);
}
