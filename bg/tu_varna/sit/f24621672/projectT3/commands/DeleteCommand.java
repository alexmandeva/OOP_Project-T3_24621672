package bg.tu_varna.sit.f24621672.projectT3.commands;

import bg.tu_varna.sit.f24621672.projectT3.cli.AbstractCommand;
import bg.tu_varna.sit.f24621672.projectT3.cli.CommandContext;

/**
 * Команда за изтриване на конкретен атрибут от даден XML елемент.
 * <p>
 * Синтаксис: {@code delete <id> <key>}
 */
public class DeleteCommand extends AbstractCommand {
    /**
     * Изпълнява операцията по премахване на атрибут.
     * Проверява съществуването на елемента и изтрива атрибута, съвпадащ по ключ.
     *
     * @param context текущият контекст на приложението
     * @param args    аргументи на командата: [0] - ID на елемента, [1] - ключ на атрибута
     * @return съобщение за успешно изтриване на атрибута
     */
    @Override
    public String execute(CommandContext context, String[] args) {

        requireArgs(args, 2);
        requireOpen(context);
        requireElement(context, args[0]);
        context.getEngine().deleteAttribute(args[0], args[1]);
        return "Attribute '" + args[1] + "' deleted.";
    }
}

