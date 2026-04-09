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
     * UC7 will evolve display; UC1 prints a minimal summary.
     */
    public String summary() {
        return "Coach count: " + coaches.size() + " | Coaches: " + coaches;
    }
}
