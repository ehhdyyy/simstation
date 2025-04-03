package src.mvc;

public interface AppFactory {

    public String getTitle();

    public String[] getHelp();

    public String about();

    public Model makeModel();

    public View makeView(Model m);

    public String[] getEditCommands();

    public Command makeEditCommand(Model model, String type, Object source);
}
