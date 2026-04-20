package src;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class UseCase20Test {

    @Test
    void testSearch_ThrowsExceptionWhenEmpty() {
        String[] emptyArray = {};
        IllegalStateException exception = assertThrows(IllegalStateException.class, () -> {
            UseCase20.searchWithValidation(emptyArray, "BG101");
        }, "Searching an empty array should throw IllegalStateException.");
        
        assertEquals("No bogies available in train. Cannot perform search.", exception.getMessage());
    }

    @Test
    void testSearch_AllowsSearchWhenDataExists() {
        String[] data = {"BG101", "BG205"};
        assertDoesNotThrow(() -> {
            UseCase20.searchWithValidation(data, "BG101");
        }, "Searching populated data should not throw an exception.");
    }

    @Test
    void testSearch_BogieFoundAfterValidation() {
        String[] data = {"BG101", "BG205", "BG309"};
        assertTrue(UseCase20.searchWithValidation(data, "BG205"),
            "System should return true when search key exists after passing validation.");
    }

    @Test
    void testSearch_BogieNotFoundAfterValidation() {
        String[] data = {"BG101", "BG205", "BG309"};
        assertFalse(UseCase20.searchWithValidation(data, "BG999"),
            "System should return false when search key does not exist after passing validation.");
    }

    @Test
    void testSearch_SingleElementValidCase() {
        String[] data = {"BG101"};
        assertTrue(UseCase20.searchWithValidation(data, "BG101"),
            "System should correctly identify match when only one bogie exists.");
    }
}