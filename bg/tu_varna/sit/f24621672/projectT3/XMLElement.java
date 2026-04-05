package bg.tu_varna.sit.f24621672.projectT3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class XMLElement {
    private String id;
    private Map<String,String> attributes;
    private List<XMLElement> children;
    private String text;

    public XMLElement() {
        this.id = id;
        this.attributes = new HashMap<>();
        this.children = new ArrayList<>();
        this.text = "";
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Map<String, String> getAttributes() {
        return attributes;
    }

    public List<XMLElement> getChildren() {
        return children;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void addChild(XMLElement child) {
        children.add(child);
    }

    public void setAttribute(String key, String value) {
        attributes.put(key, value);
    }
    public String getAttribute(String key) {
        return attributes.get(key);
    }
    public void deleteAttribute(String key) {
        attributes.remove(key);
    }
}
