package src.mvc;

import javax.swing.*;

public class View extends JPanel implements Subscriber {

    protected Model model;

    public View(Model model) {
        this.model = model;
        this.model.subscribe(this);
    }

    public void setModel(Model newModel) {
        this.model.unsubscribe(this);
        this.model = newModel;
        this.model.subscribe(this);
    }

    @Override
    public void update() {
        repaint();
    }

}
