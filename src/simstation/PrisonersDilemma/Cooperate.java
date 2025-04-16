package src.simstation.PrisonersDilemma;

public class Cooperate extends Strategy{

    @Override
    public boolean cooperate(Prisoner prisoner) {
        return true; // Always cooperate
    }
    
}
