package src;

import java.util.Arrays;

/**
 * Use Case 19: Binary Search for Bogie ID
 * Description: Demonstrates searching for a specific bogie ID 
 * using the Binary Search algorithm on sorted data.
 */
public class UseCase19 {

    /**
     * Performs a binary search to find a bogie ID.
     * Sorts the array first to ensure the precondition is met.
     *
     * @param bogieIds The array of bogie IDs.
     * @param key The bogie ID to search for.
     * @return true if the ID is found, false otherwise.
     */
    public static boolean binarySearch(String[] bogieIds, String key) {
        if (bogieIds == null || bogieIds.length == 0) {
            return false;
        }

        // Ensure data is sorted before binary search (precondition)
        String[] sortedIds = Arrays.copyOf(bogieIds, bogieIds.length);
        Arrays.sort(sortedIds);

        int low = 0;
        int high = sortedIds.length - 1;

        while (low <= high) {
            int mid = low + (high - low) / 2;
            int comparison = key.compareTo(sortedIds[mid]);

            if (comparison == 0) {
                return true; // Match found
            } else if (comparison < 0) {
                high = mid - 1; // Search left half
            } else {
                low = mid + 1; // Search right half
            }
        }
        
        return false; // Not found
    }

    public static void execute() {
        System.out.println("==================================================");
        System.out.println("UC19 - Binary Search for Bogie ID");
        System.out.println("==================================================");
        System.out.println();

        // Create array of bogie IDs
        String[] bogieIds = {"BG101", "BG205", "BG309", "BG412", "BG550"};
        
        // Ensure data is sorted before binary search (precondition)
        Arrays.sort(bogieIds);
        
        // Search key
        String key = "BG309";

        // Display available bogies
        System.out.println("Sorted Bogie IDs:");
        for (String id : bogieIds) {
            System.out.println(id);
        }
        System.out.println();

        // Binary Search Logic application
        boolean found = binarySearch(bogieIds, key);

        if (found) {
            System.out.println("Bogie " + key + " found using Binary Search.");
        } else {
            System.out.println("Bogie " + key + " not found.");
        }

        System.out.println();
        System.out.println("UC19 search completed...");
    }
}