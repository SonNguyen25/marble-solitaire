import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import cs3500.marblesolitaire.model.hw02.EnglishSolitaireModel;
import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelState;
import cs3500.marblesolitaire.view.MarbleSolitaireTextView;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Class for testing related methods in class EnglishSolitaireModel.
 */
public class EnglishSolitaireModelTest {
  private EnglishSolitaireModel defBoard;
  private EnglishSolitaireModel fiveFive;

  @Before
  public void setUp() {
    defBoard = new EnglishSolitaireModel();
    fiveFive = new EnglishSolitaireModel(5);
  }

  /**
   * Test armThickness as an even number.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testConstructor() {
    new EnglishSolitaireModel(6);
  }

  /**
   * Test armThickness as a negative number.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testConstructor1() {
    new EnglishSolitaireModel(-1);
  }

  /**
   * Test armThickness as 1.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testConstructor2() {
    new EnglishSolitaireModel(1);
  }

  /**
   * Test armThickness as 0.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testConstructor3() {
    new EnglishSolitaireModel(0);
  }

  /**
   * Test invalid row/column (invalid center).
   */
  @Test(expected = IllegalArgumentException.class)
  public void testConstructor4() {
    new EnglishSolitaireModel(1, 1);
  }

  /**
   * Test invalid row/column (invalid center).
   */
  @Test(expected = IllegalArgumentException.class)
  public void testConstructor5() {
    new EnglishSolitaireModel(1, 5);
  }

  /**
   * Test invalid row/column (invalid center).
   */
  @Test(expected = IllegalArgumentException.class)
  public void testConstructor6() {
    new EnglishSolitaireModel(5, 1);
  }

  /**
   * Test invalid row/column (invalid center).
   */
  @Test(expected = IllegalArgumentException.class)
  public void testConstructor7() {
    new EnglishSolitaireModel(5, 5);
  }

  /**
   * Test center at (0,0).
   */
  @Test(expected = IllegalArgumentException.class)
  public void testConstructor8() {
    new EnglishSolitaireModel(0, 0);
  }

  /**
   * Test center at negative position.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testConstructor9() {
    new EnglishSolitaireModel(-1, -1);
  }

  /**
   * Test center at negative position.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testConstructor10() {
    new EnglishSolitaireModel(-1, 3);
  }

  /**
   * Test center at negative position.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testConstructor11() {
    new EnglishSolitaireModel(3, -1);
  }


  /**
   * Test invalid parameters.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidParameters() {
    new EnglishSolitaireModel(3, 0, 0);
  }

  /**
   * Test invalid parameters.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidParameters1() {
    new EnglishSolitaireModel(3, -1, -1);
  }

  /**
   * Test invalid parameters.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidParameters2() {
    new EnglishSolitaireModel(6, 3, 3);
  }

  /**
   * Test invalid parameters.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidParameters3() {
    new EnglishSolitaireModel(5, 10000, 10000);
  }

  /**
   * Test invalid parameters.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidParameters4() {
    new EnglishSolitaireModel(3, 1, 5);
  }

  /**
   * Test invalid parameters.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidParameters5() {
    new EnglishSolitaireModel(3, 5, 1);
  }

  /**
   * Test invalid parameters.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidParameters6() {
    new EnglishSolitaireModel(3, -1, 3);
  }

  /**
   * Test invalid parameters.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidParameters7() {
    new EnglishSolitaireModel(3, 3, -1);
  }

  /**
   * Testing move method using only one basic movement.
   */
  @Test
  public void testBasicMoves() {

    //defaultBoard
    //test move to the left
    EnglishSolitaireModel moveTest = new EnglishSolitaireModel();
    MarbleSolitaireTextView gameModel = new MarbleSolitaireTextView(moveTest);
    moveTest.move(3, 5, 3, 3);
    String move = "    O O O"
            + "\n    O O O"
            + "\nO O O O O O O"
            + "\nO O O O _ _ O"
            + "\nO O O O O O O"
            + "\n    O O O"
            + "\n    O O O";
    assertEquals(move, gameModel.toString());
    assertEquals(gameModel.toString(), move);

    //test move to the right
    moveTest.move(3, 2, 3, 4);
    String move1 = "    O O O"
            + "\n    O O O"
            + "\nO O O O O O O"
            + "\nO O _ _ O _ O"
            + "\nO O O O O O O"
            + "\n    O O O"
            + "\n    O O O";
    assertEquals(move1, gameModel.toString());
    assertEquals(gameModel.toString(), move1);

    //Custom board
    //test move upwards
    EnglishSolitaireModel moveCustom = new EnglishSolitaireModel(5);
    MarbleSolitaireTextView moveCustomBoard = new MarbleSolitaireTextView(moveCustom);
    String move2 = "        O O O O O"
            + "\n        O O O O O"
            + "\n        O O O O O"
            + "\n        O O O O O"
            + "\nO O O O O O O O O O O O O"
            + "\nO O O O O O O O O O O O O"
            + "\nO O O O O O O O O O O O O"
            + "\nO O O O O O _ O O O O O O"
            + "\nO O O O O O _ O O O O O O"
            + "\n        O O O O O"
            + "\n        O O O O O"
            + "\n        O O O O O"
            + "\n        O O O O O";
    moveCustom.move(8, 6, 6, 6);
    assertEquals(move2, moveCustomBoard.toString());
    assertEquals(moveCustomBoard.toString(), move2);

    //test move downwards
    String move3 = "        O O O O O"
            + "\n        O O O O O"
            + "\n        O O O O O"
            + "\n        O O O O O"
            + "\nO O O O O O O O O O O O O"
            + "\nO O O O O O _ O O O O O O"
            + "\nO O O O O O _ O O O O O O"
            + "\nO O O O O O O O O O O O O"
            + "\nO O O O O O _ O O O O O O"
            + "\n        O O O O O"
            + "\n        O O O O O"
            + "\n        O O O O O"
            + "\n        O O O O O";
    moveCustom.move(5, 6, 7, 6);
    assertEquals(move3, moveCustomBoard.toString());
    assertEquals(moveCustomBoard.toString(), move3);
  }

  /**
   * Test invalid move.
   * Move outside of board.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidMove() {
    EnglishSolitaireModel moveTest = new EnglishSolitaireModel();
    moveTest.move(0, 2, -2, 2);
  }

  /**
   * Test invalid move.
   * Move outside of board.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidMove1() {
    EnglishSolitaireModel moveTest = new EnglishSolitaireModel();
    moveTest.move(0, 3, 0, 5);
  }

  /**
   * Test invalid starting position (empty slot at start).
   */
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidMove2() {
    EnglishSolitaireModel moveTest = new EnglishSolitaireModel();
    moveTest.move(0, 0, 2, 0);
  }

  /**
   * Test invalid starting position.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidMove3() {
    EnglishSolitaireModel moveTest = new EnglishSolitaireModel();
    moveTest.move(-1, 0, 1, 0);
  }

  /**
   * Test invalid starting position.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidMove4() {
    EnglishSolitaireModel moveTest = new EnglishSolitaireModel();
    moveTest.move(0, -1, 0, 1);
  }

  //position tests are using the same helper method from constructor to see invalid position,
  //and it is already tested, thus now we are only focusing on invalid moves.

  /**
   * Test invalid to position and distance bigger than 2.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidMove5() {
    EnglishSolitaireModel moveTest = new EnglishSolitaireModel();
    moveTest.move(3, 1, 6, 1);
  }

  /**
   * Test invalid to position and distance bigger than 2.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidMove6() {
    EnglishSolitaireModel moveTest = new EnglishSolitaireModel();
    moveTest.move(3, 1, 3, 6);
  }

  /**
   * Test invalid middle marble (no middle marble).
   */
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidMove7() {
    EnglishSolitaireModel moveTest = new EnglishSolitaireModel();
    moveTest.move(1, 3, 3, 3);
    moveTest.move(3, 3, 1, 3);
  }

  /**
   * Test invalid ending slot, no empty slot.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidMove8() {
    EnglishSolitaireModel moveTest = new EnglishSolitaireModel();
    moveTest.move(1, 2, 3, 2);
  }

  /**
   * Test invalid starting marble (no starting marble).
   */
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidMove9() {
    EnglishSolitaireModel moveTest = new EnglishSolitaireModel();
    moveTest.move(3, 1, 3, 3);
    moveTest.move(5, 2, 3, 2);
    moveTest.move(4, 0, 4, 2);
    moveTest.move(4, 3, 4, 1);
    moveTest.move(4, 5, 4, 3);
    moveTest.move(6, 4, 4, 4);
    moveTest.move(3, 4, 5, 4);
    moveTest.move(6, 2, 6, 4);
    moveTest.move(6, 4, 4, 4);
    moveTest.move(5, 4, 3, 4); //starting with empty slot -> illegal
  }

  /**
   * Test target location not on same row/column.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidMove10() {
    EnglishSolitaireModel moveTest = new EnglishSolitaireModel();
    moveTest.move(3, 1, 3, 3);
    moveTest.move(5, 2, 3, 2);
    moveTest.move(4, 0, 4, 2);
    moveTest.move(4, 3, 4, 1);
    moveTest.move(4, 5, 4, 3);
    moveTest.move(6, 4, 4, 4);
    moveTest.move(3, 4, 5, 4);
    moveTest.move(6, 2, 6, 4);
    moveTest.move(6, 4, 4, 4);
    moveTest.move(5, 3, 6, 2); //target not on same column or same row
    //move diagonally -> illegal
  }

  /**
   * Testing isGameOver method when game ends but there's still marbles.
   */
  @Test
  public void isGameOver() {
    EnglishSolitaireModel moveTest = new EnglishSolitaireModel();
    MarbleSolitaireTextView gameModel = new MarbleSolitaireTextView(moveTest);
    String move = "    O O O"
            + "\n    O O O"
            + "\nO O O O O O O"
            + "\nO _ _ O O O O"
            + "\nO O O O O O O"
            + "\n    O O O"
            + "\n    O O O";
    moveTest.move(3, 1, 3, 3);
    assertEquals(gameModel.toString(), move);
    assertEquals(move, gameModel.toString());
    assertFalse(moveTest.isGameOver());

    String move1 = "    O O O"
            + "\n    O O O"
            + "\nO O O O O O O"
            + "\nO _ O O O O O"
            + "\nO O _ O O O O"
            + "\n    _ O O"
            + "\n    O O O";
    moveTest.move(5, 2, 3, 2);
    assertEquals(gameModel.toString(), move1);
    assertEquals(move1, gameModel.toString());
    assertFalse(moveTest.isGameOver());

    String move2 = "    O O O"
            + "\n    O O O"
            + "\nO O O O O O O"
            + "\nO _ O O O O O"
            + "\n_ _ O O O O O"
            + "\n    _ O O"
            + "\n    O O O";
    moveTest.move(4, 0, 4, 2);
    assertEquals(gameModel.toString(), move2);
    assertEquals(move2, gameModel.toString());
    assertFalse(moveTest.isGameOver());

    String move3 = "    O O O"
            + "\n    O O O"
            + "\nO O O O O O O"
            + "\nO _ O O O O O"
            + "\n_ O _ _ O O O"
            + "\n    _ O O"
            + "\n    O O O";
    moveTest.move(4, 3, 4, 1);
    assertEquals(gameModel.toString(), move3);
    assertEquals(move3, gameModel.toString());
    assertFalse(moveTest.isGameOver());

    String move4 = "    O O O"
            + "\n    O O O"
            + "\nO O O O O O O"
            + "\nO _ O O O O O"
            + "\n_ O _ O _ _ O"
            + "\n    _ O O"
            + "\n    O O O";
    moveTest.move(4, 5, 4, 3);
    assertEquals(gameModel.toString(), move4);
    assertEquals(move4, gameModel.toString());
    assertFalse(moveTest.isGameOver());

    String move5 = "    O O O"
            + "\n    O O O"
            + "\nO O O O O O O"
            + "\nO _ O O O O O"
            + "\n_ O _ O O _ O"
            + "\n    _ O _"
            + "\n    O O _";
    moveTest.move(6, 4, 4, 4);
    assertEquals(gameModel.toString(), move5);
    assertEquals(move5, gameModel.toString());
    assertFalse(moveTest.isGameOver());

    String move6 = "    O O O"
            + "\n    O O O"
            + "\nO O O O O O O"
            + "\nO _ O O _ O O"
            + "\n_ O _ O _ _ O"
            + "\n    _ O O"
            + "\n    O O _";
    moveTest.move(3, 4, 5, 4);
    assertEquals(gameModel.toString(), move6);
    assertEquals(move6, gameModel.toString());
    assertFalse(moveTest.isGameOver());

    String move7 = "    O O O"
            + "\n    O O O"
            + "\nO O O O O O O"
            + "\nO _ O O _ O O"
            + "\n_ O _ O _ _ O"
            + "\n    _ O O"
            + "\n    _ _ O";
    moveTest.move(6, 2, 6, 4);
    assertEquals(gameModel.toString(), move7);
    assertEquals(move7, gameModel.toString());
    assertFalse(moveTest.isGameOver());

    String move8 = "    O O O"
            + "\n    O O O"
            + "\nO O O O O O O"
            + "\nO _ O O _ O O"
            + "\n_ O _ O O _ O"
            + "\n    _ O _"
            + "\n    _ _ _";
    moveTest.move(6, 4, 4, 4);
    assertEquals(gameModel.toString(), move8);
    assertEquals(move8, gameModel.toString());
    assertFalse(moveTest.isGameOver());

    String move9 = "    O O O"
            + "\n    O O O"
            + "\nO O _ O O O O"
            + "\nO _ _ O _ O O"
            + "\n_ O O O O _ O"
            + "\n    _ O _"
            + "\n    _ _ _";
    moveTest.move(2, 2, 4, 2);
    assertEquals(gameModel.toString(), move9);
    assertEquals(move9, gameModel.toString());
    assertFalse(moveTest.isGameOver());

    String move10 = "    _ O O"
            + "\n    _ O O"
            + "\nO O O O O O O"
            + "\nO _ _ O _ O O"
            + "\n_ O O O O _ O"
            + "\n    _ O _"
            + "\n    _ _ _";
    moveTest.move(0, 2, 2, 2);
    assertEquals(gameModel.toString(), move10);
    assertEquals(move10, gameModel.toString());
    assertFalse(moveTest.isGameOver());

    String move11 = "    _ O O"
            + "\n    _ O _"
            + "\nO O O O _ O O"
            + "\nO _ _ O O O O"
            + "\n_ O O O O _ O"
            + "\n    _ O _"
            + "\n    _ _ _";
    moveTest.move(1, 4, 3, 4);
    assertEquals(gameModel.toString(), move11);
    assertEquals(move11, gameModel.toString());
    assertFalse(moveTest.isGameOver());

    String move12 = "    _ O O"
            + "\n    _ O _"
            + "\nO O O O _ O O"
            + "\nO _ _ O _ O O"
            + "\n_ O O O _ _ O"
            + "\n    _ O O"
            + "\n    _ _ _";
    moveTest.move(3, 4, 5, 4);
    assertEquals(gameModel.toString(), move12);
    assertEquals(move12, gameModel.toString());
    assertFalse(moveTest.isGameOver());

    String move13 = "    _ O O"
            + "\n    _ O _"
            + "\nO O O O _ O O"
            + "\nO _ _ O _ O O"
            + "\n_ O O O _ _ O"
            + "\n    O _ _"
            + "\n    _ _ _";
    moveTest.move(5, 4, 5, 2);
    assertEquals(gameModel.toString(), move13);
    assertEquals(move13, gameModel.toString());
    assertFalse(moveTest.isGameOver());

    String move14 = "    _ O O"
            + "\n    _ O _"
            + "\nO O O O _ O O"
            + "\nO _ O O _ O O"
            + "\n_ O _ O _ _ O"
            + "\n    _ _ _"
            + "\n    _ _ _";
    moveTest.move(5, 2, 3, 2);
    assertEquals(gameModel.toString(), move14);
    assertEquals(move14, gameModel.toString());
    assertFalse(moveTest.isGameOver());

    String move15 = "    _ O O"
            + "\n    O O _"
            + "\nO O _ O _ O O"
            + "\nO _ _ O _ O O"
            + "\n_ O _ O _ _ O"
            + "\n    _ _ _"
            + "\n    _ _ _";
    moveTest.move(3, 2, 1, 2);
    assertEquals(gameModel.toString(), move15);
    assertEquals(move15, gameModel.toString());
    assertFalse(moveTest.isGameOver());

    String move16 = "    _ O O"
            + "\n    O O _"
            + "\n_ O _ O _ O O"
            + "\n_ _ _ O _ O O"
            + "\nO O _ O _ _ O"
            + "\n    _ _ _"
            + "\n    _ _ _";
    moveTest.move(2, 0, 4, 0);
    assertEquals(gameModel.toString(), move16);
    assertEquals(move16, gameModel.toString());
    assertFalse(moveTest.isGameOver());

    String move17 = "    _ O O"
            + "\n    O O _"
            + "\n_ O _ O _ O O"
            + "\n_ _ _ O _ O O"
            + "\n_ _ O O _ _ O"
            + "\n    _ _ _"
            + "\n    _ _ _";
    moveTest.move(4, 0, 4, 2);
    assertEquals(gameModel.toString(), move17);
    assertEquals(move17, gameModel.toString());
    assertFalse(moveTest.isGameOver());

    String move18 = "    _ O O"
            + "\n    O O _"
            + "\n_ O _ O _ O O"
            + "\n_ _ _ O _ O O"
            + "\n_ _ _ _ O _ O"
            + "\n    _ _ _"
            + "\n    _ _ _";
    moveTest.move(4, 2, 4, 4);
    assertEquals(gameModel.toString(), move18);
    assertEquals(move18, gameModel.toString());
    assertFalse(moveTest.isGameOver());

    String move19 = "    _ O O"
            + "\n    O O _"
            + "\n_ O _ O O _ _"
            + "\n_ _ _ O _ O O"
            + "\n_ _ _ _ O _ O"
            + "\n    _ _ _"
            + "\n    _ _ _";
    moveTest.move(2, 6, 2, 4);
    assertEquals(gameModel.toString(), move19);
    assertEquals(move19, gameModel.toString());
    assertFalse(moveTest.isGameOver());

    String move20 = "    _ O O"
            + "\n    O O _"
            + "\n_ O _ _ _ O _"
            + "\n_ _ _ O _ O O"
            + "\n_ _ _ _ O _ O"
            + "\n    _ _ _"
            + "\n    _ _ _";
    moveTest.move(2, 3, 2, 5);
    assertEquals(gameModel.toString(), move20);
    assertEquals(move20, gameModel.toString());
    assertFalse(moveTest.isGameOver());

    String move21 = "    _ O O"
            + "\n    O O _"
            + "\n_ O _ _ _ O O"
            + "\n_ _ _ O _ O _"
            + "\n_ _ _ _ O _ _"
            + "\n    _ _ _"
            + "\n    _ _ _";
    moveTest.move(4, 6, 2, 6);
    assertEquals(gameModel.toString(), move21);
    assertEquals(move21, gameModel.toString());
    assertFalse(moveTest.isGameOver());

    String move22 = "    _ O O"
            + "\n    O O _"
            + "\n_ O _ _ O _ _"
            + "\n_ _ _ O _ O _"
            + "\n_ _ _ _ O _ _"
            + "\n    _ _ _"
            + "\n    _ _ _";
    moveTest.move(2, 6, 2, 4);
    assertEquals(gameModel.toString(), move22);
    assertEquals(move22, gameModel.toString());
    assertFalse(moveTest.isGameOver());

    String move23 = "    O _ _"
            + "\n    O O _"
            + "\n_ O _ _ O _ _"
            + "\n_ _ _ O _ O _"
            + "\n_ _ _ _ O _ _"
            + "\n    _ _ _"
            + "\n    _ _ _";
    moveTest.move(0, 4, 0, 2);
    assertEquals(gameModel.toString(), move23);
    assertEquals(move23, gameModel.toString());
    assertFalse(moveTest.isGameOver());

    String move24 = "    _ _ _"
            + "\n    _ O _"
            + "\n_ O O _ O _ _"
            + "\n_ _ _ O _ O _"
            + "\n_ _ _ _ O _ _"
            + "\n    _ _ _"
            + "\n    _ _ _";
    moveTest.move(0, 2, 2, 2);
    assertEquals(gameModel.toString(), move24);
    assertEquals(move24, gameModel.toString());
    assertFalse(moveTest.isGameOver());

    String move25 = "    _ _ _"
            + "\n    _ O _"
            + "\n_ _ _ O O _ _"
            + "\n_ _ _ O _ O _"
            + "\n_ _ _ _ O _ _"
            + "\n    _ _ _"
            + "\n    _ _ _";
    moveTest.move(2, 1, 2, 3);
    assertEquals(gameModel.toString(), move25);
    assertEquals(move25, gameModel.toString());
    assertFalse(moveTest.isGameOver());

    String move26 = "    _ O _"
            + "\n    _ _ _"
            + "\n_ _ _ _ O _ _"
            + "\n_ _ _ O _ O _"
            + "\n_ _ _ _ O _ _"
            + "\n    _ _ _"
            + "\n    _ _ _";
    moveTest.move(2, 3, 0, 3);
    assertEquals(gameModel.toString(), move26);
    assertEquals(move26, gameModel.toString());
    assertTrue(moveTest.isGameOver()); //game ends with no legal moves
    assertEquals(moveTest.getScore(), 5); //test getScore when game ends
  }

  /**
   * Test getBoardSize method.
   */
  @Test
  public void getBoardSize() {
    assertEquals(this.defBoard.getBoardSize(), 7);
    assertEquals(this.fiveFive.getBoardSize(), 13);
  }

  /**
   * Test getSlotAt method.
   */
  @Test
  public void getSlotAt() {
    Assert.assertEquals(this.defBoard.getSlotAt(0, 0), MarbleSolitaireModelState.SlotState.Invalid);
    assertEquals(this.defBoard.getSlotAt(3, 3), MarbleSolitaireModelState.SlotState.Empty);
    assertEquals(this.defBoard.getSlotAt(3, 4), MarbleSolitaireModelState.SlotState.Marble);
  }

  /**
   * Test getSlotAt method when input illegal arguments.
   */
  @Test (expected = IllegalArgumentException.class)
  public void getSlotAtException() {
    this.defBoard.getSlotAt(-1, 0);
  }

  /**
   * Test getSlotAt method when input illegal arguments.
   */
  @Test (expected = IllegalArgumentException.class)
  public void getSlotAtException1() {
    this.defBoard.getSlotAt(0, -1);
  }

  /**
   * Test getSlotAt method when input illegal arguments.
   */
  @Test (expected = IllegalArgumentException.class)
  public void getSlotAtException2() {
    this.defBoard.getSlotAt(7, 0);
  }

  /**
   * Test getSlotAt method when input illegal arguments.
   */
  @Test (expected = IllegalArgumentException.class)
  public void getSlotAtException3() {
    this.defBoard.getSlotAt(0, 7);
  }

  /**
   * Test getScore method.
   */
  @Test
  public void getScore() {
    assertEquals(this.defBoard.getScore(), 32);
    assertEquals(this.fiveFive.getScore(), 104);

    EnglishSolitaireModel moveTest = new EnglishSolitaireModel();
    moveTest.move(3, 5, 3, 3);
    assertEquals(moveTest.getScore(), 31);
    moveTest.move(3, 2, 3, 4);
    assertEquals(moveTest.getScore(), 30);
  }

  /**
   * Testing isGameOver method when we win.
   */
  @Test
  public void isGameOver1() {
    EnglishSolitaireModel moveTest = new EnglishSolitaireModel();
    MarbleSolitaireTextView gameModel = new MarbleSolitaireTextView(moveTest);
    String move = "    O O O"
            + "\n    O O O"
            + "\nO O O O O O O"
            + "\nO _ _ O O O O"
            + "\nO O O O O O O"
            + "\n    O O O"
            + "\n    O O O";
    moveTest.move(3, 1, 3, 3);
    assertEquals(gameModel.toString(), move);
    assertEquals(move, gameModel.toString());
    assertFalse(moveTest.isGameOver());

    String move1 = "    O O O"
            + "\n    O O O"
            + "\nO O O O O O O"
            + "\nO _ O O O O O"
            + "\nO O _ O O O O"
            + "\n    _ O O"
            + "\n    O O O";
    moveTest.move(5, 2, 3, 2);
    assertEquals(gameModel.toString(), move1);
    assertEquals(move1, gameModel.toString());
    assertFalse(moveTest.isGameOver());

    String move2 = "    O O O"
            + "\n    O O O"
            + "\nO O O O O O O"
            + "\nO _ O O O O O"
            + "\n_ _ O O O O O"
            + "\n    _ O O"
            + "\n    O O O";
    moveTest.move(4, 0, 4, 2);
    assertEquals(gameModel.toString(), move2);
    assertEquals(move2, gameModel.toString());
    assertFalse(moveTest.isGameOver());

    String move3 = "    O O O"
            + "\n    O O O"
            + "\nO O O O O O O"
            + "\nO _ O O O O O"
            + "\n_ O _ _ O O O"
            + "\n    _ O O"
            + "\n    O O O";
    moveTest.move(4, 3, 4, 1);
    assertEquals(gameModel.toString(), move3);
    assertEquals(move3, gameModel.toString());
    assertFalse(moveTest.isGameOver());

    String move4 = "    O O O"
            + "\n    O O O"
            + "\nO O O O O O O"
            + "\nO _ O O O O O"
            + "\n_ O _ O _ _ O"
            + "\n    _ O O"
            + "\n    O O O";
    moveTest.move(4, 5, 4, 3);
    assertEquals(gameModel.toString(), move4);
    assertEquals(move4, gameModel.toString());
    assertFalse(moveTest.isGameOver());

    String move5 = "    O O O"
            + "\n    O O O"
            + "\nO O O O O O O"
            + "\nO _ O O O O O"
            + "\n_ O _ O O _ O"
            + "\n    _ O _"
            + "\n    O O _";
    moveTest.move(6, 4, 4, 4);
    assertEquals(gameModel.toString(), move5);
    assertEquals(move5, gameModel.toString());
    assertFalse(moveTest.isGameOver());

    String move6 = "    O O O"
            + "\n    O O O"
            + "\nO O O O O O O"
            + "\nO _ O O _ O O"
            + "\n_ O _ O _ _ O"
            + "\n    _ O O"
            + "\n    O O _";
    moveTest.move(3, 4, 5, 4);
    assertEquals(gameModel.toString(), move6);
    assertEquals(move6, gameModel.toString());
    assertFalse(moveTest.isGameOver());

    String move7 = "    O O O"
            + "\n    O O O"
            + "\nO O O O O O O"
            + "\nO _ O O _ O O"
            + "\n_ O _ O _ _ O"
            + "\n    _ O O"
            + "\n    _ _ O";
    moveTest.move(6, 2, 6, 4);
    assertEquals(gameModel.toString(), move7);
    assertEquals(move7, gameModel.toString());
    assertFalse(moveTest.isGameOver());

    String move8 = "    O O O"
            + "\n    O O O"
            + "\nO O O O O O O"
            + "\nO _ O O _ O O"
            + "\n_ O _ O O _ O"
            + "\n    _ O _"
            + "\n    _ _ _";
    moveTest.move(6, 4, 4, 4);
    assertEquals(gameModel.toString(), move8);
    assertEquals(move8, gameModel.toString());
    assertFalse(moveTest.isGameOver());

    String move9 = "    O O O"
            + "\n    O O O"
            + "\nO O _ O O O O"
            + "\nO _ _ O _ O O"
            + "\n_ O O O O _ O"
            + "\n    _ O _"
            + "\n    _ _ _";
    moveTest.move(2, 2, 4, 2);
    assertEquals(gameModel.toString(), move9);
    assertEquals(move9, gameModel.toString());
    assertFalse(moveTest.isGameOver());

    String move10 = "    _ O O"
            + "\n    _ O O"
            + "\nO O O O O O O"
            + "\nO _ _ O _ O O"
            + "\n_ O O O O _ O"
            + "\n    _ O _"
            + "\n    _ _ _";
    moveTest.move(0, 2, 2, 2);
    assertEquals(gameModel.toString(), move10);
    assertEquals(move10, gameModel.toString());
    assertFalse(moveTest.isGameOver());

    String move11 = "    _ O O"
            + "\n    _ O _"
            + "\nO O O O _ O O"
            + "\nO _ _ O O O O"
            + "\n_ O O O O _ O"
            + "\n    _ O _"
            + "\n    _ _ _";
    moveTest.move(1, 4, 3, 4);
    assertEquals(gameModel.toString(), move11);
    assertEquals(move11, gameModel.toString());
    assertFalse(moveTest.isGameOver());

    String move12 = "    _ O O"
            + "\n    _ O _"
            + "\nO O O O _ O O"
            + "\nO _ _ O _ O O"
            + "\n_ O O O _ _ O"
            + "\n    _ O O"
            + "\n    _ _ _";
    moveTest.move(3, 4, 5, 4);
    assertEquals(gameModel.toString(), move12);
    assertEquals(move12, gameModel.toString());
    assertFalse(moveTest.isGameOver());

    String move13 = "    _ O O"
            + "\n    _ O _"
            + "\nO O O O _ O O"
            + "\nO _ _ O _ O O"
            + "\n_ O O O _ _ O"
            + "\n    O _ _"
            + "\n    _ _ _";
    moveTest.move(5, 4, 5, 2);
    assertEquals(gameModel.toString(), move13);
    assertEquals(move13, gameModel.toString());
    assertFalse(moveTest.isGameOver());

    String move14 = "    _ O O"
            + "\n    _ O _"
            + "\nO O O O _ O O"
            + "\nO _ O O _ O O"
            + "\n_ O _ O _ _ O"
            + "\n    _ _ _"
            + "\n    _ _ _";
    moveTest.move(5, 2, 3, 2);
    assertEquals(gameModel.toString(), move14);
    assertEquals(move14, gameModel.toString());
    assertFalse(moveTest.isGameOver());

    String move15 = "    _ O O"
            + "\n    O O _"
            + "\nO O _ O _ O O"
            + "\nO _ _ O _ O O"
            + "\n_ O _ O _ _ O"
            + "\n    _ _ _"
            + "\n    _ _ _";
    moveTest.move(3, 2, 1, 2);
    assertEquals(gameModel.toString(), move15);
    assertEquals(move15, gameModel.toString());
    assertFalse(moveTest.isGameOver());

    String move16 = "    _ O O"
            + "\n    O O _"
            + "\n_ O _ O _ O O"
            + "\n_ _ _ O _ O O"
            + "\nO O _ O _ _ O"
            + "\n    _ _ _"
            + "\n    _ _ _";
    moveTest.move(2, 0, 4, 0);
    assertEquals(gameModel.toString(), move16);
    assertEquals(move16, gameModel.toString());
    assertFalse(moveTest.isGameOver());

    String move17 = "    _ O O"
            + "\n    O O _"
            + "\n_ O _ O _ O O"
            + "\n_ _ _ O _ O O"
            + "\n_ _ O O _ _ O"
            + "\n    _ _ _"
            + "\n    _ _ _";
    moveTest.move(4, 0, 4, 2);
    assertEquals(gameModel.toString(), move17);
    assertEquals(move17, gameModel.toString());
    assertFalse(moveTest.isGameOver());

    String move18 = "    _ O O"
            + "\n    O O _"
            + "\n_ O _ O _ O O"
            + "\n_ _ _ O _ O O"
            + "\n_ _ _ _ O _ O"
            + "\n    _ _ _"
            + "\n    _ _ _";
    moveTest.move(4, 2, 4, 4);
    assertEquals(gameModel.toString(), move18);
    assertEquals(move18, gameModel.toString());
    assertFalse(moveTest.isGameOver());

    String move19 = "    _ O O"
            + "\n    O O _"
            + "\n_ O _ O O _ _"
            + "\n_ _ _ O _ O O"
            + "\n_ _ _ _ O _ O"
            + "\n    _ _ _"
            + "\n    _ _ _";
    moveTest.move(2, 6, 2, 4);
    assertEquals(gameModel.toString(), move19);
    assertEquals(move19, gameModel.toString());
    assertFalse(moveTest.isGameOver());

    String move20 = "    _ O O"
            + "\n    O O _"
            + "\n_ O _ _ _ O _"
            + "\n_ _ _ O _ O O"
            + "\n_ _ _ _ O _ O"
            + "\n    _ _ _"
            + "\n    _ _ _";
    moveTest.move(2, 3, 2, 5);
    assertEquals(gameModel.toString(), move20);
    assertEquals(move20, gameModel.toString());
    assertFalse(moveTest.isGameOver());

    String move21 = "    _ O O"
            + "\n    O O _"
            + "\n_ O _ _ _ O O"
            + "\n_ _ _ O _ O _"
            + "\n_ _ _ _ O _ _"
            + "\n    _ _ _"
            + "\n    _ _ _";
    moveTest.move(4, 6, 2, 6);
    assertEquals(gameModel.toString(), move21);
    assertEquals(move21, gameModel.toString());
    assertFalse(moveTest.isGameOver());

    String move22 = "    _ O O"
            + "\n    O O _"
            + "\n_ O _ _ O _ _"
            + "\n_ _ _ O _ O _"
            + "\n_ _ _ _ O _ _"
            + "\n    _ _ _"
            + "\n    _ _ _";
    moveTest.move(2, 6, 2, 4);
    assertEquals(gameModel.toString(), move22);
    assertEquals(move22, gameModel.toString());
    assertFalse(moveTest.isGameOver());

    String move23 = "    O _ _"
            + "\n    O O _"
            + "\n_ O _ _ O _ _"
            + "\n_ _ _ O _ O _"
            + "\n_ _ _ _ O _ _"
            + "\n    _ _ _"
            + "\n    _ _ _";
    moveTest.move(0, 4, 0, 2);
    assertEquals(gameModel.toString(), move23);
    assertEquals(move23, gameModel.toString());
    assertFalse(moveTest.isGameOver());

    String move24 = "    _ _ _"
            + "\n    _ O _"
            + "\n_ O O _ O _ _"
            + "\n_ _ _ O _ O _"
            + "\n_ _ _ _ O _ _"
            + "\n    _ _ _"
            + "\n    _ _ _";
    moveTest.move(0, 2, 2, 2);
    assertEquals(gameModel.toString(), move24);
    assertEquals(move24, gameModel.toString());
    assertFalse(moveTest.isGameOver());

    String move25 = "    _ _ _"
            + "\n    _ O _"
            + "\n_ _ _ O O _ _"
            + "\n_ _ _ O _ O _"
            + "\n_ _ _ _ O _ _"
            + "\n    _ _ _"
            + "\n    _ _ _";
    moveTest.move(2, 1, 2, 3);
    assertEquals(gameModel.toString(), move25);
    assertEquals(move25, gameModel.toString());
    assertFalse(moveTest.isGameOver());

    String move26 = "    _ _ _"
            + "\n    _ O _"
            + "\n_ _ _ _ _ O _"
            + "\n_ _ _ O _ O _"
            + "\n_ _ _ _ O _ _"
            + "\n    _ _ _"
            + "\n    _ _ _";
    moveTest.move(2, 3, 2, 5);
    assertEquals(gameModel.toString(), move26);
    assertEquals(move26, gameModel.toString());
    assertFalse(moveTest.isGameOver());

    String move27 = "    _ _ _"
            + "\n    _ O _"
            + "\n_ _ _ _ _ _ _"
            + "\n_ _ _ O _ _ _"
            + "\n_ _ _ _ O O _"
            + "\n    _ _ _"
            + "\n    _ _ _";
    moveTest.move(2, 5, 4, 5);
    assertEquals(gameModel.toString(), move27);
    assertEquals(move27, gameModel.toString());
    assertFalse(moveTest.isGameOver());

    String move28 = "    _ _ _"
            + "\n    _ O _"
            + "\n_ _ _ _ _ _ _"
            + "\n_ _ _ O _ _ _"
            + "\n_ _ _ O _ _ _"
            + "\n    _ _ _"
            + "\n    _ _ _";
    moveTest.move(4, 5, 4, 3);
    assertEquals(gameModel.toString(), move28);
    assertEquals(move28, gameModel.toString());
    assertFalse(moveTest.isGameOver());

    String move29 = "    _ _ _"
            + "\n    _ O _"
            + "\n_ _ _ O _ _ _"
            + "\n_ _ _ _ _ _ _"
            + "\n_ _ _ _ _ _ _"
            + "\n    _ _ _"
            + "\n    _ _ _";
    moveTest.move(4, 3, 2, 3);
    assertEquals(gameModel.toString(), move29);
    assertEquals(move29, gameModel.toString());
    assertFalse(moveTest.isGameOver());

    String move30 = "    _ _ _"
            + "\n    _ _ _"
            + "\n_ _ _ _ _ _ _"
            + "\n_ _ _ O _ _ _"
            + "\n_ _ _ _ _ _ _"
            + "\n    _ _ _"
            + "\n    _ _ _";
    moveTest.move(1, 3, 3, 3);
    assertEquals(gameModel.toString(), move30);
    assertEquals(move30, gameModel.toString());
    assertTrue(moveTest.isGameOver()); //no more valid moves
    assertEquals(moveTest.getScore(), 1); //yay, you win! Last peg in the middle!
  }
}