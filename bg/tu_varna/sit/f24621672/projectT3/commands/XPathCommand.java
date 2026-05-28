package bg.tu_varna.sit.f24621672.projectT3.commands;

import bg.tu_varna.sit.f24621672.projectT3.cli.AbstractCommand;
import bg.tu_varna.sit.f24621672.projectT3.cli.CommandContext;

/**
 * Команда за изпълнение на опростени XPath заявки върху XML документа.
 * Поддържа четири оператора:
 * <ul>
 *   <li>{@code /} — стъпка към дете по tag name</li>
 *   <li>{@code [n]} — индексиране (0-базирано)</li>
 *   <li>{@code (@attr)} — проекция на стойност на атрибут</li>
 *   <li>{@code (key="val")} — филтриране по стойност на дете или атрибут</li>
 * </ul>
 * <p>
 */
public class XPathCommand extends AbstractCommand {
    /**
     * Изпълнява XPath заявката върху елемента с посочения идентификатор.
     * Тъй като изразът може да съдържа интервали, всички аргументи след индекс 1
     * се събират в един низ преди делегирането към engine-а.
     *
     * @param context текущият контекст на приложението
     * @param args аргументи на командата: [0] - ID на контекстния елемент,
     * [1..n] - части от XPath израза
     * @return резултатите от заявката като форматиран низ или {@code "No results."}
     */
    @Override
    public String execute(CommandContext context, String[] args) {

        requireArgs(args, 2);
        requireOpen(context);

        StringBuilder expr = new StringBuilder(args[1]);
        for (int i = 2; i < args.length; i++) {
            expr.append(' ').append(args[i]);
        }

        return context.getEngine().xpath(args[0], expr.toString());
    }
}