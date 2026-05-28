package bg.tu_varna.sit.f24621672.projectT3.core;
/**
 * Контейнер за обектния модел на структуриран XML документ. Съдържа референция
 * към корена (root) на йерархията от елементи.
 */
public class XmlDocument {

    private XmlElement root;

    /**
     * Връща корения елемент на документа.
     *
     * @return коренният {@link XmlElement} или {@code null} ако документът е празен
     */
    public XmlElement getRoot() {
        return root;
    }

    /**
     * Задава корения елемент на документа.
     *
     * @param root новият корен на документа
     */
    public void setRoot(XmlElement root) {
        this.root = root;
    }

    /**
     * Проверява дали документът е празен (няма зареден корен).
     *
     * @return {@code true} ако коренът е {@code null}
     */
    public boolean isEmpty() {
        return root == null;
    }

    /**
     * Изчиства документа, като нулира референцията към корена.
     */
    public void clear() {
        root = null;
    }
}