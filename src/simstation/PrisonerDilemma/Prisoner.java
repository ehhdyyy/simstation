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
        return false;
    }

    @Override
    public void update() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    public void updateFitness(int amt) {

    }
}
