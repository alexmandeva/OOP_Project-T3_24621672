package bg.tu_varna.sit.f24621672.projectT3.core;

import bg.tu_varna.sit.f24621672.projectT3.exception.ParseException;

import java.io.*;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * Токенизатор и синтактичен анализатор (парсер), отговорен за последователното четене на
 * XML файлове, изграждането на йерархичното дърво и подсигуряването на уникални ID-та.
 */
public class XmlParser {

    /**
     * Парсира подадения файл и връща конструирания обектен модел на документа.
     *
     * @param file XML файлът за парсиране
     * @return парсираният {@link XmlDocument}
     * @throws ParseException ако файлът не може да бъде прочетен или е със счупена структура
     */
    public XmlDocument parse(File file) {
        String content = readFile(file);
        List<String> tokens = tokenize(content);
        return buildTree(tokens, new IdGenerator());
    }

    private String readFile(File file) {
        StringBuilder sb = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) sb.append(line).append('\n');
        } catch (IOException e) {
            throw new ParseException("Cannot read file: " + file.getName(), e);
        }
        return sb.toString();
    }

    /**
     * Splits raw XML content into a flat list of tokens:
     * each token is either a tag string ({@code <...>}) or a text string.
     * Processing instructions and comments are dropped.
     */
    private List<String> tokenize(String content) {
        List<String> tokens = new ArrayList<>();
        int i = 0, len = content.length();

        while (i < len) {
            if (content.charAt(i) == '<') {
                int end = content.indexOf('>', i);
                if (end == -1) throw new ParseException("Unclosed '<' at position " + i);
                String tag = content.substring(i, end + 1);

                if (tag.startsWith("<?")) { i = end + 1; continue; }
                if (tag.startsWith("<!--")) {
                    int commentEnd = content.indexOf("-->", i);
                    i = (commentEnd == -1) ? len : commentEnd + 3;
                    continue;
                }
                tokens.add(tag);
                i = end + 1;
            } else {
                int end = content.indexOf('<', i);
                String text = (end == -1) ? content.substring(i) : content.substring(i, end);
                String trimmed = text.trim();
                if (!trimmed.isEmpty()) tokens.add(trimmed);
                i = (end == -1) ? len : end;
            }
        }
        return tokens;
    }

    private XmlDocument buildTree(List<String> tokens, IdGenerator idGen) {
        XmlDocument doc = new XmlDocument();
        Deque<XmlElement> stack = new ArrayDeque<>();

        for (String token : tokens) {
            if (token.startsWith("</")) {
                if (!stack.isEmpty()) {
                    XmlElement closed = stack.pop();
                    if (stack.isEmpty()) doc.setRoot(closed);
                    else stack.peek().addChild(closed);
                }

            } else if (token.startsWith("<")) {
                boolean selfClosing = token.endsWith("/>");
                String inner = selfClosing
                        ? token.substring(1, token.length() - 2).trim()
                        : token.substring(1, token.length() - 1).trim();

                XmlElement el = parseOpeningTag(inner, idGen);

                if (selfClosing) {
                    if (stack.isEmpty()) doc.setRoot(el);
                    else stack.peek().addChild(el);
                } else {
                    stack.push(el);
                }

            } else {
                if (!stack.isEmpty()) {
                    stack.peek().setText(token);
                }
            }
        }

        if (doc.getRoot() == null && !stack.isEmpty()) {
            doc.setRoot(stack.pop());
        }

        patchDuplicates(doc.getRoot(), idGen);
        return doc;
    }


    private XmlElement parseOpeningTag(String inner, IdGenerator idGen) {
        int spaceIdx = inner.indexOf(' ');
        String tagName = (spaceIdx == -1) ? inner : inner.substring(0, spaceIdx);
        XmlElement el = new XmlElement(tagName);

        if (spaceIdx != -1) {
            parseAttributes(inner.substring(spaceIdx + 1).trim(), el);
        }

        XmlAttribute idAttr = el.getAttribute("id");
        if (idAttr != null) {
            String resolved = idGen.generate(idAttr.getValue());
            el.setId(resolved);
            idAttr.setValue(resolved);
        } else {
            el.setId(idGen.generateGlobal());
        }
        return el;
    }

    /**
     * Parses {@code key="value"} pairs from the attribute section of an
     * opening tag and adds them to {@code el}.
     */
    private void parseAttributes(String attrString, XmlElement el) {
        int i = 0, len = attrString.length();
        while (i < len) {
            while (i < len && attrString.charAt(i) == ' ') i++;
            if (i >= len) break;
            int eq = attrString.indexOf('=', i);
            if (eq == -1) break;
            String key = attrString.substring(i, eq).trim();
            int q1 = attrString.indexOf('"', eq + 1);
            if (q1 == -1) break;
            int q2 = attrString.indexOf('"', q1 + 1);
            if (q2 == -1) break;
            String value = attrString.substring(q1 + 1, q2);
            el.addAttribute(new XmlAttribute(key, value));
            i = q2 + 1;
        }
    }

    private void patchDuplicates(XmlElement root, IdGenerator idGen) {
        if (root == null) return;
        patchRecursive(root, idGen);
    }

    private void patchRecursive(XmlElement el, IdGenerator idGen) {
        if (el == null) return;
        String id = el.getId();
        if (id != null && idGen.isDuplicated(id)) {
            String newId = id + "_1";
            el.setId(newId);
            XmlAttribute idAttr = el.getAttribute("id");
            if (idAttr != null) idAttr.setValue(newId);
        }
        for (XmlElement child : el.getChildren()) {
            patchRecursive(child, idGen);
        }
    }
}