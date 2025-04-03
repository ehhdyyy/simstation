package src.simstation;

import java.io.Serializable;

public abstract class Agent implements Runnable, Serializable {
    private int xc, yc;
    private boolean paused = false, stopped = false;
    private String agentName;
    private Thread myThread;

}
