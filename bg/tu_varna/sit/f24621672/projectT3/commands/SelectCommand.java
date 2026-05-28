package bg.tu_varna.sit.f24621672.projectT3.commands;

import bg.tu_varna.sit.f24621672.projectT3.cli.AbstractCommand;
import bg.tu_varna.sit.f24621672.projectT3.cli.CommandContext;


/**
 * Команда за извличане на стойността на конкретен атрибут от XML елемент.
 * Търси елемента по неговия идентификатор и връща стойността на атрибута по ключ.
 * Ако атрибутът не съществува, се връща информативно съобщение.
 */
public class SelectCommand extends AbstractCommand {

    @Override
    public String execute(CommandContext context, String[] args) {

        requireArgs(args, 2);
        requireOpen(context);

        return context.getEngine().select(args[0], args[1]);
    }
}