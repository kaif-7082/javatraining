import java.util.*;
import java.util.stream.Collectors;

public class Collectionsdemo {
    public static void main(String[] args) {

        // ---------- 1️⃣ LIST ----------
        System.out.println("=== List Example ===");
        List<String> names = new ArrayList<>();
        names.add("Kaif");
        names.add("John");
        names.add("Kaif"); // duplicates allowed
        System.out.println("List: " + names);

        // Accessing elements
        System.out.println("First element: " + names.get(0));

        // ---------- 2️⃣ SET ----------
        System.out.println("\n=== Set Example ===");
        Set<Integer> numbers = new HashSet<>();
        numbers.add(10);
        numbers.add(20);
        numbers.add(10); // duplicate ignored
        System.out.println("Set: " + numbers);

        // ---------- 3️⃣ MAP ----------
        System.out.println("\n=== Map Example ===");
        Map<Integer, String> productMap = new HashMap<>();
        productMap.put(101, "Laptop");
        productMap.put(102, "Phone");
        productMap.put(101, "Tablet"); // replaces Laptop
        System.out.println("Map: " + productMap);

        // Loop through map
        for (Map.Entry<Integer, String> entry : productMap.entrySet()) {
            System.out.println(entry.getKey() + " -> " + entry.getValue());
        }

        // ---------- 4️⃣ QUEUE ----------
        System.out.println("\n=== Queue Example ===");
        Queue<String> tasks = new LinkedList<>();
        tasks.add("Task1");
        tasks.add("Task2");
        System.out.println("Processing: " + tasks.poll()); // removes first
        System.out.println("Next Task: " + tasks.peek());  // shows next

        // ---------- 5️⃣ ITERATOR ----------
        System.out.println("\n=== Iterator Example ===");
        Iterator<String> iterator = names.iterator();
        while (iterator.hasNext()) {
            System.out.println("Name: " + iterator.next());
        }

        // ---------- 6️⃣ STREAMS + LAMBDA ----------
        System.out.println("\n=== Streams Example ===");
        List<Integer> listNums = Arrays.asList(5, 10, 15, 20, 25);

        // filter numbers > 10 and collect
        List<Integer> filtered = listNums.stream()
                .filter(n -> n > 10)
                .collect(Collectors.toList());

        System.out.println("Numbers > 10: " + filtered);

        // Sum using stream
        int sum = listNums.stream().mapToInt(n -> n).sum();
        System.out.println("Sum of all numbers: " + sum);

        // ---------- 7️⃣ COMBINED PRACTICE ----------
        System.out.println("\n=== Combined Example ===");
        Map<String, Integer> marks = new HashMap<>();
        marks.put("Alice", 85);
        marks.put("Bob", 72);
        marks.put("Charlie", 90);

        // Students who scored > 80
        marks.entrySet().stream()
                .filter(e -> e.getValue() > 80)
                .forEach(e -> System.out.println(e.getKey() + " scored " + e.getValue()));
    }
}
