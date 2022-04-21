
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
                   return Sword(h);
             case 1:
                   return Axe(h);
        }
   }

    /**
     * Sword attack method
     * @param e the entity being attacked with a sword
     * @return the string description of the sword attack
     */
    public String Sword(Entity e) {
        int dmg = (int) (Math.random() * 5) + 1;
        e.takeDamage(dmg);
        return this.getName() + " slashes " + e.getName() + " with a sword for " + dmg + " damage.";
    }

    /**
     * Axe attack method
     * @param e the entity being attacked with an axe
     * @return the string description of the axe attack
     */
    public String Axe(Entity e) {
        int dmg = (int) (Math.random() * 5) + 1;
        e.takeDamage(dmg);
        return this.getName() + " slashes " + e.getName() +
        " with an axe for " + dmg + " damage";
    }
}
