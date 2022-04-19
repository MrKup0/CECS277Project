/*
     Two versions are here, one if the interface works as is
     the other if methods must be overridden here.
*/

public class Ranger extends Enemy implements Archer {

    public Ranger(String n, int mHp) {
        super(n, mHp);
    }

    /**
     * Randomly chooses an attack from the Archer interface
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
}

/*
public class Ranger extends Enemy implements Archer {

    public Ranger(String n, int mHp) {
        super(n, mHp);
    }

    /**
     * Randomly chooses an attack from the Archer interface
     * @param the hero object taking damage
     * @return the string description of the attack
     *//*
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
      * Basic arrow attack method
      * @param e the entity being attacked
      * @return a string description of the attack
      *//*
     public String arrow(entity e) {
        int dmg = (int) (Math.random() * 5) + 1; // Recomended damage, might tweak so hero does more dmg
        e.takeDamage(dmg);
        return this.getName() + " shoots at " + e.getName()
        + " with an arrow for " + dmg + " points of damage.";
     }

     /**
      * Fire arrow attack method
      * @param e the entity being shoot at with a fire arrow
      * @return the string description of the fire arrow attack
      *//*
     public String fireArrow(entity e) {
        int dmg = (int) (Math.random() * 5) + 1; // Recomended damage, might tweak so hero does more dmg
        e.takeDamage(dmg);
        return this.getName() + " lobs a fire arrow at " + e.getName() +
        " for " + dmg + " points of damage";
     }
}*/
