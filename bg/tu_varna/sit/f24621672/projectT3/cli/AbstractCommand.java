package bg.tu_varna.sit.f24621672.projectT3.cli;

import bg.tu_varna.sit.f24621672.projectT3.core.XmlElement;
import bg.tu_varna.sit.f24621672.projectT3.exception.CommandException;
/**
 * Базова абстрактна реализация на интерфейса {@link Command}.
 * Предоставя споделени помощни методи за валидация на аргументи, проверка
 * за зареден файл и безопасно извличане на XML елементи.
 */
public abstract class AbstractCommand implements Command {
    /**
     * Валидира дали в контекста на изпълнение има зареден XML документ.
     *
     * @param context текущият контекст на приложението
     * @throws CommandException ако няма отворен файл или XML ядрото не е инициализирано
     */
    protected void requireOpen(CommandContext context) {
        if (!context.isOpen() || context.getEngine() == null) {
            throw new CommandException("No file is currently open");
        }
    }
    /**
     * Хвърля {@link CommandException}, ако са подадени по-малко от {@code min} аргументи.
     *
     * @param args масив с аргументи на командата
     * @param min  минимален изискван брой аргументи
     */
    protected void requireArgs(String[] args, int min) {
        if (args.length < min) {
            throw new CommandException("Not enough arguments");
        }
    }
    /**
     * Търси елемент по неговото ID и хвърля изключение, ако не бъде намерен.
     * Заменя повтарящите се проверки за null във всяка XML команда.
     *
     * @param context текущото състояние на приложението
     * @param id идентификатор на елемента за търсене
     * @return намереният елемент (никога не е null)
     * @throws CommandException ако не съществува елемент с даденото ID
     */
    protected XmlElement requireElement(CommandContext context, String id) {
        XmlElement el = context.getEngine().findById(id);
        if (el == null) {
            throw new CommandException("Element not found: " + id);
        }
        return el;
    }
}
