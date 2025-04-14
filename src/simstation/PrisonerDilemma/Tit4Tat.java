package src.simstation.PrisonerDilemma;

public class Tit4Tat implements Strategy {
    @Override
    public boolean cooperate() {
        if (Prisoner.getPartnerCheated()) {
            return true;
        } else {
            return false;
        }
    }
}
