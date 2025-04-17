package src.simstation.Greed;

import src.simstation.*;

import src.mvc.*;

public class Meadow extends World {

    public int waitPenalty = 5;
    public static int moveEnergy = 10;
    private int numCows = 50;
    private int dim;
    private final Patch[][] meadow;

    public Meadow(){
        super();
        dim = SIZE/Patch.getPatchSize();
        meadow = new Patch[dim][dim];

        for(int i = 0; i < meadow.length; i++){
            for(int j = 0; j < meadow[i].length; j++){
                Patch patch = new Patch("Patch");
                meadow[i][j] = patch;
            }
        }
    }

    public Patch[][] getMeadow(){
        return meadow;
    }

    @Override
    public void populate() {
        for(int i = 0; i < numCows; i++) {
            addAgent(new Cow("Cow"));
        }

        for(int i = 0; i < dim; i++){
            for(int j = 0; j < dim; j++){
                meadow[i][j].setEnergy(Utilities.rng.nextInt(101));
            }
        }
    }

    public Patch getPatch(int xc, int yc) {
        return meadow[xc][yc];
    }

    public int getMoveEnergy(){
        return moveEnergy;
    }
    
    public static void main(String[] args) {
        AppPanel app = new GreedPanel(new GreedFactory());
        app.display();
    }

    public static void setMoveEnergy(int value) {
        moveEnergy = value;
    }

}
