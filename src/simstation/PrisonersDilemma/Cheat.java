package src.simstation.PrisonersDilemma;

public class Cheat extends Strategy {

    @Override
    public boolean cooperate(Prisoner prisoner) {     
        return false; // Always cheat
    }
    
}
