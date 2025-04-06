package src.simstation;

public abstract class MobileAgent extends Agent {

    protected Heading heading;

    public MobileAgent(String agentName, World world) {
        super(agentName, world);
        heading = Heading.random();
    }

    public void move(int steps) {

    }

    public void turn(Heading dir) {

    }

}
