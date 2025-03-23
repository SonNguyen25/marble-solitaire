
import org.junit.Test;
import junit.framework.TestCase;

import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelState;
import cs3500.marblesolitaire.model.hw04.TriangleSolitaireModel;
import cs3500.marblesolitaire.view.MarbleSolitaireView;
import cs3500.marblesolitaire.view.TriangleSolitaireTextView;


/**
 * Class to test related methods in MarbleSolitaireTextView class.
 */
public class TriangleSolitaireTextViewTest extends TestCase {


  /**
   * Testing toString method.
   */
  @Test
  public void testToString() {
    MarbleSolitaireModelState m = new TriangleSolitaireModel();
    MarbleSolitaireView fiveView = new TriangleSolitaireTextView(m);
    String five = "    _"
            + "\n   O O"
            + "\n  O O O"
            + "\n O O O O"
            + "\nO O O O O";
    assertEquals(five, fiveView.toString());

    MarbleSolitaireModelState armThick = new TriangleSolitaireModel(8);
    MarbleSolitaireView armThickView = new TriangleSolitaireTextView(armThick);
    String armThickString = "       _\n" +
            "      O O\n" +
            "     O O O\n" +
            "    O O O O\n" +
            "   O O O O O\n" +
            "  O O O O O O\n" +
            " O O O O O O O\n" +
            "O O O O O O O O";
    assertEquals(armThickString, armThickView.toString());

    MarbleSolitaireModelState empty = new TriangleSolitaireModel(3, 3);
    MarbleSolitaireView emptyView = new TriangleSolitaireTextView(empty);
    String emptySlot = "    O\n" +
            "   O O\n" +
            "  O O O\n" +
            " O O O _\n" +
            "O O O O O";
    assertEquals(emptySlot, emptyView.toString());

    MarbleSolitaireModelState full = new TriangleSolitaireModel(4, 3, 1);
    MarbleSolitaireView fullView = new TriangleSolitaireTextView(full);
    String fullString = "   O\n" +
            "  O O\n" +
            " O O O\n" +
            "O _ O O";
    assertEquals(fullString, fullView.toString());
  }

  /**
   * Testing constructor.
   */
  @Test
  public void testConstructor() {
    try {
      new TriangleSolitaireTextView(null);
      fail(); //test fail if the constructor doesn't give an Illegal Argument Exception
    } catch (IllegalArgumentException e) {
      return;
    }
  }
}