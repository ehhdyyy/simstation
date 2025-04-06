package src.simstation;
import src.simstation.*;

public class ObserverAgent extends Agent {

    World world;

    public ObserverAgent(World world) {
        super(world);
        this.world = world;
    }

    @Override
    public void update() {
        updateStatistics();
    }

    

}
