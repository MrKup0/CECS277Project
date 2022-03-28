/**
 * Abstract class representing an Enemy
 */
public abstract class Enemy extends Entity{
     public Enemy(String n, int mHp) {
          super(n, mhp);
     }

     public abstract String attack(Hero h);
}



