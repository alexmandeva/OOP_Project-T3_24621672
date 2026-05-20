package bg.tu_varna.sit.f24621672.projectT3.commands;

import bg.tu_varna.sit.f24621672.projectT3.cli.AbstractCommand;
import bg.tu_varna.sit.f24621672.projectT3.cli.CommandContext;
import bg.tu_varna.sit.f24621672.projectT3.core.XmlAttribute;
import bg.tu_varna.sit.f24621672.projectT3.core.XmlElement;
/**
 * Selects attribute value.
 */
public class SelectCommand extends AbstractCommand {

    @Override
    public String execute(CommandContext context, String[] args) {

        requireArgs(args, 2);
        requireOpen(context);

        return context.getEngine().select(args[0], args[1]);
    }
}