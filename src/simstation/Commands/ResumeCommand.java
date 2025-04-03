package src.simstation.Commands;

import src.mvc.*;
import src.simstation.World;

public class ResumeCommand extends Command {

    public ResumeCommand(Model model) {
        super(model);
    }

    @Override
    protected void execute() throws Exception {
        ((World) model).resumeAgents();
    }

}
