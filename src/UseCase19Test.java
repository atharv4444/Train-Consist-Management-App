package src;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class UseCase19Test {

    @Test
    void testBinarySearch_BogieFound() {
        String[] input = {"BG101", "BG205", "BG309", "BG412", "BG550"};
        assertTrue(UseCase19.binarySearch(input, "BG309"), 
            "System should successfully identify an existing bogie ID.");
    }

    @Test
    void testBinarySearch_BogieNotFound() {
        String[] input = {"BG101", "BG205", "BG309", "BG412", "BG550"};
        assertFalse(UseCase19.binarySearch(input, "BG999"), 
            "System should return false when the bogie ID does not exist.");
    }

    @Test
    void testBinarySearch_FirstElementMatch() {
        String[] input = {"BG101", "BG205", "BG309", "BG412", "BG550"};
        assertTrue(UseCase19.binarySearch(input, "BG101"), 
            "Search should correctly detect a match at the first position.");
    }

    @Test
    void testBinarySearch_LastElementMatch() {
        String[] input = {"BG101", "BG205", "BG309", "BG412", "BG550"};
        assertTrue(UseCase19.binarySearch(input, "BG550"), 
            "Search should correctly detect a match at the last position.");
    }

    @Test
    void testBinarySearch_SingleElementArray() {
        String[] input = {"BG101"};
        assertTrue(UseCase19.binarySearch(input, "BG101"), 
            "Search should work correctly when only one bogie ID exists.");
    }

    @Test
    void testBinarySearch_EmptyArray() {
        String[] input = {};
        assertFalse(UseCase19.binarySearch(input, "BG101"), 
            "System should safely handle an empty bogie list.");
    }

    @Test
    void testBinarySearch_UnsortedInputHandled() {
        String[] input = {"BG309", "BG101", "BG550", "BG205", "BG412"};
        assertTrue(UseCase19.binarySearch(input, "BG205"), 
            "Unsorted input should be sorted before applying Binary Search.");
    }
}