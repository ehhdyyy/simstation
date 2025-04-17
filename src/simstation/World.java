package src.simstation;

import java.util.ArrayList;
import java.util.List;
import java.util.*;

import src.mvc.*;

public abstract class World extends Model {
    public final static int SIZE = 500;
    public int clock = 0;
    public int alive = 0;

    private List<Agent> agents;
    private ObserverAgent observer;

    public World() {
        super();
        agents = new ArrayList<Agent>();
        observer = new ObserverAgent("Observer");
    }

    public Iterator<Agent> iterator() {
        return agents.iterator();
    }

    public void addAgent(Agent a) {
        agents.add(a);
    }

    public List<Agent> getAgents() {
        return agents;
    }

    public void pauseAgents() {
        for (Agent agent : agents) {
            agent.pause();
        }
        observer.pause();
        changed();
    }

    public void resumeAgents() {
        for (Agent agent : agents) {
            agent.resume();
        }
        observer.resume();
        changed();
    }

    public void startAgents() {
        clock = 0;

        stopAgents();
        agents.clear();

        populate();
        for (Agent agent : agents) {
            agent.setWorld(this);
            agent.start();
        }
        observer.setWorld(this);
        observer.start();
        changed();
    }

    public void stopAgents() {
        for (Agent agent : agents) {
            agent.stop();
        }
        observer.stop();
        changed();
    }

    public void getStatus() {
        Utilities.inform(new String[] {
                "#agents: " + agents.size(),
                "#living: " + alive,
                "clock: " + clock,
        });
    }

    public abstract void populate();

    public void updateStatistics() {
        clock++;
        int dead = 0;
        for (Agent agent : agents) {
            if (agent.isStopped()) {
                dead++;
            }
        }
        alive = agents.size() - dead;
    }

    public Agent getNeighbor(Agent caller, int radius) {
        List<Agent> neighbors = new ArrayList<Agent>();
        for (Agent agent : agents) {
            if (agent != caller) {
                int x = Math.abs(agent.getAgentX() - caller.getAgentX());
                int y = Math.abs(agent.getAgentY() - caller.getAgentY());
                if (x < radius && y < radius) {
                    neighbors.add(agent);
                }
            }
        }
        if (neighbors.size() == 0) {
            return null;
        }
        return neighbors.get(Utilities.rng.nextInt(neighbors.size()));
    }
}
