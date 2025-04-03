package src.mvc;

public abstract class Command {
    protected Model model;

    public Command(Model m){
        this.model = m;
    }

    protected abstract void execute() throws Exception;
}
