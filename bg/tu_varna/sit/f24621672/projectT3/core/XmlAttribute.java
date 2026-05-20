package bg.tu_varna.sit.f24621672.projectT3.core;

/**
 * Представлява единичен XML атрибут като двойка ключ-стойност.
 */
public class XmlAttribute {

    private String key;
    private String value;

    /**
     * Конструира нов атрибут.
     * @param key   име на атрибута
     * @param value стойност на атрибута
     */
    public XmlAttribute(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public String getKey()              { return key; }
    public String getValue()            { return value; }
    public void setValue(String value)  { this.value = value; }

    @Override
    public String toString() {
        return key + "=\"" + value + "\"";
    }
}