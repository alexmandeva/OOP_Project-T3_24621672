package bg.tu_varna.sit.f24621672.projectT3.core;

import java.util.HashMap;
import java.util.Map;
/**
 * Помощен генератор, отговорен за създаването на уникални идентификатори на XML елементите.
 * Предотвратява дублирането на ID-та чрез добавяне на наставки (суфикси) или глобални последователности.
 */
public class IdGenerator {

    private Map<String, Integer> counters = new HashMap<>();
    private int global = 1;
    /**
     * Генерира уникален идентификатор въз основа на дадено име на таг. Следи колко
     * пъти се е срещало името, за да добави правилен индекс като наставка.
     *
     * @param base базовата нишка/име на таг за обработка
     * @return уникален ID низ
     */
    public String generate(String base) {
        int count = counters.getOrDefault(base, 0) + 1;
        counters.put(base, count);

        if (count == 1) return base;          // first occurrence — no suffix
        if (count == 2) return base + "_2";   // second — caller patches first to _1
        return base + "_" + count;            // third and beyond
    }
    /**
     * Проверява дали даден базов идентификатор се е срещал повече от веднъж.
     * @param base идентификаторът за проверка
     * @return true, ако ID-то е дублирано, в противен случай - false
     */
    public boolean isDuplicated(String base) {
        return counters.getOrDefault(base, 0) >= 2;
    }

    public String generateGlobal() {
        return "gen_" + (global++);
    }
}