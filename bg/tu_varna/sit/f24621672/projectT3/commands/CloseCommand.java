package bg.tu_varna.sit.f24621672.projectT3.commands;

import bg.tu_varna.sit.f24621672.projectT3.cli.AbstractCommand;
import bg.tu_varna.sit.f24621672.projectT3.cli.CommandContext;
import bg.tu_varna.sit.f24621672.projectT3.util.FileUtil;

/**
 * Команда за затваряне на текущо заредения XML файл.
 * Освобождава паметта от текущата структура на документа.
 * <p>
 * Синтаксис: {@code close}
 */
public class CloseCommand extends AbstractCommand {
    /**
     * Изпълнява командата за затваряне на файла.
     * Изчиства референциите към XML ядрото и нулира пътя в контекста.
     *
     * @param context текущият контекст на приложението
     * @param args    празен масив (командата не приема допълнителни параметри)
     * @return потвърдително съобщение за успешно затваряне на файла
     */
    @Override
    public String execute(CommandContext context, String[] args) {

        requireOpen(context);
        String name = FileUtil.fileName(context.getCurrentFile());
        context.getEngine().close();

        context.setOpen(false);
        context.setCurrentFile(null);

        return "Successfully closed file." + name;
    }
}