package src.simstation.PrisonerDilemma;

import src.mvc.Utilities;
import src.simstation.MobileAgent;

public class Prisoner extends MobileAgent {
    private int fitness = 0;
    private boolean partnerCheated = false;
    private Strategy strategy;

    public Prisoner(String agentName) {
        super(agentName);
    }

    public boolean getPartnerCheated() {
        return partnerCheated;
    }

    public boolean cooperate() {
        return strategy.cooperate(this);
    }

    @Override
    public void update() {
        MobileAgent partner = (Prisoner) world.getNeighbor(this, 100);

        boolean cheated = this.cooperate();
        boolean partnerCheated = partner.cooperate();

        if (partner != null) {
            if (cheated) {
                if (partnerCheated) {
                    updateFitness(1);
                    partner.updateFitness(1);
                } else {
                    updateFitness(5);
                }
            } else {
                if (partnerCheated) {
                    partner.updateFitness(5);
                } else {
                    pdateFitness(3);
                    partner.updatefitness(3);
                }
            }
        }

        int steps = Utilities.rng.nextInt(20) + 1;
        move(steps);
    }

    public void updateFitness(int amt) {
        this.fitness += amt;
    }
}
