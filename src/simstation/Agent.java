package src.simstation;

import java.io.Serializable;

import src.mvc.Utilities;

public abstract class Agent implements Runnable, Serializable {
    private int xc, yc;
    private boolean paused = false;
    private boolean stopped = false;
    private String agentName;
    protected World world;
    transient protected Thread myThread;

    public Agent(String agentName) {
        this.agentName = agentName;
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

    public synchronized void stop() {
        stopped = true;
    }

    public synchronized boolean isStopped() {
        return stopped;
    }

    public synchronized void pause() {
        paused = true;
    }

    public synchronized boolean isPaused() {
        return paused;
    }

    public synchronized void resume() {
        this.notify();
    }

    public void run() {
        myThread = Thread.currentThread();
        while (!stopped) {
            try {
                update();
                Thread.sleep(250);
                checkSuspended();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    public synchronized void checkSuspended() {
        try {
            while (!stopped && paused) {
                wait();
                paused = false;
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public abstract void update();

    public int getAgentX() {
        return xc;
    }

    public void setAgentX(int x) {
        xc = x;
    }

    public int getAgentY() {
        return yc;
    }

    public void setAgentY(int y) {
        yc = y;
    }

    public String getAgentName() {
        return agentName;
    }

    public World getWorld() {
        return world;
    }

    public void setWorld(World world) {
        this.world = world;
    }
}
