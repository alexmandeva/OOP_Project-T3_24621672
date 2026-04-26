package bg.tu_varna.sit.f24621672.projectT3.model;

import java.util.HashMap;
import java.util.Map;
import java.util.*;

public class XmlDocument {
    private XMLElement root;
    private Map<String, XMLElement> elementsById;
    private int idCounter;

    public XmlDocument() {
        elementsById = new HashMap<>();
        idCounter = 1;
    }

    public XMLElement getRoot() {
        return root;
    }

    public void setRoot(XMLElement root) {
        this.root = root;
        registerElement(root);
    }

    public void addElement(XMLElement element) {
        String id = element.getId();

        if (id == null || elementsById.containsKey(id)) {
            id = generateUniqueId(id == null ? "id" : id);
            element.setId(id);
        }

        elementsById.put(id, element);
    }

    private void registerElement(XMLElement element) {
        if (element == null) return;

        addElement(element);

        for (XMLElement child : element.getChildren()) {
            registerElement(child);
        }
    }

    private String generateUniqueId(String base) {
        String newId = base;
        while (elementsById.containsKey(newId)) {
            newId = base + "_" + idCounter++;
        }
        return newId;
    }

    public XMLElement getElementById(String id) {
        return elementsById.get(id);
    }

    public String print() {
        StringBuilder sb = new StringBuilder();
        buildString(root, 0, sb);
        return sb.toString();
    }

    private void buildString(XMLElement element, int indent, StringBuilder sb) {
        if (element == null) return;

        String indentStr = "  ".repeat(indent);
        sb.append(indentStr).append("<").append(element.getName());

        for (Map.Entry<String, String> attr : element.getAttributes().entrySet()) {
            sb.append(" ")
                    .append(attr.getKey())
                    .append("=\"")
                    .append(attr.getValue())
                    .append("\"");
        }

        sb.append(">");

        if (!element.getText().isEmpty()) {
            sb.append(element.getText());
        }

        sb.append("\n");

        for (XMLElement child : element.getChildren()) {
            buildString(child, indent + 1, sb);
        }

        sb.append(indentStr)
                .append("</")
                .append(element.getName())
                .append(">\n");
    }
}