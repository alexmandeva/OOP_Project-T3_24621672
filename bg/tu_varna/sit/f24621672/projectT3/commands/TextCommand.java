package bg.tu_varna.sit.f24621672.projectT3.commands;

import bg.tu_varna.sit.f24621672.projectT3.cli.AbstractCommand;
import bg.tu_varna.sit.f24621672.projectT3.cli.Command;
import bg.tu_varna.sit.f24621672.projectT3.cli.CommandContext;
import bg.tu_varna.sit.f24621672.projectT3.core.XmlElement;
/**
 * Команда за извеждане на текстовото съдържание на XML елемент.
 * Ако елементът има деца вместо текст, се извежда информативно съобщение.
 */
public class TextCommand extends AbstractCommand {
    /**
     * Извлича и връща текстовото съдържание на елемента с посочения идентификатор.
     * @param context текущият контекст на приложението
     * @param args аргументи на командата: [0] - ID на елемента
     * @return текстовото съдържание на елемента или съобщение за липса на текст
     */
    @Override
    public String execute(CommandContext context, String[] args) {

        requireArgs(args, 1);
        requireOpen(context);

        return context.getEngine().text(args[0]);
    }
}
