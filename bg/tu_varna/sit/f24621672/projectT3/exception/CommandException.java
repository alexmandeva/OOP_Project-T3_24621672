package bg.tu_varna.sit.f24621672.projectT3.exception;

/**
 * Хвърля се, когато CLI команда не може да бъде изпълнена: грешен брой аргументи,
 * липса на отворен файл, несъществуващо ID на елемент и др.
 */
public class CommandException extends RuntimeException {

    public CommandException(String message) {
        super(message);
    }

    public CommandException(String message, Throwable cause) {
        super(message, cause);
    }
}
