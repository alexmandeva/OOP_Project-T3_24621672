package bg.tu_varna.sit.f24621672.projectT3.core;

import java.util.ArrayList;
import java.util.List;
/**
 * Основният структурен компонент на XML обектния модел (DOM). Представлява таг възел,
 * който може да притежава уникално ID, атрибути, текстово съдържание и вложени деца.
 */
public class XmlElement {

    private String id;
    private String name;
    private String text;

    private List<XmlElement> children = new ArrayList<>();
    private List<XmlAttribute> attributes = new ArrayList<>();

    public XmlElement(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public XmlElement(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public List<XmlAttribute> getAttributes() {
        return attributes;
    }

    public List<XmlElement> getChildren() {
        return children;
    }

    public void addAttribute(XmlAttribute attr) {
        attributes.add(attr);
    }

    public void addChild(XmlElement child) {
        children.add(child);
    }

    public XmlAttribute getAttribute(String key) {
        for (XmlAttribute a : attributes) {
            if (a.getKey().equals(key)) {
                return a;
            }
        }
        return null;
    }

    /**
     * Sets an existing attribute's value, or adds it if not present.
     *
     * @param key  attribute name
     * @param value new value
     */
    public void setAttribute(String key, String value) {
        XmlAttribute existing = getAttribute(key);
        if (existing != null) {
            existing.setValue(value);
        } else {
            attributes.add(new XmlAttribute(key, value));
        }
    }

    public void removeAttribute(String key) {
        attributes.removeIf(a -> a.getKey().equals(key));
    }

}