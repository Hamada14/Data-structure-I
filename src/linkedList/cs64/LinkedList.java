package eg.edu.alexu.csd.datastructure.linkedList.cs64;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
/**
 * Testing class to 1idate the Linked List.
 * @author Mohamed Ayman
 *
 */
public class LinkedList {

  /**
   * Constant for tests.
   */
  private static final int THREE = 3;
  /**
   * Constant for tests.
   */
  private static final int FOUR = 4;
  /**
   * Constant for tests.
   */
  private static final int SIX = 6;
  /**
   * Constant for tests.
   */
  private static final int SEVEN = 7;
  /**
   * Constant for tests.
   */
  private static final int TWELVE = 12;
  /**
   * Constant for tests.
   */
  private static final int THIRTEEN = 13;
  /**
   * Test Basic addition of new nodes.
   */
  @Test
  public void testSingleAdd1() {
    SinglyLinkedList myList = new SinglyLinkedList();
    assertEquals(0, myList.size());
    myList.add(null);
    assertEquals(1, myList.size());
    myList.add(new Integer(1));
    myList.add(new String("This is string"));
    myList.add(new String("This is string"));
    myList.add(new String("This is string"));
    myList.add(new String("This is string"));
    myList.add(new Float(0));
    assertEquals(new Float(0), myList.get(1));
  }

  /**
   * Test Addition of indexs.
   */
  @Test
  public void testSingleAdd2() {
    SinglyLinkedList myList = new SinglyLinkedList();
    myList.add(0, new String("This is string"));
    myList.add(1, new String("This is string"));
    myList.add(1, new String("This is string"));
    myList.add(1, new String("This is string"));
    myList.add(2, new Float(0));
    myList.add(2, null);
    assertEquals(true, myList.contains(new Float(0)));
    assertEquals(true, myList.contains(null));
    assertEquals(new String("This is string"), myList.get(1));
  }

  /**
   * Tests addition with contains.
   */
  @Test
  public void testSingleAddTHREE() {
    SinglyLinkedList myList = new SinglyLinkedList();
    myList.add(0, new Integer(1));
    myList.add(0, new String("A"));
    myList.add(1, new String("B"));
    myList.add(1, new String("C"));
    myList.add(1, new String("D"));
    myList.set(2, new String("ABC"));
    assertEquals(true, myList.contains(new String("B")));
    assertEquals(false, myList.contains(null));
    assertEquals(new String("A"), myList.get(0));
  }

  /**
   * Test Null Addition.
   */
  @Test
  public void testSingleNull() {
    SinglyLinkedList myList = new SinglyLinkedList();
    myList.add(0, new Integer(0));
    myList.add(0, new String("A"));
    myList.add(1, new String("B"));
    myList.add(1, new String("C"));
    myList.add(1, new String("D"));
    myList.add(2, null);
    assertEquals(null, myList.get(2));
  }

  /**
   * Tests sublists.
   */
  @Test
  public void testSingleSublist() {
    SinglyLinkedList myList = new SinglyLinkedList();
    myList.add(0, new Integer(0));
    myList.add(0, new String("A"));
    myList.add(1, new String("B"));
    myList.add(1, new String("C"));
    myList.add(1, new String("D"));
    assertEquals(new String("C"), myList.get(2));
    SinglyLinkedList sub = (SinglyLinkedList) myList.sublist(2, FOUR);
    assertEquals(THREE, sub.size());
    assertEquals(new String("C"), sub.get(0));
    assertEquals(new Integer(0), sub.get(2));
  }

  /**
   * Test clearing single linked list.
   */
  @Test
  public void testSingleClear() {
    SinglyLinkedList myList = new SinglyLinkedList();
    myList.add(0, new Integer(0));
    myList.add(0, new String("A"));
    myList.add(1, new String("B"));
    myList.add(1, new String("C"));
    myList.add(1, new String("D"));
    assertEquals(false, myList.isEmpty());
    myList.clear();
    assertEquals(true, myList.isEmpty());
  }

  /**
   * Test remove in single linked list.
   */
  @Test
  public void testSingleRemove() {
    SinglyLinkedList myList = new SinglyLinkedList();
    myList.add(new Integer(0));
    myList.add(new String("A"));
    myList.add(new String("B"));
    myList.add(new String("C"));
    myList.add(new String("D"));
    myList.remove(2);
    assertEquals(false, myList.contains(new String("B")));
    assertEquals(new String("C"), myList.get(2));
    assertEquals(FOUR, myList.size());
    myList.remove(0);
    myList.remove(0);
    myList.remove(0);
    myList.remove(0);
    assertEquals(0, myList.size());
  }

  /**
   * Test adding in doubly linked list.
   */
  @Test
  public void testDoublyAdd1() {
    DLinkedList myList = new DLinkedList();
    assertEquals(0, myList.size());
    myList.add(null);
    assertEquals(1, myList.size());
    myList.add(new Integer(0));
    myList.add(new String("This is string"));
    assertEquals(THREE, myList.size());
    myList.add(new String("This is string"));
    myList.add(new String("This is string"));
    myList.add(new String("This is string"));
    myList.add(new Float(1));
    assertEquals(new Float(1), myList.get(SIX));

  }

  /**
   * Tests addition with index with contains.
   */
  @Test
  public void testDoubleAdd2() {
    DLinkedList myList = new DLinkedList();
    myList.add(0, new String("This is string"));
    myList.add(1, new String("This is string"));
    myList.add(1, new String("This is string"));
    myList.add(1, new String("This is string"));
    myList.add(2, new Float(1));
    myList.add(2, null);
    assertEquals(true, myList.contains(new Float(1)));
    assertEquals(true, myList.contains(null));
    assertEquals(new Float(1), myList.get(THREE));
    assertEquals(new String("This is string"), myList.get(1));
  }

  /**
   * Tests complex index addition with contains.
   */
  @Test
  public void testDoublyAddTHREE() {
    DLinkedList myList = new DLinkedList();
    myList.add(0, new Integer(0));
    myList.add(0, new String("A"));
    myList.add(1, new String("B"));
    myList.add(1, new String("C"));
    myList.add(1, new String("D"));
    myList.add(2, new Float(1));
    myList.set(THREE, new String("ABC"));
    assertEquals(new String("ABC"), myList.get(THREE));
    assertEquals(false, myList.contains(null));
    assertEquals(new Float(1), myList.get(2));
  }

  /**
   * Test addition of null.
   */
  @Test
  public void testDoubleNull() {
    DLinkedList myList = new DLinkedList();
    myList.add(0, new Integer(0));
    myList.add(0, new String("A"));
    myList.add(1, new String("B"));
    myList.add(1, new String("C"));
    myList.add(1, new String("D"));
    myList.add(2, null);
    assertEquals(null, myList.get(2));
  }

  /**
   * Tests Double linked list sublist.
   */
  @Test
  public void testDoublySublist() {
    DLinkedList myList = new DLinkedList();
    myList.add(0, new Integer(0));
    myList.add(0, new String("A"));
    myList.add(1, new String("B"));
    myList.add(1, new String("C"));
    myList.add(1, new String("D"));
    DLinkedList sub = (DLinkedList) myList.sublist(0, FOUR);
    assertEquals(sub.size(), myList.size());
    for (int counter = 0; counter < sub.size(); counter++) {
      assertEquals(sub.get(counter), myList.get(counter));
    }
  }

  /**
   * Tests double linked list clear.
   */
  @Test
  public void testDoubleClear() {
    DLinkedList myList = new DLinkedList();
    myList.add(0, new Integer(0));
    myList.add(0, new String("A"));
    myList.add(1, new String("B"));
    myList.add(1, new String("C"));
    myList.add(1, new String("D"));
    assertEquals(false, myList.isEmpty());
    myList.clear();
    assertEquals(true, myList.isEmpty());
  }

  /**
   * Tests double linked list remove.
   */
  @Test
  public void testDoubleRemove() {
    DLinkedList myList = new DLinkedList();
    myList.add(new Integer(0));
    myList.add(new String("A"));
    myList.add(new String("B"));
    myList.add(new String("C"));
    myList.add(new String("D"));
    myList.remove(2);
    assertEquals(false, myList.contains(new String("B")));
    assertEquals(new String("C"), myList.get(2));
    assertEquals(FOUR, myList.size());
    myList.remove(0);
    myList.remove(0);
    myList.remove(0);
    myList.remove(0);
    assertEquals(0, myList.size());
  }

  /**
   * Tests Bounds exceptions.
   */
  @Test(expected = RuntimeException.class)
  public void singlyGetOutOfIndex() {
    SinglyLinkedList myList = new SinglyLinkedList();
    myList.add(0, new Integer(0));
    myList.add(0, new String("A"));
    myList.add(1, new String("B"));
    myList.add(1, new String("C"));
    myList.add(1, new String("D"));
    myList.get(TWELVE);
  }

  /**
   * Test negative index.
   */
  @Test(expected = RuntimeException.class)
  public void singlyRemoveOutOfIndex() {
    SinglyLinkedList myList = new SinglyLinkedList();
    myList.add(0, new Integer(0));
    myList.add(0, new String("A"));
    myList.add(1, new String("B"));
    myList.add(1, new String("C"));
    myList.add(1, new String("D"));
    myList.get(-TWELVE);
  }

  /**
   * Tests Off by 1 bounds exception.
   */
  @Test(expected = RuntimeException.class)
  public void doublyAddOutOfIndex() {
    SinglyLinkedList myList = new SinglyLinkedList();
    myList.add(0, new Integer(0));
    myList.add(0, new String("A"));
    myList.add(1, new String("B"));
    myList.add(1, new String("C"));
    myList.add(1, new String("D"));
    myList.add(SIX, new Integer(0));
  }

  /**
   * Tests Doubly bounds exception.
   */
  @Test(expected = RuntimeException.class)
  public void doublySetOutOfIndex() {
    SinglyLinkedList myList = new SinglyLinkedList();
    myList.add(0, new Integer(0));
    myList.add(0, new String("A"));
    myList.add(1, new String("B"));
    myList.add(1, new String("C"));
    myList.add(1, new String("D"));
    myList.set(SIX, new Integer(TWELVE));
  }

  /**
   * Tests sublist bounds exception.
   */
  @Test(expected = RuntimeException.class)
  public void doublySublistOutOfIndex() {
    SinglyLinkedList myList = new SinglyLinkedList();
    myList.add(0, new Integer(0));
    myList.add(0, new String("A"));
    myList.add(1, new String("B"));
    myList.add(1, new String("C"));
    myList.add(1, new String("D"));
    myList.sublist(1, THIRTEEN);
  }

  /**
   * Tests addition out of bounds.
   */
  @Test(expected = RuntimeException.class)
  public void testSingleAddOut() {
    SinglyLinkedList myList = new SinglyLinkedList();
    myList.add(0, new Integer(0));
    myList.add(0, new String("A"));
    myList.add(1, new String("B"));
    myList.add(SEVEN, new String("C"));
  }
}
