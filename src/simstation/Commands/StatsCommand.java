package src.simstation.Commands;

import src.mvc.*;
import src.simstation.World;

public class StatsCommand extends Command {

    public StatsCommand(Model model) {
        super(model);
    }

    @Override
    protected void execute() throws Exception {
        ((World) model).getStatus();
    }

}
