package src.simstation.PrisonersDilemma;

import src.simstation.*;
import src.mvc.*;
import java.awt.*;

public class PDSimulation extends World{
    static int numCheaters = 10;
    static int numCooperators = 10;
    static int numTit4Tats = 10;
    static int numRandomlyCooperators = 10;

    public void populate(){
        for(int i = 0; i < numCheaters; i++){
            addAgent(new Prisoner("Cheater", new Cheat()));
        }
        for(int i = 0; i < numCooperators; i++){
            addAgent(new Prisoner("Cooperator", new Cooperate()));
        }
        for(int i = 0; i < numTit4Tats; i++){
            addAgent(new Prisoner("Tit4Tat", new Tit4Tat()));
        }
        for(int i = 0; i < numRandomlyCooperators; i++){
            addAgent(new Prisoner("RandomlyCooperator", new RandomlyCooperate()));
        }
    }

    public double avgScore(String name){
        double total = 0;
        int count = 0;
        for(Agent a: getAgents()){
            if(a.getAgentName().equals(name)){
                total += ((Prisoner)a).getFitness();
                count++;
            }
        }
        return total/count;
    }

    @Override
    public void getStatus(){
        Utilities.inform(new String[]{
            "Average Scores:",
            "-Cheaters: " + avgScore("Cheater"),
            "-Cooperators: " + avgScore("Cooperator"),
            "-Tit4Tat: " + avgScore("Tit4Tat"),
            "-RandomlyCooperator: " + avgScore("RandomlyCooperator"),
        });
    }

    public static void main(String[] args) {
        AppPanel app = new WorldPanel(new PDSimulationFactory());
        app.display();
    }

}

class PDSimulationFactory extends WorldFactory{
    
    public Model makeModel() {
        return new PDSimulation();
    }

    @Override
    public View makeView(Model m) {
        return new PDView(m);
    }

    public String getTitle() {
        return "Prisoner's Dilemma Simulation";
    }

    public String about() {
        return "Simulation of Prisoner's Dilemma";
    }
    
}

class PDView extends WorldView{
    
    public PDView(Model model) {
        super(model);
    }

    @Override
    public void drawAgents(Agent a, Graphics gc) {
        Prisoner p = (Prisoner)a;

        if(p.getAgentName().equals("Cheater")){
            gc.setColor(Color.RED);
        }else if(p.getAgentName().equals("Cooperator")){
            gc.setColor(Color.GREEN);
        }else if(p.getAgentName().equals("Tit4Tat")){
            gc.setColor(Color.BLUE);
        }else if(p.getAgentName().equals("RandomlyCooperator")){
            gc.setColor(Color.YELLOW);
        }
        if(p.cooperate() || p.getAgentName().equals("Cheater")){
            gc.fillOval(a.getAgentX(),a.getAgentY(),10,10);
        }
        else{
            gc.drawOval(a.getAgentX(),a.getAgentY(),10,10);
        }

    }

}