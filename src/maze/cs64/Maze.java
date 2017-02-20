package eg.edu.alexu.csd.datastructure.maze.cs64;

import java.awt.Point;
import java.io.File;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import eg.edu.alexu.csd.datastructure.maze.IMazeSolver;
import eg.edu.alexu.csd.datastructure.queue.cs64.LinkedQueue;
import eg.edu.alexu.csd.datastructure.stack.cs64.MyStack;

/**
 * Class implementation of the maze solver using breadth first search and depth
 * first search.
 * @author Mohamed Ayman
 */
public class Maze implements IMazeSolver {

  /**
   * Scanner used to parse input.
   */
  private static Scanner s;
  /**
   * boolean array used to avoid cycles in the traversal.
   */
  private boolean[][] visited;
  /**
   * the representation of the four forms of directions, north, south, east and
   * west.
   */
  private Point[] pointChange = {new Point(-1, 0), new Point(0, 1),
      new Point(1, 0), new Point(0, -1) };
  /**
   * number of formations.
   */
  private static final int FORMATIONS = 4;
  /**
   * number of row of the map.
   */
  private int nRows;
  /**
   * number of columns in the map.
   */
  private int nCols;

  @Override
  public int[][] solveBFS(final File maze) {
    String[] mazeMap = getMap(maze);
    LinkedQueue mapQueue = new LinkedQueue();
    Map<Point, Point> mazeTrace = new HashMap<Point, Point>();
    Point start = findStart(mazeMap);
    Point currentPoint = new Point(0, 0);
    boolean endFound = false;
    mapQueue.enqueue(start);
    while (!mapQueue.isEmpty()) {
      currentPoint = (Point) mapQueue.dequeue();
      int currentRow = (int) currentPoint.getY();
      int currentCol = (int) currentPoint.getX();
      if (mazeMap[currentRow].charAt(currentCol) == 'E') {
        endFound = true;
        break;
      }
      visited[currentRow][currentCol] = true;
      for (int counter = 0; counter < FORMATIONS; counter++) {
        Point change = pointChange[counter];
        Point nextPoint = new Point(currentCol + (int) change.getX(),
            currentRow + (int) change.getY());
        if (validPoint((int) nextPoint.getX(), (int) nextPoint.getY(), mazeMap)
            && !visited[(int) nextPoint.getY()][(int) nextPoint.getX()]) {
          mapQueue.enqueue(nextPoint);
          mazeTrace.put(nextPoint, currentPoint);
        }
      }
    }
    if (!endFound) {
      return null;
    }
    return traceMaze(currentPoint, mazeTrace);
  }

  @Override
  public int[][] solveDFS(final File maze) {
    String[] mazeMap = getMap(maze);
    MyStack mapStack = new MyStack();
    Map<Point, Point> mazeTrace = new HashMap<Point, Point>();
    Point start = findStart(mazeMap);
    Point currentPoint = new Point(0, 0);
    boolean endFound = false;
    mapStack.push(start);
    while (!mapStack.isEmpty()) {
      currentPoint = (Point) mapStack.pop();
      int currentRow = (int) currentPoint.getY();
      int currentCol = (int) currentPoint.getX();
      if (mazeMap[currentRow].charAt(currentCol) == 'E') {
        endFound = true;
        break;
      }
      visited[currentRow][currentCol] = true;
      for (int counter = 0; counter < FORMATIONS; counter++) {
        Point change = pointChange[counter];
        Point nextPoint = new Point(currentCol + (int) change.getX(),
            currentRow + (int) change.getY());
        if (validPoint((int) nextPoint.getX(), (int) nextPoint.getY(), mazeMap)
            && !visited[(int) nextPoint.getY()][(int) nextPoint.getX()]) {
          mapStack.push(nextPoint);
          mazeTrace.put(nextPoint, currentPoint);
        }
      }
    }
    if (!endFound) {
      return null;
    }
    int[][] mazePath = traceMaze(currentPoint, mazeTrace);
    return mazePath;
  }

  /**
   * Traces the path back to the starting point.
   * @param currentPoint the last Visited point
   * @param mazeTrace the Map describing parent-child relation.
   * @return 2d Array describing the path
   */
  private int[][] traceMaze(final Point currentPoint,
      final Map<Point, Point> mazeTrace) {
    int[][] mazePath = new int[nRows * nCols][2];
    int pathSize = 0;
    Point nextPoint = currentPoint;
    while (nextPoint != null) {
      mazePath[pathSize][0] = (int) nextPoint.getY();
      mazePath[pathSize++][1] = (int) nextPoint.getX();
      nextPoint = mazeTrace.get(nextPoint);
    }
    mazePath = reformArray(mazePath, pathSize);
    return mazePath;
  }

  /**
   * removes the unneeded size of the array and reverses
   * it.
   * @param mazePath the Maze Path
   * @param pathSize the length of the path
   * @return a 2d Array describing the path
   */
  private int[][] reformArray(final int[][] mazePath, final int pathSize) {
    int[][] mazePathResult = Arrays.copyOfRange(mazePath, 0, pathSize);
    for (int counter = 0; counter < pathSize / 2; counter++) {
      int[] temp = Arrays.copyOf(mazePathResult[counter],
          mazePathResult[counter].length);
      mazePathResult[counter] = Arrays.copyOf(
          mazePathResult[pathSize - counter - 1],
          mazePathResult[pathSize - counter - 1].length);
      mazePathResult[pathSize - counter - 1] = Arrays.copyOf(temp, temp.length);
    }
    return mazePathResult;
  }

  /**
   * Validates that a point can be visited.
   * @param colIndex the column in which the point lies.
   * @param rowIndex the row in which the point lies.
   * @param mazeMap the maze.
   * @return a boolean variable to state whether the point can
   * be visited or not.
   */
  private boolean validPoint(final int colIndex,
      final int rowIndex, final String[] mazeMap) {
    if (colIndex < 0 || rowIndex < 0
        || rowIndex >= nRows || colIndex >= nCols) {
      return false;
    }
    if (mazeMap[rowIndex].charAt(colIndex) == '#') {
      return false;
    }
    return true;
  }

  /**
   * Searches the maze for the starting point.
   * @param mazeMap the String describing the maze.
   * @return a Point in which the point starts.
   */
  private Point findStart(final String[] mazeMap) {
    nRows = mazeMap.length;
    nCols = mazeMap[0].length();
    Point start = new Point();
    boolean startFound = false;
    boolean endFound = false;
    for (int rowIndex = 0; rowIndex < nRows; rowIndex++) {
      for (int colIndex = 0; colIndex < nCols; colIndex++) {
        if (mazeMap[rowIndex].charAt(colIndex) == 'S') {
          if (startFound) {
            throw new RuntimeException("Found more than one Entrance.");
          } else {
            startFound = true;
            start = new Point(colIndex, rowIndex);
          }
        } else if (mazeMap[rowIndex].charAt(colIndex) == 'E') {
          endFound = true;
        }
      }
    }
    if (!startFound || !endFound) {
      throw new RuntimeException("Entrance or/and Exit are found");
    }
    return start;
  }

  /**
   * Parses the file and read the maze into a String array.
   * @param maze the file input.
   * @return String array describing the maze.
   */
  private String[] getMap(final File maze) {
    try {
      s = new Scanner(maze);
    } catch (Exception e) {
      throw new RuntimeException("File not Found");
    }
    nRows = s.nextInt();
    nCols = s.nextInt();
    if (s.hasNextInt()) {
      throw new RuntimeException();
    }
    validateSize(nRows, nCols);
    s.nextLine();
    visited = new boolean[nRows][nCols];
    String[] mazeMap = new String[nRows];
    for (int rowIndex = 0; rowIndex < nRows; rowIndex++) {
      mazeMap[rowIndex] = s.nextLine();
      if (mazeMap[rowIndex].length() != nCols) {
        throw new RuntimeException();
      }
    }
    if (s.hasNext()) {
      throw new RuntimeException();
    }
    return mazeMap;
  }

  /**
   * Validates the size of the maze.
   * @param row number of rows
   * @param col number of columns
   */
  private void validateSize(final int row, final int col) {
    if (row <= 0 || col <= 0 || row * col <= 1) {
      throw new RuntimeException("Invalid Size");
    }
  }
}
