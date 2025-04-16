package src.simstation.PrisonerDilemma;

import src.mvc.Utilities;
import src.simstation.MobileAgent;

public class Prisoner extends MobileAgent {
    private int fitness = 0;
    private boolean partnerCheated = false;

    public Prisoner(String agentName) {
        super(agentName);
    }

    public boolean getPartnerCheated() {
        return partnerCheated;
    }

    public boolean cooperate() {
        return strategy.cooperate();
    }

    @Override
    public void update() {
        MobileAgent partner = getWorld.getNeighbor(this, 100);

        Boolean cheated = this.cooperate();

        if (partner != null) {
            updateFitness(fitness);
            if (cheated) {
                if (partnerCheated) {
                    updateFitness(1);
                } else {
                    updateFitness(5);
                }
            } else {
                if (!partnerCheated) {
                    updateFitness(3);
                }
            }
        }

        move(Drunk);
    }

    public void updateFitness(int amt) {
        fitness += amt;
    }
}
