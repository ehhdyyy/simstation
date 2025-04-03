package src.simstation.Commands;

import src.mvc.*;
import src.simstation.World;

public class PauseCommand extends Command {

    public PauseCommand(Model model) {
        super(model);

    }

    @Override
    protected void execute() throws Exception {
        ((World) model).pauseAgents();
    }

}
