import java.util.*;

class CargoSafetyException extends RuntimeException {
    public CargoSafetyException(String message) {
        super(message);
    }
}

class GoodsBogie {
    String shape;
    String cargo;

    GoodsBogie(String shape) {
        this.shape = shape;
    }

    void assignCargo(String cargo) {
        try {
            // Rule: Rectangular cannot carry Petroleum
            if (shape.equalsIgnoreCase("Rectangular") &&
                    cargo.equalsIgnoreCase("Petroleum")) {

                throw new CargoSafetyException("Unsafe cargo assignment!");
            }

            this.cargo = cargo;
            System.out.println("Cargo assigned successfully -> " + cargo);

        } catch (CargoSafetyException e) {
            System.out.println("Error: " + e.getMessage());

        } finally {
            System.out.println("Cargo validation completed for " + shape + " bogie\n");
        }
    }
}

public class UseCase15 {
    public static void main(String[] args) {

        System.out.println("====================================");
        System.out.println("UC15 - Safe Cargo Assignment");
        System.out.println("====================================\n");

        // Valid case
        GoodsBogie b1 = new GoodsBogie("Cylindrical");
        b1.assignCargo("Petroleum");

        // Invalid case
        GoodsBogie b2 = new GoodsBogie("Rectangular");
        b2.assignCargo("Petroleum");

        System.out.println("UC15 runtime handling completed...");
    }
}