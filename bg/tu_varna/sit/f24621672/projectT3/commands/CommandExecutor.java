package bg.tu_varna.sit.f24621672.projectT3.commands;

import java.util.HashMap;
import java.util.Map;

public class CommandExecutor {
    private Map<String, Command> commands;

    public CommandExecutor() {
        commands = new HashMap<>();
    }

    public void register(String name, Command command) {
        commands.put(name, command);
    }

    public String execute(String input) {
        String[] parts = input.split(" ");
        String commandName = parts[0];

        Command command = commands.get(commandName);

        if (command == null) {
            return "Unknown command";
        }
        return command.execute(parts);
    }
}