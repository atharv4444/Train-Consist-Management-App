import java.util.LinkedList;
import java.util.ListIterator;

/**
 * TrainConsist manages the ordered collection of coaches.
 *
 * Data structure:
 * - Uses LinkedList<Coach> for dynamic insertion/removal operations.
 */
public class TrainConsist {
    private final LinkedList<Coach> coaches;

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
     * UC7 will evolve display; UC1 prints a minimal summary.
     */
    public String summary() {
        return "Coach count: " + coaches.size() + " | Coaches: " + coaches;
    }
}
