package bg.tu_varna.sit.f24621672.projectT3.exception;

/**
 * Хвърля се, когато XML парсерът срещне невалиден, деформиран или неподдържан XML синтаксис.
 */
public class ParseException extends XmlException {

    public ParseException(String message) {
        super(message);
    }

    public ParseException(String message, Throwable cause) {
        super(message, cause);
    }
}