package bg.tu_varna.sit.f24621672.projectT3.core;

import bg.tu_varna.sit.f24621672.projectT3.exception.XmlException;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
/**
 * Основният управляващ двигател (Engine), координиращ състоянието на файла, извеждането на текст,
 * манипулацията на атрибути и изпълнението на XPath заявки върху документа.
 */
public class XmlEngine {

    private XmlDocument document;
    private String currentFile;
    private int newChildCounter = 0;

    /**
     * Opens and parses the file, or creates an empty document if it does not
     * exist yet.
     *
     * @param file file to open
     * @return the populated document
     */
    public XmlDocument open(File file) {
        if (!file.exists()) {
            document = new XmlDocument();
        } else {
            document = new XmlParser().parse(file);
        }
        currentFile = file.getAbsolutePath();
        return document;
    }

    public void close() {
        this.document = null;
        this.currentFile = null;
    }

    public boolean isOpen() {
        return document != null;
    }

    public XmlDocument getDocument() {
        return document;
    }

    public String getCurrentFile() {
        return currentFile;
    }

    public String print() {
        if (document == null || document.getRoot() == null) {
            throw new XmlException("No document loaded");
        }

        return printElement(document.getRoot(), 0);
    }

    private String printElement(XmlElement el, int indent) {

        StringBuilder sb = new StringBuilder();
        String tabs = "  ".repeat(indent);

        sb.append(tabs).append("<").append(el.getName());

        for (XmlAttribute a : el.getAttributes()) {
            sb.append(" ")
                    .append(a.getKey())
                    .append("=\"")
                    .append(a.getValue())
                    .append("\"");
        }

        if (el.getChildren().isEmpty() && (el.getText() == null || el.getText().isEmpty())) {
            sb.append("/>\n");
            return sb.toString();
        }

        sb.append(">");

        if (el.getText() != null) {
            sb.append(el.getText());
        }

        if (!el.getChildren().isEmpty()) {
            sb.append("\n");
            for (XmlElement child : el.getChildren()) {
                sb.append(printElement(child, indent + 1));
            }
            sb.append(tabs);
        }

        sb.append("</").append(el.getName()).append(">\n");

        return sb.toString();
    }


    public XmlElement findById(String id) {
        if (document == null) return null;
        return findByIdRec(document.getRoot(), id);
    }

    private XmlElement findByIdRec(XmlElement el, String id) {
        if (el == null) return null;                         // ← добави това
        if (el.getId() != null && el.getId().equals(id)) {

            return el;
        }

        for (XmlElement child : el.getChildren()) {
            XmlElement found = findByIdRec(child, id);
            if (found != null) return found;
        }

        return null;
    }

    public String select(String id, String key) {
        XmlElement el = requireElement(id);
        XmlAttribute attr = el.getAttribute(key);
        return attr != null ? attr.getValue() : "Attribute '" + key + "' not found.";
    }

    public void setAttribute(String id, String key, String value) {

        XmlElement el = findById(id);

        if (el == null) {
            throw new XmlException("Element not found: " + id);
        }

        XmlAttribute attr = el.getAttribute(key);

        if (attr != null) {
            attr.setValue(value);
        } else {
            el.addAttribute(new XmlAttribute(key, value));
        }
    }

    public void deleteAttribute(String id, String key) {

        XmlElement el = findById(id);

        if (el == null) {
            throw new XmlException("Element not found: " + id);
        }

        el.removeAttribute(key);
    }

    public String children(String id) {
        XmlElement el = requireElement(id);
        if (el.getChildren().isEmpty()) {
            return "Element <" + el.getName() + "> has no children.";
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < el.getChildren().size(); i++) {
            XmlElement child = el.getChildren().get(i);
            sb.append("[").append(i).append("] <").append(child.getName()).append(">");
            for (XmlAttribute a : child.getAttributes()) {
                sb.append(" ").append(a.toString());
            }
            sb.append('\n');
        }
        return sb.toString().trim();
    }

    public XmlElement child(String id, int index) {
        XmlElement el = requireElement(id);
        if (index < 0 || index >= el.getChildren().size()) {
            throw new XmlException("Invalid child index: " + index
                    + " (element has " + el.getChildren().size() + " children).");
        }
        return el.getChildren().get(index);
    }

    public String text(String id) {
        XmlElement el = findById(id);
        if (el == null) throw new XmlException("Element not found: " + id);
        String t = el.getText();
        return (t != null && !t.isEmpty()) ? t
                : "Element <" + el.getName() + "> has no text content.";
    }

    public XmlElement newChild(String parentId) {

        XmlElement parent = requireElement(parentId);
        newChildCounter++;

        String tag = "element" + newChildCounter;
        String id  = "new_" + newChildCounter;

        XmlElement child = new XmlElement(id, tag);
        child.addAttribute(new XmlAttribute("id", id));
        parent.addChild(child);
        return child;
    }

    public String xpath(String id, String expression) {
        XmlElement context = requireElement(id);
        List<XmlElement> current = new ArrayList<>();
        current.add(context);

        List<String> steps = splitSteps(expression);
        List<String> projectedValues = null;

        for (String step : steps) {
            if (step.isEmpty()) continue;

            if (step.contains("(@") && step.endsWith(")")) {
                int p = step.indexOf("(@");
                String tag  = step.substring(0, p).trim();
                String attr = step.substring(p + 2, step.length() - 1).trim();
                if (!tag.isEmpty()) current = childrenWithTag(current, tag);
                projectedValues = new ArrayList<>();
                for (XmlElement el : current) {
                    XmlAttribute a = el.getAttribute(attr);
                    if (a != null) projectedValues.add(a.getValue());
                }
                current = new ArrayList<>();
                break;
            }

            if (step.contains("(") && step.contains("=") && step.endsWith(")")) {
                int p = step.indexOf('(');
                String tag    = step.substring(0, p).trim();
                String filter = step.substring(p + 1, step.length() - 1).trim();
                if (!tag.isEmpty()) current = childrenWithTag(current, tag);
                current = applyFilter(current, filter);
                continue;
            }

            if (step.contains("[") && step.endsWith("]")) {
                int b   = step.indexOf('[');
                String tag = step.substring(0, b).trim();
                int idx = Integer.parseInt(step.substring(b + 1, step.length() - 1).trim());
                if (!tag.isEmpty()) current = childrenWithTag(current, tag);
                if (idx >= 0 && idx < current.size()) {
                    List<XmlElement> one = new ArrayList<>();
                    one.add(current.get(idx));
                    current = one;
                } else {
                    current = new ArrayList<>();
                }
                continue;
            }

            current = childrenWithTag(current, step.trim());
        }

        if (projectedValues != null) {
            return projectedValues.isEmpty() ? "No results." : String.join("\n", projectedValues);
        }
        if (current.isEmpty()) return "No results.";

        StringBuilder sb = new StringBuilder();
        for (XmlElement el : current) {
            sb.append(formatElement(el)).append('\n');
        }
        return sb.toString().trim();
    }

    /** Splits an XPath expression on {@code /} ignoring slashes inside {@code ()}. */
    private List<String> splitSteps(String expr) {
        List<String> steps = new ArrayList<>();
        int depth = 0;
        StringBuilder cur = new StringBuilder();
        for (char c : expr.toCharArray()) {
            if (c == '(') depth++;
            else if (c == ')') depth--;
            if (c == '/' && depth == 0) {
                steps.add(cur.toString()); cur = new StringBuilder();
            }
            else cur.append(c);
        }
        steps.add(cur.toString());
        return steps;
    }

    /** Returns all children of {@code parents} whose tag name equals {@code tag}. */
    private List<XmlElement> childrenWithTag(List<XmlElement> parents, String tag) {
        List<XmlElement> result = new ArrayList<>();
        for (XmlElement p : parents) {
            for (XmlElement child : p.getChildren()) {
                if (child.getName().equals(tag)) result.add(child);
            }
        }
        return result;
    }

    /**
     * Filters {@code elements} by a {@code key="value"} predicate.
     * Checks child text content first, then attribute value.
     */
    private List<XmlElement> applyFilter(List<XmlElement> elements, String filter) {
        int eq = filter.indexOf('=');
        if (eq == -1) return elements;
        String key = filter.substring(0, eq).trim();
        String val = filter.substring(eq + 1).trim();
        if (val.startsWith("\"") && val.endsWith("\"")) val = val.substring(1, val.length() - 1);
        final String expected = val;

        List<XmlElement> result = new ArrayList<>();
        for (XmlElement el : elements) {
            boolean match = false;
            for (XmlElement child : el.getChildren()) {
                if (child.getName().equals(key) && expected.equals(child.getText())) {
                    match = true; break;
                }
            }
            if (!match) {
                XmlAttribute a = el.getAttribute(key);
                if (a != null && expected.equals(a.getValue())) match = true;
            }
            if (match) result.add(el);
        }
        return result;
    }

    /** Formats one element for XPath result output. */
    private String formatElement(XmlElement el) {
        if (el.getText() != null && !el.getText().isEmpty() && el.getChildren().isEmpty()) {
            return "<" + el.getName() + ">" + el.getText() + "</" + el.getName() + ">";
        }
        StringBuilder sb = new StringBuilder("<").append(el.getName());
        for (XmlAttribute a : el.getAttributes()) sb.append(' ').append(a.toString());
        return sb.append('>').toString();
    }

    private void requireDocument() {
        if (document == null) throw new XmlException("No file is currently open.");
    }

    private XmlElement requireElement(String id) {
        requireDocument();
        XmlElement el = findById(id);
        if (el == null) throw new XmlException("Element not found: " + id);
        return el;
    }
}
