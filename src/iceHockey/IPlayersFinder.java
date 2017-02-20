package eg.edu.alexu.csd.datastructure.iceHockey;

public interface IPlayersFinder {

  /**
   * uses depth first search to find all the players in
   * the image.
   * @param photo The photo resembled in the 2D array
   * @param team the number which resembles the players
   * @param threshold the minimum threshold
   * @return array containing the position of players
   * in the photo.
   */
  java.awt.Point[] findPlayers(String[] photo, int team, int threshold);
}