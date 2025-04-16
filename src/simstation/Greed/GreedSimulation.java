package src.simstation.Greed;

import src.simstation.*;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;

import src.mvc.*;

public class GreedSimulation{

   
}

class GreedFactory extends WorldFactory{

    @Override
    public Model makeModel() {
        return new Meadow();
    }

    public View makeView(Model model) {
        return new GreedView(model);
    }

    public String getTitle(){
        return "Greed Simulation";
    }

    public String about(){
        return "Simulation of Greed";
    }

    public String[] getHelp(){
        return new String[] {
            "Greediness: The amount of energy a cow will consume from a patch",
            "Grow Back Rate: The rate at which a patch will grow back energy",
            "Move Energy: The amount of energy a cow will lose when it moves"
        };
    }
}

class GreedPanel extends WorldPanel implements ChangeListener{

    JPanel sliderPanel = new JPanel();
    JSlider greedSlider, gbrateSlider, moveSlider;

    public GreedPanel(WorldFactory factory) {
        super(factory);
        

        sliderPanel.setLayout(new GridLayout(3, 1));
        sliderPanel.setOpaque(false);

        greedSlider = new JSlider(JSlider.HORIZONTAL, 0, 100, 50);
        greedSlider.setMajorTickSpacing(10);
        greedSlider.setPaintTicks(true);
        greedSlider.setPaintLabels(true);
        greedSlider.setLabelTable(greedSlider.createStandardLabels(10));

        gbrateSlider = new JSlider(JSlider.HORIZONTAL, 0, 10, 1);
        gbrateSlider.setMajorTickSpacing(10);
        gbrateSlider.setMinorTickSpacing(2);
        gbrateSlider.setPaintTicks(true);
        gbrateSlider.setPaintLabels(true);
        gbrateSlider.setLabelTable(gbrateSlider.createStandardLabels(10));

        moveSlider = new JSlider(JSlider.HORIZONTAL, 0, 50, 10);
        moveSlider.setMajorTickSpacing(10);
        moveSlider.setMinorTickSpacing(5);
        moveSlider.setPaintTicks(true);
        moveSlider.setPaintLabels(true);
        moveSlider.setLabelTable(moveSlider.createStandardLabels(10));

        greedSlider.addChangeListener(this);
        gbrateSlider.addChangeListener(this);
        moveSlider.addChangeListener(this);

        greedSlider.addChangeListener(e -> {
            Cow.greediness = greedSlider.getValue();
        });
        gbrateSlider.addChangeListener(e -> {
            Patch.growBackRate = gbrateSlider.getValue();
        });
        moveSlider.addChangeListener(e -> {
            Meadow.moveEnergy = moveSlider.getValue();
        });

        JPanel p = new JPanel();
        JPanel pp = new JPanel();

        p.setLayout(new BorderLayout());
        p.setOpaque(false);
        pp.setOpaque(false);
        pp.add(new JLabel("Greediness"));
        p.add(pp, BorderLayout.NORTH);
        pp = new JPanel();
        pp.setOpaque(false);
        pp.add(greedSlider);
        p.add(pp, BorderLayout.CENTER);
        sliderPanel.add(p);

        p = new JPanel();
        pp = new JPanel();
        p.setLayout(new BorderLayout());
        p.setOpaque(false);
        pp.setOpaque(false);
        pp.add(new JLabel("Grow Back Rate"));
        p.add(pp, BorderLayout.NORTH);
        pp = new JPanel();
        pp.setOpaque(false);
        pp.add(gbrateSlider);
        p.add(pp, BorderLayout.CENTER);
        sliderPanel.add(p);

        p = new JPanel();
        pp = new JPanel();
        p.setLayout(new BorderLayout());
        p.setOpaque(false);
        pp.setOpaque(false);
        pp.add(new JLabel("Move Energy"));
        p.add(pp, BorderLayout.NORTH);
        pp = new JPanel();
        pp.setOpaque(false);
        pp.add(moveSlider);
        p.add(pp, BorderLayout.CENTER);
        sliderPanel.add(p);

        p = new JPanel();
        p.setLayout(new BoxLayout(p,BoxLayout.Y_AXIS));
        p.setOpaque(false);
        p.add(threadPanel);
        p.add(sliderPanel);

        controlPanel.add(p, BorderLayout.NORTH);
        
    }
    

    @Override
    public void stateChanged(ChangeEvent e) {
        if(e.getSource() == greedSlider){
            Cow.setGreediness(greedSlider.getValue());
        }else if(e.getSource() == gbrateSlider){
            Patch.setGrowBackRate(gbrateSlider.getValue());
        }else if(e.getSource() == moveSlider){
            Meadow.setMoveEnergy(moveSlider.getValue());
        }
        model.changed();
    }

    public void update(){
        greedSlider.setValue(Cow.greediness);
        gbrateSlider.setValue(Patch.growBackRate);
        moveSlider.setValue(Meadow.moveEnergy);
    }
}

class GreedView extends WorldView{
    public GreedView(Model model) {
        super(model);
    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);

        Patch[][] meadow = ((Meadow)model).getMeadow();

        for(int rowIndex = 0; rowIndex < 50; rowIndex++){
            for(int colIndex = 0; colIndex < 50; colIndex++){
                drawPatch(g, meadow[rowIndex][colIndex], rowIndex, colIndex);
            }
        }
        
        for(Agent a:agentList){
            drawAgents(a,g);
        }
    } 

    public void drawPatch(Graphics g, Patch patch, int rowIndex, int colIndex){
        int x = rowIndex * 10;
        int y = colIndex * 10;
        if(patch.getEnergy() == 0){
            g.setColor(new Color(5, 31, 1));
            g.fillRect(x, y, 10, 10);
        }else if(patch.getEnergy() < 20){
            g.setColor(new Color(16,92,4));
            g.fillRect(x, y, 10, 10);
        }else if(patch.getEnergy() < 40){
            g.setColor(new Color(27,153 ,7));
            g.fillRect(x, y, 10, 10);
        }else if(patch.getEnergy() < 60){
            g.setColor(new Color(38, 214, 10));
            g.fillRect(x, y, 10, 10);
        }else if(patch.getEnergy() < 80){
            g.setColor(new Color(49, 255, 13));
            g.fillRect(x, y, 10, 10);
        }else{
            g.setColor(Color.GREEN);
            g.fillRect(x, y, 10, 10);
        }
        g.setColor(Color.WHITE);
        g.drawRect(x, y, 10, 10);

    }

    @Override
    public void drawAgents(Agent a, Graphics gc) {
        Cow cow = (Cow)a;
            if(cow.getEnergy() == 0){
                gc.setColor(Color.white);
                gc.fillOval(cow.getAgentX() - 5, cow.getAgentY() - 5, 10, 10);
            }else if(cow.getEnergy() < 33){
                gc.setColor(Color.YELLOW);
                gc.fillOval(cow.getAgentX() - 5, cow.getAgentY() - 5, 10, 10);
            }else if(cow.getEnergy() < 66){
                gc.setColor(Color.ORANGE);
                gc.fillOval(cow.getAgentX() - 5, cow.getAgentY() - 5, 10, 10);
            }else{
                gc.setColor(Color.RED);
                gc.fillOval(cow.getAgentX() - 5, cow.getAgentY() - 5, 10, 10);
            }
    }
}
