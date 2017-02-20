package eg.edu.alexu.csd.datastructure.iceHockey.cs64;

import eg.edu.alexu.csd.datastructure.iceHockey.IPlayersFinder;

import java.awt.Point;
import java.util.Arrays;
import java.util.Comparator;

/**
 * Implementation of the Hockey Interface.
 * @author Mohamed Ayman
 */
public class Hockey implements IPlayersFinder {
  /**
   * maxSize of a photo.
   */
  private static final int MAX_SIZE = 51;
  /**
   * The value of one block.
   */
  private static final int BLOCK_AREA = 4;
  /**
   * Array containing players found.
   */
  private Point[] resultPoints = new Point[MAX_SIZE];
  /**
   * The photo sent by the user.
   */
  private String[] photos;
  /**
   * minimum value for the x-coordinate used in finding the position.
   */
  private int minX = Integer.MAX_VALUE;
  /**
   * minimum value for the y-coordinate used in finding the position.
   */
  private int minY = Integer.MAX_VALUE;
  /**
   * maximum value for the x-coordinate used in finding the position.
   */
  private int maxX = -1;
  /**
   * maximum value for the y-coordinate used in finding the position.
   */
  private int maxY = -1;
  /**
   * number of rows in the photo sent by the user.
   */
  private int noRows;
  /**
   * flagTeam of the currently searched for players.
   */
  private Character teamFlag;
  /**
   * Visited array to avoid cycles while using depth first search.
   */
  private boolean[][] appear = null;

  /**
   * Recursive functions that calculates the number of Blocks.
   *
   * @param counter
   *          Row
   * @param counter2
   *          Column
   * @return the Area of this player
   */
  public final int dfs(final int counter, final int counter2) {
    if (counter < 0 || counter >= noRows) {
      return 0;
    }
    if (counter2 < 0 || counter2 >= photos[counter].length()) {
      return 0;
    }
    if (appear[counter][counter2]) {
      return 0;
    } else {
      appear[counter][counter2] = true;
    }
    if (Character.valueOf(photos[counter].charAt(counter2)).equals(teamFlag)) {
      if (counter2 < minX) {
        minX = counter2;
      }
      if (counter2 > maxX) {
        maxX = counter2;
      }
      if (counter < minY) {
        minY = counter;
      }
      if (counter > maxY) {
        maxY = counter;
      }
      int sum = 1;
      sum += dfs(counter + 1, counter2) + dfs(counter - 1, counter2)
          + dfs(counter, counter2 + 1) + dfs(counter, counter2 - 1);
      return sum;
    } else {
      return 0;
    }
  }

  @Override
  public final Point[] findPlayers(final String[] photo, final int team,
      final int threshold) {
    photos = photo;
    if (photo == null) {
      return new Point[0];
    }
    noRows = photo.length;
    if (noRows == 0) {
      return new Point[0];
    }
    int index = 0;
    appear = new boolean[photo.length][photo[0].length()];
    resultPoints = new Point[MAX_SIZE];
    teamFlag = new Character((char) (team + '0'));
    for (int counter = 0; counter < noRows; counter++) {
      if (photo[counter].length() == 0) {
        return new Point[0];
      }
      for (int counter2 = 0; counter2 < photo[counter].length(); counter2++) {
        if (Character.valueOf(photo[counter].charAt(counter2)).equals(teamFlag)
            && !appear[counter][counter2]) {
          int val = dfs(counter, counter2);
          if (val * BLOCK_AREA >= threshold) {
            resultPoints[index] = new Point((int) (minX + maxX + 1),
                (int) (minY + maxY + 1));
            index++;
          }
          minX = Integer.MAX_VALUE;
          maxX = -1;
          minY = Integer.MAX_VALUE;
          maxY = -1;
        }
      }
    }
    if (index > 0) {
      Point[] resultArr = new Point[index];
      int counter = 0;
      for (int count = 0; counter < index; count++) {
        resultArr[counter++] = resultPoints[count];
      }
      Arrays.sort(resultArr, new Comparator<Point>() {
        public int compare(final Point a, final Point b) {
          if (a.x == b.x) {
            return a.y - b.y;
          } else {
            return a.x - b.x;
          }
        }
      });
      return resultArr;
    } else {
      return new Point[0];
    }
  }
}
