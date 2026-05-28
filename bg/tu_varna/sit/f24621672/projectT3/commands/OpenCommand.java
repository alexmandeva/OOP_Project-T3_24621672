package bg.tu_varna.sit.f24621672.projectT3.commands;

import bg.tu_varna.sit.f24621672.projectT3.cli.AbstractCommand;
import bg.tu_varna.sit.f24621672.projectT3.cli.CommandContext;
import bg.tu_varna.sit.f24621672.projectT3.core.XmlEngine;

import java.io.File;
import java.io.IOException;
/**
 * Команда за отваряне и зареждане на XML документ от диска.
 * Ако файлът не съществува, командата се опитва автоматично да създаде празен такъв.
 * <p>
 * Синтаксис: {@code open <path>}
 */
public class OpenCommand extends AbstractCommand {
    /**
     * Отваря XML файла, парсира го и зарежда дървото в паметта.
     * Ако файлът не съществува, той се създава автоматично като нов празен файл.
     *
     * @param context текущият контекст на приложението
     * @param args    аргументи на командата: [0] - пътят до XML файла
     * @return потвърдително съобщение за успешно отваряне на файла
     */
    @Override
    public String execute(CommandContext context, String[] args) {

        requireArgs(args, 1);
        File file = new File(args[0]);

        if (!file.exists()) {
            try {
                if (file.getParentFile() != null) file.getParentFile().mkdirs();
                file.createNewFile();
            } catch (IOException e) {
                return "Error: could not create file: " + args[0];
            }
        }

        XmlEngine engine = new XmlEngine();
        engine.open(file);

        context.setEngine(engine);
        context.setCurrentFile(file.getAbsolutePath());
        context.setOpen(true);

        return "Successfully opened " + file.getName();
    }
}