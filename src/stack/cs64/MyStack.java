package eg.edu.alexu.csd.datastructure.stack.cs64;

import java.util.Scanner;

import eg.edu.alexu.csd.datastructure.linkedList.cs64.DLinkedList;
import eg.edu.alexu.csd.datastructure.stack.IStack;

/**
 * The class is an implementation of IStack which is a stack data structure with
 * the only modification. to add with index.
 * @author Mohamed Ayman
 */
public class MyStack implements IStack {

  /**
   * A Linked list used to implement the stack.
   */
  private DLinkedList myList = new DLinkedList();
  /**
   * Scanner to be used in the User interface.
   */
  private static Scanner s = new Scanner(System.in);

  /**
   * The main ui of the application of the stack.
   * @param args
   */
  /**
   * input Value for peek.
   */
  private static final int PEEK = 3;
  /**
   * input value for get.
   */
  private static final int GET = 4;
  /**
   * input value for check.
   */
  private static final int CHECK = 5;

  /**
   * the main method ui.
   * @param args cmd line arguments
   */
  public static void main(final String[] args) {
    MyStack stackApp = new MyStack();
    printList();
    do {
      int tempVar = Integer.parseInt(s.nextLine());
      executeAction(stackApp, tempVar);
      printList();
    } while (s.hasNextInt());
    System.out.println("You Entered an Invalid Input.");
    System.out.println("The program will Exit..");
  }

  /**
   * prints the list of option given by the Stack ui.
   */
  public static void printList() {
    System.out.println("Enter the number next to the desired option: ");
    System.out.println("1: Push");
    System.out.println("2: Pop");
    System.out.println("3: Peek");
    System.out.println("4: Get size");
    System.out.println("5: Check if empty");
  }

  /**
   * Executes an action given by user.
   * @param stack
   *          the stack on which the action is done.
   * @param var
   *          the id of the action from the list.
   */
  public static void executeAction(final MyStack stack, final int var) {
    switch (var) {
    case 1:
      System.out.println("Enter the number or String,"
          + " you wish to push to the stack.");
      stack.push(s.nextLine());
      break;
    case 2:
      if (stack.size() == 0) {
        System.out.println("Nothing to pop, The stack is empty.");
      } else {
        System.out.println((String) stack.pop());
      }
      break;
    case PEEK:
      if (stack.size() == 0) {
        System.out.println("Nothing to peek at, The stack is empty.");
      } else {
        System.out.println((String) stack.peek());
      }
      break;
    case GET:
      System.out.println(stack.size());
      break;
    case CHECK:
      if (stack.isEmpty()) {
        System.out.println("The Stack is empty.");
      } else {
        System.out.println("The Stack isn't empty.");
      }
      break;
    default:
      System.out.println("Please make sure to enter a number from 1-5");
    }
  }

  @Override
  public final void add(final int index, final Object element) {
    if (index > myList.size() || index < 0) {
      throw new RuntimeException();
    }
    myList.add(myList.size() - index, element);
  }

  @Override
  public final Object pop() {
    if (myList.isEmpty()) {
      throw new RuntimeException();
    }
    Object obj = myList.get(0);
    myList.remove(0);
    return obj;
  }

  @Override
  public final Object peek() {
    if (myList.isEmpty()) {
      throw new RuntimeException();
    }
    return myList.get(0);
  }

  @Override
  public final void push(final Object element) {
    myList.add(0, element);
  }

  @Override
  public final boolean isEmpty() {
    return myList.isEmpty();
  }

  @Override
  public final int size() {
    return myList.size();
  }

}
