import java.util.Scanner;

/**
 * Driver program for the Train Consist Management App.
 *
 * UC1: Initialize an empty consist and display summary.
 * Later use cases extend this to a menu-driven console application.
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("=== Train Consist Management App ===");

        TrainConsist consist = new TrainConsist();
        System.out.println("UC1 - Initialize Consist: " + consist.summary());

        // UC2: Basic menu to add coaches.
        try (Scanner scanner = new Scanner(System.in)) {
            while (true) {
                System.out.println();
                System.out.println("Menu");
                System.out.println("1) UC2 - Add Coach");
                System.out.println("2) UC3 - Remove Coach (by ID)");
                System.out.println("3) UC4 - Reorder Coaches (move by index)");
                System.out.println("4) UC5 - Search Coach (by ID)");
                System.out.println("5) UC6 - Validate Consist");
                System.out.println("6) UC7 - Display Consist");
                System.out.println("0) Exit");
                System.out.print("Choose: ");

                String choice = scanner.nextLine().trim();
                if ("0".equals(choice)) {
                    System.out.println("Goodbye.");
                    return;
                }

                if ("1".equals(choice)) {
                    Coach coach = readCoach(scanner);
                    int index = readInsertIndex(scanner, consist.size());

                    if (index == 0) {
                        consist.addCoachAtBeginning(coach);
                    } else if (index == consist.size()) {
                        // After reading the index, size() is still the current size.
                        consist.addCoachAtEnd(coach);
                    } else {
                        consist.addCoachAtPosition(coach, index);
                    }

                    System.out.println("Added coach. " + consist.summary());
                    continue;
                }

                if ("2".equals(choice)) {
                    System.out.print("Enter Coach ID to remove: ");
                    String id = scanner.nextLine();
                    Coach removed = consist.removeCoachById(id);
                    if (removed == null) {
                        System.out.println("No coach found with ID: " + id);
                    } else {
                        System.out.println("Removed: " + removed);
                    }
                    System.out.println(consist.summary());
                    continue;
                }

                if ("3".equals(choice)) {
                    if (consist.isEmpty()) {
                        System.out.println("Consist is empty.");
                        continue;
                    }

                    int maxIndex = consist.size() - 1;
                    System.out.print("Move from index (0.." + maxIndex + "): ");
                    int from = readInt(scanner);
                    System.out.print("Move to index (0.." + maxIndex + "): ");
                    int to = readInt(scanner);

                    try {
                        consist.moveCoach(from, to);
                        System.out.println("Reordered. " + consist.summary());
                    } catch (RuntimeException ex) {
                        System.out.println("Reorder failed: " + ex.getMessage());
                    }
                    continue;
                }

                if ("4".equals(choice)) {
                    System.out.print("Enter Coach ID to search: ");
                    String id = scanner.nextLine();
                    try {
                        int index = consist.indexOfCoachById(id);
                        if (index < 0) {
                            System.out.println("Not found.");
                        } else {
                            Coach coach = consist.findCoachById(id);
                            System.out.println("Found at index " + index + ": " + coach);
                        }
                    } catch (RuntimeException ex) {
                        System.out.println("Search failed: " + ex.getMessage());
                    }
                    continue;
                }

                if ("5".equals(choice)) {
                    TrainConsist.ValidationReport report = consist.validateConsist();
                    if (report.isValid()) {
                        System.out.println("Consist is VALID.");
                    } else {
                        System.out.println("Consist is INVALID. Issues:");
                        for (String issue : report.getIssues()) {
                            System.out.println("- " + issue);
                        }
                    }
                    continue;
                }

                if ("6".equals(choice)) {
                    System.out.println(consist.displayConsist());
                    continue;
                }

                // Default case
                {
                    System.out.println("Invalid choice.");
                }
            }
        }
    }

    private static Coach readCoach(Scanner scanner) {
        System.out.print("Coach ID: ");
        String id = scanner.nextLine();

        Coach.Type type = readCoachType(scanner);

        System.out.print("Capacity (>= 0): ");
        int capacity = readInt(scanner);

        return new Coach(id, type, capacity);
    }

    private static Coach.Type readCoachType(Scanner scanner) {
        while (true) {
            System.out.print("Type (ENGINE/PASSENGER/CARGO/GUARD): ");
            String raw = scanner.nextLine().trim().toUpperCase();
            try {
                return Coach.Type.valueOf(raw);
            } catch (IllegalArgumentException ex) {
                System.out.println("Invalid type.");
            }
        }
    }

    /**
     * Insert index is entered as a 0-based integer for simplicity.
     * Valid range is [0..currentSize].
     */
    private static int readInsertIndex(Scanner scanner, int currentSize) {
        while (true) {
            System.out.print("Insert at index (0.." + currentSize + "): ");
            int index = readInt(scanner);
            if (index < 0 || index > currentSize) {
                System.out.println("Index out of range.");
                continue;
            }
            return index;
        }
    }

    private static int readInt(Scanner scanner) {
        while (true) {
            String raw = scanner.nextLine().trim();
            try {
                return Integer.parseInt(raw);
            } catch (NumberFormatException ex) {
                System.out.print("Enter a valid integer: ");
            }
        }
    }
}
