package bg.tu_varna.sit.f24621672.projectT3.commands;

import bg.tu_varna.sit.f24621672.projectT3.cli.AbstractCommand;
import bg.tu_varna.sit.f24621672.projectT3.cli.Command;
import bg.tu_varna.sit.f24621672.projectT3.cli.CommandContext;
import bg.tu_varna.sit.f24621672.projectT3.core.XmlElement;

public class TextCommand extends AbstractCommand {

    @Override
    public String execute(CommandContext context, String[] args) {

        requireArgs(args, 1);
        requireOpen(context);

        return context.getEngine().text(args[0]);
    }
}
