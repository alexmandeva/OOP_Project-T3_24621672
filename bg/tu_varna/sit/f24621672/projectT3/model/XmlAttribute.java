package bg.tu_varna.sit.f24621672.projectT3.model;

public class XmlAttribute {

    private String key;
    private String value;

    public XmlAttribute(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
