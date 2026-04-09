import java.util.LinkedList;

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
     * UC7 will evolve display; UC1 prints a minimal summary.
     */
    public String summary() {
        return "Coach count: " + coaches.size() + " | Coaches: " + coaches;
    }
}

