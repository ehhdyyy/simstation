package src.simstation.PrisonersDilemma;
import src.mvc.Utilities;

public class RandomlyCooperate extends Strategy{

    @Override
    public boolean cooperate(Prisoner prisoner) {
        return Utilities.rng.nextBoolean();
    }
    
}
