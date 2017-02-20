package eg.edu.alexu.csd.datastructure.linkedList.cs64;

import eg.edu.alexu.csd.datastructure.linkedList.ILinkedList;

/**
 * Doubly linked list implementation.
 * @author Mohamed Ayman
 *
 */
public class DLinkedList implements ILinkedList {

  /**
   * Sentinel head of the list.
   */
  private DoublyNode head = new DoublyNode(null, null, null);
  /**
   * Size counter of the nodes inside the list.
   */
  private int size = 0;
  /**
   * Sentinel tail of the list.
   */
  private DoublyNode tail = new DoublyNode(null, null, head);

  /**
   * Doubly linked list node class.
   * @author Mohamed Ayman
   *
   */
  public class DoublyNode {
    /**
     * Value of the node.
     */
    public Object head;
    /**
     * Reference to the next node.
     */
    public DoublyNode next;
    /**
     * reference to the previous node.
     */
    public DoublyNode prev;

    /**
     * Doubly Node constructor.
     * @param headNode the value of the node.
     * @param nextNode the reference to the next node.
     * @param prevNode the reference to the previous node.
     */
    public DoublyNode(final Object headNode,
        final DoublyNode nextNode, final DoublyNode prevNode) {
      head = headNode;
      next = nextNode;
      prev = prevNode;
    }

    /**
     * Sets the next node for a certain node.
     * @param obj reference to the next node.
     */
    public final void setNext(final Object obj) {
      next = (DoublyNode) obj;
    }

    /**
     * Sets the previous node for a certain node.
     * @param obj reference to the previous node.
     */
    public final void setPrev(final Object obj) {
      prev = (DoublyNode) obj;
    }
  }

  @Override
  public void add(final int index, final Object element) {
    if (size == 0) {
      head.setNext(tail);
    }
    if (index < 0 || index > size) {
      throw new RuntimeException();
    } else {
      DoublyNode iterator = head;
      for (int counter = 0; counter < index; counter++) {
        iterator = iterator.next;
      }
      DoublyNode nextNode = iterator.next;
      iterator.next = new DoublyNode(element, nextNode, iterator);
      if (nextNode == null) {
        throw new RuntimeException(Integer.toString(index));
      } else {
        nextNode.setPrev(iterator.next);
      }
      size++;
    }
  }

  @Override
  public boolean contains(final Object obj) {
    DoublyNode iterator = head;
    if (obj != null) {
      for (int counter = 0; counter < size; counter++) {
        iterator = iterator.next;
        if (obj.equals(iterator.head)) {
          return true;
        }
      }
    } else {
      for (int counter = 0; counter < size; counter++) {
        iterator = iterator.next;
        if (iterator.head == null) {
          return true;
        }
      }
    }
    return false;
  }

  @Override
  public ILinkedList sublist(final int fromIndex, final int toIndex) {
    if (toIndex >= size) {
      throw new RuntimeException();
    }
    DLinkedList sub = new DLinkedList();
    DoublyNode iterator = head;
    for (int counter = 0; counter <= toIndex; counter++) {
      iterator = iterator.next;
      if (counter >= fromIndex) {
        sub.add(iterator.head);
      }
    }
    return sub;
  }

  @Override
  public int size() {
    return size;
  }

  @Override
  public void add(final Object element) {
    if (size == 0) {
      head.setNext(tail);
    }
    DoublyNode iterator = head;
    for (int counter = 0; counter < size; counter++) {
      iterator = iterator.next;
    }
    DoublyNode nextNode = iterator.next;
    iterator.next = new DoublyNode(element, nextNode, iterator);
    size++;
  }

  @Override
  public void remove(final int index) {
    if (index < 0 || index >= size) {
      throw new RuntimeException();
    }
    DoublyNode iterator = head;
    for (int counter = 0; counter <= index; counter++) {
      iterator = iterator.next;
    }
    DoublyNode lastN = iterator.prev;
    DoublyNode nextN = iterator.next;
    lastN.setNext(nextN);
    nextN.setPrev(lastN);
    size--;
  }

  @Override
  public boolean isEmpty() {
    return (size == 0);

  }

  @Override
  public void clear() {
    head.setNext(null);
    size = 0;
  }

  @Override
  public void set(final int index, final Object element) {
    if (index < 0 || index >= size) {
      throw new RuntimeException();
    }
    DoublyNode iterator = head;
    for (int counter = 0; counter <= index; counter++) {
      iterator = iterator.next;
    }
    iterator.head = element;
  }

  @Override
  public Object get(final int index) {
    if (index < 0 || index >= size) {
      throw new RuntimeException();
    }
    DoublyNode iterator = head;
    for (int counter = 0; counter <= index; counter++) {
      iterator = iterator.next;
    }
    return iterator.head;
  }
}
