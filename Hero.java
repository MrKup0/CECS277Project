public class Hero extends Entity implements Fighter, Archer, Magical {
  private Point loc;
  private int level;
  private int gold;
  private int keys;
  private int potions;

  public Hero(String n, int maxHp){
    super(n, maxHp);
    level = 1;
    gold = 25;

  }

  @Override
  public String toString() {
    return this.getName() + ": " + this.gethp() + " /25";
    }

  public void levelUp(){
    level++;
    //go to next map
  }

  public int getLevel() {
      return level;
  }

  // All go() methods are temporary until Map is implemented //
  public char goNorth() {
    Map table = Map.getInstance(); // this will likely be the cause of any errors
    loc.translate(0, 1);
    return table.getCharAtLoc(loc);
  }
  public char goSouth {
    Map table = Map.getInstance();
    loc.translate(0, -1);
    return table.getCharAtLoc(loc);
  }
  public char goEast {
    Map table = Map.getInstance();
    loc.translate(1, 0);
    return table.getCharAtLoc(loc);
  }
  public char goWest() {
    Map table = Map.getInstance();
    loc.translate(-1, 1);
    return table.getCharAtLoc(loc);
  }
  // END temporary go() block //

  public String getAttackMenu() {
      return "1. Physical Attck\n2/. Magical Attack\n3. Ranged Attack";
  }

  public int getNumAttackMenuItems() {
      return 3;
  }

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

  public String attack(Enemy e, int choice, int subchoice) {
      switch (choice) {
          case 1:
              if (subchoice == 1) {
                  return sword(e);
              }
              if (subchoice == 2) {
                  return axe(e);
              }
              return "You missed!";

          case 2:
              if (subchoice == 1) {
                  return magicMissle(e);
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
      //gold -= g; could also call collectGold(-g);
      return true;
    }
    return false;
  }

  boolean hasKey(){
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
      //set hp to hp + 10;
      return true;
    }
    return false;
  }
}
