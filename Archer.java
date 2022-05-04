/**
 * Interface for bow attacks
 */
public interface Archer {
     static final String ARCHER_MENU = "1. Arrow\n2. Fire Arrow";
     static final int NUM_ARCHER_MENU_ITEMS = 2;

     /**
      * Basic arrow attack method
      * @param e the entity being attacked
      * @return a string description of the attack
      */
     public String arrow(Entity e);

     /**
      * Fire arrow attack method
      * @param e the entity being shoot at with a fire arrow
      * @return the string description of the fire arrow attack
      */
     public String fireArrow(Entity e);
}
