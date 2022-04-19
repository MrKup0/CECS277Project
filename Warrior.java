/*
     Two versions are here, one if the interface works as is
     the other if methods must be overridden here.
*/

public class Warrior extends Enemy implements Fighter {

    public Warrior(String n, int mHp) {
        super(n, mHp);
    }
}

/*
public class Warrior extends Enemy implements Fighter {

    public Warrior(String n, int mHp) {
        super(n, mHp);
    }

    /**
     * Sword attack method
     * @param e the entity being attacked with a sword
     * @return the string description of the sword attack
     *//*
    public String Sword(Entity e) {
        int dmg = (int) (Math.random() * 5) + 1;
        e.takeDamage(dmg);
        return this.getName() + " slashes " + e.getName() + " with a sword for " + dmg + " damage.";
    }

    /**
     * Axe attack method
     * @param e the entity being attacked with an axe
     * @return the string description of the axe attack
     *//*
    public String Axe(Entity e) {
        int dmg = (int) (Math.random() * 5) + 1;
        e.takeDamage(dmg);
        return this.getName() + " slashes " + e.getName() +
        " with an axe for " + dmg + " damage";
    }
}
*/
