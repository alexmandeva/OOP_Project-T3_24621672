package bg.tu_varna.sit.f24621672.projectT3.core;

import bg.tu_varna.sit.f24621672.projectT3.exception.XmlException;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
/**
 * Отговаря за сериализацията на вътрешния обектен модел (DOM) обратно в плосък
 * текстов формат и записването му във физически файл на диска.
 */
public class XmlSerializer {
    /**
     * Записва йерархичната структура на документа в съответния целеви файл.
     *
     * @param document документът, който трябва да се запише
     * @param file     целевият файл на диска
     * @throws XmlException ако документът е празен или възникне грешка при записването (I/O)
     */
    public void save(XmlDocument document, File file) {

        if (document == null || document.getRoot() == null) {
            throw new XmlException("Empty document cannot be saved");
        }

        String xml = serialize(document.getRoot(), 0);

        try (FileWriter writer = new FileWriter(file)) {
            writer.write(xml);
        } catch (IOException e) {
            throw new XmlException("Error while saving XML file" + e.getMessage(), e);
        }
    }

    private String serialize(XmlElement element, int indent) {

        StringBuilder sb = new StringBuilder();
        String tabs = "  ".repeat(indent);

        sb.append(tabs)
                .append("<")
                .append(element.getName());

        for (XmlAttribute attr : element.getAttributes()) {
            sb.append(" ")
                    .append(attr.getKey())
                    .append("=\"")
                    .append(attr.getValue())
                    .append("\"");
        }

        boolean hasChildren = !element.getChildren().isEmpty();
        boolean hasText = element.getText() != null && !element.getText().isEmpty();

        if (!hasChildren && !hasText) {
            sb.append("/>\n");
            return sb.toString();
        }

        sb.append(">");

        if (hasText) {
            sb.append(element.getText());
        }

        if (hasChildren) {
            sb.append("\n");
            for (XmlElement child : element.getChildren()) {
                sb.append(serialize(child, indent + 1));
            }
            sb.append(tabs);
        }

        sb.append("</")
                .append(element.getName())
                .append(">\n");

        return sb.toString();
    }
}