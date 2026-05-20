package bg.tu_varna.sit.f24621672.projectT3.util;

/**
 * Помощен служебен клас за улеснена обработка на текстови низове (Strings).
 * Класът е капсулиран (final) и не може да бъде инстанциран.
 */
public final class StringUtil {

    private StringUtil() {
    }
    /**
     * Проверява дали даден низ е празен, съдържа само разстояния (whitespace) или е null.
     *
     * @param value низът за проверка
     * @return true, ако низът е null или празен, в противен случай - false
     */
    public static boolean isNullOrEmpty(String value) {
        return value == null || value.trim().isEmpty();
    }
    /**
     * Безопасно премахва водещите и затварящите празни пространства в низа.
     * За разлика от стандартния метод .trim(), този не хвърля NullPointerException, ако стойността е null.
     *
     * @param value низът за изчистване
     * @return изчистеният низ или null, ако подаденият низ е бил null
     */
    public static String safeTrim(String value) {
        return value == null ? null : value.trim();
    }

    /**
     * Разделя подадения текст на масив от думи въз основа на интервалите в него.
     * Автоматично обединява последователни празни пространства и изрязва краищата на текста.
     *
     * @param input текстът, който ще се разделя
     * @return масив от отделните думи/токени или празен масив, ако входът е null
     */
    public static String[] splitBySpace(String input) {
        if (input == null) return new String[0];
        return input.trim().split("\\s+");
    }
}