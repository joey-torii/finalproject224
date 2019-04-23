package Tiles;

import java.lang.reflect.Constructor;
import graphics.*;

/**
    A goal class is a Sprite that the player can pick up.
*/
public abstract class goal extends Sprite {

    public goal(Animation anim) {
        super(anim);
    }

    public Object clone() {
        // use reflection to create the correct subclass
        Constructor constructor = getClass().getConstructors()[0];
        try {
            return constructor.newInstance(
                new Object[] {(Animation)anim.clone()});
        }
        catch (Exception ex) {
            // should never happen
            ex.printStackTrace();
            return null;
        }
    }


    /**
        A Goal PowerUp. Advances to the next map.
    */
    public static class Goal extends goal {
        public Goal(Animation anim) {
            super(anim);
        }
    }

}
