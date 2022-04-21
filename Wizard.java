public class Wizard extends Enemy implements Magical {

    public Wizard(String n, int mHp) {
        super(n, mHp);
    }

    /**
     * Randomly chooses an attack from the Magical interface
     * @param the hero object taking damage
     * @return the string description of the attack
     */
    public String attack(Hero h) {
        int attackChoice = (int) (Math.random() * 2);
        switch (attackChoice) {
            case 0:
                return arrow(h);
            case 1:
                return fireArrow(h);
        }
    }

     /**
      * Iconic magic missle attack method
      * @param e the entity being attacked
      * @return a string description of the attack
      */
     public String magicMissile(Entity e) {
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
     public String fireball(Entity e) {
          int dmg = (int) (Math.random() * 5) + 1; // Recomended damage, might tweak so hero does more dmg
          e.takeDamage(dmg);
          return this.getName() + " casts Fireball on " + e.getName()
          + " dealing " + dmg + " points of fire damage.";
     }
}
