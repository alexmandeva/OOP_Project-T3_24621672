package bg.tu_varna.sit.f24621672.projectT3.exception;

/**
 * Базово изключение за всички грешки, свързани с XML обработката (грешки при парсиране,
 * липсващи елементи, неуспешна сериализация/запис).
 */
public class XmlException extends RuntimeException {

    public XmlException(String message) {
        super(message);
    }

    public XmlException(String message, Throwable cause) {
        super(message, cause);
    }
}
