package src.simstation.Commands;

import src.mvc.*;
import src.simstation.World;

public class StopCommand extends Command {

    public StopCommand(Model model) {
        super(model);
    }

    @Override
    protected void execute() throws Exception {
        ((World) model).stopAgents();
    }

}
