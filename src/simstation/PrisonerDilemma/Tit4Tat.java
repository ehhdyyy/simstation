package src.simstation.PrisonerDilemma;

public class Tit4Tat implements Strategy {
    @Override
    public boolean cooperate(Prisoner prisoner) {
        return !prisoner.getPartnerCheated();
    }
}
