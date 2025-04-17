package src.simstation.PrisonersDilemma;
import src.mvc.*;
import src.simstation.*;

public class Prisoner extends MobileAgent{

    private int fitness;
    private boolean partnerCheated = false;
    private Strategy strategy;
    private String name;

    public Prisoner(String name, Strategy strategy){
        super(name);
        this.name = name;
        this.fitness = 0;
        this.strategy = strategy;
    }


    public boolean cooperate(){
        return strategy.cooperate(this);
    }

    public void updateFitness(int amt){
        this.fitness += amt;
    }

    public int getFitness(){
        return this.fitness;
    }

    public boolean getPartnerCheated(){
        return this.partnerCheated;
    }

    public void setPartnerCheated(boolean partnerCheated){
        this.partnerCheated = partnerCheated;
    }

    @Override
    public void update() {
        Prisoner opponent = (Prisoner)world.getNeighbor(this, 100);
        if(opponent !=null){
            boolean myMove = cooperate();
            boolean opponentMove = opponent.cooperate();

            if(myMove && opponentMove){         //Both cooperate
                this.updateFitness(3); 
                opponent.updateFitness(3);
            }else if(!myMove && !opponentMove){ //Both cheat
                this.updateFitness(1); 
                opponent.updateFitness(1);
            }else if(myMove && !opponentMove){  // I cooperate, opponent cheats
                opponent.updateFitness(5);
                this.setPartnerCheated(true);
            }else{                              // I cheat, opponent cooperates
                updateFitness(5); 
                this.setPartnerCheated(false);
            }
        }

        heading = Heading.random();
        int steps = Utilities.rng.nextInt(20) + 1;
        move(steps);

    }
    
}
