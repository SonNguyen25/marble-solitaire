import org.junit.Test;

import java.io.Reader;
import java.io.StringReader;

import cs3500.marblesolitaire.controller.MarbleSolitaireController;
import cs3500.marblesolitaire.controller.MarbleSolitaireControllerImpl;
import cs3500.marblesolitaire.model.hw02.EnglishSolitaireModel;
import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;
import cs3500.marblesolitaire.view.MarbleSolitaireTextView;
import cs3500.marblesolitaire.view.MarbleSolitaireView;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Class for testing any related methods for Marble Solitaire Controller.
 */
public class MarbleSolitaireControllerImplTest {

  /**
   * Test if inputs is passed in.
   */
  @Test
  public void testInputs() {
    Readable input = new StringReader("3 4 5 4 Q");
    Appendable output = new StringBuilder();

    Appendable mockAppendable = new StringBuilder();
    MarbleSolitaireModel model = new MockModel(mockAppendable);
    MarbleSolitaireView view = new MarbleSolitaireTextView(model, output);

    MarbleSolitaireController controller = new MarbleSolitaireControllerImpl(model, view, input);

    controller.playGame();

    assertEquals("2 3 4 3", mockAppendable.toString());
  }

  @Test(expected = IllegalStateException.class)
  public void testNoInput() {
    Readable input = new StringReader(
            "");
    Appendable output = new StringBuilder();
    MarbleSolitaireModel mock = new EnglishSolitaireModel();
    MarbleSolitaireView view = new MarbleSolitaireTextView(mock, output);
    MarbleSolitaireController controller = new MarbleSolitaireControllerImpl(mock, view, input);
    controller.playGame();
  }

  /**
   * Test one bad input with character that is not integer.
   */
  @Test
  public void testOneBadInput() {
    Readable input = new StringReader(
            "x q");
    Appendable output = new StringBuilder();
    MarbleSolitaireModel mock = new EnglishSolitaireModel();
    MarbleSolitaireView view = new MarbleSolitaireTextView(mock, output);
    MarbleSolitaireController controller = new MarbleSolitaireControllerImpl(mock, view, input);
    controller.playGame();
    assertEquals("    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 32\n" +
            "Invalid move. Play again.\n" +
            "Game quit!\n" +
            "State of game when quit:\n" +
            "    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 32", output.toString());
  }

  /**
   * Test multiple bad inputs with characters that are not integers.
   */
  @Test
  public void testMultipleBadInputs() {
    Readable input = new StringReader(
            "hello world  dswdaw  @# /.sw q");
    Appendable output = new StringBuilder();
    MarbleSolitaireModel mock = new EnglishSolitaireModel();
    MarbleSolitaireView view = new MarbleSolitaireTextView(mock, output);
    MarbleSolitaireController controller = new MarbleSolitaireControllerImpl(mock, view, input);
    controller.playGame();

    assertEquals("    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 32\n" +
            "Invalid move. Play again.\n" +
            "Invalid move. Play again.\n" +
            "Invalid move. Play again.\n" +
            "Invalid move. Play again.\n" +
            "Invalid move. Play again.\n" +
            "Game quit!\n" +
            "State of game when quit:\n" +
            "    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 32", output.toString());
  }

  /**
   * Test when letters interrupt moves.
   */
  @Test
  public void testLetterInBetweenMoves() {
    Readable input = new StringReader(
            "2 4 4 4 a 3 2 3 4 q");
    Appendable output = new StringBuilder();
    MarbleSolitaireModel mock = new EnglishSolitaireModel();
    MarbleSolitaireView view = new MarbleSolitaireTextView(mock, output);
    MarbleSolitaireController controller = new MarbleSolitaireControllerImpl(mock, view, input);
    controller.playGame();
    assertEquals("    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 32\n" +
            "    O O O\n" +
            "    O _ O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 31\n" +
            "Invalid move. Play again.\n" +
            "    O O O\n" +
            "    O _ O\n" +
            "O _ _ O O O O\n" +
            "O O O O O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 30\n" +
            "Game quit!\n" +
            "State of game when quit:\n" +
            "    O O O\n" +
            "    O _ O\n" +
            "O _ _ O O O O\n" +
            "O O O O O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 30", output.toString());
  }

  /**
   * Testing for appendable error.
   */
  @Test(expected = IllegalStateException.class)
  public void errorAppendableTest() {
    Appendable ap = new FakeAppendable();
    Reader in = new StringReader("4 2 4 4");

    MarbleSolitaireModel test = new EnglishSolitaireModel();
    MarbleSolitaireView view = new MarbleSolitaireTextView(test, ap);
    MarbleSolitaireController controller = new MarbleSolitaireControllerImpl(test, view, in);

    controller.playGame();
  }

  /**
   * Testing null appendable.
   */
  @Test(expected = IllegalArgumentException.class)
  public void nullAppendableTest() {
    Reader in = new StringReader("4 4 4 2");

    MarbleSolitaireModel test = new EnglishSolitaireModel();
    MarbleSolitaireView view = new MarbleSolitaireTextView(test, null);
    MarbleSolitaireController controller = new MarbleSolitaireControllerImpl(test, view, in);
  }

  /**
   * Testing null readable.
   */
  @Test(expected = IllegalArgumentException.class)
  public void nullReadableTest() {
    Appendable out = new StringBuilder();

    MarbleSolitaireModel test = new EnglishSolitaireModel();
    MarbleSolitaireView view = new MarbleSolitaireTextView(test, out);
    MarbleSolitaireController controller = new MarbleSolitaireControllerImpl(test, view, null);
  }

  /**
   * Testing null model.
   */
  @Test(expected = IllegalArgumentException.class)
  public void nullModelTest() {
    Appendable out = new StringBuilder();
    Reader in = new StringReader("4 4 4 2");

    MarbleSolitaireModel test = null;
    MarbleSolitaireView view = new MarbleSolitaireTextView(test, out);
    MarbleSolitaireController controller = new MarbleSolitaireControllerImpl(test, view, in);
  }

  /**
   * Test inputs with no ending (q or Q).
   */
  @Test(expected = IllegalStateException.class)
  public void noEndingTest() {
    Appendable out = new StringBuffer();
    Reader in = new StringReader("4 2 4 4");

    MarbleSolitaireModel test = new EnglishSolitaireModel();
    MarbleSolitaireView view = new MarbleSolitaireTextView(test, out);
    MarbleSolitaireController controller = new MarbleSolitaireControllerImpl(test, view, in);

    controller.playGame();
  }

  /**
   * Test 6 inputs but not complete the game.
   */
  @Test(expected = IllegalStateException.class)
  public void testSixInputs() {
    Appendable out = new StringBuffer();
    Reader in = new StringReader("4 3 4 5 4 2");

    MarbleSolitaireModel test = new EnglishSolitaireModel();
    MarbleSolitaireView view = new MarbleSolitaireTextView(test, out);
    MarbleSolitaireController controller = new MarbleSolitaireControllerImpl(test, view, in);

    controller.playGame();
  }

  /**
   * Test 3 inputs but not complete the game.
   */
  @Test(expected = IllegalStateException.class)
  public void testThreeInputs() {
    Appendable out = new StringBuffer();
    Reader in = new StringReader("4 3 4");

    MarbleSolitaireModel test = new EnglishSolitaireModel();
    MarbleSolitaireView view = new MarbleSolitaireTextView(test, out);
    MarbleSolitaireController controller = new MarbleSolitaireControllerImpl(test, view, in);

    controller.playGame();
  }

  /**
   * Test move out of bounds.
   */
  @Test
  public void testOutOfBounds() {
    Appendable out = new StringBuffer();
    Reader in = new StringReader("4 -1 4 1 q");

    MarbleSolitaireModel test = new EnglishSolitaireModel();
    MarbleSolitaireView view = new MarbleSolitaireTextView(test, out);
    MarbleSolitaireController controller = new MarbleSolitaireControllerImpl(test, view, in);

    controller.playGame();

    assertTrue(out.toString().contains("Invalid move. Play again."));
  }

  /**
   * Test invalid moves.
   */
  @Test
  public void testInvalidMove() {
    Appendable out = new StringBuffer();
    Reader in = new StringReader("4 3 4 5 q");

    MarbleSolitaireModel test = new EnglishSolitaireModel();
    MarbleSolitaireView view = new MarbleSolitaireTextView(test, out);
    MarbleSolitaireController controller = new MarbleSolitaireControllerImpl(test, view, in);

    controller.playGame();

    assertTrue(out.toString().contains("Invalid move. Play again."));
  }

  /**
   * Test move: invalid middle marble (no middle marble).
   */
  @Test
  public void testNoMiddleMarble() {
    Appendable out = new StringBuffer();
    Reader in = new StringReader("2, 4, 4, 4, 4, 4, 2, 4, q");

    MarbleSolitaireModel test = new EnglishSolitaireModel();
    MarbleSolitaireView view = new MarbleSolitaireTextView(test, out);
    MarbleSolitaireController controller = new MarbleSolitaireControllerImpl(test, view, in);

    controller.playGame();

    assertTrue(out.toString().contains("Invalid move. Play again."));
  }

  /**
   * Test invalid ending slot, no empty slot.
   */
  @Test
  public void testInvalidEndingSlot() {
    Appendable out = new StringBuffer();
    Reader in = new StringReader("2, 3, 4, 3, q");

    MarbleSolitaireModel test = new EnglishSolitaireModel();
    MarbleSolitaireView view = new MarbleSolitaireTextView(test, out);
    MarbleSolitaireController controller = new MarbleSolitaireControllerImpl(test, view, in);

    controller.playGame();

    assertTrue(out.toString().contains("Invalid move. Play again."));
  }

  /**
   * Test invalid to position and distance bigger than 2.
   */
  @Test
  public void testInvalidDistance() {
    Appendable out = new StringBuffer();
    Reader in = new StringReader("4, 2, 4, 7, q");

    MarbleSolitaireModel test = new EnglishSolitaireModel();
    MarbleSolitaireView view = new MarbleSolitaireTextView(test, out);
    MarbleSolitaireController controller = new MarbleSolitaireControllerImpl(test, view, in);

    controller.playGame();

    assertTrue(out.toString().contains("Invalid move. Play again."));
  }

  /**
   * Test invalid starting marble (no starting marble).
   */
  @Test
  public void testNoStartMarble() {
    Appendable out = new StringBuffer();
    Reader in = new StringReader("4, 2, 4, 4, 6, 3, 4, 3, 5, 1, 5, 3, 5, 4, 5, 2," +
            " 5, 6, 5, 4, 7, 5, 5, 5, 4, 5, 6, 5, 7, 3, 7, 5, 7, 5, 5, 5, 6, 5, 4, 5, q");

    MarbleSolitaireModel test = new EnglishSolitaireModel();
    MarbleSolitaireView view = new MarbleSolitaireTextView(test, out);
    MarbleSolitaireController controller = new MarbleSolitaireControllerImpl(test, view, in);

    controller.playGame();

    assertTrue(out.toString().contains("Invalid move. Play again."));
  }

  /**
   * Test target location not on same row/column (diagonal).
   */
  @Test
  public void testDiagonal() {
    Appendable out = new StringBuffer();
    Reader in = new StringReader("4, 2, 4, 4, 6, 3, 4, 3, 5, 1, 5, 3, 5, 4, 5, 2," +
            "5, 6, 5, 4, 7, 5, 5, 5, 4, 5, 6, 5, 7, 3, 7, 5, 7, 5, 5, 5, 4, 4, 7, 5 q");

    MarbleSolitaireModel test = new EnglishSolitaireModel();
    MarbleSolitaireView view = new MarbleSolitaireTextView(test, out);
    MarbleSolitaireController controller = new MarbleSolitaireControllerImpl(test, view, in);

    controller.playGame();
    assertTrue(out.toString().contains("Invalid move. Play again."));
  }

  /**
   * Test when you have no more moves and have unfinished moves in between.
   */
  @Test
  public void testGameEndUnfinishedMoves() {
    Appendable out = new StringBuffer();
    Reader in = new StringReader("2 4 4 4 3 2 3 4 3 5 3 3 1 5 3 5 1 3 1 5 3 3 1 3 4 5 2 5 5 3 " +
            "3 3  5 4 3 4 3 3 3 5 2 5 4 5 5 5 3 5 5 2 3 2 3 1 3 3 3 6 3 4 5 6 3 6 3 7 3 5 3 4 3 2" +
            " 5 1 3 1 3 1 3 3 5 7 3 7 7 3 5 3 6 5 6 3 5 3 7 3");

    MarbleSolitaireModel test = new EnglishSolitaireModel();
    MarbleSolitaireView view = new MarbleSolitaireTextView(test, out);
    MarbleSolitaireController controller = new MarbleSolitaireControllerImpl(test, view, in);

    controller.playGame();

    assertEquals("    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 32\n" +
            "    O O O\n" +
            "    O _ O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 31\n" +
            "    O O O\n" +
            "    O _ O\n" +
            "O _ _ O O O O\n" +
            "O O O O O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 30\n" +
            "    O O O\n" +
            "    O _ O\n" +
            "O _ O _ _ O O\n" +
            "O O O O O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 29\n" +
            "    O O _\n" +
            "    O _ _\n" +
            "O _ O _ O O O\n" +
            "O O O O O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 28\n" +
            "    _ _ O\n" +
            "    O _ _\n" +
            "O _ O _ O O O\n" +
            "O O O O O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 27\n" +
            "    O _ O\n" +
            "    _ _ _\n" +
            "O _ _ _ O O O\n" +
            "O O O O O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 26\n" +
            "    O _ O\n" +
            "    _ _ O\n" +
            "O _ _ _ _ O O\n" +
            "O O O O _ O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 25\n" +
            "    O _ O\n" +
            "    _ _ O\n" +
            "O _ O _ _ O O\n" +
            "O O _ O _ O O\n" +
            "O O _ O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 24\n" +
            "    O _ O\n" +
            "    _ _ O\n" +
            "O _ O O _ O O\n" +
            "O O _ _ _ O O\n" +
            "O O _ _ O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 23\n" +
            "    O _ O\n" +
            "    _ _ O\n" +
            "O _ _ _ O O O\n" +
            "O O _ _ _ O O\n" +
            "O O _ _ O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 22\n" +
            "    O _ O\n" +
            "    _ _ _\n" +
            "O _ _ _ _ O O\n" +
            "O O _ _ O O O\n" +
            "O O _ _ O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 21\n" +
            "    O _ O\n" +
            "    _ _ _\n" +
            "O _ _ _ O O O\n" +
            "O O _ _ _ O O\n" +
            "O O _ _ _ O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 20\n" +
            "    O _ O\n" +
            "    _ _ _\n" +
            "O O _ _ O O O\n" +
            "O _ _ _ _ O O\n" +
            "O _ _ _ _ O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 19\n" +
            "    O _ O\n" +
            "    _ _ _\n" +
            "_ _ O _ O O O\n" +
            "O _ _ _ _ O O\n" +
            "O _ _ _ _ O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 18\n" +
            "    O _ O\n" +
            "    _ _ _\n" +
            "_ _ O O _ _ O\n" +
            "O _ _ _ _ O O\n" +
            "O _ _ _ _ O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 17\n" +
            "    O _ O\n" +
            "    _ _ _\n" +
            "_ _ O O _ O O\n" +
            "O _ _ _ _ _ O\n" +
            "O _ _ _ _ _ O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 16\n" +
            "    O _ O\n" +
            "    _ _ _\n" +
            "_ _ O O O _ _\n" +
            "O _ _ _ _ _ O\n" +
            "O _ _ _ _ _ O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 15\n" +
            "    O _ O\n" +
            "    _ _ _\n" +
            "_ O _ _ O _ _\n" +
            "O _ _ _ _ _ O\n" +
            "O _ _ _ _ _ O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 14\n" +
            "    O _ O\n" +
            "    _ _ _\n" +
            "O O _ _ O _ _\n" +
            "_ _ _ _ _ _ O\n" +
            "_ _ _ _ _ _ O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 13\n" +
            "    O _ O\n" +
            "    _ _ _\n" +
            "_ _ O _ O _ _\n" +
            "_ _ _ _ _ _ O\n" +
            "_ _ _ _ _ _ O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 12\n" +
            "    O _ O\n" +
            "    _ _ _\n" +
            "_ _ O _ O _ O\n" +
            "_ _ _ _ _ _ _\n" +
            "_ _ _ _ _ _ _\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 11\n" +
            "    O _ O\n" +
            "    _ _ _\n" +
            "_ _ O _ O _ O\n" +
            "_ _ _ _ _ _ _\n" +
            "_ _ O _ _ _ _\n" +
            "    _ O O\n" +
            "    _ O O\n" +
            "Score: 10\n" +
            "    O _ O\n" +
            "    _ _ _\n" +
            "_ _ O _ O _ O\n" +
            "_ _ _ _ _ _ _\n" +
            "_ _ O _ _ _ _\n" +
            "    O _ _\n" +
            "    _ O O\n" +
            "Score: 9\n" +
            "    O _ O\n" +
            "    _ _ _\n" +
            "_ _ O _ O _ O\n" +
            "_ _ _ _ _ _ _\n" +
            "_ _ _ _ _ _ _\n" +
            "    _ _ _\n" +
            "    O O O\n" +
            "Score: 8\n" +
            "Game over!\n" +
            "    O _ O\n" +
            "    _ _ _\n" +
            "_ _ O _ O _ O\n" +
            "_ _ _ _ _ _ _\n" +
            "_ _ _ _ _ _ _\n" +
            "    _ _ _\n" +
            "    O O O\n" +
            "Score: 8", out.toString());
  }

  /**
   * Testing input q at near the end.
   */
  @Test
  public void testQuitNearEnding() {
    Appendable out = new StringBuffer();
    Reader in = new StringReader("4 2 4 q 5");

    MarbleSolitaireModel test = new EnglishSolitaireModel();
    MarbleSolitaireView view = new MarbleSolitaireTextView(test, out);
    MarbleSolitaireController controller = new MarbleSolitaireControllerImpl(test, view, in);

    controller.playGame();


    String quit = "    O O O"
            + "\n    O O O"
            + "\nO O O O O O O"
            + "\nO O O _ O O O"
            + "\nO O O O O O O"
            + "\n    O O O"
            + "\n    O O O"
            + "\nScore: 32"
            + "\nGame quit!\nState of game when quit:\n"
            + "    O O O"
            + "\n    O O O"
            + "\nO O O O O O O"
            + "\nO O O _ O O O"
            + "\nO O O O O O O"
            + "\n    O O O"
            + "\n    O O O"
            + "\nScore: 32";

    assertEquals(quit, out.toString());
  }

  /**
   * Testing quit in the middle.
   */
  @Test
  public void testInMiddleQ() {
    Appendable out = new StringBuffer();
    Reader in = new StringReader("4 2 q 4 4");

    MarbleSolitaireModel test = new EnglishSolitaireModel();
    MarbleSolitaireView view = new MarbleSolitaireTextView(test, out);
    MarbleSolitaireController controller = new MarbleSolitaireControllerImpl(test, view, in);

    controller.playGame();

    String quit = "    O O O"
            + "\n    O O O"
            + "\nO O O O O O O"
            + "\nO O O _ O O O"
            + "\nO O O O O O O"
            + "\n    O O O"
            + "\n    O O O"
            + "\nScore: 32"
            + "\nGame quit!\nState of game when quit:\n"
            + "    O O O"
            + "\n    O O O"
            + "\nO O O O O O O"
            + "\nO O O _ O O O"
            + "\nO O O O O O O"
            + "\n    O O O"
            + "\n    O O O"
            + "\nScore: 32";

    assertEquals(quit, out.toString());
  }

  /**
   * Testing quit before moves.
   */
  @Test
  public void testQuitBeforeMoves() {
    Appendable out = new StringBuffer();
    Reader in = new StringReader("q 4 4 4 2");

    MarbleSolitaireModel test = new EnglishSolitaireModel();
    MarbleSolitaireView view = new MarbleSolitaireTextView(test, out);
    MarbleSolitaireController controller = new MarbleSolitaireControllerImpl(test, view, in);

    controller.playGame();
    String quit = "    O O O"
            + "\n    O O O"
            + "\nO O O O O O O"
            + "\nO O O _ O O O"
            + "\nO O O O O O O"
            + "\n    O O O"
            + "\n    O O O"
            + "\nScore: 32"
            + "\nGame quit!\nState of game when quit:\n"
            + "    O O O"
            + "\n    O O O"
            + "\nO O O O O O O"
            + "\nO O O _ O O O"
            + "\nO O O O O O O"
            + "\n    O O O"
            + "\n    O O O"
            + "\nScore: 32";

    assertEquals(quit, out.toString());
  }

  /**
   * Testing quit in one move.
   */
  @Test
  public void testQuitOne() {
    Appendable out = new StringBuffer();
    Reader in = new StringReader("q");

    MarbleSolitaireModel test = new EnglishSolitaireModel();
    MarbleSolitaireView view = new MarbleSolitaireTextView(test, out);
    MarbleSolitaireController controller = new MarbleSolitaireControllerImpl(test, view, in);

    controller.playGame();
    String quit = "    O O O"
            + "\n    O O O"
            + "\nO O O O O O O"
            + "\nO O O _ O O O"
            + "\nO O O O O O O"
            + "\n    O O O"
            + "\n    O O O"
            + "\nScore: 32"
            + "\nGame quit!\nState of game when quit:\n"
            + "    O O O"
            + "\n    O O O"
            + "\nO O O O O O O"
            + "\nO O O _ O O O"
            + "\nO O O O O O O"
            + "\n    O O O"
            + "\n    O O O"
            + "\nScore: 32";

    assertEquals(quit, out.toString());
  }

  /**
   * Testing quit in one move.
   */
  @Test
  public void testQuitOne2() {
    Appendable out = new StringBuffer();
    Reader in = new StringReader("Q");

    MarbleSolitaireModel test = new EnglishSolitaireModel();
    MarbleSolitaireView view = new MarbleSolitaireTextView(test, out);
    MarbleSolitaireController controller = new MarbleSolitaireControllerImpl(test, view, in);

    controller.playGame();
    String quit = "    O O O"
            + "\n    O O O"
            + "\nO O O O O O O"
            + "\nO O O _ O O O"
            + "\nO O O O O O O"
            + "\n    O O O"
            + "\n    O O O"
            + "\nScore: 32"
            + "\nGame quit!\nState of game when quit:\n"
            + "    O O O"
            + "\n    O O O"
            + "\nO O O O O O O"
            + "\nO O O _ O O O"
            + "\nO O O O O O O"
            + "\n    O O O"
            + "\n    O O O"
            + "\nScore: 32";

    assertEquals(quit, out.toString());
  }

  /**
   * Testing multiple quits.
   */
  @Test
  public void testMultipleQuits() {
    Appendable out = new StringBuffer();
    Reader in = new StringReader("q Q");

    MarbleSolitaireModel test = new EnglishSolitaireModel();
    MarbleSolitaireView view = new MarbleSolitaireTextView(test, out);
    MarbleSolitaireController controller = new MarbleSolitaireControllerImpl(test, view, in);

    controller.playGame();
    String quit = "    O O O"
            + "\n    O O O"
            + "\nO O O O O O O"
            + "\nO O O _ O O O"
            + "\nO O O O O O O"
            + "\n    O O O"
            + "\n    O O O"
            + "\nScore: 32"
            + "\nGame quit!\nState of game when quit:\n"
            + "    O O O"
            + "\n    O O O"
            + "\nO O O O O O O"
            + "\nO O O _ O O O"
            + "\nO O O O O O O"
            + "\n    O O O"
            + "\n    O O O"
            + "\nScore: 32";

    assertEquals(quit, out.toString());
  }

  /**
   * Testing moves with changes in board.
   */
  @Test
  public void moveTest() {
    Reader in1 = new StringReader("4 2 4 4 q");
    Appendable out = new StringBuffer();

    MarbleSolitaireModel model1 = new EnglishSolitaireModel();
    MarbleSolitaireView view1 = new MarbleSolitaireTextView(model1, out);
    MarbleSolitaireController test1 = new MarbleSolitaireControllerImpl(model1, view1, in1);
    test1.playGame();

    String oneMove = "    O O O"
            + "\n    O O O"
            + "\nO O O O O O O"
            + "\nO O O _ O O O"
            + "\nO O O O O O O"
            + "\n    O O O"
            + "\n    O O O"
            + "\nScore: 32"
            + "\n    O O O"
            + "\n    O O O"
            + "\nO O O O O O O"
            + "\nO _ _ O O O O"
            + "\nO O O O O O O"
            + "\n    O O O"
            + "\n    O O O\nScore: 31"
            + "\nGame quit!\nState of game when quit:\n"
            + "    O O O"
            + "\n    O O O"
            + "\nO O O O O O O"
            + "\nO _ _ O O O O"
            + "\nO O O O O O O"
            + "\n    O O O"
            + "\n    O O O"
            + "\nScore: 31";

    assertEquals(oneMove, out.toString());
  }

  /**
   * Testing moves with unfinished moves.
   */
  @Test
  public void moveTestWithUnfinishedMoves() {
    Reader in1 = new StringReader("4 2 4 4 4 4 q");
    Appendable out = new StringBuffer();

    MarbleSolitaireModel model1 = new EnglishSolitaireModel();
    MarbleSolitaireView view1 = new MarbleSolitaireTextView(model1, out);
    MarbleSolitaireController test1 = new MarbleSolitaireControllerImpl(model1, view1, in1);
    test1.playGame();

    String oneMove = "    O O O"
            + "\n    O O O"
            + "\nO O O O O O O"
            + "\nO O O _ O O O"
            + "\nO O O O O O O"
            + "\n    O O O"
            + "\n    O O O"
            + "\nScore: 32"
            + "\n    O O O"
            + "\n    O O O"
            + "\nO O O O O O O"
            + "\nO _ _ O O O O"
            + "\nO O O O O O O"
            + "\n    O O O"
            + "\n    O O O\nScore: 31"
            + "\nGame quit!\nState of game when quit:\n"
            + "    O O O"
            + "\n    O O O"
            + "\nO O O O O O O"
            + "\nO _ _ O O O O"
            + "\nO O O O O O O"
            + "\n    O O O"
            + "\n    O O O"
            + "\nScore: 31";

    assertEquals(oneMove, out.toString());
  }

  /**
   * Testing moves with unfinished moves and continue with the next moves, ignoring the unfinished.
   */
  @Test
  public void moveTestWithUnfinishedMoves2() {
    Reader in1 = new StringReader("4 2 4 4 6 3" + "6 3 4 3 q");
    Appendable out = new StringBuffer();

    MarbleSolitaireModel model1 = new EnglishSolitaireModel();
    MarbleSolitaireView view1 = new MarbleSolitaireTextView(model1, out);
    MarbleSolitaireController test1 = new MarbleSolitaireControllerImpl(model1, view1, in1);
    test1.playGame();

    String oneMove = "    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 32\n" +
            "    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O _ _ O O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 31\n" +
            "Invalid move. Play again.\n" +
            "    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O _ _ O O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 31\n" +
            "Game quit!\n" +
            "State of game when quit:\n" +
            "    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O _ _ O O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 31";

    assertEquals(oneMove, out.toString());
  }

  /**
   * Test move with a larger/different board.
   */
  @Test
  public void testMoveLarge() {
    Appendable out = new StringBuilder();
    Readable in = new StringReader("5 7 7 7 q");
    MarbleSolitaireModel model = new EnglishSolitaireModel(5);
    MarbleSolitaireView view = new MarbleSolitaireTextView(model, out);
    MarbleSolitaireController controller = new MarbleSolitaireControllerImpl(model, view, in);
    controller.playGame();
    assertEquals("        O O O O O\n" +
            "        O O O O O\n" +
            "        O O O O O\n" +
            "        O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "O O O O O O _ O O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "        O O O O O\n" +
            "        O O O O O\n" +
            "        O O O O O\n" +
            "        O O O O O\n" +
            "Score: 104\n" +
            "        O O O O O\n" +
            "        O O O O O\n" +
            "        O O O O O\n" +
            "        O O O O O\n" +
            "O O O O O O _ O O O O O O\n" +
            "O O O O O O _ O O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "        O O O O O\n" +
            "        O O O O O\n" +
            "        O O O O O\n" +
            "        O O O O O\n" +
            "Score: 103\n" +
            "Game quit!\n" +
            "State of game when quit:\n" +
            "        O O O O O\n" +
            "        O O O O O\n" +
            "        O O O O O\n" +
            "        O O O O O\n" +
            "O O O O O O _ O O O O O O\n" +
            "O O O O O O _ O O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "        O O O O O\n" +
            "        O O O O O\n" +
            "        O O O O O\n" +
            "        O O O O O\n" +
            "Score: 103", out.toString());

  }

  /**
   * Test win the game.
   */
  @Test
  public void testWin() {
    Appendable out = new StringBuffer();
    Reader in = new StringReader("4 2 4 4 6 3 4 3 5 1 5 3 5 4 5 2 5 6 5 4 7 5 5 5 4 5 6 5" +
            " 7 3 7 5 7 5 5 5 3 3 5 3 1 3 3 3 2 5 4 5 4 5 6 5 6 5 6 3 6 3 4 3 4 3 2 3 3 1 5 1" +
            " 5 1 5 3 5 3 5 5 3 7 3 5 3 4 3 6 5 7 3 7 3 7 3 5 1 5 1 3 1 3 3 3 3 2 3 4 3 " +
            "4 3 6 3 6 5 6 5 6 5 4 5 4 3 4 2 4 4 4");
    MarbleSolitaireModel model1 = new EnglishSolitaireModel();
    MarbleSolitaireView view1 = new MarbleSolitaireTextView(model1, out);
    MarbleSolitaireController test1 = new MarbleSolitaireControllerImpl(model1, view1, in);
    test1.playGame();
    assertEquals("    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 32\n" +
            "    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O _ _ O O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 31\n" +
            "    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O _ O O O O O\n" +
            "O O _ O O O O\n" +
            "    _ O O\n" +
            "    O O O\n" +
            "Score: 30\n" +
            "    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O _ O O O O O\n" +
            "_ _ O O O O O\n" +
            "    _ O O\n" +
            "    O O O\n" +
            "Score: 29\n" +
            "    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O _ O O O O O\n" +
            "_ O _ _ O O O\n" +
            "    _ O O\n" +
            "    O O O\n" +
            "Score: 28\n" +
            "    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O _ O O O O O\n" +
            "_ O _ O _ _ O\n" +
            "    _ O O\n" +
            "    O O O\n" +
            "Score: 27\n" +
            "    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O _ O O O O O\n" +
            "_ O _ O O _ O\n" +
            "    _ O _\n" +
            "    O O _\n" +
            "Score: 26\n" +
            "    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O _ O O _ O O\n" +
            "_ O _ O _ _ O\n" +
            "    _ O O\n" +
            "    O O _\n" +
            "Score: 25\n" +
            "    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O _ O O _ O O\n" +
            "_ O _ O _ _ O\n" +
            "    _ O O\n" +
            "    _ _ O\n" +
            "Score: 24\n" +
            "    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O _ O O _ O O\n" +
            "_ O _ O O _ O\n" +
            "    _ O _\n" +
            "    _ _ _\n" +
            "Score: 23\n" +
            "    O O O\n" +
            "    O O O\n" +
            "O O _ O O O O\n" +
            "O _ _ O _ O O\n" +
            "_ O O O O _ O\n" +
            "    _ O _\n" +
            "    _ _ _\n" +
            "Score: 22\n" +
            "    _ O O\n" +
            "    _ O O\n" +
            "O O O O O O O\n" +
            "O _ _ O _ O O\n" +
            "_ O O O O _ O\n" +
            "    _ O _\n" +
            "    _ _ _\n" +
            "Score: 21\n" +
            "    _ O O\n" +
            "    _ O _\n" +
            "O O O O _ O O\n" +
            "O _ _ O O O O\n" +
            "_ O O O O _ O\n" +
            "    _ O _\n" +
            "    _ _ _\n" +
            "Score: 20\n" +
            "    _ O O\n" +
            "    _ O _\n" +
            "O O O O _ O O\n" +
            "O _ _ O _ O O\n" +
            "_ O O O _ _ O\n" +
            "    _ O O\n" +
            "    _ _ _\n" +
            "Score: 19\n" +
            "    _ O O\n" +
            "    _ O _\n" +
            "O O O O _ O O\n" +
            "O _ _ O _ O O\n" +
            "_ O O O _ _ O\n" +
            "    O _ _\n" +
            "    _ _ _\n" +
            "Score: 18\n" +
            "    _ O O\n" +
            "    _ O _\n" +
            "O O O O _ O O\n" +
            "O _ O O _ O O\n" +
            "_ O _ O _ _ O\n" +
            "    _ _ _\n" +
            "    _ _ _\n" +
            "Score: 17\n" +
            "    _ O O\n" +
            "    O O _\n" +
            "O O _ O _ O O\n" +
            "O _ _ O _ O O\n" +
            "_ O _ O _ _ O\n" +
            "    _ _ _\n" +
            "    _ _ _\n" +
            "Score: 16\n" +
            "    _ O O\n" +
            "    O O _\n" +
            "_ O _ O _ O O\n" +
            "_ _ _ O _ O O\n" +
            "O O _ O _ _ O\n" +
            "    _ _ _\n" +
            "    _ _ _\n" +
            "Score: 15\n" +
            "    _ O O\n" +
            "    O O _\n" +
            "_ O _ O _ O O\n" +
            "_ _ _ O _ O O\n" +
            "_ _ O O _ _ O\n" +
            "    _ _ _\n" +
            "    _ _ _\n" +
            "Score: 14\n" +
            "    _ O O\n" +
            "    O O _\n" +
            "_ O _ O _ O O\n" +
            "_ _ _ O _ O O\n" +
            "_ _ _ _ O _ O\n" +
            "    _ _ _\n" +
            "    _ _ _\n" +
            "Score: 13\n" +
            "    _ O O\n" +
            "    O O _\n" +
            "_ O _ O O _ _\n" +
            "_ _ _ O _ O O\n" +
            "_ _ _ _ O _ O\n" +
            "    _ _ _\n" +
            "    _ _ _\n" +
            "Score: 12\n" +
            "    _ O O\n" +
            "    O O _\n" +
            "_ O _ _ _ O _\n" +
            "_ _ _ O _ O O\n" +
            "_ _ _ _ O _ O\n" +
            "    _ _ _\n" +
            "    _ _ _\n" +
            "Score: 11\n" +
            "    _ O O\n" +
            "    O O _\n" +
            "_ O _ _ _ O O\n" +
            "_ _ _ O _ O _\n" +
            "_ _ _ _ O _ _\n" +
            "    _ _ _\n" +
            "    _ _ _\n" +
            "Score: 10\n" +
            "    _ O O\n" +
            "    O O _\n" +
            "_ O _ _ O _ _\n" +
            "_ _ _ O _ O _\n" +
            "_ _ _ _ O _ _\n" +
            "    _ _ _\n" +
            "    _ _ _\n" +
            "Score: 9\n" +
            "    O _ _\n" +
            "    O O _\n" +
            "_ O _ _ O _ _\n" +
            "_ _ _ O _ O _\n" +
            "_ _ _ _ O _ _\n" +
            "    _ _ _\n" +
            "    _ _ _\n" +
            "Score: 8\n" +
            "    _ _ _\n" +
            "    _ O _\n" +
            "_ O O _ O _ _\n" +
            "_ _ _ O _ O _\n" +
            "_ _ _ _ O _ _\n" +
            "    _ _ _\n" +
            "    _ _ _\n" +
            "Score: 7\n" +
            "    _ _ _\n" +
            "    _ O _\n" +
            "_ _ _ O O _ _\n" +
            "_ _ _ O _ O _\n" +
            "_ _ _ _ O _ _\n" +
            "    _ _ _\n" +
            "    _ _ _\n" +
            "Score: 6\n" +
            "    _ _ _\n" +
            "    _ O _\n" +
            "_ _ _ _ _ O _\n" +
            "_ _ _ O _ O _\n" +
            "_ _ _ _ O _ _\n" +
            "    _ _ _\n" +
            "    _ _ _\n" +
            "Score: 5\n" +
            "    _ _ _\n" +
            "    _ O _\n" +
            "_ _ _ _ _ _ _\n" +
            "_ _ _ O _ _ _\n" +
            "_ _ _ _ O O _\n" +
            "    _ _ _\n" +
            "    _ _ _\n" +
            "Score: 4\n" +
            "    _ _ _\n" +
            "    _ O _\n" +
            "_ _ _ _ _ _ _\n" +
            "_ _ _ O _ _ _\n" +
            "_ _ _ O _ _ _\n" +
            "    _ _ _\n" +
            "    _ _ _\n" +
            "Score: 3\n" +
            "    _ _ _\n" +
            "    _ O _\n" +
            "_ _ _ O _ _ _\n" +
            "_ _ _ _ _ _ _\n" +
            "_ _ _ _ _ _ _\n" +
            "    _ _ _\n" +
            "    _ _ _\n" +
            "Score: 2\n" +
            "    _ _ _\n" +
            "    _ _ _\n" +
            "_ _ _ _ _ _ _\n" +
            "_ _ _ O _ _ _\n" +
            "_ _ _ _ _ _ _\n" +
            "    _ _ _\n" +
            "    _ _ _\n" +
            "Score: 1\n" +
            "Game over!\n" +
            "    _ _ _\n" +
            "    _ _ _\n" +
            "_ _ _ _ _ _ _\n" +
            "_ _ _ O _ _ _\n" +
            "_ _ _ _ _ _ _\n" +
            "    _ _ _\n" +
            "    _ _ _\n" +
            "Score: 1", out.toString());
  }

  /**
   * Test win with non-integers interrupting.
   */
  @Test
  public void testWinWithBadChar() {
    Appendable out = new StringBuffer();
    Reader in = new StringReader("4 2 4 4 *@E#$ 6 3 4 3 5 1 5 3 5 4 5 2 5 6 5 4 7 5 5 5 4 5 6 5" +
            " 7 3 7 5 aaaaaa 7 5 5 5 3 3 5 3 1 3 3 3 2 5 4 5 4 5 6 5 6 5 6 3 6 3 4 3 4 3 2 3" +
            " 3 1 5 1" +
            " 5 1 5 3 5 3 5 5 3 7 3 5 3 4 3 6 5 7 3 7 3 7 3 5 1 5 1 3 1 3 3 3 3 2 3 4 3 " +
            "4 3 6 3 6 5 6 5 6 5 4 5 4 3 4 2 4 4 4");
    MarbleSolitaireModel model1 = new EnglishSolitaireModel();
    MarbleSolitaireView view1 = new MarbleSolitaireTextView(model1, out);
    MarbleSolitaireController test1 = new MarbleSolitaireControllerImpl(model1, view1, in);
    test1.playGame();
    assertEquals("    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 32\n" +
            "    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O _ _ O O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 31\n" +
            "Invalid move. Play again.\n" +
            "    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O _ O O O O O\n" +
            "O O _ O O O O\n" +
            "    _ O O\n" +
            "    O O O\n" +
            "Score: 30\n" +
            "    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O _ O O O O O\n" +
            "_ _ O O O O O\n" +
            "    _ O O\n" +
            "    O O O\n" +
            "Score: 29\n" +
            "    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O _ O O O O O\n" +
            "_ O _ _ O O O\n" +
            "    _ O O\n" +
            "    O O O\n" +
            "Score: 28\n" +
            "    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O _ O O O O O\n" +
            "_ O _ O _ _ O\n" +
            "    _ O O\n" +
            "    O O O\n" +
            "Score: 27\n" +
            "    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O _ O O O O O\n" +
            "_ O _ O O _ O\n" +
            "    _ O _\n" +
            "    O O _\n" +
            "Score: 26\n" +
            "    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O _ O O _ O O\n" +
            "_ O _ O _ _ O\n" +
            "    _ O O\n" +
            "    O O _\n" +
            "Score: 25\n" +
            "    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O _ O O _ O O\n" +
            "_ O _ O _ _ O\n" +
            "    _ O O\n" +
            "    _ _ O\n" +
            "Score: 24\n" +
            "Invalid move. Play again.\n" +
            "    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O _ O O _ O O\n" +
            "_ O _ O O _ O\n" +
            "    _ O _\n" +
            "    _ _ _\n" +
            "Score: 23\n" +
            "    O O O\n" +
            "    O O O\n" +
            "O O _ O O O O\n" +
            "O _ _ O _ O O\n" +
            "_ O O O O _ O\n" +
            "    _ O _\n" +
            "    _ _ _\n" +
            "Score: 22\n" +
            "    _ O O\n" +
            "    _ O O\n" +
            "O O O O O O O\n" +
            "O _ _ O _ O O\n" +
            "_ O O O O _ O\n" +
            "    _ O _\n" +
            "    _ _ _\n" +
            "Score: 21\n" +
            "    _ O O\n" +
            "    _ O _\n" +
            "O O O O _ O O\n" +
            "O _ _ O O O O\n" +
            "_ O O O O _ O\n" +
            "    _ O _\n" +
            "    _ _ _\n" +
            "Score: 20\n" +
            "    _ O O\n" +
            "    _ O _\n" +
            "O O O O _ O O\n" +
            "O _ _ O _ O O\n" +
            "_ O O O _ _ O\n" +
            "    _ O O\n" +
            "    _ _ _\n" +
            "Score: 19\n" +
            "    _ O O\n" +
            "    _ O _\n" +
            "O O O O _ O O\n" +
            "O _ _ O _ O O\n" +
            "_ O O O _ _ O\n" +
            "    O _ _\n" +
            "    _ _ _\n" +
            "Score: 18\n" +
            "    _ O O\n" +
            "    _ O _\n" +
            "O O O O _ O O\n" +
            "O _ O O _ O O\n" +
            "_ O _ O _ _ O\n" +
            "    _ _ _\n" +
            "    _ _ _\n" +
            "Score: 17\n" +
            "    _ O O\n" +
            "    O O _\n" +
            "O O _ O _ O O\n" +
            "O _ _ O _ O O\n" +
            "_ O _ O _ _ O\n" +
            "    _ _ _\n" +
            "    _ _ _\n" +
            "Score: 16\n" +
            "    _ O O\n" +
            "    O O _\n" +
            "_ O _ O _ O O\n" +
            "_ _ _ O _ O O\n" +
            "O O _ O _ _ O\n" +
            "    _ _ _\n" +
            "    _ _ _\n" +
            "Score: 15\n" +
            "    _ O O\n" +
            "    O O _\n" +
            "_ O _ O _ O O\n" +
            "_ _ _ O _ O O\n" +
            "_ _ O O _ _ O\n" +
            "    _ _ _\n" +
            "    _ _ _\n" +
            "Score: 14\n" +
            "    _ O O\n" +
            "    O O _\n" +
            "_ O _ O _ O O\n" +
            "_ _ _ O _ O O\n" +
            "_ _ _ _ O _ O\n" +
            "    _ _ _\n" +
            "    _ _ _\n" +
            "Score: 13\n" +
            "    _ O O\n" +
            "    O O _\n" +
            "_ O _ O O _ _\n" +
            "_ _ _ O _ O O\n" +
            "_ _ _ _ O _ O\n" +
            "    _ _ _\n" +
            "    _ _ _\n" +
            "Score: 12\n" +
            "    _ O O\n" +
            "    O O _\n" +
            "_ O _ _ _ O _\n" +
            "_ _ _ O _ O O\n" +
            "_ _ _ _ O _ O\n" +
            "    _ _ _\n" +
            "    _ _ _\n" +
            "Score: 11\n" +
            "    _ O O\n" +
            "    O O _\n" +
            "_ O _ _ _ O O\n" +
            "_ _ _ O _ O _\n" +
            "_ _ _ _ O _ _\n" +
            "    _ _ _\n" +
            "    _ _ _\n" +
            "Score: 10\n" +
            "    _ O O\n" +
            "    O O _\n" +
            "_ O _ _ O _ _\n" +
            "_ _ _ O _ O _\n" +
            "_ _ _ _ O _ _\n" +
            "    _ _ _\n" +
            "    _ _ _\n" +
            "Score: 9\n" +
            "    O _ _\n" +
            "    O O _\n" +
            "_ O _ _ O _ _\n" +
            "_ _ _ O _ O _\n" +
            "_ _ _ _ O _ _\n" +
            "    _ _ _\n" +
            "    _ _ _\n" +
            "Score: 8\n" +
            "    _ _ _\n" +
            "    _ O _\n" +
            "_ O O _ O _ _\n" +
            "_ _ _ O _ O _\n" +
            "_ _ _ _ O _ _\n" +
            "    _ _ _\n" +
            "    _ _ _\n" +
            "Score: 7\n" +
            "    _ _ _\n" +
            "    _ O _\n" +
            "_ _ _ O O _ _\n" +
            "_ _ _ O _ O _\n" +
            "_ _ _ _ O _ _\n" +
            "    _ _ _\n" +
            "    _ _ _\n" +
            "Score: 6\n" +
            "    _ _ _\n" +
            "    _ O _\n" +
            "_ _ _ _ _ O _\n" +
            "_ _ _ O _ O _\n" +
            "_ _ _ _ O _ _\n" +
            "    _ _ _\n" +
            "    _ _ _\n" +
            "Score: 5\n" +
            "    _ _ _\n" +
            "    _ O _\n" +
            "_ _ _ _ _ _ _\n" +
            "_ _ _ O _ _ _\n" +
            "_ _ _ _ O O _\n" +
            "    _ _ _\n" +
            "    _ _ _\n" +
            "Score: 4\n" +
            "    _ _ _\n" +
            "    _ O _\n" +
            "_ _ _ _ _ _ _\n" +
            "_ _ _ O _ _ _\n" +
            "_ _ _ O _ _ _\n" +
            "    _ _ _\n" +
            "    _ _ _\n" +
            "Score: 3\n" +
            "    _ _ _\n" +
            "    _ O _\n" +
            "_ _ _ O _ _ _\n" +
            "_ _ _ _ _ _ _\n" +
            "_ _ _ _ _ _ _\n" +
            "    _ _ _\n" +
            "    _ _ _\n" +
            "Score: 2\n" +
            "    _ _ _\n" +
            "    _ _ _\n" +
            "_ _ _ _ _ _ _\n" +
            "_ _ _ O _ _ _\n" +
            "_ _ _ _ _ _ _\n" +
            "    _ _ _\n" +
            "    _ _ _\n" +
            "Score: 1\n" +
            "Game over!\n" +
            "    _ _ _\n" +
            "    _ _ _\n" +
            "_ _ _ _ _ _ _\n" +
            "_ _ _ O _ _ _\n" +
            "_ _ _ _ _ _ _\n" +
            "    _ _ _\n" +
            "    _ _ _\n" +
            "Score: 1", out.toString());
  }

}

