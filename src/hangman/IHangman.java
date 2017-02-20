package eg.edu.alexu.csd.datastructure.hangman;

/**
 *
 * @author Mohamed Ayman
 *
 */
public interface IHangman {

  /**
   * Sets the dictionary.
   * @param words Array of strings to define the words
   * available in the dictionary.
   */
  void setDictionary(String[] words);

  /**
   * Selects a random word from the dictionary.
   * @return returns the randomly selected secret word
   */
  String selectRandomSecretWord();

  /**
   * Receive a new user guess, and verify it against the secret word.
   *
   * @param guessChar
   *          case insensitive user guess. If c is NULL then ignore it and do no
   *          change
   * @return secret word with hidden characters (use ’-’ instead unsolved
   *         characters), or return NULL if user reached max wrong guesses.
   */
  String guess(Character guessChar);

  /**
   * Set the maximum number of wrong guesses.
   *
   * @param max
   *          maximum number of wrong guesses, If is NULL, then assume it 0
   */
  void setMaxWrongGuesses(Integer max);
}
