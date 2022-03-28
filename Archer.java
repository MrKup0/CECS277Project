/**
 * Interface
 */
public interface Archer {
     static final String ARCHER_MENU = "1. Arrow\n2. Fire Arrow";
     static final int NUM_ARCHER_MENU_ITEMS = 2;

     public String arrow(entity e) {
          int dmg = (int) (Math.random() * 5) + 1; // Recomended damage, might tweak so hero does more dmg
          e.takeDamage(dmg);
          return this.getName() + " shoots at " + e.getName()
          + " with an arrow for " + dmg + " points of damage."; 
     }

     public String fireArrow(entity e) {
          int dmg = (int) (Math.random() * 5) + 1; // Recomended damage, might tweak so hero does more dmg
          e.takeDamage(dmg);
          return this.getName() + " lobs a fire arrow at " + e.getName() +
          " for " + dmg + " points of damage";
     }
}
