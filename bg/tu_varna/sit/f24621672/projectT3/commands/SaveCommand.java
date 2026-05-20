package bg.tu_varna.sit.f24621672.projectT3.commands;

import bg.tu_varna.sit.f24621672.projectT3.cli.AbstractCommand;
import bg.tu_varna.sit.f24621672.projectT3.cli.CommandContext;
import bg.tu_varna.sit.f24621672.projectT3.core.XmlSerializer;
import bg.tu_varna.sit.f24621672.projectT3.util.FileUtil;

import java.io.File;

public class SaveCommand extends AbstractCommand {

    private final XmlSerializer serializer = new XmlSerializer();

    @Override
    public String execute(CommandContext context, String[] args) {

        requireOpen(context);
        String path = context.getCurrentFile();
        serializer.save(context.getEngine().getDocument(),
                new File(context.getCurrentFile()));

        return "Successfully saved " + FileUtil.fileName(path);
    }
}