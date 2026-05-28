package bg.tu_varna.sit.f24621672.projectT3.commands;

import bg.tu_varna.sit.f24621672.projectT3.cli.AbstractCommand;
import bg.tu_varna.sit.f24621672.projectT3.cli.CommandContext;
/**
 * Команда за извеждане на списък с всички преки вложени елементи (деца) на даден XML елемент.
 * <p>
 * Синтаксис: {@code children <id>}
 */
public class ChildrenCommand extends AbstractCommand {
    /**
     * Изпълнява командата за извеждане на списък от под-елементи.
     * Изисква отворен документ и валидно подадено ID.
     *
     * @param context текущият контекст на приложението
     * @param args аргументи на командата: [0] - ID на целевия елемент
     * @return текстово описание на списъка с деца и техните индекси
     */
    @Override
    public String execute(CommandContext context, String[] args) {

        requireArgs(args, 1);
        requireOpen(context);

        return context.getEngine().children(args[0]);
    }
}