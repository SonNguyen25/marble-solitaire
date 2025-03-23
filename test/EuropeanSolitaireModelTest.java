import org.junit.Test;

import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;
import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelState;
import cs3500.marblesolitaire.model.hw04.EuropeanSolitaireModel;
import cs3500.marblesolitaire.view.MarbleSolitaireTextView;
import cs3500.marblesolitaire.view.MarbleSolitaireView;

import static org.junit.Assert.assertEquals;

/**
 * Class for testing related methods in class EuropeanSolitaireModel.
 * Also include the testing of the toString method that uses the model of EuropeanSolitaireModel.
 */
public class EuropeanSolitaireModelTest {

  /**
   * Testing toString method.
   */
  @Test
  public void testToString() {
    MarbleSolitaireModelState m = new EuropeanSolitaireModel();
    MarbleSolitaireView defView = new MarbleSolitaireTextView(m);
    String def = "    O O O\n" +
            "  O O O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "  O O O O O\n" +
            "    O O O";
    assertEquals(def, defView.toString());

    MarbleSolitaireModelState armThick = new EuropeanSolitaireModel(5);
    MarbleSolitaireView armThickView = new MarbleSolitaireTextView(armThick);
    String armThickString = "        O O O O O"
            + "\n      O O O O O O O"
            + "\n    O O O O O O O O O"
            + "\n  O O O O O O O O O O O"
            + "\nO O O O O O O O O O O O O"
            + "\nO O O O O O O O O O O O O"
            + "\nO O O O O O _ O O O O O O"
            + "\nO O O O O O O O O O O O O"
            + "\nO O O O O O O O O O O O O"
            + "\n  O O O O O O O O O O O"
            + "\n    O O O O O O O O O"
            + "\n      O O O O O O O"
            + "\n        O O O O O";

    assertEquals(armThickString, armThickView.toString());

    MarbleSolitaireModelState empty = new EuropeanSolitaireModel(5, 5);
    MarbleSolitaireView emptyView = new MarbleSolitaireTextView(empty);
    String emptySlot = "    O O O\n" +
            "  O O O O O\n" +
            "O O O O O O O\n" +
            "O O O O O O O\n" +
            "O O O O O O O\n" +
            "  O O O O _\n" +
            "    O O O";
    assertEquals(emptySlot, emptyView.toString());

    MarbleSolitaireModelState full = new EuropeanSolitaireModel(3, 0, 3);
    MarbleSolitaireView fullView = new MarbleSolitaireTextView(full);
    String fullString = "    O _ O"
            + "\n  O O O O O"
            + "\nO O O O O O O"
            + "\nO O O O O O O"
            + "\nO O O O O O O"
            + "\n  O O O O O"
            + "\n    O O O";
    assertEquals(fullString, fullView.toString());
  }

  /**
   * Test move into corners (check for corners validity).
   */
  @Test
  public void testMoveCorners() {
    MarbleSolitaireModel game = new EuropeanSolitaireModel(1, 1);
    MarbleSolitaireView view = new MarbleSolitaireTextView(game);
    game.move(1, 3, 1, 1);
    assertEquals("    O O O\n" +
            "  O _ _ O O\n" +
            "O O O O O O O\n" +
            "O O O O O O O\n" +
            "O O O O O O O\n" +
            "  O O O O O\n" +
            "    O O O", view.toString());

    MarbleSolitaireModel game2 = new EuropeanSolitaireModel(1, 5);
    MarbleSolitaireView view2 = new MarbleSolitaireTextView(game2);
    game2.move(1, 3, 1, 5);
    assertEquals("    O O O\n" +
            "  O O _ _ O\n" +
            "O O O O O O O\n" +
            "O O O O O O O\n" +
            "O O O O O O O\n" +
            "  O O O O O\n" +
            "    O O O", view2.toString());

    MarbleSolitaireModel game3 = new EuropeanSolitaireModel(5, 1);
    game3.move(5, 3, 5, 1);
    MarbleSolitaireView view3 = new MarbleSolitaireTextView(game3);
    assertEquals("    O O O\n" +
            "  O O O O O\n" +
            "O O O O O O O\n" +
            "O O O O O O O\n" +
            "O O O O O O O\n" +
            "  O _ _ O O\n" +
            "    O O O", view3.toString());

    MarbleSolitaireModel game4 = new EuropeanSolitaireModel(5, 5);
    game4.move(5, 3, 5, 5);
    MarbleSolitaireView view4 = new MarbleSolitaireTextView(game4);
    assertEquals("    O O O\n" +
            "  O O O O O\n" +
            "O O O O O O O\n" +
            "O O O O O O O\n" +
            "O O O O O O O\n" +
            "  O O _ _ O\n" +
            "    O O O", view4.toString());
  }


  /**
   * Testing move started from empty position.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testEmptyStart() {
    MarbleSolitaireModel game = new EuropeanSolitaireModel();
    game.move(3, 3, 1, 3);
  }

  /**
   * Test no middle marble between move.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testNoMiddle() {
    MarbleSolitaireModel game = new EuropeanSolitaireModel();
    game.move(2, 3, 4, 3);
  }

  /**
   * Test moving out of board.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testOutofBoard() {
    MarbleSolitaireModel game = new EuropeanSolitaireModel();
    game.move(3, 5, 3, 7);
  }

  /**
   * Test invalid ending position (there's a marble).
   */
  @Test(expected = IllegalArgumentException.class)
  public void testMarbleAtTargetPosition() {
    MarbleSolitaireModel game = new EuropeanSolitaireModel();
    game.move(6, 2, 3, 4);
  }

  /**
   * Test moving diagonally.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testDiagonal() {
    MarbleSolitaireModel game = new EuropeanSolitaireModel();
    game.move(1, 1, 3, 3);
  }

  /**
   * Test even armThickness value.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testEvenArmThickness() {
    MarbleSolitaireModel test = new EuropeanSolitaireModel(4);
  }

  /**
   * Test invalid empty positions for constructor.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidEmpty() {
    new EuropeanSolitaireModel(0, 1);
  }

  /**
   * Test negative empty positions for constructor.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidEmpty1() {
    new EuropeanSolitaireModel(-1, -1);
  }

  /**
   * Test invalid armThickness.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidDimension() {
    new EuropeanSolitaireModel(-1);
  }

  /**
   * Test all 3 invalid arguments.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidArguments() {
    new EuropeanSolitaireModel(-5, 0, 0);
  }

  /**
   * Test constructor with 3 arguments but invalid armThickness.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidArguments1() {
    new EuropeanSolitaireModel(-5, 3, 3);
  }

  /**
   * Test constructor with 3 arguments but invalid empty position.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidArguments2() {
    new EuropeanSolitaireModel(5, 0, -1);
  }

  /**
   * Test move method.
   */
  @Test
  public void testMove() {
    //Right
    MarbleSolitaireModel game = new EuropeanSolitaireModel();
    MarbleSolitaireView view = new MarbleSolitaireTextView(game);
    game.move(3, 1, 3, 3);
    assertEquals("    O O O\n" +
            "  O O O O O\n" +
            "O O O O O O O\n" +
            "O _ _ O O O O\n" +
            "O O O O O O O\n" +
            "  O O O O O\n" +
            "    O O O", view.toString());
    //Up
    game.move(5, 1, 3, 1);
    assertEquals(
            "    O O O\n" +
            "  O O O O O\n" +
            "O O O O O O O\n" +
            "O O _ O O O O\n" +
            "O _ O O O O O\n" +
            "  _ O O O O\n" +
            "    O O O", view.toString());
    //Left
    game.move(3, 4, 3, 2);
    assertEquals("    O O O\n" +
            "  O O O O O\n" +
            "O O O O O O O\n" +
            "O O O _ _ O O\n" +
            "O _ O O O O O\n" +
            "  _ O O O O\n" +
            "    O O O", view.toString());
    //Down
    game.move(2, 1, 4, 1);
    assertEquals("    O O O\n" +
            "  O O O O O\n" +
            "O _ O O O O O\n" +
            "O _ O _ _ O O\n" +
            "O O O O O O O\n" +
            "  _ O O O O\n" +
            "    O O O", view.toString());
  }

  /**
   * Test getScore method.
   */
  @Test
  public void testGetScore() {
    MarbleSolitaireModel game = new EuropeanSolitaireModel();
    assertEquals(36, game.getScore());
    game.move(3, 1, 3, 3);
    assertEquals(35, game.getScore());

  }

  /**
   * Test getSlotAt method.
   */
  @Test
  public void testGetSlot() {
    MarbleSolitaireModel game = new EuropeanSolitaireModel();
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, game.getSlotAt(3, 3));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, game.getSlotAt(3, 4));
    assertEquals(MarbleSolitaireModelState.SlotState.Invalid, game.getSlotAt(0, 0));
  }

  /**
   * Test getBoardSize method.
   */
  @Test
  public void testGetBoardSize() {
    MarbleSolitaireModel game = new EuropeanSolitaireModel();
    assertEquals(7, game.getBoardSize());

    MarbleSolitaireModel game1 = new EuropeanSolitaireModel(5);
    assertEquals(13, game1.getBoardSize());
  }

  /**
   * Test isGameOver method.
   */
  @Test
  public void testIsGameOver() {
    MarbleSolitaireModel gameModel = new EuropeanSolitaireModel();
    MarbleSolitaireView view = new MarbleSolitaireTextView(gameModel);
    String output = "    O O O"
            + "\n  O O O O O"
            + "\nO O O O O O O"
            + "\nO O O _ O O O"
            + "\nO O O O O O O"
            + "\n  O O O O O"
            + "\n    O O O";
    assertEquals(36, gameModel.getScore());
    assertEquals(false, gameModel.isGameOver());
    assertEquals(output, view.toString());

    gameModel.move(1, 3, 3, 3);
    output = "    O O O"
            + "\n  O O _ O O"
            + "\nO O O _ O O O"
            + "\nO O O O O O O"
            + "\nO O O O O O O"
            + "\n  O O O O O"
            + "\n    O O O";
    assertEquals(35, gameModel.getScore());
    assertEquals(output, view.toString());
    assertEquals(false, gameModel.isGameOver());

    gameModel.move(2, 1, 2, 3);
    output = "    O O O"
            + "\n  O O _ O O"
            + "\nO _ _ O O O O"
            + "\nO O O O O O O"
            + "\nO O O O O O O"
            + "\n  O O O O O"
            + "\n    O O O";
    assertEquals(34, gameModel.getScore());
    assertEquals(output, view.toString());
    assertEquals(false, gameModel.isGameOver());

    gameModel.move(2, 4, 2, 2);
    output = "    O O O"
            + "\n  O O _ O O"
            + "\nO _ O _ _ O O"
            + "\nO O O O O O O"
            + "\nO O O O O O O"
            + "\n  O O O O O"
            + "\n    O O O";
    assertEquals(33, gameModel.getScore());
    assertEquals(output, view.toString());
    assertEquals(false, gameModel.isGameOver());

    gameModel.move(0, 4, 2, 4);
    output = "    O O _"
            + "\n  O O _ _ O"
            + "\nO _ O _ O O O"
            + "\nO O O O O O O"
            + "\nO O O O O O O"
            + "\n  O O O O O"
            + "\n    O O O";
    assertEquals(32, gameModel.getScore());
    assertEquals(output, view.toString());
    assertEquals(false, gameModel.isGameOver());

    gameModel.move(0, 2, 0, 4);
    output = "    _ _ O"
            + "\n  O O _ _ O"
            + "\nO _ O _ O O O"
            + "\nO O O O O O O"
            + "\nO O O O O O O"
            + "\n  O O O O O"
            + "\n    O O O";
    assertEquals(31, gameModel.getScore());
    assertEquals(output, view.toString());
    assertEquals(false, gameModel.isGameOver());

    gameModel.move(2, 2, 0, 2);
    output = "    O _ O"
            + "\n  O _ _ _ O"
            + "\nO _ _ _ O O O"
            + "\nO O O O O O O"
            + "\nO O O O O O O"
            + "\n  O O O O O"
            + "\n    O O O";
    assertEquals(30, gameModel.getScore());
    assertEquals(output, view.toString());
    assertEquals(false, gameModel.isGameOver());

    gameModel.move(3, 4, 1, 4);
    output = "    O _ O"
            + "\n  O _ _ O O"
            + "\nO _ _ _ _ O O"
            + "\nO O O O _ O O"
            + "\nO O O O O O O"
            + "\n  O O O O O"
            + "\n    O O O";
    assertEquals(29, gameModel.getScore());
    assertEquals(output, view.toString());
    assertEquals(false, gameModel.isGameOver());

    gameModel.move(4, 2, 2, 2);
    output = "    O _ O"
            + "\n  O _ _ O O"
            + "\nO _ O _ _ O O"
            + "\nO O _ O _ O O"
            + "\nO O _ O O O O"
            + "\n  O O O O O"
            + "\n    O O O";
    assertEquals(28, gameModel.getScore());
    assertEquals(output, view.toString());
    assertEquals(false, gameModel.isGameOver());

    gameModel.move(4, 3, 2, 3);
    output = "    O _ O"
            + "\n  O _ _ O O"
            + "\nO _ O O _ O O"
            + "\nO O _ _ _ O O"
            + "\nO O _ _ O O O"
            + "\n  O O O O O"
            + "\n    O O O";
    assertEquals(27, gameModel.getScore());
    assertEquals(output, view.toString());
    assertEquals(false, gameModel.isGameOver());

    gameModel.move(2, 2, 2, 4);
    output = "    O _ O"
            + "\n  O _ _ O O"
            + "\nO _ _ _ O O O"
            + "\nO O _ _ _ O O"
            + "\nO O _ _ O O O"
            + "\n  O O O O O"
            + "\n    O O O";
    assertEquals(26, gameModel.getScore());
    assertEquals(output, view.toString());
    assertEquals(false, gameModel.isGameOver());

    gameModel.move(1, 4, 3, 4);
    output = "    O _ O"
            + "\n  O _ _ _ O"
            + "\nO _ _ _ _ O O"
            + "\nO O _ _ O O O"
            + "\nO O _ _ O O O"
            + "\n  O O O O O"
            + "\n    O O O";
    assertEquals(25, gameModel.getScore());
    assertEquals(output, view.toString());
    assertEquals(false, gameModel.isGameOver());

    gameModel.move(4, 4, 2, 4);
    output = "    O _ O"
            + "\n  O _ _ _ O"
            + "\nO _ _ _ O O O"
            + "\nO O _ _ _ O O"
            + "\nO O _ _ _ O O"
            + "\n  O O O O O"
            + "\n    O O O";
    assertEquals(24, gameModel.getScore());
    assertEquals(output, view.toString());
    assertEquals(false, gameModel.isGameOver());

    gameModel.move(4, 1, 2, 1);
    output = "    O _ O"
            + "\n  O _ _ _ O"
            + "\nO O _ _ O O O"
            + "\nO _ _ _ _ O O"
            + "\nO _ _ _ _ O O"
            + "\n  O O O O O"
            + "\n    O O O";
    assertEquals(23, gameModel.getScore());
    assertEquals(output, view.toString());
    assertEquals(false, gameModel.isGameOver());

    gameModel.move(2, 0, 2, 2);
    output = "    O _ O"
            + "\n  O _ _ _ O"
            + "\n_ _ O _ O O O"
            + "\nO _ _ _ _ O O"
            + "\nO _ _ _ _ O O"
            + "\n  O O O O O"
            + "\n    O O O";
    assertEquals(22, gameModel.getScore());
    assertEquals(output, view.toString());
    assertEquals(false, gameModel.isGameOver());

    gameModel.move(2, 5, 2, 3);
    output = "    O _ O"
            + "\n  O _ _ _ O"
            + "\n_ _ O O _ _ O"
            + "\nO _ _ _ _ O O"
            + "\nO _ _ _ _ O O"
            + "\n  O O O O O"
            + "\n    O O O";
    assertEquals(21, gameModel.getScore());
    assertEquals(output, view.toString());
    assertEquals(false, gameModel.isGameOver());

    gameModel.move(4, 5, 2, 5);
    output = "    O _ O"
            + "\n  O _ _ _ O"
            + "\n_ _ O O _ O O"
            + "\nO _ _ _ _ _ O"
            + "\nO _ _ _ _ _ O"
            + "\n  O O O O O"
            + "\n    O O O";
    assertEquals(20, gameModel.getScore());
    assertEquals(output, view.toString());
    assertEquals(false, gameModel.isGameOver());

    gameModel.move(2, 6, 2, 4);
    output = "    O _ O"
            + "\n  O _ _ _ O"
            + "\n_ _ O O O _ _"
            + "\nO _ _ _ _ _ O"
            + "\nO _ _ _ _ _ O"
            + "\n  O O O O O"
            + "\n    O O O";
    assertEquals(19, gameModel.getScore());
    assertEquals(output, view.toString());
    assertEquals(false, gameModel.isGameOver());

    gameModel.move(2, 3, 2, 1);
    output = "    O _ O"
            + "\n  O _ _ _ O"
            + "\n_ O _ _ O _ _"
            + "\nO _ _ _ _ _ O"
            + "\nO _ _ _ _ _ O"
            + "\n  O O O O O"
            + "\n    O O O";
    assertEquals(18, gameModel.getScore());
    assertEquals(output, view.toString());
    assertEquals(false, gameModel.isGameOver());

    gameModel.move(4, 0, 2, 0);
    output = "    O _ O"
            + "\n  O _ _ _ O"
            + "\nO O _ _ O _ _"
            + "\n_ _ _ _ _ _ O"
            + "\n_ _ _ _ _ _ O"
            + "\n  O O O O O"
            + "\n    O O O";
    assertEquals(17, gameModel.getScore());
    assertEquals(output, view.toString());
    assertEquals(false, gameModel.isGameOver());

    gameModel.move(2, 0, 2, 2);
    output = "    O _ O"
            + "\n  O _ _ _ O"
            + "\n_ _ O _ O _ _"
            + "\n_ _ _ _ _ _ O"
            + "\n_ _ _ _ _ _ O"
            + "\n  O O O O O"
            + "\n    O O O";
    assertEquals(16, gameModel.getScore());
    assertEquals(output, view.toString());
    assertEquals(false, gameModel.isGameOver());

    gameModel.move(4, 6, 2, 6);
    output = "    O _ O"
            + "\n  O _ _ _ O"
            + "\n_ _ O _ O _ O"
            + "\n_ _ _ _ _ _ _"
            + "\n_ _ _ _ _ _ _"
            + "\n  O O O O O"
            + "\n    O O O";
    assertEquals(15, gameModel.getScore());
    assertEquals(output, view.toString());
    assertEquals(false, gameModel.isGameOver());

    gameModel.move(6, 2, 4, 2);
    output = "    O _ O"
            + "\n  O _ _ _ O"
            + "\n_ _ O _ O _ O"
            + "\n_ _ _ _ _ _ _"
            + "\n_ _ O _ _ _ _"
            + "\n  O _ O O O"
            + "\n    _ O O";
    assertEquals(14, gameModel.getScore());
    assertEquals(output, view.toString());
    assertEquals(false, gameModel.isGameOver());

    gameModel.move(5, 4, 5, 2);
    output = "    O _ O"
            + "\n  O _ _ _ O"
            + "\n_ _ O _ O _ O"
            + "\n_ _ _ _ _ _ _"
            + "\n_ _ O _ _ _ _"
            + "\n  O O _ _ O"
            + "\n    _ O O";
    assertEquals(13, gameModel.getScore());
    assertEquals(output, view.toString());
    assertEquals(false, gameModel.isGameOver());

    gameModel.move(4, 2, 6, 2);
    output = "    O _ O"
            + "\n  O _ _ _ O"
            + "\n_ _ O _ O _ O"
            + "\n_ _ _ _ _ _ _"
            + "\n_ _ _ _ _ _ _"
            + "\n  O _ _ _ O"
            + "\n    O O O";
    assertEquals(12, gameModel.getScore());
    assertEquals(output, view.toString());
    assertEquals(true, gameModel.isGameOver());
  }



}

