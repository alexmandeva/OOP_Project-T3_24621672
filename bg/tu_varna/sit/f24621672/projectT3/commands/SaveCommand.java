package bg.tu_varna.sit.f24621672.projectT3.commands;

import bg.tu_varna.sit.f24621672.projectT3.cli.AbstractCommand;
import bg.tu_varna.sit.f24621672.projectT3.cli.CommandContext;
import bg.tu_varna.sit.f24621672.projectT3.core.XmlSerializer;
import bg.tu_varna.sit.f24621672.projectT3.util.FileUtil;

import java.io.File;
/**
 * Команда за записване на текущия XML документ обратно в оригиналния файл.
 * Всички направени промени (set, delete, newchild) се запазват на диска.
 */
public class SaveCommand extends AbstractCommand {

    private final XmlSerializer serializer = new XmlSerializer();
    /**
     * Записва XML документа в нов файл и актуализира текущия път в контекста.
     *
     * @param context текущият контекст на приложението
     * @param args аргументи на командата: [0] - пътят до новия файл
     * @return потвърдително съобщение за успешен запис с краткото име на файла
     */
    @Override
    public String execute(CommandContext context, String[] args) {

        requireOpen(context);
        String path = context.getCurrentFile();
        serializer.save(context.getEngine().getDocument(),
                new File(context.getCurrentFile()));

        return "Successfully saved " + FileUtil.fileName(path);
    }
}