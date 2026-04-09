/**
 * Represents a single coach/bogie in a train consist.
 *
 * Encapsulation:
 * - Fields are private and accessed via getters.
 * - Coach objects are immutable after creation for safer list operations.
 */
public class Coach {
    /**
     * Basic coach classification used by validation and display.
     * Kept inside Coach to respect the required 3-file output structure.
     */
    public enum Type {
        ENGINE,
        PASSENGER,
        CARGO,
        GUARD
    }

    private final String id;
    private final Type type;
    private final int capacity;

    public Coach(String id, Type type, int capacity) {
        if (id == null || id.trim().isEmpty()) {
            throw new IllegalArgumentException("Coach ID cannot be null/blank");
        }
        if (type == null) {
            throw new IllegalArgumentException("Coach type cannot be null");
        }
        if (capacity < 0) {
            throw new IllegalArgumentException("Capacity cannot be negative");
        }

        this.id = id.trim();
        this.type = type;
        this.capacity = capacity;
    }

    public String getId() {
        return id;
    }

    public Type getType() {
        return type;
    }

    public int getCapacity() {
        return capacity;
    }

    @Override
    public String toString() {
        return "Coach{id='" + id + "', type=" + type + ", capacity=" + capacity + "}";
    }
}

