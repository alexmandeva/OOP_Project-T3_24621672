package bg.tu_varna.sit.f24621672.projectT3.commands;

import bg.tu_varna.sit.f24621672.projectT3.cli.AbstractCommand;
import bg.tu_varna.sit.f24621672.projectT3.cli.CommandContext;
import bg.tu_varna.sit.f24621672.projectT3.core.IdGenerator;
import bg.tu_varna.sit.f24621672.projectT3.core.XmlElement;
/**
 * Команда за динамично добавяне на нов, празен вложен елемент (дете) към съществуващ такъв.
 * <p>
 * Синтаксис: {@code newchild <id>}
 */
public class NewChildCommand extends AbstractCommand {
    /**
     * Изпълнява логиката по генериране и вмъкване на нов под-елемент под посочения родител.
     *
     * @param context текущият контекст на приложението
     * @param args    аргументи на командата: [0] - ID на родителския елемент
     * @return потвърдително съобщение с името и автоматично генерираното ID на новото дете
     */
    @Override
    public String execute(CommandContext context, String[] args) {

        requireArgs(args, 1);
        requireOpen(context);
        requireElement(context, args[0]);
        XmlElement child = context.getEngine().newChild(args[0]);

        return "New child <" + child.getName() + " id=\"" + child.getId() + "\"> added.";
    }
}