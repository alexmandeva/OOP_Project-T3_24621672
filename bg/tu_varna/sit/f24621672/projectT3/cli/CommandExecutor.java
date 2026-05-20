package bg.tu_varna.sit.f24621672.projectT3.cli;

import bg.tu_varna.sit.f24621672.projectT3.exception.CommandException;

public class CommandExecutor {

    private final CommandRegistry registry;
    /**
     * Конструира изпълнител, поддържан от дадения регистър.
     * @param registry регистърът, съдържащ изпълнимите команди
     */
    public CommandExecutor(CommandRegistry registry) {
        this.registry = registry;
    }

    /**
     * Парсира суровия ред от конзолата, извлича аргументите и пренасочва изпълнението
     * към съответната инстанция на {@link Command}.
     *
     * @param input суровият входен ред от конзолата
     * @param context оперативното състояние на приложението
     * @return съобщението с резултата от изпълнената команда
     * @throws CommandException ако въведената команда е непозната
     */
    public String execute(String input, CommandContext context) {

        if (input == null || input.isBlank()) {
            return "";
        }

        String[] tokens = input.trim().split("\\s+");

        String commandName = tokens[0];

        Command command = registry.get(commandName);

        if (command == null) {
            throw new CommandException("Unknown command: " + commandName);
        }

        String[] args = new String[tokens.length - 1];
        System.arraycopy(tokens, 1, args, 0, args.length);

        return command.execute(context, args);
    }
}