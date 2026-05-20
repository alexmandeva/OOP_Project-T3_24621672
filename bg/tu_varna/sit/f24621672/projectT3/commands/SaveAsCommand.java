package bg.tu_varna.sit.f24621672.projectT3.commands;

import bg.tu_varna.sit.f24621672.projectT3.cli.AbstractCommand;
import bg.tu_varna.sit.f24621672.projectT3.cli.CommandContext;
import bg.tu_varna.sit.f24621672.projectT3.core.XmlSerializer;

import java.io.File;

/**
 * Saves document to another file.
 */
public class SaveAsCommand extends AbstractCommand {

    private final XmlSerializer serializer = new XmlSerializer();

    @Override
    public String execute(CommandContext context, String[] args) {

        requireArgs(args, 1);
        requireOpen(context);

        File file = new File(args[0]);
        serializer.save(context.getEngine().getDocument(), file);
        context.setCurrentFile(file.getAbsolutePath());

        return "Successfully saved as " + file.getName();
    }
}