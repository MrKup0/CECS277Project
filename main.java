public class main {
    public static void main() {
        Map m = new Map();
        Hero h = new Hero("test", 25);
        boolean victory = false;

        while(h.gethp() > 0 && !victory) {

        }
    }

    /**
     * Displays the main menu, allowing the user to select a direction to move
     * @param a Hero object
     * @return an integer between 1 and 5 reflective of the user input
     */
    public static int mainMenu(Hero h) {
        System.out.println(h); // check Entity if error
        System.out.println("Level: " + h.getLevel());
        System.out.println("Gold: " + h.getGold());
        System.out.println("P: " + h.hasPotion() + "K: " + h.hasKey());
        System.out.println(m);
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
            System.out.println("You have encountered a " + e);
            if(h.hasPotion() && h.gethp() < 25) {
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
                    if (h.gethp() == 0) {
                        return false;
                    } else {
                        return true;
                    }
                }
                break;
            case 2:
                int dir = (int) (Math.random() * 4);
                switch (dir) {
                    case 0: h.goNorth(); break;
                    case 1: h.goEast(); break;
                    case 2: h.goSouth(); break;
                    case 3: h.goWest(); break;
                }
                System.out.println("You ran away!");
                return true;
                break;
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
     * A singular turn in comabt where the user selects how they would like to attack
     * and then are attacked by the enemy, if the enemy is still alive
     * @param h a hero object
     * @param e an enemy object
     * @return true if someone dies, false otherwise
     */
    public static boolean fight(Hero h, Enemy e) {
        System.out.print(h.getAttackMenu());
        int mainAttack = CheckInput.getIntRange(1, h.getNumAttackMenuItems());
        System.out.println(h.getSubAttackMenu(mainAttack));
        int subAttack = CheckInput.getIntRange(1, h.getNumSubAttackMenuItems(mainAttack));

        h.attack(e, mainAttack, subAttack);

        if (e.gethp() == 0) {
            return true;
        }

        e.attack(h);

        if (h.gethp() == 0) {
            return true;
        }

        return false;
    }

    /**
     * A shop menu where the user can choose to by a health potion, or a key
     * @param a hero object
     */
    public static void store(Hero h) {

    }
}
