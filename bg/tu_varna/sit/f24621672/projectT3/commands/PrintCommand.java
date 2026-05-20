package bg.tu_varna.sit.f24621672.projectT3.commands;

import bg.tu_varna.sit.f24621672.projectT3.cli.AbstractCommand;
import bg.tu_varna.sit.f24621672.projectT3.cli.CommandContext;

/**
 * Prints formatted XML.
 */
public class PrintCommand extends AbstractCommand {

    @Override
    public String execute(CommandContext context, String[] args) {

        requireOpen(context);

        return context.getEngine().print();
    }
}