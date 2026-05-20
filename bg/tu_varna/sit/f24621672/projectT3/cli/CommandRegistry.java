package bg.tu_varna.sit.f24621672.projectT3.cli;

import java.util.HashMap;
import java.util.Map;

/**
 * Регистър, който свързва текстовите имена на командите с техните {@link Command} инстанции.
 */
public class CommandRegistry {

    private final Map<String, Command> commands = new HashMap<>();
    /**
     * Регистрира команда под дадено име (записва се с малки букви).
     *
     * @param name името, което потребителят въвежда
     * @param command имплементацията на командата
     */
    public void register(String name, Command command) {
        commands.put(name.toLowerCase(), command);
    }
    /**
     * Връща командата, регистрирана с даденото име, или {@code null}.
     *
     * @param name име на командата (нечувствително към главни/малки букви)
     * @return съвпадащата {@link Command} или {@code null}
     */
    public Command get(String name) {
        return commands.get(name.toLowerCase());
    }
}