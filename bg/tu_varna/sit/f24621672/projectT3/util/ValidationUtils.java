package bg.tu_varna.sit.f24621672.projectT3.util;

import bg.tu_varna.sit.f24621672.projectT3.exception.CommandException;
/**
 * Споделен помощен клас за централизирана валидация на състояния и аргументи в приложението.
 * Предоставя кратки методи за хвърляне на специфични изключения при невалидни данни.
 */
public final class ValidationUtils {
    /**
     * Частен конструктор за предотвратяване на инстанцирането на помощния клас.
     */
    private ValidationUtils() {
    }

    /**
     * Изисква определено булево условие да бъде вярно (true). Ако условието се провали,
     * прекратява изпълнението със съответното съобщение за грешка.
     *
     * @param condition условието, което задължително трябва да е изпълнено
     * @param message   съобщението, което описва грешката, ако условието е false
     * @throws CommandException ако условието не е изпълнено (е равно на false)
     */
    public static void require(boolean condition, String message) {
        if (!condition) {
            throw new CommandException(message);
        }
    }

    /**
     * Валидира дали масивът с аргументи съдържа поне очаквания брой елементи за командата.
     * Ако дължината е недостатъчна, хвърля структурирано съобщение за правилно използване (Usage).
     *
     * @param args масивът с подадени аргументи
     * @param expected минималният очакван брой аргументи
     * @param usage текстово упътване как се изписва правилно съответната команда
     * @throws CommandException ако масивът е null или съдържа по-малко елементи от очакваното
     */
    public static void requireArgsLength(String[] args, int expected, String usage) {
        if (args == null || args.length < expected) {
            throw new CommandException("Invalid arguments. Usage: " + usage);
        }
    }
}