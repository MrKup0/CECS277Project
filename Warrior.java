/**
 * Warrior Enemy type, has access to a sword and an axe
 */
public class Warrior extends Enemy implements Fighter {

    public Warrior(String n, int mHp) {
        super(n, mHp);
    }

   /**
    * Randomly chooses an attack from the Fighter interface
    * @param h the hero object taking damage
    * @return the string description of the attack
    */
   public String attack(Hero h) {
        int attackChoice = (int) (Math.random() * 2);
        switch (attackChoice) {
             case 0:
                   return sword(h);
             case 1:
                   return axe(h);
        }
        return "";
   }

    /**
     * Sword attack method
     * @param e the entity being attacked with a sword
     * @return the string description of the sword attack
     */
    public String sword(Entity e) {
        int dmg = (int) (Math.random() * 6) + 1;
        e.takeDamage(dmg);
        return this.getName() + " slashes " + e.getName() + " with a sword for " + dmg + " damage.";
    }

    /**
     * Axe attack method
     * @param e the entity being attacked with an axe
     * @return the string description of the axe attack
     */
    public String axe(Entity e) {
        int dmg = (int) (Math.random() * 8) + 1;
        e.takeDamage(dmg);
        return this.getName() + " slashes " + e.getName() +
        " with an axe for " + dmg + " damage";
    }
}
