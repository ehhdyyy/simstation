package src.simstation.PrisonersDilemma;

public class Tit4Tat extends Strategy{
    
    @Override
    public boolean cooperate(Prisoner prisoner) {
        return !prisoner.getPartnerCheated(); // Cooperate if the partner did not cheat in the last round, ex: if opponent cheated(true) then return cheat(false aka do not cooperate)
    }
}
