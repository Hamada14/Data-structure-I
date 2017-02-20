package eg.edu.alexu.csd.datastructure.stack.cs64;

/**
 * An implementation of generic pair Class to relate 2 non previously type
 * defined variables to each other.
 * @author Mohamed Ayman
 * @param <A>
 *          first Variable
 * @param <B>
 *          second Variable
 */
public class Pair<A, B> {
  /**
   * first pair parameter.
   */
  private A first;
  /**
   * second pair parameter.
   */
  private B second;

  /**
   * Constructor for the pair.
   * @param firstVal first pair parameter.
   * @param secondVal second pair parameter.
   */
  public Pair(final A firstVal, final B secondVal) {
    this.first = firstVal;
    this.second = secondVal;
  }

  /**
   *first getter.
   * @return the first parameter of the pair.
   */
  public final A getFirst() {
    return first;
  }

  /**
   *second getter.
   * @return the second parameter of the pair.
   */
  public final B getSecond() {
    return second;
  }
  /**
   *first setter.
   *@param setVal value to be set
   */
  public final void setFirst(final A setVal) {
    first = setVal;
  }

  /**
   *second setter.
   *@param setVal value to be set
   */
  public final void setSecond(final B setVal) {
    second = setVal;
  }
}
