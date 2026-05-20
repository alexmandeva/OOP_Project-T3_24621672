package bg.tu_varna.sit.f24621672.projectT3.commands;

import bg.tu_varna.sit.f24621672.projectT3.cli.AbstractCommand;
import bg.tu_varna.sit.f24621672.projectT3.cli.CommandContext;

/**
 * Sets attribute value.
 */
public class SetCommand extends AbstractCommand {

    @Override
    public String execute(CommandContext context, String[] args) {

        requireArgs(args, 3);
        requireOpen(context);
        requireElement(context, args[0]);
        context.getEngine().setAttribute(args[0], args[1], args[2]);

        return "Attribute '" + args[1] + "' set to '" + args[2] + "'.";
    }
}