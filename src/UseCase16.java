import java.util.*;

public class UseCase16 {
    public static void main(String[] args) {

        System.out.println("====================================");
        System.out.println("UC16 - Manual Sorting using Bubble Sort");
        System.out.println("====================================\n");

        // Step 1: Create array (from PDF example)
        int[] capacities = {72, 56, 24, 70, 60};

        // Step 2: Display original array
        System.out.println("Original Capacities:");
        for (int c : capacities) {
            System.out.print(c + " ");
        }

        // =========================
        // BUBBLE SORT LOGIC
        // =========================
        int n = capacities.length;

        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {

                // Compare adjacent elements
                if (capacities[j] > capacities[j + 1]) {

                    // Swap
                    int temp = capacities[j];
                    capacities[j] = capacities[j + 1];
                    capacities[j + 1] = temp;
                }
            }
        }

        // Step 3: Display sorted result
        System.out.println("\n\nSorted Capacities (Ascending):");
        for (int c : capacities) {
            System.out.print(c + " ");
        }

        System.out.println("\n\nUC16 sorting completed...");
    }
}