package src;

/**
 * Use Case 20: Exception Handling During Search Operations
 * Description: Prevents searching when no bogies exist 
 * by applying fail-fast validation using exceptions.
 */
public class UseCase20 {

    /**
     * Performs a search with fail-fast validation.
     *
     * @param bogieIds The array of bogie IDs.
     * @param searchId The bogie ID to search for.
     * @return true if the ID is found, false otherwise.
     * @throws IllegalStateException if the bogie array is empty or null.
     */
    public static boolean searchWithValidation(String[] bogieIds, String searchId) {
        // FAIL-FAST VALIDATION
        if (bogieIds == null || bogieIds.length == 0) {
            throw new IllegalStateException("No bogies available in train. Cannot perform search.");
        }

        // SEARCH LOGIC (executes only if data exists)
        boolean found = false;
        for (String id : bogieIds) {
            if (id != null && id.equals(searchId)) {
                found = true;
                break;
            }
        }
        
        return found;
    }

    public static void execute() {
        System.out.println("==================================================");
        System.out.println("UC20 - Exception Handling During Search Operations");
        System.out.println("==================================================");
        System.out.println();

        // Create empty bogie array (empty train scenario)
        String[] emptyBogieIds = {};
        
        // Search key
        String searchId = "BG101";

        try {
            // Attempt to search on an empty array
            boolean found = searchWithValidation(emptyBogieIds, searchId);
            
            // This code should not be reached if validation works
            if (found) {
                System.out.println("Found: " + searchId);
            } else {
                System.out.println("Not Found: " + searchId);
            }
        } catch (IllegalStateException e) {
            System.err.println("Exception: " + e.getMessage());
            // Intentionally not printing stack trace here to keep console output clean,
            // but the exception is thrown and caught.
        }

        System.out.println("\nUC20 execution completed...");
    }
}