package eg.edu.alexu.csd.datastructure.queue.cs64;

import eg.edu.alexu.csd.datastructure.queue.IArrayBased;
import eg.edu.alexu.csd.datastructure.queue.IQueue;

/**
 * Queue implementation using Array.
 * @author Mohamed Ayman
 */
public class ArrayQueue implements IQueue, IArrayBased {

  /**
   * Current size of the Queue.
   */
  private int size = 0;
  /**
   * Max size of the queue.
   */
  private int maxSize = 0;
  /**
   * Start index of the queue in the array.
   */
  private int startIndex = 0;
  /**
   * End index of the queue in the array.
   */
  private int endIndex = 0;
  /**
   * Array used to store the values.
   */
  private Object[] arr;

  /**
   * Constructore for the Queue.
   * @param n
   *          the maximum size of the array.
   */
  public ArrayQueue(final int n) {
    arr = new Object[n];
    maxSize = n;
  }

  @Override
  public final void enqueue(final Object item) {
    if (size >= maxSize) {
      throw new RuntimeException();
    }
    arr[endIndex] = item;
    endIndex = (endIndex + 1) % maxSize;
    size++;
  }

  @Override
  public final Object dequeue() {
    if (isEmpty()) {
      throw new RuntimeException();
    }
    Object result = arr[startIndex];
    startIndex = (startIndex + 1) % maxSize;
    size--;
    return result;
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
