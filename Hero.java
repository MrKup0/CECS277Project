import org.w3c.dom.Entity;

import java.awt.*;

/**
 * Hero class designed to be used by the player
 */

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

  /**
   * Displays the main attack options the object can take
   * @return the string of options the object has
   */
  public String getAttackMenu() {
      return "1. Physical Attck\n2. Magical Attack\n3. Ranged Attack";
  }

  /**
   * Gets the number of avaliable main attack options
   * @return the integer representing the number of choices for main attacks
   */
  public int getNumAttackMenuItems() {
      return 3;
  }

  /**
   * Gets the sub attacks based on the pased in main attack choice
   * @param choice the integer choice for main attack between 1 and getNumAttackMenuItems()
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
   * @return the respective number of menu items
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

  /**
   * Gets the hero objects current gold count
   * @return the current gold count
   */
  public int getGold(){
    return gold;
  }

  /**
   * Increases the hero objects gold count by g
   * @param g the amount to increase the gold by
   */
  public void collectGold(int g){
    gold += g;
  }

  /**
   * Decrements the hero objects gold count by g if the counter can go that low
   * @param g the amount to decrease by
   * @return true if the gold was spent, false if it was unable to be spent
   */
  boolean spendGold(int g){
    if (g <= gold){
      gold -= g;
      return true;
    }
    return false;
  }

  /**
   * Gets the current location of the hero object
   * @return the current position of the hero as a point object
   */
  public Point getLocation() {
       return loc;
 }

  /**
   * Checks if the hero object has a key
   * @return true if the hero object has at least 1 key, false otherwise
   */
  public boolean hasKey(){
    if (keys >= 1){
      return true;
    }
    return false;
  }

  /**
   * Increments the number of keys the hero object has
   */
  public void pickUpKey(){
    keys++;
  }

  /**
   * Decrements the number of keys if the hero object has any
   */
  public void useKey(){
    if(hasKey()){
      keys--;
    } else {
      System.out.print("No keys left!");
    }
  }

  /**
   * Checks if the hero object has any potions
   * @return true if the hero object has at least 1 potion, false otherwise
   */
  public boolean hasPotion(){
    if (potions >= 1){
      return true;
    }
    return false;
  }

  /**
   * Increments the number of potions held by the hero object
   */
  public void pickUpPotion(){
    potions++;
  }

  /**
   * Uses a stored potion to heal the hero object
   * @return true if the use was healed, false otherwise
   */
  public boolean usePotion(){
    if(hasPotion()){
      this.heal();
      System.out.println("You have been healed!");
      return true;
    }
    return false;
  }
    /**
      * Basic arrow attack method
      * @param e the entity being attacked
      * @return a string description of the attack
      */
    @Override
    public String arrow(Entity e) {
        int dmg = (int) (Math.random() * 5) + 1;
        e.takeDamage(dmg);
        return this.getName() + " shoots at " + e.getName()
                + " with an arrow for " + dmg + " points of damage.";
    }

     /**
      * Fire arrow attack method
      * @param e the entity being shoot at with a fire arrow
      * @return the string description of the fire arrow attack
      */
    @Override
    public String fireArrow(Entity e) {
        int dmg = (int) (Math.random() * 7) + 2;
        e.takeDamage(dmg);
        return this.getName() + " lobs a fire arrow at " + e.getName() +
                " for " + dmg + " points of damage";
    }

    /**
     * Sword attack method
     * @param e the entity being attacked with a sword
     * @return the string description of the sword attack
     */
    @Override
    public String sword(Entity e) {
        int dmg = (int) (Math.random() * 5) + 3;
        e.takeDamage(dmg);
        return this.getName() + " slashes " + e.getName()
                + " with a sword for " + dmg + " damage.";
    }

    /**
     * Axe attack method
     * @param e the entity being attacked with an axe
     * @return the string description of the axe attack
     */
    @Override
    public String axe(Entity e) {
        int dmg = (int) (Math.random() * 10) + 2;
        e.takeDamage(dmg);
        return this.getName() + " slashes " + e.getName() +
                " with an axe for " + dmg + " damage";
    }

    /**
     * Iconic magic missle attack method, costs 2 hp to cast
     * @param e the entity being attacked
     * @return a string description of the attack
     */
    @Override
    public String magicMissile(Entity e) {
        int dmg = (int) (Math.random() * 7) + 3;
        this.takeDamage(2);
        e.takeDamage(dmg);
        return this.getName() + " expends 2 hp to cast Magic Missle on " + e.getName()
                + " dealing " + dmg + " points of force damage./n" + this.getName() + " takes 2 damage for using black magic!";
    }

   /**
    * Iconic fireball attack method, costs 4 hp to cast
    * @param e the entity being attacked
    * @return a string description of the attack
    */
    @Override
    public String fireball(Entity e) {
        int dmg = (int) (Math.random() * 12) + 5;
        e.takeDamage(dmg);
        this.takeDamage(4);
        return this.getName() + " expends 4 hp to cast Fireball on " + e.getName()
                + " dealing " + dmg + " points of force damage./n" + this.getName() + " takes 4 damage for using black magic!";
    }
}
