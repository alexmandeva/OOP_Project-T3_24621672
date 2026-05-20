package bg.tu_varna.sit.f24621672.projectT3.cli;

import bg.tu_varna.sit.f24621672.projectT3.core.XmlEngine;
/**
 * Пази глобалното състояние на приложението по време на работа. Следи за активната
 * инстанция на XML ядрото, пътя до заредения файл и дали цикълът на конзолата е активен.
 */
public class CommandContext {

    private XmlEngine engine;
    private String currentFile;
    private boolean open;
    private boolean running = true;

    public XmlEngine getEngine() {
        return engine;
    }

    public void setEngine(XmlEngine engine) {
        this.engine = engine;
    }

    public String getCurrentFile() {
        return currentFile;
    }

    public void setCurrentFile(String currentFile) {
        this.currentFile = currentFile;
    }

    public boolean isOpen() {
        return open;
    }

    public void setOpen(boolean open) {
        this.open = open;
    }

    public boolean isRunning() {
        return running;
    }

    public void setRunning(boolean running) {
        this.running = running;
    }
}