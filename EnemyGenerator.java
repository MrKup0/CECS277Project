import java.util.*;
import java.io.*;

class EnemyGenerator{
    private static HashMap<String, Integer> enemyPlacemat = new HashMap<String, Integer>();
    /**
     * Constructor which reads from Enemies.txt and assigns the enemies to a hashmap
     * along with their base hp.
     */
    public EnemyGenerator(){
        try {
            Scanner console = new Scanner(new File("Enemies.txt"));
            while(console.hasNextLine()) {
                String read = console.nextLine();
                int commaPos = read.indexOf(",");
                String enemyType = read.substring(0, commaPos);
                int hp = Integer.parseInt(read.substring(commaPos + 1));
                
                enemyPlacemat.put(enemyType, hp);
            }
        } catch (FileNotFoundException fnf) {
            System.out.println("File not found!");
        }
    }
    /**
     * generateEnemy creates a random with a random enemy type
     * @param level is the corresponding map the player is on
     * @return Enemy object with a certain class
     */
    public Enemy generateEnemy(int level){
        int rand = (int)(Math.random() * 3);
        String enemyType = " ";
        ArrayList<String> monsters = new ArrayList<String>(enemyPlacemat.keySet());
        String name = monsters.get((int)(Math.random() * monsters.size()));
        enemyPlacemat.get(name);
        switch(rand){
            case 0:
                enemyType = "Ranger";
                Ranger newRanger = new Ranger(name + " " + enemyType, enemyPlacemat.get(name) + (int)(1.5*level*enemyPlacemat.get(name)));
                return newRanger;
            case 1:
                enemyType = "Wizard";
                Wizard newWizard = new Wizard(name + " " + enemyType, enemyPlacemat.get(name) + (int)(1.5*level*enemyPlacemat.get(name)));
                return newWizard;

            case 2:
                enemyType = "Warrior";
                Warrior newWarrior = new Warrior(name + " " + enemyType, enemyPlacemat.get(name) + (int)(1.5*level*enemyPlacemat.get(name)));
                return newWarrior;
        }
        return null;
    }
}