package bg.tu_varna.sit.f24621672.projectT3.commands;

import bg.tu_varna.sit.f24621672.projectT3.cli.AbstractCommand;
import bg.tu_varna.sit.f24621672.projectT3.cli.CommandContext;
import bg.tu_varna.sit.f24621672.projectT3.core.XmlAttribute;
import bg.tu_varna.sit.f24621672.projectT3.core.XmlElement;
import bg.tu_varna.sit.f24621672.projectT3.exception.CommandException;
/**
 * Команда за достъп и визуализация на конкретно дете на XML елемент по неговия индекс.
 * <p>
 * Синтаксис: {@code child <id> <index>}
 */
public class ChildCommand extends AbstractCommand {
    /**
     * Изпълнява командата за извличане на информация за конкретно дете на елемент.
     * Проверява дали са подадени нужните аргументи, дали индексът е валидно число,
     * след което форматира и връща информация за намерения под-елемент.
     *
     * @param context текущият контекст на приложението
     * @param args аргументи на командата: [0] - ID на родителя, [1] - индекс на детето
     * @return текстова презентация на намереното дете (име на таг, атрибути и текст)
     * @throws CommandException ако индексът не е цяло число или елементът не съществува
     */
    @Override
    public String execute(CommandContext context, String[] args) {
        requireArgs(args, 2);
        requireOpen(context);

        int index;
        try {
            index = Integer.parseInt(args[1]);
        } catch (NumberFormatException e) {
            throw new CommandException("Index must be an integer, got: " + args[1]);
        }

        XmlElement el = context.getEngine().child(args[0], index);

        StringBuilder sb = new StringBuilder("<").append(el.getName()).append(">");
        for (XmlAttribute a : el.getAttributes()) {
            sb.append(" ").append(a.toString());
        }
        if (el.getText() != null && !el.getText().isEmpty()) {
            sb.append(" → \"").append(el.getText()).append('"');
        }
        return sb.toString();
    }
}