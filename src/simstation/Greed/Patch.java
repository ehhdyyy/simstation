package src.simstation.Greed;

import src.simstation.*;
import src.mvc.*;

public class Patch extends Agent {

    public int energy;
    public static int growBackRate;
    public static int patchSize = 10;
    public Object lock = new Object();
    transient public Thread myThread;

    public Patch(String agentName) {
        super(agentName);
        this.energy = Utilities.rng.nextInt(101);
        myThread = new Thread(this);
        myThread.start();
    }


    public static void setGrowBackRate(int g) {
        growBackRate = g;
    }

    public void eatMe(Cow cow, int amt){
        synchronized(lock){
            energy -= amt;
        }
        
    }

    public void setEnergy(int e){
        this.energy = e;
    }

    public int getEnergy(){
        return energy;
    }

    public static int getPatchSize(){
        return patchSize;
    }

    @Override
    public void update() {
        try{
            synchronized(lock){
                energy = Math.min(100, energy += growBackRate);
                Thread.sleep(1000);
            }
        }catch (InterruptedException ie){
            System.err.println(ie.getMessage());
        }
    }

}
