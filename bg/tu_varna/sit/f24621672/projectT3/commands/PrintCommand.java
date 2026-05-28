package bg.tu_varna.sit.f24621672.projectT3.commands;

import bg.tu_varna.sit.f24621672.projectT3.cli.AbstractCommand;
import bg.tu_varna.sit.f24621672.projectT3.cli.CommandContext;

/**
 * Команда за извеждане на форматирано XML съдържание на конзолата.
 * Обхожда цялото дърво рекурсивно от корена и го отпечатва с два интервала
 * на ниво отстъп. Листови елементи без текст се показват в самозатварящ формат.
 * <p>
 */
public class PrintCommand extends AbstractCommand {
    /**
     * Извежда форматираното XML съдържание на текущо заредения документ.
     * Делегира към {@code XmlEngine.print()}, който изгражда форматирания низ рекурсивно.
     *
     * @param context текущият контекст на приложението
     * @param args    празен масив (командата не приема допълнителни параметри)
     * @return форматиран XML низ с отстъпи
     */
    @Override
    public String execute(CommandContext context, String[] args) {

        requireOpen(context);

        return context.getEngine().print();
    }
}