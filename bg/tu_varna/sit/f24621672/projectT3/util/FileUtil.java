package bg.tu_varna.sit.f24621672.projectT3.util;

import java.io.*;
/**
 * Помощен служебен клас за работа с файловата система.
 * Предоставя статични методи за улеснено четене, запис и обработка на файлове.
 * Класът е капсулиран (final) и не може да бъде инстанциран.
 */
public final class FileUtil {
    /**
     * Частен конструктор за предотвратяване на инстанцирането на помощния клас.
     */
    private FileUtil() {
    }
    /**
     * Прочита цялото съдържание на текстов файл по зададен път и го връща като низ.
     *
     * @param path пътят до файла в коя да е директория
     * @return съдържанието на файла като {@link String}
     * @throws IOException ако възникне входно-изходна грешка при достъпа или четенето на файла
     */
    public static String readFile(String path) throws IOException {

        StringBuilder sb = new StringBuilder();

        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            String line;

            while ((line = reader.readLine()) != null) {
                sb.append(line).append(System.lineSeparator());
            }
        }

        return sb.toString();
    }
    /**
     * Записва текстово съдържание в посочения файл. Ако файлът съществува,
     * съдържанието му се пренаписва.
     *
     * @param path    пътят, където да се запише файлът
     * @param content текстът, който да бъде записан
     * @throws IOException ако възникне входно-изходна грешка по време на записа на диска
     */
    public static void writeFile(String path, String content) throws IOException {

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(path))) {
            writer.write(content);
        }
    }
    /**
     * Извлича само името на файла (заедно с разширението му) от пълния му файлов път,
     * като отрязва директориите. Работи както с наклонени черти за Unix/Linux, така и за Windows.
     *
     * @param path пълният файлов път
     * @return само името на самия файл или празен низ, ако подаденият път е null
     */    public static String fileName(String path) {
        if (path == null) return "";
        int idx = Math.max(path.lastIndexOf('/'), path.lastIndexOf('\\'));
        return idx >= 0 ? path.substring(idx + 1) : path;
    }
}