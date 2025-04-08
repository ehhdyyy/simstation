package src.simstation;

import src.mvc.*;
import java.util.List;
import java.awt.*;
import javax.swing.*;

public class WorldView extends View {
    World world;
    List<Agent>agentList;

    public WorldView(Model model) {
        super(model);
        world = (World) model;
        agentList = world.getAgents();
        setPreferredSize(new Dimension(500, 500));
        setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        setBackground(Color.GRAY);
    }

    @Override
    public void setModel(Model newModel) {
        super.setModel(newModel);
        repaint();
    }

    @Override
    public void paintComponent(Graphics gc) {
        super.paintComponent(gc);

        for(Agent a:agentList){
            drawAgents(a,gc);
        }

        update();
    }

    public void drawAgents(Agent a, Graphics gc) {

        gc.setColor(Color.RED);
        gc.fillOval(a.getAgentX(),a.getAgentY(),10,10);

    }

    @Override
    public void update() {
        // Update the view based on the model's state
        // This could involve redrawing the world, updating agent positions, etc.
        repaint();
    }

}
