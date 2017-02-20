package eg.edu.alexu.csd.datastructure.queue.cs64;

/**
 * Doubly Linkedlist node.
 * @author Mohamed Ayman
 */
public class Node {
  /**
   * Value of the node.
   */
  private Object value = null;
  /**
   * Next node.
   */
  private Node next = null;
  /**
   * Previous node.
   */
  private Node prev = null;

  /**
   * Constructor that takes 3 parameters which
   * are the value, next Node and the previous node.
   * @param val Value of the node
   * @param prv previous node
   * @param nxt next node
   */
  public Node(final Object val, final Node prv, final Node nxt) {
    value = val;
    prev = prv;
    next = nxt;
  }

  /**
   * Object getter method.
   * @return value embedded in the node.
   */
  public final Object getValue() {
    return value;
  }

  /**
   * Sets the next node.
   * @param nextNode
   *          nextNode to be set
   */
  public final void setNext(final Node nextNode) {
    next = nextNode;
  }

  /**
   * sets the previous node.
   * @param prevNode
   *          previous node to be set
   */
  public final void setPrev(final Node prevNode) {
    prev = prevNode;
  }

  /**
   * getter for the previous node.
   * @return the previous node.
   */
  public final Node getPrev() {
    return prev;
  }

  /**
   * getter for the next node.
   * @return the next node.
   */
  public final Node getNext() {
    return next;
  }
}
