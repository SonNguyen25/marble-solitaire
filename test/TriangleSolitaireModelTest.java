import org.junit.Test;

import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;
import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelState;
import cs3500.marblesolitaire.model.hw04.TriangleSolitaireModel;
import cs3500.marblesolitaire.view.MarbleSolitaireView;
import cs3500.marblesolitaire.view.TriangleSolitaireTextView;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Class for testing related methods in class TriangleSolitaireModel.
 */
public class TriangleSolitaireModelTest {

  /**
   * Test invalid empty positions.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidEmpty() {
    new TriangleSolitaireModel(0, 1);
  }

  /**
   * Test negative empty positions.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidEmpty1() {
    new TriangleSolitaireModel(-1, -1);
  }

  /**
   * Test invalid dimension.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidDimension() {
    new TriangleSolitaireModel(-1);
  }

  /**
   * Test all 3 invalid arguments.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidArguments() {
    new TriangleSolitaireModel(-5, 5, 6);
  }

  /**
   * Test consutrutor with 3 arguments but wrong dimension.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidArguments1() {
    new TriangleSolitaireModel(-5, 0, 0);
  }

  /**
   * Test constructor with 3 arguments but wrong empty position.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidArguments2() {
    new TriangleSolitaireModel(5, 0, -1);
  }

  /**
   * Test all valid moves work.
   */
  @Test
  public void testMove() {
    MarbleSolitaireModel board = new TriangleSolitaireModel(6, 0, 0);
    MarbleSolitaireView view = new TriangleSolitaireTextView(board);
    //test move diagonally up from right to left.
    board.move(2, 2, 0, 0);
    String after = "     O"
            + "\n    O _"
            + "\n   O O _"
            + "\n  O O O O"
            + "\n O O O O O"
            + "\nO O O O O O";
    assertEquals(after, view.toString());

    //move diagonally up left to right
    MarbleSolitaireModel board1 = new TriangleSolitaireModel(6, 0, 0);
    MarbleSolitaireView view1 = new TriangleSolitaireTextView(board1);
    board1.move(2, 0, 0, 0);
    after = "     O"
            + "\n    _ O"
            + "\n   _ O O"
            + "\n  O O O O"
            + "\n O O O O O"
            + "\nO O O O O O";
    assertEquals(after, view1.toString());

    //move right
    MarbleSolitaireModel board2 = new TriangleSolitaireModel(6, 3, 2);
    MarbleSolitaireView view2 = new TriangleSolitaireTextView(board2);
    board2.move(3, 0, 3, 2);
    after = "     O"
            + "\n    O O"
            + "\n   O O O"
            + "\n  _ _ O O"
            + "\n O O O O O"
            + "\nO O O O O O";
    assertEquals(after, view2.toString());

    //move left
    MarbleSolitaireModel board3 = new TriangleSolitaireModel(6, 3, 1);
    MarbleSolitaireView view3 = new TriangleSolitaireTextView(board3);
    board3.move(3, 3, 3, 1);
    after = "     O"
            + "\n    O O"
            + "\n   O O O"
            + "\n  O O _ _"
            + "\n O O O O O"
            + "\nO O O O O O";
    assertEquals(after, view3.toString());

    //move diagonally down from right to left
    MarbleSolitaireModel board4 = new TriangleSolitaireModel(6, 3, 1);
    MarbleSolitaireView view4 = new TriangleSolitaireTextView(board4);
    board4.move(1, 1, 3, 1);
    after = "     O"
            + "\n    O _"
            + "\n   O _ O"
            + "\n  O O O O"
            + "\n O O O O O"
            + "\nO O O O O O";
    assertEquals(after, view4.toString());

    //move diagonally down from left to right
    MarbleSolitaireModel board5 = new TriangleSolitaireModel(6, 3, 2);
    MarbleSolitaireView view5 = new TriangleSolitaireTextView(board5);
    board5.move(1, 0, 3, 2);
    after = "     O"
            + "\n    _ O"
            + "\n   O _ O"
            + "\n  O O O O"
            + "\n O O O O O"
            + "\nO O O O O O";
    assertEquals(after, view5.toString());
  }

  /**
   * Test move method: from position is outside of board.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testOutOfBoundsFromPosition() {
    MarbleSolitaireModel board = new TriangleSolitaireModel(6, 3, 2);
    MarbleSolitaireView view = new TriangleSolitaireTextView(board);
    board.move(-3, 0, 3, 2);
  }

  /**
   * Test move method: from position is empty.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testStartingPositionNoMarble() {
    MarbleSolitaireModel board = new TriangleSolitaireModel(6, 3, 2);
    MarbleSolitaireView view = new TriangleSolitaireTextView(board);
    board.move(1, 0, 3, 2);
    board.move(4, 3, 2, 1);
    board.move(1, 0, 3, 2);
  }

  /**
   * Test moving over an empty slot (diagonally left).
   */
  @Test(expected = IllegalArgumentException.class)
  public void testJumpOverEmpty() {
    MarbleSolitaireModel board = new TriangleSolitaireModel(6, 3, 2);
    MarbleSolitaireView view = new TriangleSolitaireTextView(board);
    board.move(2, 0, 0, 0);
    board.move(0, 0, 2, 0);
  }

  /**
   * Test moving over an empty slot (diagonally right).
   */
  @Test(expected = IllegalArgumentException.class)
  public void testJumpOverEmpty1() {
    MarbleSolitaireModel board = new TriangleSolitaireModel(6, 3, 2);
    MarbleSolitaireView view = new TriangleSolitaireTextView(board);
    board.move(2, 2, 0, 0);
    board.move(0, 0, 2, 2);
  }

  /**
   * Test getScore gives you the right score.
   */
  @Test
  public void testGetScore() {
    MarbleSolitaireModel board3 = new TriangleSolitaireModel(6, 3, 1);
    MarbleSolitaireView view3 = new TriangleSolitaireTextView(board3);
    assertEquals(20, board3.getScore());
    board3.move(3, 3, 3, 1);
    assertEquals(19, board3.getScore());
    String after = "     O"
            + "\n    O O"
            + "\n   O O O"
            + "\n  O O _ _"
            + "\n O O O O O"
            + "\nO O O O O O";
    assertEquals(after, view3.toString());
  }

  /**
   * Test isGameOver and beat the game.
   */
  @Test
  public void testWin() {
    MarbleSolitaireModel game = new TriangleSolitaireModel(5, 0, 0);
    MarbleSolitaireView view = new TriangleSolitaireTextView(game);

    assertEquals(14, game.getScore());
    assertFalse(game.isGameOver());
    game.move(2, 2, 0, 0);
    assertEquals(13, game.getScore());
    assertFalse(game.isGameOver());
    game.move(2, 0, 2, 2);
    assertFalse(game.isGameOver());
    game.move(0, 0, 2, 0);
    assertFalse(game.isGameOver());
    game.move(3, 3, 1, 1);
    assertFalse(game.isGameOver());
    game.move(4, 2, 2, 2);
    assertFalse(game.isGameOver());
    game.move(4, 4, 4, 2);
    assertFalse(game.isGameOver());
    game.move(3, 0, 1, 0);
    assertFalse(game.isGameOver());
    game.move(4, 1, 4, 3);
    assertFalse(game.isGameOver());
    game.move(2, 2, 0, 0);
    assertFalse(game.isGameOver());
    game.move(0, 0, 2, 0);
    assertFalse(game.isGameOver());
    game.move(2, 0, 4, 2);
    assertFalse(game.isGameOver());
    game.move(4, 3, 4, 1);
    assertFalse(game.isGameOver());
    game.move(4, 0, 4, 2);
    assertTrue(game.isGameOver());
  }

  /**
   * Test getBoardSize method.
   */
  @Test
  public void testSize() {
    assertEquals(5, new TriangleSolitaireModel().getBoardSize());
    assertEquals(8, new TriangleSolitaireModel(8).getBoardSize());
    assertEquals(2, new TriangleSolitaireModel(2, 0, 0).getBoardSize());
  }

  /**
   * Test getSlotAt method.
   */
  @Test
  public void testGetSlotAt() {
    MarbleSolitaireModel game = new TriangleSolitaireModel(5, 0, 0);
    MarbleSolitaireView view = new TriangleSolitaireTextView(game);
    assertEquals(game.getSlotAt(0, 0), MarbleSolitaireModelState.SlotState.Empty);
    assertEquals(game.getSlotAt(1, 1), MarbleSolitaireModelState.SlotState.Marble);
  }

}