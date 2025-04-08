package src.simstation;

public abstract class MobileAgent extends Agent {

    protected Heading heading;

    public MobileAgent(String agentName) {
        super(agentName);
        heading = Heading.random();
    }

    public void move(int steps) {
        int x = getAgentX();
        int y = getAgentY();
        for(int i = 0; i < steps; i++) {
            if (heading == Heading.NORTH) {
                setAgentY(y--);
            } else if (heading == Heading.SOUTH) {
                setAgentY(y++);
            } else if (heading == Heading.WEST) {
                setAgentX(x--);
            } else if (heading == Heading.EAST) {
                setAgentX(x++);
            }
        }
    }

    public void turn(Heading dir) {
        this.heading = dir;
    }

}
