package src.simstation.PrisonerDilemma;

import src.mvc.Utilities;
import src.simstation.Heading;
import src.simstation.MobileAgent;

public class Prisoner extends MobileAgent {
    private int fitness;
    private boolean partnerCheated = false;
    private Strategy strategy;

    public Prisoner(String agentName) {
        super(agentName);
        this.fitness = 0;
    }

    public boolean getPartnerCheated() {
        return partnerCheated;
    }

    public void setPartnerCheated(boolean partnerCheated) {
        this.partnerCheated = partnerCheated;
    }

    public boolean cooperate() {
        return strategy.cooperate(this);
    }

    public void updateFitness(int amt) {
        fitness += amt;
    }

    public int getFitness(){
        return fitness;
    }

    @Override
    public void update() {
        Prisoner partner = (Prisoner) world.getNeighbor(this, 100);

        if (partner != null) {
            boolean cooperate = this.cooperate();
            partnerCheated = !partner.cooperate();

            if (cooperate) {
                if (partnerCheated) {
                    this.updateFitness(1);
                    partner.updateFitness(1);
                } else {
                    this.updateFitness(5);
                    this.setPartnerCheated(false);
                }
            } else {
                if (partnerCheated) {
                    partner.updateFitness(5);
                    this.setPartnerCheated(true);
                } else {
                    partner.updateFitness(3);
                    this.updateFitness(3);
                }
            }
        }

        heading = Heading.random();
        int steps = Utilities.rng.nextInt(20) + 1;
        move(steps);
    }
}
