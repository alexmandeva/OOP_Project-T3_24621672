package bg.tu_varna.sit.f24621672.projectT3.commands;

import bg.tu_varna.sit.f24621672.projectT3.cli.AbstractCommand;
import bg.tu_varna.sit.f24621672.projectT3.cli.CommandContext;
/**
 * Команда за прекратяване на работата на приложението.
 * <p>
 * Синтаксис: {@code exit}
 */
public class ExitCommand extends AbstractCommand {
    /**
     * Сигнализира на главния цикъл на приложението да спре, като променя флага {@code running}.
     *
     * @param context текущият контекст на приложението
     * @param args    празен масив (командата не приема допълнителни параметри)
     * @return съобщение, известяващо за изход от програмата
     */
    @Override
    public String execute(CommandContext context, String[] args) {

        context.setRunning(false);
        return "Exiting program...";
    }
}