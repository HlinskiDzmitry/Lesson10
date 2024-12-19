import java.util.*;

public class WordProcessor {
    public static void main(String[] args) {
        // Создаем массив слов
        List<String> words = Arrays.asList(
                "дом", "сон", "риск", "воск", "миля",
                "лист", "арбуз", "дом", "яблоко", "лист",
                "ешь", "риск", "риск", "лава", "пей",
                "воск", "дом", "ешь", "рис"
        );

        Set<String> uniqueWords = new HashSet<>(words);
        System.out.println("Уникальные слова: " + uniqueWords);

        Map<String, Integer> wordCounts = new HashMap<>();
        for (String word : words) {
            wordCounts.put(word, wordCounts.getOrDefault(word, 0) + 1);
        }

        System.out.println("Количество вхождений каждого слова:");
        for (Map.Entry<String, Integer> entry : wordCounts.entrySet()) {
            System.out.println(entry.getKey() + ":" + entry.getValue());
        }
    }
}