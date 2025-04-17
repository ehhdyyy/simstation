package src.simstation;

public abstract class MobileAgent extends Agent {

    protected Heading heading;

    public MobileAgent(String agentName) {
        super(agentName);
        heading = Heading.random();
    }

    public void move(int steps) {
        int x = getAgentX();
        int newX;
        int y = getAgentY();
        int newY;
        for(int i = 0; i < steps; i++) {
            if (heading == Heading.NORTH) {
                newY = y--;
                if(newY <= 0){
                    setAgentY(World.SIZE);
                }else{
                    setAgentY(newY);
                }
            } else if (heading == Heading.SOUTH) {
                newY = y++;
                if(newY >= 500){
                    setAgentY(0);
                }else{
                    setAgentY(newY);
                }
            } else if (heading == Heading.WEST) {
                newX = x--;
                if(newX <= 0){
                    setAgentX(World.SIZE);
                }else{
                    setAgentX(newX);
                }

            } else if (heading == Heading.EAST) {
                newX = x++;
                if(newX >= 500){
                    setAgentX(0);
                }else{
                    setAgentX(newX);
                }
            }
            world.changed();
        }
    }

    public void turn(Heading dir) {
        this.heading = dir;
    }

}
