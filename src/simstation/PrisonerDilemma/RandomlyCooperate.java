package src.simstation.PrisonerDilemma;

import java.util.Random;

public class RandomlyCooperate implements Strategy {
    @Override
    public boolean cooperate() {
        Random random = new Random();
        boolean rand = random.nextBoolean();

        return rand;
    }
}
