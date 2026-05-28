package bg.tu_varna.sit.f24621672.projectT3.commands;

import bg.tu_varna.sit.f24621672.projectT3.cli.AbstractCommand;
import bg.tu_varna.sit.f24621672.projectT3.cli.CommandContext;

/**
 * Команда за задаване или обновяване на стойността на атрибут върху XML елемент.
 * Ако атрибутът с посочения ключ вече съществува, стойността му се обновява.
 * Ако не съществува, нов атрибут се добавя към елемента.
 */
public class SetCommand extends AbstractCommand {

    /**
     * Задава или обновява стойността на атрибут на елемент.
     * Валидира наличието на елемента преди да извърши промяната.
     *
     * @param context текущият контекст на приложението
     * @param args    аргументи на командата: [0] - ID на елемента,
     *                [1] - ключ на атрибута, [2] - нова стойност
     * @return потвърдително съобщение за успешно задаване на атрибута
     */
    @Override
    public String execute(CommandContext context, String[] args) {

        requireArgs(args, 3);
        requireOpen(context);
        requireElement(context, args[0]);
        context.getEngine().setAttribute(args[0], args[1], args[2]);

        return "Attribute '" + args[1] + "' set to '" + args[2] + "'.";
    }
}