package src.simstation;

import java.util.ArrayList;
import java.util.List;
import java.util.*;

import src.mvc.*;

public abstract class World extends Model {
    public final static int SIZE = 500;
    public int clock = 0;
    public int alive = 0;

    private List<Agent> agents = new ArrayList<>();

    public World() {
        super();
        ObserverAgent observer = new ObserverAgent(this);
    }

    public Iterator<Agent> iterator() {
        return agents.iterator();
    }

    public void addAgent(Agent a) {
        agents.add(a);
    }

    public void pauseAgents() {
        for (Agent agent : agents) {
            agent.pause();
        }
    }

    public void resumeAgents() {
        for (Agent agent : agents) {
            agent.resume();
        }
    }

    public void startAgents() {
        for (Agent agent : agents) {
            agent.start();
        }
    }

    public void stopAgents() {
        for (Agent agent : agents) {
            agent.stop();
        }
    }

    public void getStatus() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getStatus'");
    }

    public abstract void populate();

    public void updateStatistics() {

    }

    public Agent getNeighbors(Agent caller, int radius) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getNeighbors'");
    }

}
