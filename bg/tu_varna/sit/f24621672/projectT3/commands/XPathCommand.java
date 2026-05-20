package bg.tu_varna.sit.f24621672.projectT3.commands;

import bg.tu_varna.sit.f24621672.projectT3.cli.AbstractCommand;
import bg.tu_varna.sit.f24621672.projectT3.cli.CommandContext;

/**
 * Executes simplified XPath queries.
 */
public class XPathCommand extends AbstractCommand {

    @Override
    public String execute(CommandContext context, String[] args) {

        requireArgs(args, 2);
        requireOpen(context);

        StringBuilder expr = new StringBuilder(args[1]);
        for (int i = 2; i < args.length; i++) {
            expr.append(' ').append(args[i]);
        }

        return context.getEngine().xpath(args[0], expr.toString());
    }
}