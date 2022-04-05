public abstract class Entity {
     private String name;
     private int hp;
     private int maxHp;

     /**
      * Default constructor, takes a name and a maximum HP
      * @param n name of the entity
      * @param mHp max HP of the entity
      */
     public Entity(String n, int mHp) {
          name = n;
          maxHp = mHp;
          hp = mHp;
     }

     /**
      * Getter that returns the name of the entity
      * @return name the string name of the entity
      */
     public String getName() {
          return name;
     }

     /**
      * Getter that returns the current hp of the entity
      * @return hp the current integer hp the entity has
      */
     public int getHp() {
          return hp;
     }

     /**
      * Fully heals the entity to maximum HP
      */
     public void heal() {
          hp = maxHp;
     }

     /**
      * Deals damage to the entity. If the damage is greater than
      * the entities current hp, the HP is set to zero
      * @param d the integer damage to be taken
      */
     public void takeDamage(int d) {
          if (d > hp) {
               hp = 0;
          } else {
               hp -= d;
          }
     }

     /**
      * Overrides the toString() function to allow for easier calls
      * @return name: hp/maxHp
      */
     @Override
     public String toString() {
          return name + ": " + hp + "/" + maxHp;
     }
}
