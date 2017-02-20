package eg.edu.alexu.csd.datastructure.calculator.cs64;

import eg.edu.alexu.csd.datastructure.calculator.ICalculator;

/**
 *
 * @author Mohamed Ayman
 *
 */
public class MyCal implements ICalculator {

  @Override
  public final int add(final int firstNum, final int secondNum) {
    return firstNum + secondNum;
  }

  @Override
  public final float divide(final int firstNum, final int secondNum) {
    return ((float) firstNum) / secondNum;
  }

}
