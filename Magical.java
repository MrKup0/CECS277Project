/**
 * Interface
 */

public interface Magical {
     static final String MAGIC_MENU = "1. Magic Missile\n2. Fireball";
     static final int NUM_MAGIC_MENU_ITEMS = 2;
     /**
      * Iconic magic missle attack method
      * @param e the entity being attacked
      * @return a string description of the attack
      */
     public String magicMissile(entity e) {
          int dmg = (int) (Math.random() * 5) + 1; // Recomended damage, might tweak so hero does more dmg
          e.takeDamage(dmg);
          return this.getName() + "casts Magic Missle on " + e.getName()
          + " dealing " + dmg + " points of force damage.";
     }

     /**
      * Iconic fireball attack method
      * @param e the entity being attacked
      * @return a string description of the attack
      */
     public String fireball(entity e) {
          int dmg = (int) (Math.random() * 5) + 1; // Recomended damage, might tweak so hero does more dmg
          e.takeDamage(dmg);
          return this.getName() + " casts Fireball on " + e.getName()
          + " dealing " + dmg + " points of fire damage.";
     }
}
