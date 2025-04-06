package src.simstation;

import src.mvc.*;
import java.awt.*;

public class WorldView extends View {

    public WorldView(Model model) {
        super(model);
        setPreferredSize(new Dimension(500, 500));
        setBackground(Color.GRAY);
    }

    @Override
    public void paintComponent(Graphics gc) {
        super.paintComponent(gc);

    }

    public void drawAgents(Agent a, Graphics gc) {
        gc.setColor(Color.RED);

    }

    @Override
    public void update() {
        // Update the view based on the model's state
        // This could involve redrawing the world, updating agent positions, etc.
    }

}
