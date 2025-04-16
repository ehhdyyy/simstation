package src.simstation.PrisonerDilemma;

import java.util.Random;

public class RandomlyCooperate implements Strategy {
    @Override
    public boolean cooperate(Prisoner prisoner) {
        Random random = new Random();

        return random.nextBoolean();
    }
}
