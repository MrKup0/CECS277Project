/**
 * Singelton, points are in (y, x) since 2D arrays flip co-ords around
 */
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Map () {
     private char [][] map;
     private boolean [][] revealed;
     private static Map instance = null;

     /**
      * Constructor which loads the map default starting map
      */
     public Map() {
          loadMap(1);
     }

     /**
      * Gets the current instance of the Singelton map
      * @return the current Map instance
      */
     public static Map getInstance() {
          if (instance == null) {
               instance = new Map();
          }
          return instance;
     }

     /**
      * Loads the map from a plain text file and populates the revealed array
      * with all false
      */
     public void loadMap(int mapNum) { // ugly loading, will probably need fixing
          String mapName = "map" + mapNum + ".txt";
          try {
               Scanner read = new Scanner(new File(mapName));
               map = new char [5] [5];
               // update map[][] //
               for (int i = 0; i < 5; i++) {
                    String line = read.nextLine();
                    for (int j = 0; j < 5; j++) {
                         map[i][j] = line.charAt(0);
                         line = line.substring(2);
                    }
               }
               // populate revealed[][] //
               revealed = new boolean [5][5];
               for (int i = 0; i < 5; i++) {
                    for (int j = 0; j < 5; j++) {
                         revealed[i][j] = false;
                    }
               }
          } catch (FileNotFoundException fnf) {
               System.out.println("File not found");
          }
     }

     /**
      * Gets the character stored in position p
      * @param the point containing x and y cordinates
      * @return the character at the position specified by the point object
      */
     public char getCharAtLoc(Point p) {
          return map[int(p.getX())][int(p.getY());
     }

     /**
      * Locates the 's' on the map and returns the cordinates to it
      * @return the position of the 's' as a point object
      */
     public Point findStart() {
          for (int i = 0; i < 5; i++) {
               for (int j = 0; j < 5; j++) {
                    if (map[i][j].equals('s')) {
                         Point p = new Point(i,j);
                         return p;
                    }
               }
          }
     }

     /**
      * Marks the specified position as revealed
      * @param the point containing the position to be revealed
      */
     public void reveal(Point p) {
          revealed[p.getX()][p.getY()] = true;
          removeCharAtLoc(p);
     }

     /**
      * Marks the character at a given position with '*'; indicating that the point has been visited
      * @param the point object containing the x and y cordinates to be marked
      */
     public void removeCharAtLoc(Point p) {
          map[p.getY()][p.getX()] = '*';
     }

     /**
      * Custom string function which displays the map point as a String
      * @param a point object containing the cordinates to be parsed
      * @return the string stored at the cordinates specified
      */
     public String mapToString(Point p) {
          String stringMap = "";
          for (int i = 0; i < 5; i++) {
               for (int j = 0; j < 5; j++) {
                    if (p.getX() == i && p.getY() == j) {
                         stringMap += " * ";
                    } else if (revealed[i][j]) {
                         stringMap += " " + map[i][j] + " ";
                    } else {
                         stringMap += " x ";
                    }
               }
               stringMap += "\n";
          }
          return stringMap;
     }
}
