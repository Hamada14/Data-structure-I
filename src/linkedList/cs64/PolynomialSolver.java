package eg.edu.alexu.csd.datastructure.linkedList.cs64;

import eg.edu.alexu.csd.datastructure.linkedList.IPolynomialSolver;

import java.awt.Point;
import java.util.Scanner;

/**
 * @author Hamada14
 */
public class PolynomialSolver implements IPolynomialSolver {

  /**
   * A polynomial equation.
   */
  private DLinkedList firstList = new DLinkedList();
  /**
   * B polynomial equation.
   */
  private DLinkedList secondList = new DLinkedList();
  /**
   * C Polynomial equation.
   */
  private DLinkedList thirdList = new DLinkedList();
  /**
   * R polynomial equation.
   */
  private DLinkedList accList = new DLinkedList();
  /**
   * State of A equation if set or not.
   */
  private boolean firstSet = false;
  /**
   * State of B equation if set or not.
   */
  private boolean secondSet = false;
  /**
   * State of C equation if set or not.
   */
  private boolean thirdSet = false;
  /**
   * scanner for the user interface.
   */
  private static Scanner s = new Scanner(System.in);
  /**
   * Value of addition in the user interface.
   */
  private static final int ADD = 3;
  /**
   * Value of subtraction in the user interface.
   */
  private static final int SUB = 4;
  /**
   * Value of multiplication in the user interface.
   */
  private static final int MULT = 5;
  /**
   * Value of evaluation in the user interface.
   */
  private static final int EVALUATE = 6;
  /**
   * value of clearing polynomial in the user
   * interface.
   */
  private static final int CLEAR = 7;
  /**
   * Max size of array.
   */
  private static final int MAX = 1000;

  /**
   * The user interface.
   * @param args cmd line arguments.
   */
  public static void main(final String[] args) {
    PolynomialSolver app = new PolynomialSolver();
    while (true) {
      printInterface();
      int operation = s.nextInt();
      s.nextLine();
      if (operation < 1 || operation > CLEAR) {
        System.out.println("This choice isn't included in the List.");
      } else {
        app.doIt(operation);
      }
      System.out.println("-------------------------\n");
    }
  }

  /**
   * Executes an operation for the user.
   * @param choice which operation.
   */
  public final void doIt(final int choice) {
    switch (choice) {
    case 1:
      getPolynomial();
      break;
    case 2:
      System.out.println("Insert the variable name : A, B, C or R");
      char poly = s.nextLine().charAt(0);
      while (poly != 'A' && poly != 'B' && poly != 'C' && poly != 'R') {
        System.out.println("Warning: Insert one of the listed letters");
        System.out.println("Insert the variable name : A, B, C or R");
        poly = s.nextLine().charAt(0);
      }
      String display = print(poly);
      if (display != null) {
        System.out.println("Value in " + poly + ": " + display);
      } else {
        System.out.println("Value in " + poly + " isn't set");
      }
      break;
    case EVALUATE:
      evaluatePoly();
      break;
    case CLEAR:
      clearFun();
      break;
    default:
      oppPoly(choice);
    }
  }

  /**
   * Evaluates a polynomial for the user.
   */
  public final void evaluatePoly() {
    System.out.println("Insert the first variable name : A, B, C or R");
    char poly1 = s.nextLine().charAt(0);
    while (!checkPoly(poly1)) {
      System.out.println("Warning: Insert a set list");
      System.out.println("Insert the variable name : A, B, C or R");
      poly1 = s.nextLine().charAt(0);
    }
    System.out.println("Enter the value of R: ");
    float value = s.nextFloat();
    s.nextLine();
    System.out.println("The Value is: " + evaluatePolynomial(poly1, value));
  }

  /**
   * Clears a polynomial equation.
   */
  public final void clearFun() {
    System.out.println("Insert the variable name : A, B, or C");
    char poly1 = s.nextLine().charAt(0);
    while (poly1 != 'A' && poly1 != 'B' && poly1 != 'C') {
      System.out.println("Warning: Insert one of the listed letters");
      System.out.println("Insert the variable name : A, B, or C");
      poly1 = s.nextLine().charAt(0);
    }
    clearPolynomial(poly1);
    System.out.println("The List is Cleared.");
  }

  /**
   * Checks if a polynomial is set or not.
   * @param poly which polynomial to check.
   * @return whether it's checked or not.
   */
  public final boolean checkPoly(final char poly) {
    if (poly == 'A' && firstSet) {
      return true;
    } else if (poly == 'B' && secondSet) {
      return true;
    } else if (poly == 'C' && thirdSet) {
      return true;
    } else if (poly == 'R') {
      return true;
    }
    return false;
  }

  /**
   * Checks which operation to execute.
   * @param choice the user choice of operation.
   */
  public final void oppPoly(final int choice) {
    System.out.println("Insert the first variable name : A, B, or C");
    char poly1 = s.nextLine().charAt(0);
    while (!checkPoly(poly1)) {
      System.out.println("Warning: Insert a set list");
      System.out.println("Insert the variable name : A, B, or C");
      poly1 = s.nextLine().charAt(0);
    }
    System.out.println("Insert the second variable name : A, B, or C");
    char poly2 = s.nextLine().charAt(0);
    while (!checkPoly(poly2)) {
      System.out.println("Warning: Insert a set list");
      System.out.println("Insert the variable name : A, B, or C");
      poly2 = s.nextLine().charAt(0);
    }
    accList.clear();
    if (choice == ADD) {
      add(poly1, poly2);
    } else if (choice == SUB) {
      subtract(poly1, poly2);
    } else if (choice == MULT) {
      multiply(poly1, poly2);
    }
    printR();
  }

  /**
   * Prints the resulting equation.
   */
  public final void printR() {
    System.out.print("Result set in R: ");
    for (int counter = 0; counter < accList.size(); counter++) {
      Point point = (Point) accList.get(counter);
      System.out.print(" (" + point.x + "," + point.y + ") ");
      if (counter < accList.size() - 1) {
        System.out.print(", ");
      }
    }
    System.out.println();
  }

  /**
   * Gets a polynomial equation from the user and
   * validates it.
   */
  public final void getPolynomial() {
    System.out.println("Insert the variable name : A, B or C");
    char poly = s.nextLine().charAt(0);
    while (poly != 'A' && poly != 'B' && poly != 'C') {
      System.out.println("Warning: Insert one of the listed letters");
      System.out.println("Insert the variable name : A, B or C");
      poly = s.nextLine().charAt(0);
    }
    System.out.println("Insert the polynomial terms in the form :");
    System.out.println("(coeff1 , exponent1 ), (coeff2 , exponent2 ), ..");
    String input = s.nextLine();
    int[][] inputArr = new int[input.length()][2];
    int numTerms = 0;
    String val = new String();
    Point term = new Point();
    String str = new String();
    int cc = 0;
    for (int counter = 0; counter < input.length(); counter++) {
      if (input.charAt(counter) == '(' || input.charAt(counter) == ','
          || input.charAt(counter) == ' ' || input.charAt(counter) == ')') {
        str += "*";
      } else {
        str += input.charAt(counter);
      }
    }
    for (int counter = 0; counter < str.length(); counter++) {
      if (str.charAt(counter) != '*') {
        val += str.charAt(counter);
      }
      if (str.charAt(counter) != '*' && str.charAt(counter + 1) == '*') {
        if (cc % 2 == 0) {
          term.x = Integer.parseInt(val);
        } else {
          term.y = Integer.parseInt(val);
          inputArr[numTerms][0] = term.x;
          inputArr[numTerms++][1] = term.y;
        }
        val = new String();
        cc++;
      }
    }
    int[][] arr = new int[numTerms][2];
    for (int counter = 0; counter < numTerms; counter++) {
      arr[counter][0] = inputArr[counter][0];
      arr[counter][1] = inputArr[counter][1];
      if (counter < numTerms - 1
          && inputArr[counter][1] < inputArr[counter + 1][1]) {
        System.out.println("Invalid Input");
        return;
      }
    }
    setPolynomial(poly, arr);
  }

  /**
   * Prints the main interface of the program.
   */
  public static void printInterface() {
    System.out.println("Please choose an action");
    System.out.println("-----------------------");
    System.out.println("1- Set a polynomial variable");
    System.out.println("2- Print the value of a polynomial variable");
    System.out.println("3- Add two polynomials");
    System.out.println("4- Subtract two polynomials");
    System.out.println("5- Multiply two polynomials");
    System.out.println("6- Evaluate a polynomial at some point");
    System.out.println("7- Clear a polynomial variable");
  }

  /**
   * Sets an equation.
   * @param poly which equation to set.
   * @return returns an empty Doubly linked list.
   */
  public final DLinkedList setList(final char poly) {
    switch (poly) {
    case 'A':
      return firstList;
    case 'B':
      return secondList;
    case 'C':
      return thirdList;
    case 'R':
      return accList;
    default:
      return new DLinkedList();
    }
  }

  /**
   * Checks whether an equation is set or not.
   * @param poly which equation to check.
   */
  public final void checkNotSet(final char poly) {
    switch (poly) {
    case 'A':
      if (!firstSet) {
        throw new RuntimeException();
      }
      break;
    case 'B':
      if (!secondSet) {
        throw new RuntimeException();
      }
      break;
    case 'R':
      break;
    default:
      if (!thirdSet) {
        throw new RuntimeException();
      }
      break;
    }
  }

  @Override
  public void setPolynomial(final char poly, final int[][] terms) {
    if (poly != 'A' && poly != 'B' && poly != 'C') {
      throw new RuntimeException("A");
    }
    int[] arr = new int[MAX];
    for (int i = 0; i < terms.length; i++) {
      arr[terms[i][1]] += terms[i][0];
      if (i < terms.length - 1 && terms[i][1] <= terms[i + 1][1]) {
        throw new RuntimeException("B");
      }
    }
    DLinkedList myList = setList(poly);
    switch (poly) {
    case 'A':
      firstSet = true;
      break;
    case 'B':
      secondSet = true;
      break;
    default:
      thirdSet = true;
    }
    for (int counter = 0; counter < MAX; counter++) {
      if (arr[counter] != 0) {
        myList.add(new Point(arr[counter], counter));
      }
    }
  }

  @Override
  public int[][] multiply(final char poly1, final char poly2) {
    DLinkedList first = setList(poly1);
    DLinkedList second = setList(poly2);
    checkNotSet(poly1);
    checkNotSet(poly2);
    int[] arr = new int[MAX];
    for (int counter = 0; counter < first.size(); counter++) {
      int coef1 = (int) ((Point) first.get(counter)).getX();
      int exp1 = (int) ((Point) first.get(counter)).getY();
      for (int counter2 = 0; counter2 < second.size(); counter2++) {
        int coef2 = (int) ((Point) second.get(counter2)).getX();
        int exp2 = (int) ((Point) second.get(counter2)).getY();
        arr[exp1 + exp2] += (coef1 * coef2);
      }
    }
    int cc = 0;
    for (int counter = 0; counter < MAX; counter++) {
      if (arr[counter] != 0) {
        cc++;
      }
    }
    int[][] arrResult = new int[cc][2];
    cc--;
    for (int counter = 0; counter < MAX; counter++) {
      if (arr[counter] != 0) {
        arrResult[cc][0] = arr[counter];
        arrResult[cc--][1] = counter;
      }
    }
    for (int counter2 = 0; counter2 < accList.size(); counter2++) {
      int coef2 = (int) ((Point) accList.get(counter2)).getX();
      int exp2 = (int) ((Point) accList.get(counter2)).getY();
      arr[exp2] += coef2;
    }
    accList.clear();
    for (int counter = MAX - 1; counter >= 0; counter--) {
      if (arr[counter] != 0) {
        accList.add(new Point(arr[counter], counter));
      }
    }
    return arrResult;
  }

  @Override
  public int[][] subtract(final char poly1, final char poly2) {
    if (poly1 == poly2) {
      return new int[][] {{0, 0 } };
    }
    DLinkedList second = setList(poly2);
    checkNotSet(poly2);
    for (int counter = 0; counter < second.size(); counter++) {
      int coefVal = (int) ((Point) (second.get(counter))).getX();
      int expVal = (int) ((Point) (second.get(counter))).getY();
      second.set(counter, new Point(-coefVal, expVal));

    }
    int[][] arr = add(poly1, poly2);
    for (int counter = 0; counter < second.size(); counter++) {
      int coefVal = (int) ((Point) (second.get(counter))).getX();
      int expVal = (int) ((Point) (second.get(counter))).getY();
      second.set(counter, new Point(-coefVal, expVal));
    }
    return arr;
  }

  @Override
  public int[][] add(final char poly1, final char poly2) {
    if (poly1 > 'C' || poly1 < 'A' || poly2 > 'C' || poly2 < 'A') {
      throw new RuntimeException();
    }
    int cc = 0;
    DLinkedList first = setList(poly1);
    checkNotSet(poly1);
    int[] arrInd = new int[MAX];
    for (int counter2 = 0; counter2 < first.size(); counter2++) {
      int exp1 = (int) ((Point) (first.get(counter2))).getY();
      int coef1 = (int) ((Point) (first.get(counter2))).getX();
      arrInd[exp1] += coef1;
    }
    DLinkedList second = setList(poly2);
    checkNotSet(poly2);
    for (int counter2 = 0; counter2 < second.size(); counter2++) {
      int exp1 = (int) ((Point) (second.get(counter2))).getY();
      int coef1 = (int) ((Point) (second.get(counter2))).getX();
      arrInd[exp1] += coef1;
    }
    for (int counter = 0; counter < MAX; counter++) {
      if (arrInd[counter] != 0) {
        cc++;
      }
    }
    int[][] arr = new int[cc][2];
    cc--;
    for (int counter = 0; counter < MAX; counter++) {
      if (arrInd[counter] != 0) {
        arr[cc][0] = arrInd[counter];
        arr[cc--][1] = counter;
      }
    }
    for (int counter2 = 0; counter2 < accList.size(); counter2++) {
      int exp1 = (int) ((Point) (accList.get(counter2))).getY();
      int coef1 = (int) ((Point) (accList.get(counter2))).getX();
      arrInd[exp1] += coef1;
    }
    accList.clear();
    for (int counter = MAX - 1; counter >= 0; counter--) {
      if (arrInd[counter] != 0) {
        accList.add(new Point(arrInd[counter], counter));
      }
    }
    if (cc == 0) {
      arr = new int[][] {{0, 0 } };
    }
    return arr;
  }

  @Override
  public float evaluatePolynomial(final char poly, final float value) {
    double result = 0;
    checkNotSet(poly);
    DLinkedList myList = setList(poly);
    for (int i = myList.size() - 1; i >= 0; i--) {
      double coef = ((Point) myList.get(i)).getX();
      double exp = ((Point) myList.get(i)).getY();
      result += coef * Math.pow(value, exp);
    }
    return (float) result;
  }

  @Override
  public void clearPolynomial(final char poly) {
    if (Character.toLowerCase(poly) == 'a') {
      firstList.clear();
      firstSet = false;
    } else if (Character.toLowerCase(poly) == 'b') {
      secondList.clear();
      secondSet = false;
    } else if (Character.toLowerCase(poly) == 'c') {
      thirdList.clear();
      thirdSet = false;
    } else if (Character.toLowerCase(poly) == 'r') {
      accList.clear();
    }
  }

  @Override
  public String print(final char poly) {
    if (poly != 'A' && poly != 'B' && poly != 'C' && poly != 'R') {
      throw new RuntimeException();
    }
    String display = new String();
    DLinkedList myList = setList(poly);
    for (int i = myList.size() - 1; i >= 0; i--) {
      int coef = (int) ((Point) myList.get(i)).getX();
      int exp = (int) ((Point) myList.get(i)).getY();
      if (i != myList.size() - 1 && coef > 0) {
        display += "+";
      }
      if (exp != 0) {
        if (coef == -1) {
          display += "-";
        } else if (coef != 1) {
          display += Integer.toString(coef);
        }
      } else if (coef != 1) {
        display += Integer.toString(coef);
      } else {
        display += "1";
      }
      if (exp > 0) {
        display += "X";
        if (exp > 1) {
          display += "^";
        }
        display += Integer.toString(exp);
      }
    }
    if (display.length() == 0) {
      return null;
    }
    return display;
  }
}
