package bg.tu_varna.sit.f24621672.projectT3.app;

import bg.tu_varna.sit.f24621672.projectT3.cli.*;
import bg.tu_varna.sit.f24621672.projectT3.commands.*;

import java.util.Scanner;
/**
 * Главният входен пункт на приложението за XML Парсер.
 * Инициализира регистъра с команди, конфигурира наличните CLI команди
 * и стартира основния интерактивен цикъл за потребителско въвеждане.
 */
public class Application {
    /**
     * Основният цикъл на приложението. Настройва контекста, регистрира поддържаните
     * команди и обработва непрекъснато входа, докато не бъде задействана командата за изход.
     *
     * @param args аргументи от командния ред (не се използват)
     */
    public static void main(String[] args) {

        CommandContext context = new CommandContext();
        CommandRegistry registry = new CommandRegistry();
        CommandExecutor executor = new CommandExecutor(registry);

        registry.register("open", new OpenCommand());
        registry.register("close", new CloseCommand());
        registry.register("save", new SaveCommand());
        registry.register("saveas", new SaveAsCommand());
        registry.register("help", new HelpCommand());
        registry.register("exit", new ExitCommand());

        registry.register("print", new PrintCommand());
        registry.register("select", new SelectCommand());
        registry.register("set", new SetCommand());
        registry.register("delete", new DeleteCommand());

        registry.register("children", new ChildrenCommand());
        registry.register("child", new ChildCommand());
        registry.register("text", new TextCommand());
        registry.register("newchild", new NewChildCommand());

        registry.register("xpath", new XPathCommand());

        Scanner scanner = new Scanner(System.in);

        System.out.println("XML Parser started. Type 'help' for commands.");

        while (context.isRunning()) {

            System.out.print("> ");
            String input = scanner.nextLine();

            try {
                String result = executor.execute(input, context);

                if (result != null && !result.isBlank()) {
                    System.out.println(result);
                }

            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
        scanner.close();
    }
}