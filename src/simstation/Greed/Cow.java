package src.simstation.Greed;

import src.simstation.*;
import src.mvc.*;

public class Cow extends MobileAgent {

    public int energy = 100;
    public static int greediness = 25;

    public Cow(String agentName) {
        super("Cow");
        this.energy = 100;
        xc = getAgentX();
        yc = getAgentY();
    }

    public static void setGreediness(int g) {
        greediness = g;
    }

    public void addEnergy(int e) {
        energy += e;
    }

    public int getEnergy(){
        return this.energy;
    }

    @Override
    public void update() {
        
        Meadow meadow = (Meadow) world;
        Patch[][] patches = meadow.getMeadow();

        int x = getAgentX() / 10; 
        int y = getAgentY() / 10;

        if (x >= 0 && x < patches.length && y >= 0 && y < patches[0].length) {
            Patch currentPatch = patches[x][y];

            if (currentPatch.getEnergy() >= greediness) { //Eat if patch has enough energy
                currentPatch.eatMe(this, greediness);
                addEnergy(greediness);
            } else if(this.energy > meadow.getMoveEnergy()){ //Move if patch doesn't have enough energy & cow has enough energy to move
                energy -= meadow.getMoveEnergy();
                heading = Heading.random();
                int steps = Utilities.rng.nextInt(20) + 1;
                move(steps);
            }
            energy -= 1;

            if (energy <= 0) {
                stop(); // Stop the cow if it has no energy left
            }
        }
    }
}
