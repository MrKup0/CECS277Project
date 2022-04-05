/**
 * Singelton, points are in (y, x) since 2D arrays flip co-ords around
 */
import java.util.*;

public class Map () {
     private char [][] map;
     private boolean [][] revealed;
     private static Map instance = null;

     public Map() {
          loadMap(1);
     }

     public static Map getInstance() {
          if (instance == null) {
               instance = new Map();
          }
          return instance;
     }

     public void loadMap(int mapNum) { // ugly loading, will probably need fixing
          String mapName = "map" + mapNum + ".txt";
          try {
               Scanner read = new Scanner(new File(mapName));
               map = new char [5] [5];
               // update map[][] //
               for (int i = 0; i < 5; i++) {
                    string line = read.nextLine();
                    for (int j = 0; j < 5; j++) {
                         map[i][j] = line[0];
                         line = line.substring(2);
                    }
               }
               // populate revealed[][] //
               revealed = new boolean [5] [5];
               for (int i = 0; i < 5; i++) {
                    for (int j = 0; j < 5; j++) {
                         revealed[i][j] = false;
                    }
               }
          } catch (FileNotFoundException fnf) {
               System.out.println("File not found");
          }
     }

     public char getCharAtLoc(Point p) {
          return map[p.getY()][p.getX()];
     }

     public Point findStart() {
          for (int i = 0; i < 5; i++) {
               for (int j = 0; j < 5; j++) {
                    if (map[i][j] == 's') {
                         return new Point(j)(i);
                    }
               }
          }
     }

     public void reveal(Point p) {
          revealed[p.getY()][p.getX()] = true;
          removeCharAtLoc(p);
     }

     public void removeCharAtLoc(Point p) {
          map[p.getY()][p.getX()] = '*';
     }

     public String mapToString(Point p) {
          return toString(map[p.getY()][p.getX()]);
     }
}        1 file(s) copied.
