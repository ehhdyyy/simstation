package src.simstation;

import src.simstation.*;

public class ObserverAgent extends Agent {

    public ObserverAgent(String agentName) {
        super("Stats");
    }

    @Override
    public void update() {
        try {
            getWorld().updateStatistics();
            Thread.sleep(1000); // Sleep for 1 second before updating again
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
