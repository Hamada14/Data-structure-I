package eg.edu.alexu.csd.datastructure.hangman.cs64;

import eg.edu.alexu.csd.datastructure.hangman.IHangman;

import java.util.Random;

/**
 *
 * @author Mohamed Ayman
 *Implementation of the IHangman Interface used to
 *develope the hangman game.
 */
public class HangMan implements IHangman {
  /**
   * Dictionary of words used to select a random
   * word.
   */
  private String[] dict;
  /**
   * the result that the user has reached till
   * the current state of the game.
   */
  private char[] result;
  /**
   * Maximum number of wrong guesses.
   */
  private int maxWrongGuesses;
  /**
   * Current number of wrong guesses.
   */
  private int wrongGuesses = 0;
  /**
   * The random word.
   */
  private String word;

  @Override
  public final void setDictionary(final String[] words) {
    dict = words;
  }

  @Override
  public final void setMaxWrongGuesses(final Integer max) {
    if (max == null) {
      maxWrongGuesses = 0;
    } else {
      maxWrongGuesses = max.intValue();
    }
  }

  @Override
  public final String guess(final Character guessChar) {
    int found = 0;
    if (wrongGuesses >= maxWrongGuesses) {
      return null;
    }
    if (wrongGuesses == maxWrongGuesses || dict == null) {
      return null;
    }
    if (guessChar == null) {
      return new String(result);
    }
    for (int counter = 0; counter < word.length(); counter++) {
      char firstChar = word.toLowerCase().charAt(counter);
      char secondChar = Character.toLowerCase(guessChar);
      if (firstChar == secondChar) {
        result[counter] = word.charAt(counter);
        found++;
      }
    }
    if (found == 0) {
      wrongGuesses++;
    }
    if (wrongGuesses == maxWrongGuesses) {
      return null;
    }
    return new String(result);
  }

  @Override
  public final String selectRandomSecretWord() {
    if (dict == null || dict.length == 0) {
      return null;
    }
    Random randVar = new Random();
    int randNum = randVar.nextInt(dict.length);
    if (dict[randNum] != null) {
      result = new char[dict[randNum].length()];
      for (int counter = 0; counter < dict[randNum].length(); counter++) {
        result[counter] = '-';
      }
    }
    word = dict[randNum];
    return word;
  }
}
