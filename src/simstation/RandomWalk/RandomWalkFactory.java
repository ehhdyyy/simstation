package src.simstation.RandomWalk;

import src.mvc.*;
import src.simstation.*;

public class RandomWalkFactory extends WorldFactory {
    public Model makeModel() {
        return new RandomWalkSimulation();
    }

    public String getTitle() {
        return "Random Walks";
    }
}
