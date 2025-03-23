import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;

import cs3500.marblesolitaire.model.hw02.EnglishSolitaireModel;
import cs3500.marblesolitaire.model.hw04.EuropeanSolitaireModel;
import cs3500.marblesolitaire.view.MarbleSolitaireTextView;


/**
 * Class to test related methods in MarbleSolitaireTextView class.
 */
public class MarbleSolitaireTextViewTest extends TestCase {

  //English Solitaire
  private MarbleSolitaireTextView threeThree;
  private MarbleSolitaireTextView fiveFive;
  private MarbleSolitaireTextView threeThree1;
  private MarbleSolitaireTextView threeThree2;
  private MarbleSolitaireTextView threeThree3;
  //European Solitaire
  private MarbleSolitaireTextView eThreeThree;

  private String three = "    O O O\n    O O O\nO O O O O O O\nO O O _ O O O\nO O O O O O O"
          + "\n    O O O\n    O O O";
  //with a center at (2, 2)
  private String three1 = "    O O O\n    O O O\nO O O _ O O O\nO O O O O O O\nO O O O O O O"
          + "\n    O O O\n    O O O";
  private String five = "        O O O O O\n        O O O O O\n        O O O O O" +
          "\n        O O O O O\nO O O O O O O O O O O O O"
          + "\nO O O O O O O O O O O O O\nO O O O O O _ O O O O O O\nO O O O O O O O O O O O O"
          + "\nO O O O O O O O O O O O O\n        O O O O O\n        O O O O O\n        O O O O O"
          + "\n        O O O O O";

  private String eThree = "    O O O\n" +
          "  O O O O O\n" +
          "O O O O O O O\n" +
          "O O O _ O O O\n" +
          "O O O O O O O\n" +
          "  O O O O O\n" +
          "    O O O";

  /**
   * Setting up variables before testing.
   */
  @Before
  public void setUp() {
    threeThree = new MarbleSolitaireTextView(new EnglishSolitaireModel());
    threeThree1 = new MarbleSolitaireTextView(new EnglishSolitaireModel(3));
    threeThree2 = new MarbleSolitaireTextView(new EnglishSolitaireModel(3, 3, 3));
    threeThree3 = new MarbleSolitaireTextView(new EnglishSolitaireModel(2, 3));
    fiveFive = new MarbleSolitaireTextView(new EnglishSolitaireModel(5));

    eThreeThree = new MarbleSolitaireTextView(new EuropeanSolitaireModel());
  }

  /**
   * Testing toString method.
   */
  @Test
  public void testToString() {
    //English Solitaire Model
    assertEquals(three, threeThree.toString());
    assertEquals(three, threeThree1.toString());
    assertEquals(three, threeThree2.toString());
    assertEquals(three1, threeThree3.toString());
    assertEquals(five, fiveFive.toString());

    //European Solitaire Model
    assertEquals(eThree, eThreeThree.toString());
  }

  /**
   * Testing constructor.
   */
  @Test
  public void testConstructor() {
    try {
      new MarbleSolitaireTextView(null);
      fail(); //test fail if the constructor doesn't give an Illegal Argument Exception
    } catch (IllegalArgumentException e) {
      return;
    }
  }


}