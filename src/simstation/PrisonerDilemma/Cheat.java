package src.simstation.PrisonerDilemma;

public class Cheat implements Strategy {

    @Override
    public boolean cooperate(Prisoner prisoner) {
        return false;
    }

}
