import java.util.*;
import java.util.stream.*;

class GoodsBogie {
    String type;
    String cargo;

    GoodsBogie(String type, String cargo) {
        this.type = type;
        this.cargo = cargo;
    }

    public String toString() {
        return type + " -> " + cargo;
    }
}

public class UseCase12 {
    public static void main(String[] args) {

        System.out.println("====================================");
        System.out.println("UC12 - Safety Compliance Check for Goods Bogies");
        System.out.println("====================================\n");

        // Step 1: Create goods bogie list (same as PDF example page 5)
        List<GoodsBogie> goodsBogies = new ArrayList<>();

        goodsBogies.add(new GoodsBogie("Cylindrical", "Petroleum"));
        goodsBogies.add(new GoodsBogie("Open", "Coal"));
        goodsBogies.add(new GoodsBogie("Box", "Grain"));
        goodsBogies.add(new GoodsBogie("Cylindrical", "Coal")); // ❌ INVALID

        // Step 2: Display bogies
        System.out.println("Goods Bogies in Train:");
        for (GoodsBogie g : goodsBogies) {
            System.out.println(g);
        }

        // Step 3: SAFETY CHECK (CORE LOGIC)
        boolean isSafe = goodsBogies.stream()
                .allMatch(g ->
                        !g.type.equalsIgnoreCase("Cylindrical") ||
                                g.cargo.equalsIgnoreCase("Petroleum")
                );

        // Step 4: Output result
        System.out.println("\nSafety Compliance Status: " + isSafe);

        if (isSafe) {
            System.out.println("Train formation is SAFE.");
        } else {
            System.out.println("Train formation is NOT SAFE.");
        }

        System.out.println("\nUC12 safety validation completed...");
    }
}