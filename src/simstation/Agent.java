package src.simstation;

import java.io.Serializable;

import src.mvc.Utilities;

public abstract class Agent implements Runnable, Serializable {
    private int xc, yc;
    private boolean paused = false;
    private boolean stopped = false;
    private String agentName;
    private World world;
    transient protected Thread myThread;

    public Agent(String agentName, World world) {
        this.agentName = agentName;
        this.world = world;
        xc = Utilities.rng.nextInt(World.SIZE);
        yc = Utilities.rng.nextInt(World.SIZE);
        paused = false;
        stopped = false;
    }

    public void start() {
        paused = false;
        stopped = false;
        myThread = new Thread(this);
        myThread.start();
    }

    public void stop() {
        stopped = true;
    }

    public void pause() {
        paused = true;
    }

    public void resume() {
        paused = false;
    }

    public abstract void update();

    public void run() {
        while (!stopped) {
            if (!paused) {
                update();
            }
            try {
                Thread.sleep(100); // Adjust sleep time as needed
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    public int getAgentX() {
        return xc;
    }

    public int getAgentY() {
        return yc;
    }

    public String getAgentName() {
        return agentName;
    }

    public World getWorld() {
        return world;
    }
}
