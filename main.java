/**
 * A mini dungeon game consisting of 3 levels integrating various techniques
 * from class
 *
 * By: Jacob Mitchell and Chris Murillo
 */
public class main {
    public static void main(String[] args) {
        // Set up
        Map m = new Map();
        EnemyGenerator gen = new EnemyGenerator();
        Enemy cachedMonster = gen.generateEnemy(1);
        boolean alive = true;
        boolean victory = false;

        System.out.println("Welcome hero! what is your name");
        Hero h = new Hero(CheckInput.getString(), 25); // establish hero

        // Main loop
        while(alive && !victory) {
             int selection = mainMenu(h);
             char moved = ' ';
             do {
                  switch (selection) {
                      case 1: moved = h.goNorth(); break;
                      case 2: moved = h.goSouth(); break;
                      case 3: moved = h.goEast(); break;
                      case 4: moved = h.goWest(); break;
                      case 5: moved = 'T'; break;
                  }
                  if (moved == 'L') {
                      System.out.println("Cannot move that direction! Select again");
                      selection = mainMenu(h);
                  }
             } while (moved == 'L');

             // Determine what action to take based on the space uncovered
             switch (moved) {
                  case 'f':
                         if (!h.hasKey()) {
                              System.out.println("You do not have a key! Return when you have found one!");
                         } else {
                              if (h.getLevel() == 3) {
                                   victory = true;
                                   System.out.println("Congrats! You win!");
                              } else {
                                   h.useKey();
                                   h.levelUp();
                                   System.out.println("Congrats! You leveled up!");
                              }
                         } break;
                  case 's':
                         store(h);
                         break;
                  case 'm':
                         if (cachedMonster.getHp() == 0) {
                              cachedMonster = gen.generateEnemy(h.getLevel());
                         }
                         alive = monsterRoom(h, cachedMonster);
                         break;
                  case 'i':
                         int item = (int)(Math.random() * 2);
                         if (item == 1) {
                              System.out.println("You found a key!");
                              h.pickUpKey();
                         } else {
                              System.out.println("You obtained a potion!");
                              h.pickUpPotion();
                         }
                         Map reinstance = Map.getInstance();
                         reinstance.removeCharAtLoc(h.getLocation());
                         break;
                 case 'T': victory = true; break;
             }
        }

        // End of game output
        if (victory) {
            System.out.println("Congrats " + h.getName() + "! You have won!");
        } else if (!alive) {
            System.out.println("You have died! Try again!");
        }
    }

    /**
     * Displays the main menu, allowing the user to select a direction to move
     * @param h a Hero object
     * @return an integer between 1 and 5 reflective of the user input
     */
    public static int mainMenu(Hero h) {
        Map m = Map.getInstance();
        System.out.println(h); // check Entity if error
        System.out.println(m.mapToString(h.getLocation()));
        System.out.println("1. Go North\n2. Go South\n3. Go East\n4. Go West\n5. Quit");

        int res = CheckInput.getIntRange(1,5);
        return res;
    }

    /**
     * Prompts the user with how they would like to handle the encountered monster
     * @param h a hero object
     * @param e and enemy object
     * @return true if the hero lives, false otherwise
     */
    public static boolean monsterRoom(Hero h, Enemy e) {
        int res = 0;
        while(true) {
            System.out.println(h.getName() + ": " + h.getHp() + "/25");
            System.out.println("You have encountered a " + e);
            if(h.hasPotion() && h.getHp() < 25) {
                System.out.println("1. Fight\n2. Run\n3. Heal");
                res = CheckInput.getIntRange(1, 3);
            } else {
                System.out.println("1. Fight\n2. Run");
                res = CheckInput.getIntRange(1, 2);
            }

            switch(res) {
            case 1:
                boolean outcome = fight(h, e); //true if someone dies
                if (outcome) {
                    if (h.getHp() == 0) {
                        return false;
                    } else { // they lived!
                        Map inst = Map.getInstance();
                        inst.removeCharAtLoc(h.getLocation());
                        return true;
                    }
                }
                break;
            case 2:
                char moved = ' ';
                do {
                     int dir = (int) (Math.random() * 4);
                     switch (dir) {
                         case 0: moved = h.goNorth(); break;
                         case 1: moved = h.goEast(); break;
                         case 2: moved = h.goSouth(); break;
                         case 3: moved = h.goWest(); break;
                     }
                } while (moved == 'L');
                System.out.println("You ran away!");
                return true;
            case 3:
                h.usePotion();
                break;
            case 0:
            default:
                System.out.println("Something went wrong!");
            }
        }
    }

    /**
     * A singular turn in combat where the user selects how they would like to attack
     * and then are attacked by the enemy, if the enemy is still alive
     * @param h a hero object
     * @param e an enemy object
     * @return true if someone dies, false otherwise
     */
    public static boolean fight(Hero h, Enemy e) {

        // Get user's attack choice
        System.out.println(h.getAttackMenu());
        int mainAttack = CheckInput.getIntRange(1, h.getNumAttackMenuItems());
        System.out.println(h.getSubAttackMenu(mainAttack));
        int subAttack = CheckInput.getIntRange(1, h.getNumSubAttackMenuItems(mainAttack));

        System.out.println(h.attack(e, mainAttack, subAttack));

        // Check for victory
        if (e.getHp() == 0) {
            System.out.println("You have felled " + e.getName() +"!");
            int gainedGold = (int)(Math.random()*10) + 2;
            System.out.println("You obtain " + gainedGold + " gold!");
            h.collectGold(gainedGold);
            return true;
        }

        // Enemy's turn
        System.out.println(e.attack(h));

        if (h.getHp() == 0) {
            return true;
        }

        return false;
    }

    /**
     * A shop menu where the user can choose to by a health potion, or a key
     * @param h a hero object
     */
    public static void store(Hero h) {
         System.out.println("Welcome to the store! What would you like to buy?");
         System.out.println("1. Health Potion - 25g\n2. Key - 50g\n3. Nothing, just browsing ...");
         int res = CheckInput.getIntRange(1, 3);

         // Determine how to procede
         switch (res) {
         // Buy a key
         case 1:
               if (h.spendGold(25)) {
                    h.pickUpPotion();
                    System.out.println("Pleasure doing busines with you!");
               } else {
                    System.out.println("Sorry, you don't have enough gold for that!");
               }
               break;
         // Buy a potion
         case 2:
               if (h.hasKey()) {
                    System.out.println("Sorry, but you already got one of those.");
               } else if (h.spendGold(50)) {
                    h.pickUpKey();
                    System.out.println("Pleasure doing busines with you!");
               } else {
                    System.out.println("Sorry, you don't have enough gold for that!");
               }
               break;
         case 3:
               System.out.println("Then get out of here!");
               break;
         }
    }
}
