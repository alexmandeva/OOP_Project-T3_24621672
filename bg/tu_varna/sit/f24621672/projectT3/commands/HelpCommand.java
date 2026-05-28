package bg.tu_varna.sit.f24621672.projectT3.commands;

import bg.tu_varna.sit.f24621672.projectT3.cli.AbstractCommand;
import bg.tu_varna.sit.f24621672.projectT3.cli.CommandContext;
/**
 * Команда за извеждане на помощна информация за потребителя.
 * Показва списък с всички поддържани от XML парсера команди.
 * <p>
 * Синтаксис: {@code help}
 */
public class HelpCommand extends AbstractCommand {
    /**
     * Връща списък с форматиран текст, описващ синтаксиса на поддържаните команди.
     *
     * @param context текущият контекст на приложението
     * @param args аргументи (не се изискват)
     * @return низ, съдържащ списък с валидните команди и техните параметри
     */
    @Override
    public String execute(CommandContext context, String[] args) {

        return """
                Supported commands:
                open <file>
                close
                save
                saveas <file>
                print
                select <id> <key>
                set <id> <key> <value>
                delete <id> <key>
                children <id>
                child <id> <index>
                text <id>
                newchild <id>
                xpath <id> <query>
                help
                exit
                """;
    }
}