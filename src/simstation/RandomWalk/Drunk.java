package src.simstation.RandomWalk;

import src.simstation.*;
import src.mvc.*;

public class Drunk extends MobileAgent {

    public Drunk() {
        super("Drunk");
    }

    @Override
    public void update() {
        heading = Heading.random();
        int steps = Utilities.rng.nextInt(20) + 1;
        move(steps);
    }

}
