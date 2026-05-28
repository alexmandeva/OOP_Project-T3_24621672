package bg.tu_varna.sit.f24621672.projectT3.commands;

import bg.tu_varna.sit.f24621672.projectT3.cli.AbstractCommand;
import bg.tu_varna.sit.f24621672.projectT3.cli.CommandContext;

/**
 * Removes an attribute from an XML element.
 * Usage:
 * delete <id> <key>
 */
public class DeleteCommand extends AbstractCommand {

    @Override
    public String execute(CommandContext context, String[] args) {

        requireArgs(args, 2);
        requireOpen(context);

        context.getEngine().deleteAttribute(args[0], args[1]);

        return "Deleted";
    }
}

