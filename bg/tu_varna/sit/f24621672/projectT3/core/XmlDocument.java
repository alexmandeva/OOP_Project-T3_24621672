package bg.tu_varna.sit.f24621672.projectT3.core;
/**
 * Контейнер за обектния модел на структуриран XML документ. Съдържа референция
 * към корена (root) на йерархията от елементи.
 */
public class XmlDocument {

    private XmlElement root;

    public XmlElement getRoot() {
        return root;
    }

    public void setRoot(XmlElement root) {
        this.root = root;
    }

    public boolean isEmpty() {
        return root == null;
    }

    public void clear() {
        root = null;
    }
}