package src.simstation.Plague;

import src.simstation.*;
import src.mvc.*;

public class Host extends MobileAgent{

    public boolean infected;
    public int timeInfected;
    public boolean dead;

    public Host(String agentName) {
        super(agentName);
        infected = false;
        timeInfected = 0;
        dead = false;
    }

    public boolean isInfected(){
        return this.infected;
    }

    public boolean isDead(){
        return this.dead;
    }

    public void setDead(boolean b){
        this.dead = b;
    }

    public void setInfected(boolean b){
        this.infected = b;
    }

    public void infect(){
        if(!isInfected()){
            int luck = Utilities.rng.nextInt(101);
            if(luck < PlagueSimulation.VIRULENCE && world != null){
                infected = true;
                timeInfected = world.getClock();
            }
        }
    }

    @Override
    public void update() {

        Host victim = (Host)world.getNeighbor(this, 30);
        if(victim != null){
           victim.infect();
            int time = world.getClock() - timeInfected;
            if (time >= PlagueSimulation.infectionLength){
                if(PlagueSimulation.FATAL){
                    this.setDead(true);
                    stop();
                }else{
                    victim.setInfected(false);
                }
            }
        }
        
        heading = Heading.random();
        int steps = Utilities.rng.nextInt(20) + 1;
        move(steps);
    }
    
}
