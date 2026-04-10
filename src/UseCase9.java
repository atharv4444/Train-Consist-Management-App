import java.util.*;
import java.util.stream.*;

class Bogie {
    String name;
    int capacity;

    Bogie(String name, int capacity) {
        this.name = name;
        this.capacity = capacity;
    }

    public String toString() {
        return "Capacity -> " + capacity;
    }
}

public class UseCase9 {
    public static void main(String[] args) {

        System.out.println("====================================");
        System.out.println("UC9 - Group Bogies by Type");
        System.out.println("====================================\n");

        // Step 1: Create list (same as PDF page 4)
        List<Bogie> bogies = new ArrayList<>();

        bogies.add(new Bogie("Sleeper", 72));
        bogies.add(new Bogie("AC Chair", 56));
        bogies.add(new Bogie("First Class", 24));
        bogies.add(new Bogie("Sleeper", 70));
        bogies.add(new Bogie("AC Chair", 60));

        // Step 2: Display all bogies
        System.out.println("All Bogies:");
        for (Bogie b : bogies) {
            System.out.println(b.name + " -> " + b.capacity);
        }

        // Step 3: GROUPING (IMPORTANT LINE)
        Map<String, List<Bogie>> groupedBogies =
                bogies.stream()
                        .collect(Collectors.groupingBy(b -> b.name));

        // Step 4: Display grouped result
        System.out.println("\nGrouped Bogies:");

        for (Map.Entry<String, List<Bogie>> entry : groupedBogies.entrySet()) {
            System.out.println("\nBogie Type: " + entry.getKey());

            for (Bogie b : entry.getValue()) {
                System.out.println(b);
            }
        }

        System.out.println("\nUC9 grouping completed...");
    }
}