package src.simstation.Plague;

import src.simstation.*;
import src.mvc.*;
import java.awt.*;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.util.List;

public class PlagueSimulation extends World{
    static int initialInfected = 10;
    static int VIRULENCE = 50;
    static int initialPopulationSize = 50;
    static int infectionLength = 200;
    static boolean FATAL = true; //Infected can die

    public PlagueSimulation(){
        super();
    }

    @Override
    public void populate(){
        for(int i = 0; i < initialPopulationSize; i++){
            addAgent(new Host("Host"));
        }
        infectInitial();
    }

    public void infectInitial(){
        List<Agent> agents = getAgents();
        int count = 0;
        while(count < initialPopulationSize/initialInfected){
            Host h = (Host)agents.get(Utilities.rng.nextInt(agents.size()));
            if(!h.isInfected()){
                h.setInfected(true);
                count++;
            }
        }
    }

    public boolean getFatal(){
        return FATAL;
    }

    public void toggleFatal() {
            FATAL = !getFatal();
            Utilities.inform(new String[]{
                "Fatal: " + getFatal(),
            });
    }

    @Override
    public void getStatus(){
        Utilities.inform(new String[]{
            "#agents = " + getAgents().size(),
            "clock = " + getClock(),
            "% infected = " + infectedPercent() + "%",
        });
    }

    private String infectedPercent() {
        List<Agent> agents = getAgents();
        double count = 0.0;
        for(Agent a : agents){
            Host h = (Host)a;
            if(h.isInfected()){
                count++;
            }
        }
        return String.format("%.1f",(count / agents.size() * 100));
    }

    public static void main(String[] args) {
        AppPanel app = new PlaguePanel(new PlagueFactory());
        app.display();
    }

}

class PlagueFactory extends WorldFactory{

    @Override
    public Model makeModel() {
        return new PlagueSimulation();
    }

    public String getTitle(){
        return "Plague Simulation";
    }

    public String about(){
        return "Simulation of Plague";
    }

    @Override
    public View makeView(Model m){
        return new PlagueView(m);
    }

    @Override
    public Command makeEditCommand(Model model, String type, Object source){
        if(type.equalsIgnoreCase("Toggle FATAL")){
            return new FatalCommand(model);
        }
        return super.makeEditCommand(model, type, source);
    }
}

class PlaguePanel extends WorldPanel implements ChangeListener{

    JPanel sliderPanel = new JPanel();
    JSlider initialInfectedSlider, infectionProbabilitySlider, initialPopulationSizeSlider, infectionLengthSlider;
    JButton fatalButton;

    public PlaguePanel(WorldFactory factory) {
        super(factory);

        sliderPanel.setLayout(new GridLayout(5, 1));
        sliderPanel.setOpaque(false);

        initialInfectedSlider = new JSlider(JSlider.HORIZONTAL, 0, 100, 10);
        initialInfectedSlider.setMajorTickSpacing(10);
        initialInfectedSlider.setPaintTicks(true);
        initialInfectedSlider.setPaintLabels(true);
        initialInfectedSlider.setLabelTable(initialInfectedSlider.createStandardLabels(10));

        infectionProbabilitySlider = new JSlider(JSlider.HORIZONTAL, 0, 100, 50);
        infectionProbabilitySlider.setMajorTickSpacing(10);
        infectionProbabilitySlider.setPaintTicks(true);
        infectionProbabilitySlider.setPaintLabels(true);
        infectionProbabilitySlider.setLabelTable(infectionProbabilitySlider.createStandardLabels(10));

        initialPopulationSizeSlider = new JSlider(JSlider.HORIZONTAL, 0, 200, 50);
        initialPopulationSizeSlider.setPreferredSize(new Dimension(400, 50));
        initialPopulationSizeSlider.setMajorTickSpacing(10);
        initialPopulationSizeSlider.setPaintTicks(true);
        initialPopulationSizeSlider.setPaintLabels(true);
        initialPopulationSizeSlider.setLabelTable(initialPopulationSizeSlider.createStandardLabels(20));

        infectionLengthSlider = new JSlider(JSlider.HORIZONTAL, 0, 500, 200);
        infectionLengthSlider.setPreferredSize(new Dimension(400,50));
        infectionLengthSlider.setMajorTickSpacing(100);
        infectionLengthSlider.setPaintTicks(true);
        infectionLengthSlider.setPaintLabels(true);
        infectionLengthSlider.setLabelTable(infectionLengthSlider.createStandardLabels(50));

        fatalButton = new JButton("Toggle FATAL");

        initialInfectedSlider.addChangeListener(this);
        infectionProbabilitySlider.addChangeListener(this);
        initialPopulationSizeSlider.addChangeListener(this);
        infectionLengthSlider.addChangeListener(this);
        fatalButton.addActionListener(this);

        initialInfectedSlider.addChangeListener(e -> {
            PlagueSimulation.initialInfected = initialInfectedSlider.getValue();
        });
        infectionProbabilitySlider.addChangeListener(e -> {
            PlagueSimulation.VIRULENCE = infectionProbabilitySlider.getValue();
        });
        initialPopulationSizeSlider.addChangeListener(e -> {
            PlagueSimulation.initialPopulationSize = initialPopulationSizeSlider.getValue();
        });
        infectionLengthSlider.addChangeListener(e -> {
            PlagueSimulation.infectionLength = infectionLengthSlider.getValue();
        });
    

        JPanel p = new JPanel();
        JPanel pp = new JPanel();

        p.setLayout(new BorderLayout());
        p.setOpaque(false);
        pp.setOpaque(false);
        pp.add(new JLabel("Initial % Infected"));
        p.add(pp, BorderLayout.NORTH);
        pp = new JPanel();
        pp.setOpaque(false);
        pp.add(initialInfectedSlider);
        p.add(pp, BorderLayout.CENTER);
        sliderPanel.add(p);

        p = new JPanel();
        pp = new JPanel();
        p.setLayout(new BorderLayout());
        p.setOpaque(false);
        pp.setOpaque(false);
        pp.add(new JLabel("Infection Probability"));
        p.add(pp, BorderLayout.NORTH);
        pp = new JPanel();
        pp.setOpaque(false);
        pp.add(infectionProbabilitySlider);
        p.add(pp, BorderLayout.CENTER);
        sliderPanel.add(p);

        p = new JPanel();
        pp = new JPanel();
        p.setLayout(new BorderLayout());
        p.setOpaque(false);
        pp.setOpaque(false);
        pp.add(new JLabel("Initial Population Size"));
        p.add(pp, BorderLayout.NORTH);
        pp = new JPanel();
        pp.setOpaque(false);
        pp.add(initialPopulationSizeSlider);
        p.add(pp, BorderLayout.CENTER);
        sliderPanel.add(p);

        p = new JPanel();
        pp = new JPanel();
        p.setLayout(new BorderLayout());
        p.setOpaque(false);
        pp.setOpaque(false);
        pp.add(new JLabel("Infection Length"));
        p.add(pp, BorderLayout.NORTH);
        pp = new JPanel();
        pp.setOpaque(false);
        pp.add(infectionLengthSlider);
        p.add(pp, BorderLayout.CENTER);
        sliderPanel.add(p);

        p = new JPanel();
        pp = new JPanel();
        p.setLayout(new BorderLayout());
        p.setOpaque(false);
        pp.setOpaque(false);
        pp.add(fatalButton);
        p.add(pp);
        sliderPanel.add(p);
        

        p = new JPanel();
        p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));
        p.setOpaque(false);
        p.add(threadPanel);
        p.add(sliderPanel);

        controlPanel.add(p, BorderLayout.NORTH);
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        if (e.getSource() == initialInfectedSlider) {
            PlagueSimulation.initialInfected = initialInfectedSlider.getValue();
        } else if (e.getSource() == infectionProbabilitySlider) {
            PlagueSimulation.VIRULENCE = infectionProbabilitySlider.getValue();
        } else if (e.getSource() == initialPopulationSizeSlider) {
            PlagueSimulation.initialPopulationSize = initialPopulationSizeSlider.getValue();
        } else if (e.getSource() == infectionLengthSlider) {
            PlagueSimulation.infectionLength = infectionLengthSlider.getValue();
        }
        model.changed();
    }

    public void update(){
        initialInfectedSlider.setValue(PlagueSimulation.initialInfected);
        infectionProbabilitySlider.setValue(PlagueSimulation.VIRULENCE);
        initialPopulationSizeSlider.setValue(PlagueSimulation.initialPopulationSize);
        infectionLengthSlider.setValue(PlagueSimulation.infectionLength);
    }
}

class PlagueView extends WorldView{
    public PlagueView(Model model){
        super(model);
    }

    @Override 
    public void paintComponent(Graphics g){
        super.paintComponent(g);

        for(Agent a:agentList){
            drawAgents(a,g);
        }
    }

    @Override
    public void drawAgents(Agent a, Graphics gc){
        Host host = (Host)a;

        if(!host.isInfected()){
            gc.setColor(Color.GREEN);
            gc.fillOval(host.getAgentX(), host.getAgentY(), 10, 10);
        }else if(host.isInfected() && !host.isDead()){
            gc.setColor(Color.RED);
            gc.fillOval(host.getAgentX(), host.getAgentY(), 10, 10);
        }else if(host.isDead()){
            gc.setColor(Color.white);
            gc.fillOval(host.getAgentX(), host.getAgentY(), 10, 10);
        }
    }
}

class FatalCommand extends Command{

    public FatalCommand(Model model){
        super(model);
    }

    @Override
    protected void execute() throws Exception{
        ((PlagueSimulation) model).toggleFatal();
    }
}