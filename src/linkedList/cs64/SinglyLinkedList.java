package eg.edu.alexu.csd.datastructure.linkedList.cs64;

import eg.edu.alexu.csd.datastructure.linkedList.ILinkedList;

/**
 * Singly linked list implementation.
 * @author Moustafa Mahmoud
 *
 */
public class SinglyLinkedList implements ILinkedList {

  /**
   * Counter of the number of nodes in the linked list.
   */
  private int numNodes = 0;

  /**
   * Single linked list node class.
   * @author Moustafa Mahmoud
   *
   */
  public class SList {
    /**
     * Value of the node.
     */
    public Object item;
    /**
     * next node reference.
     */
    public SList next;

    /**
     * Constructor of singly linked list node.
     * @param obj value of the node
     * @param node reference to the next node
     */
    public SList(final Object obj, final SList node) {
      this.item = obj;
      this.next = node;
    }
  }

  /**
   * Sentinel tail of the list.
   */
  public SList tail = new SList(null, null);
  /**
   * Sentinel head of the list.
   */
  public SList head = new SList(null, tail);

  @Override
  public void add(final int index, final Object element) {
    if (index < 0 || index > numNodes) {
      throw new RuntimeException("Out Of Range");
    }
    SList nodeToAdd = new SList(element, null);
    SList temp = head;
    for (int counter = 0; counter <= index - 1; counter++) {
      temp = temp.next;
    }
    nodeToAdd.next = temp.next;
    temp.next = nodeToAdd;
    numNodes++;
  }

  @Override
  public void add(final Object element) {
    SList nodeToAdd = new SList(element, null);
    SList temp = head;
    for (int counter = 0; counter < numNodes; counter++) {
      temp = temp.next;
    }
    nodeToAdd.next = temp.next;
    temp.next = nodeToAdd;
    numNodes++;
  }

  @Override
  public Object get(final int index) {
    if (index < 0 || index >= numNodes) {
      throw new RuntimeException("Out Of Range");
    }
    SList temp = head;
    for (int counter = 0; counter <= index; counter++) {
      temp = temp.next;
    }
    Object re = temp.item;
    return re;
  }

  @Override
  public void set(final int index, final Object element) {
    if (index < 0 || index >= numNodes) {
      throw new RuntimeException("Out Of Range");
    }
    SList temp = head;
    for (int counter = 0; counter < index; counter++) {
      temp = temp.next;
    }
    temp.next.item = element;
  }

  @Override
  public void clear() {
    head.next = tail;
    numNodes = 0;
  }

  @Override
  public boolean isEmpty() {
    if (numNodes == 0 || head.next == tail) {
      return true;
    }
    return false;
  }

  @Override
  public void remove(final int index) {
    if (index < 0 || index >= numNodes) {
      throw new RuntimeException("Out Of Range");
    }
    if (index == 0) {
      head = head.next;
    } else {
      SList temp = head;
      for (int i = 0; i <= index - 1; i++) {
        temp = temp.next;
      }
      temp.next = temp.next.next;
    }
    numNodes--;
  }

  @Override
  public int size() {
    if (numNodes < 0) {
      return 0;
    }
    return numNodes;
  }

  @Override
  public ILinkedList sublist(final int fromIndex, final int toIndex) {
    if (fromIndex < 0 || fromIndex >= numNodes || toIndex < 0
        || toIndex >= numNodes) {
      throw new RuntimeException("Out Of Range");
    }
    ILinkedList returnBack = new SinglyLinkedList();
    SList temp = head;
    for (int i = 0; i <= numNodes; i++) {
      if (i > fromIndex && i <= toIndex + 1) {
        returnBack.add(temp.item);
      }
      temp = temp.next;
    }
    return returnBack;
  }

  @Override
  public boolean contains(final Object obj) {
    SList temp = head;
    if (obj != null) {
      for (int i = 0; i < numNodes; i++) {
        temp = temp.next;
        if (obj.equals(temp.item)) {
          return true;
        }
      }
    } else {
      for (int i = 0; i < numNodes; i++) {
        temp = temp.next;
        if (null == temp.item) {
          return true;
        }
      }
    }
    return false;
  }

}
