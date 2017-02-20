package eg.edu.alexu.csd.datastructure.stack.cs64;

import java.util.ArrayList;
import java.util.Scanner;

import eg.edu.alexu.csd.datastructure.stack.IExpressionEvaluator;

/**
 * Class implementation of the Postfix-infix Evaluator and with the User
 * interface.
 * @author Mohamed Ayman
 */
public class ExpressionEvaluator implements IExpressionEvaluator {

  /**
   * Stack for the application.
   */
  private MyStack expEvaluator = new MyStack();
  /**
   * scanner used in the application.
   */
  private static Scanner s = new Scanner(System.in);
  /**
   * Array list of pairs to store the symbols values.
   */
  private static ArrayList<Pair<String, Integer>>
valueSymbol = new ArrayList<Pair<String, Integer>>();

  /**
   * main to provide a basic ui.
   * @param args command line arguments
   */
  public static void main(final String[] args) {
    ExpressionEvaluator myApp = new ExpressionEvaluator();
    System.out.println("Enter an Infix expression");
    do {
      try {
        String resultExp = myApp.infixToPostfix(s.nextLine());
        System.out.println("The result symbolic Postfix expression: "
            + resultExp);
        resultExp = validateSymbols(myApp, resultExp);
        System.out.println("The result Postfix Expression: ");
        System.out
            .println("The result Answer is: " + myApp.evaluate(resultExp));
        System.out.println("Enter an Infix expression again");
      } catch (Exception e) {
        System.out.println("You entered an Invalid Expression");
        System.out.println("The program is exitting");
        System.exit(1);
      }
    } while (s.hasNextLine());
  }

  /**
   * @param app
   *          the instance of the Expression Evaluator used by the main to
   *          execute actions done by the user.
   * @param exp
   *          the Infix expression which is to be validate to be clear of
   *          numbers, only symbolic terms.
   * @return returns a numeric expressions, replacing each term with it's value.
   */
  public static String validateSymbols(final ExpressionEvaluator app,
      final String exp) {
    int length = exp.length();
    StringBuilder fullTerm = new StringBuilder();
    for (int counter = 0; counter < length; counter++) {
      char digit = exp.charAt(counter);
      switch (digit) {
      case '+':
      case '-':
      case '*':
      case '/':
      case ' ':
        fullTerm.append(digit);
        continue;
      default:
        boolean found = false;
        StringBuilder term = new StringBuilder();
        for (int counter2 = counter; counter2 < length; counter2++) {
          counter++;
          term.append(exp.charAt(counter2));
          if (counter2 == length - 1 || exp.charAt(counter2 + 1) == ' ') {
            break;
          }
        }
        for (int symbIndex = 0; symbIndex < valueSymbol.size(); symbIndex++) {
          if (valueSymbol.get(symbIndex).getFirst().equals(term.toString())) {
            fullTerm.append(valueSymbol.get(symbIndex).getSecond() + " ");
            found = true;
            break;
          }
        }
        if (!found) {
          System.out.println("Enter the value of the term(" + term.toString()
              + ")");
          if (s.hasNextInt()) {
            int value = Integer.parseInt(s.nextLine());
            fullTerm.append(value + " ");
            valueSymbol.add(new Pair<String, Integer>(term.toString(), value));
            break;
          } else {
            System.out.println("Invalid Value, Program is exitting");
            System.exit(1);
          }
        }
      }
    }
    return fullTerm.toString();
  }

  /**
   * A method used to validate the expression by counting number of parenthesis,
   * operators and operands.
   * @param expression
   *          The expression to be validated.
   * @throws RuntimeException
   *           In case of invalid expression, the method throws a Runtime
   *           exception.
   */
  public final void checkParan(final String expression) {
    int length = expression.length();
    int leftPara = 0;
    int rightPara = 0;
    boolean op = false;
    for (int counter = 0; counter < length; counter++) {
      char digit = expression.charAt(counter);
      if (digit == '(') {
        leftPara++;
      } else if (digit == ')') {
        rightPara++;
      }
      if (rightPara > leftPara) {
        throw new RuntimeException();
      }
    }
    int nOperators = 0;
    int nNumbers = 0;
    for (int counter = 0; counter < length; counter++) {
      char digit = expression.charAt(counter);
      if (digit == '*' || digit == '+' || digit == '-' || digit == '/') {
        nOperators++;
      } else if (digit != ' ' && digit != '(' && digit != ')') {
        nNumbers++;
        for (int counter2 = counter + 1; counter2 < length; counter2++) {
          digit = expression.charAt(counter2);
          if (digit == '*' || digit == '+' || digit == '-' || digit == '/'
              || digit == ' ') {
            counter = counter2 - 1;
            break;
          }
        }
      }
    }
    if (nOperators != nNumbers - 1) {
      throw new RuntimeException();
    }
    if (rightPara != leftPara || op) {
      throw new RuntimeException();
    }
  }

  /**
   * @return the result of an evaluated expression
   * @throws RuntimeException
   *           In case there's more than one value in the stack at the end, It
   *           throws exception due to an invalid expression.
   */
  public final int solve() {
    MyStack resultExp = new MyStack();
    String term;
    while (!expEvaluator.isEmpty()) {
      term = (String) expEvaluator.pop();
      char firstDig = term.charAt(0);
      switch (firstDig) {
      case '+':
        if (resultExp.size() < 2) {
          throw new RuntimeException();
        }
        float firstVar = (Float) resultExp.pop();
        float secondVar = (Float) resultExp.pop();
        resultExp.push(new Float(firstVar + secondVar));
        break;
      case '-':
        if (resultExp.size() < 2) {
          throw new RuntimeException();
        }
        firstVar = (Float) resultExp.pop();
        secondVar = (Float) resultExp.pop();
        resultExp.push(new Float(secondVar - firstVar));
        break;
      case '*':
        if (resultExp.size() < 2) {
          throw new RuntimeException();
        }
        firstVar = (Float) resultExp.pop();
        secondVar = (Float) resultExp.pop();
        resultExp.push(new Float(firstVar * secondVar));
        break;
      case '/':
        if (resultExp.size() < 2) {
          throw new RuntimeException();
        }
        firstVar = (Float) resultExp.pop();
        secondVar = (Float) resultExp.pop();
        resultExp.push(new Float(secondVar / firstVar));
        break;
      default:
        resultExp.push(new Float(Integer.parseInt(term)));
      }
    }
    Float finalResult = (Float) resultExp.pop();
    if (!resultExp.isEmpty()) {
      throw new RuntimeException();
    }
    return finalResult.intValue();
  }

  @Override
  public final String infixToPostfix(final String expression) {
    checkParan(expression);
    int exLength = expression.length();
    MyStack operation = new MyStack();
    StringBuilder resultStr = new StringBuilder();
    char op = 0;
    for (int counter = 0; counter < exLength; counter++) {
      char symbol = expression.charAt(counter);
      switch (symbol) {
      case ' ':
        continue;
      case '+':
        if (!operation.isEmpty()) {
          op = ((String) operation.peek()).charAt(0);
        }
        if (operation.isEmpty() || op == '(') {
          operation.push(new String("+"));
        } else {
          String term = (String) operation.pop();
          resultStr.append(term + " ");
          counter--;
          continue;
        }
        break;
      case '*':
        if (!operation.isEmpty()) {
          op = ((String) operation.peek()).charAt(0);
        }
        if (operation.isEmpty() || op == '+' || op == '-' || op == '(') {
          operation.push(new String("*"));
        } else {
          String term = (String) operation.pop();
          resultStr.append(term + " ");
          counter--;
          continue;
        }
        break;
      case '-':
        if (!operation.isEmpty()) {
          op = ((String) operation.peek()).charAt(0);
        }
        if (operation.isEmpty() || op == '(') {
          operation.push(new String("-"));
        } else {
          String term = (String) operation.pop();
          resultStr.append(term + " ");
          counter--;
          continue;
        }
        break;
      case '/':
        if (!operation.isEmpty()) {
          op = ((String) operation.peek()).charAt(0);
        }
        if (operation.isEmpty() || op == '+' || op == '-' || op == '(') {
          operation.push(new String("/"));
        } else {
          String term = (String) operation.pop();
          resultStr.append(term + " ");
          counter--;
          continue;
        }
        break;
      case '(':
        operation.push(new String("("));
        break;
      case ')':
        String term = (String) operation.pop();
        while (!term.equals(new String("("))) {
          resultStr.append(term + " ");
          term = (String) operation.pop();
        }
        break;
      default:
        for (int counter2 = counter; counter2 < exLength; counter2++) {
          if (counter2 == exLength - 1
              || !Character.isDigit(expression.charAt(counter2 + 1))) {
            resultStr.append(new String(expression.substring(counter,
                counter2 + 1)) + " ");
            counter = counter2;
            break;
          }
        }
      }
    }
    while (!operation.isEmpty()) {
      resultStr.append((String) operation.pop() + " ");
    }
    String finalResult = resultStr.toString();
    return finalResult.substring(0, finalResult.length() - 1);
  }

  @Override
  public final int evaluate(final String expression) {
    if (expression == null || expression.length() == 0) {
      throw new RuntimeException("empty Expression");
    }
    int exLength = expression.length();
    int operation = 0, number = 0;
    MyStack expEvaluatorOpp = new MyStack();
    for (int counter = 0; counter < exLength; counter++) {
      char symbol = expression.charAt(counter);
      switch (symbol) {
      case ' ':
        continue;
      case '+':
      case '*':
      case '-':
      case '/':
        expEvaluatorOpp.push(new String(Character.toString(symbol)));
        operation++;
        break;
      default:
        number++;
        for (int counter2 = counter; counter2 < exLength; counter2++) {
          if (counter2 == exLength - 1
              || !Character.isDigit(expression.charAt(counter2 + 1))) {
            expEvaluatorOpp.push(new String(expression.substring(counter,
                counter2 + 1)));
            counter = counter2;
            break;
          }
        }
      }
    }
    while (!expEvaluatorOpp.isEmpty()) {
      expEvaluator.push(expEvaluatorOpp.pop());
    }
    if (number != operation + 1 || operation == 0 || number == 0) {
      throw new RuntimeException("Wrong Expression");
    }
    return solve();
  }
}
