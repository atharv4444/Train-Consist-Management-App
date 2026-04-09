import java.util.LinkedList;
import java.util.ListIterator;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * TrainConsist manages the ordered collection of coaches.
 *
 * Data structure:
 * - Uses LinkedList<Coach> for dynamic insertion/removal operations.
 */
public class TrainConsist {
    private final LinkedList<Coach> coaches;

    // Capacity constraints (simple defaults; can be tuned to requirements).
    private static final int MAX_PASSENGER_CAPACITY = 200;
    private static final int MAX_CARGO_CAPACITY = 500;
    private static final int MAX_GUARD_CAPACITY = 50;
    private static final int MAX_TOTAL_CAPACITY = 2000;

    /**
     * Validation report object (kept as a nested class to maintain 3-file structure).
     */
    public static class ValidationReport {
        private final List<String> issues;

        private ValidationReport(List<String> issues) {
            this.issues = issues;
        }

        public boolean isValid() {
            return issues.isEmpty();
        }

        public List<String> getIssues() {
            return issues;
        }

        @Override
        public String toString() {
            if (isValid()) {
                return "ValidationReport{valid=true}";
            }
            return "ValidationReport{valid=false, issues=" + issues + "}";
        }
    }

    public TrainConsist() {
        // UC1: Initialize empty consist using LinkedList.
        this.coaches = new LinkedList<>();
    }

    public int size() {
        return coaches.size();
    }

    public boolean isEmpty() {
        return coaches.isEmpty();
    }

    /**
     * UC2: Add coach at the beginning of the consist.
     */
    public void addCoachAtBeginning(Coach coach) {
        if (coach == null) {
            throw new IllegalArgumentException("Coach cannot be null");
        }
        coaches.addFirst(coach);
    }

    /**
     * UC2: Add coach at the end of the consist.
     */
    public void addCoachAtEnd(Coach coach) {
        if (coach == null) {
            throw new IllegalArgumentException("Coach cannot be null");
        }
        coaches.addLast(coach);
    }

    /**
     * UC2: Add coach at a specific 0-based index.
     *
     * Position rules:
     * - index 0 inserts at the front
     * - index == size() inserts at the end
     */
    public void addCoachAtPosition(Coach coach, int index) {
        if (coach == null) {
            throw new IllegalArgumentException("Coach cannot be null");
        }
        if (index < 0 || index > coaches.size()) {
            throw new IndexOutOfBoundsException(
                    "Index must be in range [0, " + coaches.size() + "], got: " + index
            );
        }
        coaches.add(index, coach);
    }

    /**
     * UC3: Remove a coach by its ID.
     *
     * @return removed coach if found, otherwise null
     */
    public Coach removeCoachById(String id) {
        if (id == null || id.trim().isEmpty()) {
            throw new IllegalArgumentException("Coach ID cannot be null/blank");
        }

        String target = id.trim();
        // Using ListIterator to safely remove during traversal.
        for (ListIterator<Coach> it = coaches.listIterator(); it.hasNext(); ) {
            Coach current = it.next();
            if (current.getId().equals(target)) {
                it.remove();
                return current;
            }
        }
        return null;
    }

    /**
     * UC4: Reorder coaches by moving a coach from one index to another.
     *
     * Indexing:
     * - Both indices are 0-based positions in the list *before* the move.
     * - If moving forward (toIndex > fromIndex), the insertion index is adjusted
     *   after removal to keep the "target position" intuitive.
     */
    public void moveCoach(int fromIndex, int toIndex) {
        int size = coaches.size();
        if (size == 0) {
            throw new IllegalStateException("Cannot reorder an empty consist");
        }
        if (fromIndex < 0 || fromIndex >= size) {
            throw new IndexOutOfBoundsException("fromIndex must be in range [0, " + (size - 1) + "]");
        }
        if (toIndex < 0 || toIndex >= size) {
            throw new IndexOutOfBoundsException("toIndex must be in range [0, " + (size - 1) + "]");
        }
        if (fromIndex == toIndex) {
            return; // No-op
        }

        Coach coach = coaches.remove(fromIndex);
        // After removal, elements shift left. Adjust when moving forward.
        int adjustedToIndex = (toIndex > fromIndex) ? (toIndex - 1) : toIndex;
        coaches.add(adjustedToIndex, coach);
    }

    /**
     * UC5: Linear search for a coach by ID.
     *
     * @return 0-based index if found, otherwise -1
     */
    public int indexOfCoachById(String id) {
        if (id == null || id.trim().isEmpty()) {
            throw new IllegalArgumentException("Coach ID cannot be null/blank");
        }

        String target = id.trim();
        int index = 0;
        for (Coach coach : coaches) {
            if (coach.getId().equals(target)) {
                return index;
            }
            index++;
        }
        return -1;
    }

    /**
     * UC5: Returns the coach object if it exists, otherwise null.
     * This is also implemented using linear search.
     */
    public Coach findCoachById(String id) {
        if (id == null || id.trim().isEmpty()) {
            throw new IllegalArgumentException("Coach ID cannot be null/blank");
        }

        String target = id.trim();
        for (Coach coach : coaches) {
            if (coach.getId().equals(target)) {
                return coach;
            }
        }
        return null;
    }

    /**
     * UC6: Validate consist rules.
     *
     * Rules implemented:
     * - No duplicate coach IDs.
     * - If ENGINE exists, it must be first and unique.
     * - If GUARD exists, it must be last and unique.
     * - Simple per-coach capacity limits (by type) + total capacity limit.
     */
    public ValidationReport validateConsist() {
        List<String> issues = new ArrayList<>();

        // 1) Duplicate ID check
        Set<String> ids = new HashSet<>();
        for (Coach coach : coaches) {
            if (!ids.add(coach.getId())) {
                issues.add("Duplicate coach ID found: " + coach.getId());
            }
        }

        // 2) Ordering rules: ENGINE first (if present), GUARD last (if present)
        int engineCount = 0;
        int guardCount = 0;
        int index = 0;
        int totalCapacity = 0;

        for (Coach coach : coaches) {
            Coach.Type type = coach.getType();

            if (type == Coach.Type.ENGINE) {
                engineCount++;
                if (index != 0) {
                    issues.add("ENGINE coach must be at index 0, found at index " + index);
                }
                if (coach.getCapacity() != 0) {
                    issues.add("ENGINE coach capacity must be 0, found: " + coach.getCapacity());
                }
            }

            if (type == Coach.Type.GUARD) {
                guardCount++;
                if (index != coaches.size() - 1) {
                    issues.add("GUARD coach must be last, found at index " + index);
                }
            }

            // 3) Capacity constraints (per-coach)
            int capacity = coach.getCapacity();
            switch (type) {
                case PASSENGER:
                    if (capacity <= 0 || capacity > MAX_PASSENGER_CAPACITY) {
                        issues.add("PASSENGER coach " + coach.getId() + " capacity must be in [1.." +
                                MAX_PASSENGER_CAPACITY + "], found: " + capacity);
                    }
                    break;
                case CARGO:
                    if (capacity <= 0 || capacity > MAX_CARGO_CAPACITY) {
                        issues.add("CARGO coach " + coach.getId() + " capacity must be in [1.." +
                                MAX_CARGO_CAPACITY + "], found: " + capacity);
                    }
                    break;
                case GUARD:
                    if (capacity <= 0 || capacity > MAX_GUARD_CAPACITY) {
                        issues.add("GUARD coach " + coach.getId() + " capacity must be in [1.." +
                                MAX_GUARD_CAPACITY + "], found: " + capacity);
                    }
                    break;
                case ENGINE:
                    // Already handled above.
                    break;
                default:
                    // Defensive - should never happen since Type is an enum.
                    issues.add("Unknown coach type for " + coach.getId() + ": " + type);
            }

            totalCapacity += capacity;
            index++;
        }

        if (engineCount > 1) {
            issues.add("Only one ENGINE coach is allowed, found: " + engineCount);
        }
        if (guardCount > 1) {
            issues.add("Only one GUARD coach is allowed, found: " + guardCount);
        }

        // 4) Total capacity constraint
        if (totalCapacity > MAX_TOTAL_CAPACITY) {
            issues.add("Total capacity exceeded: " + totalCapacity + " > " + MAX_TOTAL_CAPACITY);
        }

        return new ValidationReport(issues);
    }

    /**
     * UC7 will evolve display; UC1 prints a minimal summary.
     */
    public String summary() {
        return "Coach count: " + coaches.size() + " | Coaches: " + coaches;
    }
}
