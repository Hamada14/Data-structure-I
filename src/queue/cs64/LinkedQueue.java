package eg.edu.alexu.csd.datastructure.queue.cs64;

import eg.edu.alexu.csd.datastructure.queue.ILinkedBased;
import eg.edu.alexu.csd.datastructure.queue.IQueue;

/**
 * Queue implementation using linked list.
 * @author Mohamed Ayman
 */
public class LinkedQueue implements IQueue, ILinkedBased {

  /**
   * Current size of the Queue.
   */
  private int size = 0;
  /**
   * Sentinel head of the linked list.
   */
  private Node head = new Node(null, null, null);
  /**
   * Sentinel tail of the linked list.
   */
  private Node tail = new Node(null, null, null);

  @Override
  public final void enqueue(final Object item) {
    if (size == 0) {
      head.setNext(tail);
      tail.setPrev(head);
    }
    size++;
    Node prevNode = tail.getPrev();
    Node temp = new Node(item, prevNode, tail);
    prevNode.setNext(temp);
    tail.setPrev(temp);
  }

  @Override
  public final Object dequeue() {
    if (size == 0) {
      throw new RuntimeException();
    }
    Node temp = head.getNext();
    Node nextNode = temp.getNext();
    head.setNext(nextNode);
    nextNode.setPrev(head);
    size--;
    return temp.getValue();
  }

  @Override
  public final boolean isEmpty() {
    return size == 0;
  }

  @Override
  public final int size() {
    return size;
  }

}
