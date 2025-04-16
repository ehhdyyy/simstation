package src.simstation;

import src.mvc.*;
import src.simstation.Commands.*;

public class WorldFactory implements AppFactory {

    @Override
    public String getTitle() {
        return "SimStation";
    }

    @Override
    public String[] getHelp() {
        return new String[] {
                "Start: Starts the simulation", 
                "Pause: Pauses the simulation",
                "Resume: Resumes the simulation",
                "Stop: Stops the simulation",
                "Stats: Displays the statistics of the simulation",
        };
    }

    @Override
    public String about() {
        return "Simstation Group 1";
    }

    @Override
    public Model makeModel() {
        return null; //WILL BE OVERRIDEN 
    }

    @Override
    public View makeView(Model m) {
        return new WorldView(m);
    }

    @Override
    public String[] getEditCommands() {
        return new String[] {
                "Start",
                "Pause",
                "Resume",
                "Stop",
                "Stats",
        };
    }

    @Override
    public Command makeEditCommand(Model model, String type, Object source) {
        if (type.equalsIgnoreCase("Start")) {
            return new StartCommand(model);
        } else if (type.equalsIgnoreCase("Pause")) {
            return new PauseCommand(model);
        } else if (type.equalsIgnoreCase("Resume")) {
            return new ResumeCommand(model);
        } else if (type.equalsIgnoreCase("Stop")) {
            return new StopCommand(model);
        } else if (type.equalsIgnoreCase("Stats")) {
            return new StatsCommand(model);
        }
        return null;
    }

}
