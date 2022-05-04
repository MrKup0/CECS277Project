/**
 * Interface for melee attacks
 */
public interface Fighter{
     static final String FIGHTER_MENU = "1. Sword\n2. Axe";
     static final int NUM_FIGHTER_MENU_ITEMS = 2;

     /**
      * Sword attack method
      * @param e the entity being attacked with a sword
      * @return the string description of the sword attack
      */
     public String sword(Entity e);

     /**
      * Axe attack method
      * @param e the entity being attacked with an axe
      * @return the string description of the axe attack
      */
     public String axe(Entity e);
}
