import org.w3c.dom.Entity;

import java.awt.*;

public class Hero extends Entity implements Fighter, Archer, Magical {
  private Point loc;
  private String name;
  private int hp;
  private int level;
  private int gold;
  private int keys;
  private int potions;

  /**
   * Constructor requiring a name and maxHP value
   * @param n the name of the object
   * @param maxHp the integer maximum hp for the object
   */
  public Hero(String n, int maxHp){
    this.name = n;
    this.hp = maxHp;
    level = 1;
    gold = 25;
    Map m = Map.getInstance();
    loc = m.findStart();

  }

  /**
   * Overriden toString() function
   * @return the name, then the current hp out of the maximum hp
   */
  @Override
  public String toString() {
    return name + ": " + hp + " /25\nLevel: " + level + " Gold: " + gold
    + "\nP: " + potions + " K: " + keys + "\n";
    }

  /**
   * Increases the current level of the object
   */
  public void levelUp(){
    level++;
    if (level != 4) {
        Map m = Map.getInstance();
        m.loadMap(level);
        loc = m.findStart();
    }
  }

  /**
   * Gets the current level of the hero
   */
  public int getLevel() {
      return level;
  }

  // All go() methods need revision based on (x, y) cords //
  /**
   * Moves the object up one in the map
   * @return the character stored at the location the object is moving
   */
  public char goNorth() {
    Map table = Map.getInstance();
    if (loc.getX() == 0) { // top row
         return 'L';
    }
    loc.translate(0, 1);
    char coolBit = table.getCharAtLoc(loc);
    table.reveal(loc);
    return coolBit;
  }

  /**
   * Moves the object down one in the map
   * @return the character stored at the location the object is moving
   */
  public char goSouth(){
    Map table = Map.getInstance();
    if (loc.getX() == 4) {
         return 'L';
    }
    loc.translate(0, -1);
    char coolBit = table.getCharAtLoc(loc);
    table.reveal(loc);
    return coolBit;
  }

  /**
   * Moves the object right one in the map
   * @return the character stored at the location the object is moving
   */
  public char goEast(){
    Map table = Map.getInstance();
    if (loc.getY() == 4) {
         return 'L';
    }
    loc.translate(0, 1);
    char coolBit = table.getCharAtLoc(loc);
    table.reveal(loc);
    return coolBit;
  }

  /**
   * Moves the object left one in the map
   * @return the character stored at the location the object is moving
   */
  public char goWest() {
    Map table = Map.getInstance();
    if (loc.getY() == 0) {
         return 'L';
    }
    loc.translate(-1, 0);
    char coolBit = table.getCharAtLoc(loc);
    table.reveal(loc);
    return coolBit;
  }
  // END temporary go() block //

  /**
   * Displays the main attack options the object can take
   * @return the string of options the object has
   */
  public String getAttackMenu() {
      return "1. Physical Attck\n2/. Magical Attack\n3. Ranged Attack";
  }

  /**
   * Gets the number of avaliable main attack options
   * @param the integer representing the number of choices for main attacks
   */
  public int getNumAttackMenuItems() {
      return 3;
  }

  /**
   * Gets the sub attacks based on the pased in main attack choice
   * @param the integer choice for main attack between 1 and getNumAttackMenuItems()
   * @return the menu for the coresponding main attack choice
   */
  public String getSubAttackMenu(int choice) {
      switch (choice) {
        case 1:
          return FIGHTER_MENU;
        case 2:
          return MAGIC_MENU;
        case 3:
          return ARCHER_MENU;
        default:
          return "Whoops, something went wrong";
      }
  }

  /**
   * Gets the number of avaliable options for each sub attack based on the passed main attack choice
   * @param choice the number of possible options for the chose sub attack
   */
  public int getNumSubAttackMenuItems(int choice) {
      switch (choice) {
      case 1:
        return NUM_FIGHTER_MENU_ITEMS;
      case 2:
        return NUM_MAGIC_MENU_ITEMS;
      case 3:
        return NUM_ARCHER_MENU_ITEMS;
      default:
        return 2;
      }
    }

  /**
   * Deals damage to a passed enemy using the selected main attack and sub attack
   * @param e the enemy object
   * @param choice the main attack choice
   * @param subchoice the sub attack choice
   * @return the string output of the specific attack
   */
  public String attack(Enemy e, int choice, int subchoice) {
      switch (choice) {
          case 1:
              if (subchoice == 1) {
                  return Sword(e);
              }
              if (subchoice == 2) {
                  return Axe(e);
              }
              return "You missed!";

          case 2:
              if (subchoice == 1) {
                  return magicMissile(e);
              }
              if (subchoice == 2) {
                  return fireball(e);
              }
              return "You missed!";

          case 3:
              if (subchoice == 1) {
                  return arrow(e);
              }
              if (subchoice == 2) {
                  return fireArrow(e);
              }
              return "You missed!";

          default:
              return "You missed!";
      }
  }

  public int getGold(){
    return gold;
  }

  public void collectGold(int g){
    gold += g;
  }

  boolean spendGold(int g){
    if (g <= gold){
      gold -= g;
      return true;
    }
    return false;
  }

  public Point getLocation() {
       return loc;
 }

  public boolean hasKey(){
    if (keys >= 1){
      return true;
    }
    return false;
  }

  public void pickUpKey(){
    keys++;
  }

  public void useKey(){
    if(hasKey()){
      keys--;
    } else {
      System.out.print("No keys left!");
    }
  }

  public boolean hasPotion(){
    if (potions >= 1){
      return true;
    }
    return false;
  }

  public void pickUpPotion(){
    potions++;
  }

  public boolean usePotion(){
    if(hasPotion()){
      this.hp += 10;
      return true;
    }
    return false;
  }
    public String arrow(Enemy e) {
        int dmg = (int) (Math.random() * 5) + 1; // Recomended damage, might tweak so hero does more dmg
        e.takeDamage(dmg);
        return name + " shoots at " + e.getName()
                + " with an arrow for " + dmg + " points of damage.";
    }

    public String fireArrow(Enemy e) {
        int dmg = (int) (Math.random() * 5) + 1; // Recomended damage, might tweak so hero does more dmg
        e.takeDamage(dmg);
        return name + " lobs a fire arrow at " + e.getName() +
                " for " + dmg + " points of damage";
    }

    public String Sword(Enemy e) {
        int dmg = (int) (Math.random() * 5) + 1;
        e.takeDamage(dmg);
        return name + " slashes " + e.getName()
                + " with a sword for " + dmg + " damage.";
    }

    public String Axe(Enemy e) {
        int dmg = (int) (Math.random() * 5) + 1;
        e.takeDamage(dmg);
        return name + " slashes " + e.getName() +
                " with an axe for " + dmg + " damage";
    }

    public String magicMissile(Enemy e) {
        int dmg = (int) (Math.random() * 5) + 1; // Recomended damage, might tweak so hero does more dmg
        e.takeDamage(dmg);
        return name + "casts Magic Missle on " + e.getName()
                + " dealing " + dmg + " points of force damage.";
    }
}
