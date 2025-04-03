package src.simstation;

import java.util.ArrayList;
import java.util.List;
import java.util.*;

import src.mvc.*;

public abstract class World extends Model {
    protected final int SIZE = 500;
    private int clock = 0;
    private int alive = 0;

    private List<Agent> agents = new ArrayList<>();

    public Iterator<Agent> iterator() {
        return agents.iterator();
    }

    public void addAgent(Agent a) {
        agents.add(a);
    }

    public void pauseAgents() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'pauseAgents'");
    }

    public void resumeAgents() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'resumeAgents'");
    }

    public void startAgents() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'startAgents'");
    }

    public void getStatus() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getStatus'");
    }

    public void stopAgents() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'stopAgents'");
    }

    public abstract void populate();

    public void updateStatistics() {

    }

    public Agent getNeighbors(Agent caller, int radius) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getNeighbors'");
    }

}
