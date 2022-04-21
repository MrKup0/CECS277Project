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
     public String magicMissile(entity e);

     /**
      * Iconic fireball attack method
      * @param e the entity being attacked
      * @return a string description of the attack
      */
     public String fireball(entity e);
}
