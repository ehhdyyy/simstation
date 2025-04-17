package src.simstation.PrisonerDilemma;

import src.simstation.*;
import src.mvc.*;
import java.awt.*;

public class PrisonerSimulation extends World {
    static int cheatersNum = 10;
    static int cooperatorsNum = 10;
    static int tit4TatsNum = 10;
    static int randomlyCooperatorsNum = 10;

    @Override
    public void populate() {
        for (int i = 0; i < cheatersNum; i++) {
            addAgent(new Prisoner("Cheater", new Cheat()));
        }
        for (int i = 0; i < cooperatorsNum; i++) {
            addAgent(new Prisoner("Cooperator", new Cooperate()));
        }
        for (int i = 0; i < tit4TatsNum; i++) {
            addAgent(new Prisoner("Tit4Tat", new Tit4Tat()));
        }
        for (int i = 0; i < randomlyCooperatorsNum; i++) {
            addAgent(new Prisoner("RandomlyCooperator", new RandomlyCooperate()));
        }
    }

    public double avgScore(String name) {
        double total = 0;
        int count = 0;
        for (Agent agent : getAgents()) {
            if (agent.getAgentName().equals(name)) {
                total += ((Prisoner) agent).getFitness();
                count++;
            }
        }
        return total / count;
    }

    @Override
    public void getStatus() {
        Utilities.inform(new String[] {
                "Average Scores:",
                "Cheaters: " + avgScore("Cheater"),
                "Cooperators: " + avgScore("Cooperator"),
                "Tit4Tat: " + avgScore("Tit4Tat"),
                "RandomlyCooperator: " + avgScore("RandomlyCooperator")
        });
    }

    public static void main(String[] args) {
        AppPanel app = new WorldPanel(new PrisonerSimFactory());
        app.display();
    }
}

class PrisonerSimFactory extends WorldFactory {

    public Model makeModel() {
        return new PrisonerSimulation();
    }

    @Override
    public View makeView(Model m) {
        return new PrisonerView(m);
    }

    public String getTitle() {
        return "Prisoner's Dilemma Simulation";
    }

    public String about() {
        return "Simulation of Prisoner's Dilemma";
    }
}

class PrisonerView extends WorldView {

    public PrisonerView(Model model) {
        super(model);
    }

    @Override
    public void drawAgents(Agent agent, Graphics gc) {
        Prisoner prisoner = (Prisoner) agent;

        if (prisoner.getAgentName().equals("Cheater")) {
            gc.setColor(Color.RED);
        } else if (prisoner.getAgentName().equals("Cooperator")) {
            gc.setColor(Color.GREEN);
        } else if (prisoner.getAgentName().equals("Tit4Tat")) {
            gc.setColor(Color.BLUE);
        } else if (prisoner.getAgentName().equals("RandomlyCooperator")) {
            gc.setColor(Color.YELLOW);
        }
        if (prisoner.cooperate() || prisoner.getAgentName().equals("Cheater")) {
            gc.fillOval(agent.getAgentX(), agent.getAgentY(), 10, 10);
        } else {
            gc.drawOval(agent.getAgentX(), agent.getAgentY(), 10, 10);
        }
    }
}
