import java.util.*;

public class PhoneDirectory {
    private final Map<String, List<String>> directory;

    public PhoneDirectory() {
        this.directory = new HashMap<>();
    }

    public void add(String surname, String phoneNumber) {
        directory.putIfAbsent(surname, new ArrayList<>());
        directory.get(surname).add(phoneNumber);
    }

    public List<String> get(String surname) {
        return directory.getOrDefault(surname, Collections.emptyList());
    }

    public static void main(String[] args) {
        PhoneDirectory phoneDirectory = new PhoneDirectory();

        phoneDirectory.add("Иванов", "123-455");
        phoneDirectory.add("Петров", "987-654");
        phoneDirectory.add("Сидоров", "789-012");

        System.out.println("Телефоны Иванова: " + phoneDirectory.get("Иванов"));
        System.out.println("Телефоны Петрова: " + phoneDirectory.get("Петров"));
        System.out.println("Телефоны Сидорова: " + phoneDirectory.get("Сидоров"));
    }
}